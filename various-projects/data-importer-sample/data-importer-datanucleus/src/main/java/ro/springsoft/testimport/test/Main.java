/*
 * Copyright (c) 2005-2013 Green Mountain Analytics.
 * This software is the confidential and proprietary information 
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with Green Mountain Analytics.
 */
package ro.springsoft.testimport.test;

import ro.springsoft.testimport.persistence.entities.Symbol;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("HBase_PU");
    static EntityManager em = emf.createEntityManager();

    public static void main(String... args) {
//        Fx fx = new Fx();
//        fx.setActiv_name("AUCAD");
//        fx.setCurrency("AUD");
//        fx.setGma_name("AUDCAD");
//        fx.setGma_ticker("AUDCAD.FX");
//        fx.setStatus(1);
//        fx.setTick_size(0.00001);
//        fx.setTo_currency("CAD");
//        fx.setUnderlying_gma_ticker("AUDCAD.FX");


//        Query q = em.createQuery("SELECT f FROM Fx f");
//        List<Fx> fxList = q.getResultList();
//        for (Fx f : fxList) {
//            System.out.println("***" + f);
//        }


        //        Query q = em.createNamedQuery("Symbology.findSimbol");
//        Query q = em.createQuery("SELECT f FROM Symbol f WHERE f.gma_ticker = :gma_ticker AND f.activ_name = :activ_name");
//        q.setParameter("activ_name", null);
//        q.setParameter("gma_ticker", "URA.SO.UP-I");
//        Symbol f = (Symbol) q.getSingleResult();
////        System.out.println("***"+f.getCurrency());

        

        Query q1 = em.createQuery("SELECT s FROM Symbol s where s.gma_ticker = :gma_ticker ");
        q1.setParameter("gma_ticker", "AUDCAD.FX");


        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss z");
        Date d1 = new Date();
        System.out.println(dateFormat.format(d1));
        List<Symbol> symbols = q1.getResultList();
        Date d2 = new Date();
        System.out.println(dateFormat.format(d2));



        for (Symbol s : symbols) {
            System.out.println(s);
        }
        

        Query qcount = em.createQuery("SELECT COUNT(s) FROM Symbol s");
        Long nr = (Long) qcount.getSingleResult();
        System.out.println("*******"+nr);

//        Query query = em.createQuery("select f from Fx f where f = :fx");
//        query.setParameter("fx", fx);
//        List<Fx> list = query.getResultList();
//        
//        
//        
//        System.out.println(list.size());

//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Symbol> criteriaQuery = criteriaBuilder.createQuery(Symbol.class);
//
//        Root<Symbol> fxRoot = criteriaQuery.from(Symbol.class);
//
//        criteriaQuery.where(criteriaBuilder.equal(fxRoot.get("id"), 12));
//        
//
//        List<Symbol> fxs = em.createQuery(criteriaQuery).getResultList();
//        System.out.println("   *** "+ fxs.size());
//        for (Symbol f : fxs) {
//            System.out.println(f);
//        }



    }

    public void persist(Object object) {
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
}
