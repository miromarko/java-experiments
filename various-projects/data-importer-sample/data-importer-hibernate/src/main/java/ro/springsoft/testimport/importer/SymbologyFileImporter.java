/*
 *  Copyright (c) 2005-2013 Green Mountain Analytics.
 *  This software is the confidential and proprietary information 
 *  and shall use it only in accordance with the terms
 *  of the license agreement you entered into with Green Mountain Analytics.
 */
package ro.springsoft.testimport.importer;

import ro.springsoft.testimport.persistence.entities.Current;
import ro.springsoft.testimport.persistence.entities.Symbol;
import ro.springsoft.testimport.persistence.util.AssetClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalitycs.com>
 */
public class SymbologyFileImporter {

    private static final Logger LOG = Logger.getLogger(SymbologyFileImporter.class.getName());
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SymbologyPU");
    private static String[] importDirs = new String[]{"d:/symbologyPub/data1", "d:/symbologyPub/data2", "d:/symbologyPub/data3"};
    private static ThreadPool threadPool = new ThreadPool(15);

    public static void main(String... args) throws Exception {
        SymbologyFileImporter importer = new SymbologyFileImporter();
        importer.runSingle(importDirs[0]);
//        importer.runMultiple(5);
    }

    public void runSingle(String dir) {
        importFromDir(importDirs[0]);
        threadPool.closeAll();
    }

    public void runMultiple(int nrOfDataSets) {
        Random r = new Random();
        for (int i = 0; i < nrOfDataSets; i++) {
            String importDir = importDirs[r.nextInt(3)];
            importFromDir(importDir);
        }
        threadPool.closeAll();
    }

    public void importFromDir(final String dir) {
        final Date generatedDateTime = new Date();
        for (AssetClass asset : AssetClass.values()) {
            threadPool.runTask(new Task(asset, generatedDateTime, dir));
        }

    }

    public void processEntities(AssetClass asset, Date generatedDateTime, String importDir) {
        LOG.info("Start processing " + asset);
        EntityManager em = emf.createEntityManager();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(importDir + "/" + asset + ".txt"));
            String line;
            em.getTransaction().begin();
            int i = 0;
            while ((line = fileReader.readLine()) != null) {
                EntityAdapter entityAdapter = new EntityAdapter(asset, line, generatedDateTime);
                Symbol symbol = entityAdapter.getEntity();
                if (symbol != null) {
                    em.persist(symbol);
                }
                if (++i > 10000) {
                    i = 0;
                    em.flush();
                }
            }
            em.getTransaction().commit();
            em.getTransaction().begin();
            Current current = new Current(asset, generatedDateTime);
            em.persist(current);
            em.getTransaction().commit();
            fileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist(EntityManagerFactory emf, Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    class Task implements Runnable {

        AssetClass asset;
        Date generatedDateTime;
        String importDir;

        public Task(AssetClass asset, Date generatedDateTime, String importDir) {
            this.asset = asset;
            this.generatedDateTime = generatedDateTime;
            this.importDir = importDir;
        }

        public void run() {
            processEntities(asset, generatedDateTime, importDir);
        }
    }
}
