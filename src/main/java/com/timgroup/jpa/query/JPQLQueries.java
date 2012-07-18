package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;

public class JPQLQueries extends Queries {
    
    @Override
    public TypedQuery<Idea> query(EntityManager em) {
        return em.createQuery("select i from Idea i where i.stockTicker = 'NXJ'", Idea.class);
    }
    
}
