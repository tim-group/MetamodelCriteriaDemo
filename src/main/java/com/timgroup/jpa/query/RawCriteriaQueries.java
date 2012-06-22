package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class RawCriteriaQueries {
    
    public static void main(String... args) {
        PersistenceUnit.execute(new UnitOfWork() {
            @Override
            public void perform(EntityManager em) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Idea> cq = cb.createQuery(Idea.class);
                Root<Idea> idea = cq.from(Idea.class);
                cq.select(idea).where(cb.equal(idea.get("stockTicker"), "NXJ"));
                TypedQuery<Idea> query = em.createQuery(cq);
                PersistenceUnit.dumpQueryResults(query);
            }
        });
    }
    
}
