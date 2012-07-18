package com.timgroup.jpa.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public abstract class Statement implements Runnable {
    
    @Override
    public void run() {
        PersistenceUnit.execute(new UnitOfWork() {
            @Override
            public void perform(EntityManager em) {
                execute(em);
            }
        });
    }
    
    public abstract void execute(EntityManager em);

    protected <T> void dumpQueryResults(TypedQuery<T> query) {
        List<T> results = query.getResultList();
        for (T result : results) {
            System.out.println(result);
        }
    }
    
}
