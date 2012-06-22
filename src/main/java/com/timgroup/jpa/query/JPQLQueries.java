package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class JPQLQueries {
    
    public static void main(String... args) {
        PersistenceUnit.execute(new UnitOfWork() {
            @Override
            public void perform(EntityManager em) {
                TypedQuery<Idea> query = em.createQuery("select i from Idea i where i.stockTicker = 'NXJ'", Idea.class);
                PersistenceUnit.dumpQueryResults(query);
            }
        });
    }
    
}
