package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;

public abstract class Query extends Statement {
    
    @Override
    public void execute(EntityManager em) {
        TypedQuery<Idea> query = query(em);
        dumpQueryResults(query);
    }
    
    public abstract TypedQuery<Idea> query(EntityManager em);
    
}
