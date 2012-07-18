package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Company;
import com.timgroup.jpa.Idea;

public class DumpData extends Statement {
    
    @Override
    public void execute(EntityManager em) {
        dump(em, Company.class);
        dump(em, Author.class);
        dump(em, Idea.class);
    }
    
    private <T> void dump(EntityManager em, Class<T> entityClass) {
        TypedQuery<T> all = em.createQuery("select e from " + entityClass.getName() + " e", entityClass);
        dumpQueryResults(all);
    }
    
}
