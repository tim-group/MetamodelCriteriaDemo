package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.timgroup.jpa.Idea;
import com.timgroup.jpa.Idea_;

public class MetamodelCriteriaQueries extends Queries {
    
    @Override
    public TypedQuery<Idea> query(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Idea> cq = cb.createQuery(Idea.class);
        Root<Idea> idea = cq.from(Idea.class);
        cq.select(idea).where(cb.equal(idea.get(Idea_.stockTicker), "NXJ"));
        return em.createQuery(cq);
    }
    
}
