package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.timgroup.jpa.Idea;

public class RawCriteriaQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Idea> cq = cb.createQuery(Idea.class);
        Root<Idea> idea = cq.from(Idea.class);
        cq.select(idea).where(cb.equal(idea.get("stockTicker"), "NXJ"));
        return em.createQuery(cq);
    }

    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Idea> cq = cb.createQuery(Idea.class);
        Root<Idea> idea = cq.from(Idea.class);
        cq.select(idea).where(cb.gt(idea.<Integer>get("investment"), 1500000));
        return em.createQuery(cq);
    }
    
}
