package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Idea;

public class JPQLQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        return em.createQuery("select i from Idea i where i.stockTicker = 'NXJ'", Idea.class);
    }

    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        return em.createQuery("select i from Idea i where i.investment > 1500000", Idea.class);
    }
    
}
