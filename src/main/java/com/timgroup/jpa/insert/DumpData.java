package com.timgroup.jpa.insert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Company;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class DumpData {
    
    public static void main(String... args) {
        PersistenceUnit.execute(new UnitOfWork() {
            @Override
            public void perform(EntityManager em) {
                dump(em, Company.class);
                dump(em, Author.class);
                dump(em, Idea.class);
            }
            
            private <T> void dump(EntityManager em, Class<T> entityClass) {
                TypedQuery<T> all = em.createQuery("select e from " + entityClass.getName() + " e", entityClass);
                PersistenceUnit.dumpQueryResults(all);
            }
            
        });
    }
    
}
