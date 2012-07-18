package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mysema.query.jpa.impl.JPAQuery;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.QIdea;

public class QueryDSLQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        QIdea idea = QIdea.idea;
        JPAQuery dslQuery = new JPAQuery(em).from(idea).where(idea.stockTicker.eq("NXJ"));
        return (TypedQuery<Idea>) dslQuery.createQuery(idea);
    }

    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        QIdea idea = QIdea.idea;
        JPAQuery dslQuery = new JPAQuery(em).from(idea).where(idea.investment.gt(1500000));
        return (TypedQuery<Idea>) dslQuery.createQuery(idea);
    }
    
}
