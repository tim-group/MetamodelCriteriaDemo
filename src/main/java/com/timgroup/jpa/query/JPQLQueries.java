package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Author;
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
    
    @Override
    public TypedQuery<Author> findAuthorsOfIdeasOnAParticularStock(EntityManager em) {
        return em.createQuery("select a from Author a join a.ideas i where i.stockTicker = 'NXJ'", Author.class);
    }
    
    @Override
    public TypedQuery<Author> findPlagiarisingColleagues(EntityManager em) {
        return em.createQuery(
                "select b " +
        		"from Author a join a.company c join c.authors b " +
        		"where a.name = 'Gunther von Domicile' " +
        		"and a <> b " +
        		"and exists (" +
            		"select i " +
            		"from a.ideas i, b.ideas j " +
            		"where i.stockTicker = j.stockTicker " +
            		"and i.investment > 1500000 " +
            		"and j.investment > 1500000)",
        		Author.class);
    }
    
}
