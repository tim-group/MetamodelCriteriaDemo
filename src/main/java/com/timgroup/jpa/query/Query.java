package com.timgroup.jpa.query;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Idea;

public abstract class Query extends Statement {
    
    @Override
    public void execute(final EntityManager em) {
        attempt("findIdeasOnAParticularStock", em);
        attempt("findIdeasWithBigInvestments", em);
        attempt("findAuthorsOfIdeasOnAParticularStock", em);
    }
    
    private void attempt(String name, EntityManager em) {
        System.out.println();
        System.out.println(name);
        System.out.println("================================================================================");
        System.out.flush();
        try {
            Method method = getClass().getMethod(name, EntityManager.class);
            @SuppressWarnings("unchecked")
            TypedQuery<Idea> query = (TypedQuery<Idea>) method.invoke(this, em);
            dumpQueryResults(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public abstract TypedQuery<Idea> findIdeasOnAParticularStock(EntityManager em);
    
    public abstract TypedQuery<Idea> findIdeasWithBigInvestments(EntityManager em);
    
    public abstract TypedQuery<Author> findAuthorsOfIdeasOnAParticularStock(EntityManager em);
    
}
