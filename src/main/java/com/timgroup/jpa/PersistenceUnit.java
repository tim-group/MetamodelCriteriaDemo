package com.timgroup.jpa;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceUnit {
    
    public static final String NAME = "com.timgroup.jpa";
    public static final String DB_URL = "jdbc:h2:build/db/" + NAME;
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(NAME, new HashMap<String, String>() {{
        put("javax.persistence.jdbc.url", DB_URL);
    }});
    public static final Logger LOGGER = LoggerFactory.getLogger(PersistenceUnit.class);
    
    public static interface UnitOfWork {
        public void perform(EntityManager em);
    }
    
    public static void execute(UnitOfWork work) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            boolean success = false;
            try {
                work.perform(em);
                success = true;
            } finally {
                finish(tx, success);
            }
        } finally {
            close(em);
        }
    }
    
    private static void finish(EntityTransaction tx, boolean success) {
        try {
            if (success) tx.commit();
            else tx.rollback();
        } catch (RuntimeException e) {
            LOGGER.error("error finishing {}successful transaction", (success ? "" : "un"), e);
            if (success) throw e;
        }
    }
    
    private static void close(EntityManager em) {
        try {
            em.close();
        } catch (Exception e) {
            LOGGER.error("error closing entity manager", e);
        }
    }
    
}
