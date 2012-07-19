package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Author_;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.Idea_;

public class MetamodelCriteriaQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Idea> q = cb.createQuery(Idea.class);
        Root<Idea> idea = q.from(Idea.class);
        q.select(idea).where(cb.equal(idea.get(Idea_.stockTicker), "NXJ"));
        
        return em.createQuery(q);
    }

    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Idea> q = cb.createQuery(Idea.class);
        Root<Idea> idea = q.from(Idea.class);
        q.select(idea).where(cb.gt(idea.get(Idea_.investment), 1500000));
        
        return em.createQuery(q);
    }
    
    @Override
    public TypedQuery<Author> findAuthorsOfIdeasOnAParticularStock(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Author> q = cb.createQuery(Author.class);
        Root<Author> author = q.from(Author.class);
        q.select(author).where(cb.equal(author.join(Author_.ideas).get(Idea_.stockTicker), "NXJ"));
        
        return em.createQuery(q);
    }
    
}
