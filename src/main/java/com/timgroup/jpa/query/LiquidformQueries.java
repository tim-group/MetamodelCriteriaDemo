package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.code.liquidform.SubQuery;
import com.timgroup.jpa.Idea;

import static com.google.code.liquidform.LiquidForm.alias;
import static com.google.code.liquidform.LiquidForm.eq;
import static com.google.code.liquidform.LiquidForm.select;

public class LiquidformQueries extends Query {
    
    @Override
    public TypedQuery<Idea> query(EntityManager em) {
        Idea i = alias(Idea.class, "i");
        SubQuery<Idea> q = select(i).from(Idea.class).as(i).where(eq(i.getStockTicker(), "NXJ"));
        return em.createQuery(q.toString(), Idea.class);
    }
    
}
