package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mysema.query.jpa.impl.JPAQuery;
import com.timgroup.jpa.Author;
import com.timgroup.jpa.Idea;

import static com.timgroup.jpa.QAuthor.author;
import static com.timgroup.jpa.QIdea.idea;

public class QueryDSLQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        
        JPAQuery dslQuery = new JPAQuery(em).from(idea).where(idea.stockTicker.eq("NXJ"));
        
        return (TypedQuery<Idea>) dslQuery.createQuery(idea);
    }

    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        
        JPAQuery dslQuery = new JPAQuery(em).from(idea).where(idea.investment.gt(1500000));
        
        return (TypedQuery<Idea>) dslQuery.createQuery(idea);
    }
    
    @Override
    public TypedQuery<Author> findAuthorsOfIdeasOnAParticularStock(EntityManager em) {
        
        JPAQuery dslQuery = new JPAQuery(em).from(author).join(author.ideas, idea).where(idea.stockTicker.eq("NXJ"));
        
        return (TypedQuery<Author>) dslQuery.createQuery(author);
    }
    
}
