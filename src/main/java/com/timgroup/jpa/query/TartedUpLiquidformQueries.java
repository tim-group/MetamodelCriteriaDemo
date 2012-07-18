package com.timgroup.jpa.query;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.code.liquidform.FromClause;
import com.google.code.liquidform.LiquidForm;
import com.google.code.liquidform.SubQuery;
import com.timgroup.jpa.Author;
import com.timgroup.jpa.Idea;

import static com.google.code.liquidform.LiquidForm.alias;
import static com.google.code.liquidform.LiquidForm.eq;
import static com.google.code.liquidform.LiquidForm.gt;

public class TartedUpLiquidformQueries extends Query {
    
    @Override
    public TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em) {
        Idea i = anInstanceOf(Idea.class);
        SubQuery<Idea> q = select(i).where(eq(i.getStockTicker(), "NXJ"));
        return toQuery(em, q, Idea.class);
    }
    
    @Override
    public TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em) {
        Idea i = anInstanceOf(Idea.class);
        SubQuery<Idea> q = select(i).where(gt(i.getInvestment(), 1500000));
        return toQuery(em, q, Idea.class);
    }
    
    @Override
    public TypedQuery<Author> findAuthorsOfIdeasOnAParticularStock(EntityManager em) {
        Author a = anInstanceOf(Author.class);
        Idea i = alias(a.getIdeas(), "i");
        SubQuery<Author> q = select(a).innerJoin(a.getIdeas()).as(i).where(eq(i.getStockTicker(), "NXJ"));
        return em.createQuery(q.toString(), Author.class);
    }

    private static <T> T anInstanceOf(Class<T> entityClass) {
        String label = entityClass.getName().substring(0, 1).toLowerCase();
        return alias(entityClass, label);
    }
    
    private static <T> FromClause<T, T> select(T root) {
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) root.getClass().getSuperclass();
        return LiquidForm.select(root).from(entityClass).as(root);
    }
    
    private static <T> TypedQuery<T> toQuery(EntityManager em, SubQuery<T> q, Class<T> resultClass) {
        return em.createQuery(q.toString(), resultClass);
    }

}
