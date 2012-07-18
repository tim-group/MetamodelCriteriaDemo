package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;

public abstract class Query extends Statement {
    
    @Override
    public void execute(EntityManager em) {
        dumpQueryResults(findIdeasOnAParticularStock(em));
    }
    
    public abstract TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em);
    
}
