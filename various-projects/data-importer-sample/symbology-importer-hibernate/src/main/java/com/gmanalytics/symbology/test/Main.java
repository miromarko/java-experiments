/*
 * Copyright (c) 2005-2013 Green Mountain Analytics.
 * This software is the confidential and proprietary information 
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with Green Mountain Analytics.
 */
package com.gmanalytics.symbology.test;

import com.gmanalytics.symbology.persistence.entities.Fx;
import com.gmanalytics.symbology.persistence.entities.Symbol;
import com.gmanalytics.symbology.persistence.entities.UsIndex;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalytics.com>
 */
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SymbologyPU");
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


//        Query q = em.createNamedQuery("Symbology.findSimbol");
//        Query q = em.createQuery("SELECT f FROM Symbol f WHERE f.gma_ticker = :gma_ticker AND f.activ_name = :activ_name");
//        q.setParameter("activ_name", null);
//        q.setParameter("gma_ticker", "URA.SO.UP-I");
//        Symbol f = (Symbol) q.getSingleResult();
////        System.out.println("***"+f.getCurrency());

//        System.out.println(f);


//        Query query = em.createQuery("select f from Fx f where f = :fx");
//        query.setParameter("fx", fx);
//        List<Fx> list = query.getResultList();
//        
//        
//        
//        System.out.println(list.size());

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Symbol> criteriaQuery = criteriaBuilder.createQuery(Symbol.class);

        Root<Symbol> fxRoot = criteriaQuery.from(Symbol.class);

        criteriaQuery.where(criteriaBuilder.equal(fxRoot.get("gma_ticker"), "URA.SO.UP-I"));

        List<Symbol> fxs = em.createQuery(criteriaQuery).getResultList();
        System.out.println("   *** "+ fxs.size());
        for (Symbol f : fxs) {
            System.out.println(f);
        }



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
