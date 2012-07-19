package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPASubQuery;
import com.timgroup.jpa.Author;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.QAuthor;
import com.timgroup.jpa.QCompany;
import com.timgroup.jpa.QIdea;

import static com.timgroup.jpa.QAuthor.author;
import static com.timgroup.jpa.QCompany.company;
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
    
    @Override
    public TypedQuery<Author> findPlagiarisingColleagues(EntityManager em) {
        QAuthor colleague = new QAuthor("colleague");
        QIdea original = new QIdea("original");
        QIdea copy = new QIdea("copy");
        
        JPAQuery dslQuery = new JPAQuery(em)
            .from(author).join(author.company, QCompany.company).join(company.authors, colleague)
            .where(author.name.eq("Gunther von Domicile")
            .and(author.ne(colleague))
            .and(new JPASubQuery()
                .from(original, copy)
                .where(original.author.eq(author)
                .and(copy.author.eq(colleague)
                .and(original.stockTicker.eq(copy.stockTicker))
                .and(original.investment.gt(1500000))
                .and(copy.investment.gt(1500000))))
                .exists()));
        
        return (TypedQuery<Author>) dslQuery.createQuery(colleague);
    }
    
}
