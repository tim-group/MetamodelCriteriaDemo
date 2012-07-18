package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;

public abstract class Query extends Statement {
    
    @Override
    public void execute(final EntityManager em) {
        attempt("findIdeasOnAParticularStock", new Runnable() {
            @Override public void run() {
                dumpQueryResults(findIdeasOnAParticularStock(em));
            }
        });
    }
    
    private void attempt(String name, Runnable feat) {
        System.out.println(name);
        System.out.println("===========================");
        try {
            feat.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public abstract TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em);
    
}
