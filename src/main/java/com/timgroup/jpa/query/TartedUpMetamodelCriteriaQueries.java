package com.timgroup.jpa.query;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Company;
import com.timgroup.jpa.Idea;

import static com.timgroup.jpa.Author_.company;
import static com.timgroup.jpa.Author_.ideas;
import static com.timgroup.jpa.Author_.name;
import static com.timgroup.jpa.Company_.authors;
import static com.timgroup.jpa.Idea_.investment;
import static com.timgroup.jpa.Idea_.stockTicker;

public class TartedUpMetamodelCriteriaQueries extends MetamodelCriteriaQueries {
    
    @Override
    public TypedQuery<Author> findPlagiarisingColleagues(EntityManager em) {
        CriteriaBuilder _ = em.getCriteriaBuilder();
        
        CriteriaQuery<Author> q = _.createQuery(Author.class);
        Root<Author> author = q.from(Author.class);
        SetJoin<Company, Author> colleague = author.join(company).join(authors);
        
        Subquery<Idea> sq = q.subquery(Idea.class);
        SetJoin<Author, Idea> original = sq.correlate(author).join(ideas);
        SetJoin<Author, Idea> copy = sq.correlate(colleague).join(ideas);
        
        q.select(colleague).where(allOf(_,
            _.equal($(author, name), "Gunther von Domicile"),
            _.notEqual(author, colleague),
            _.exists(sq.select(original).where(allOf(_,
                _.equal($(original, stockTicker), $(copy, stockTicker)),
                _.greaterThan($(original, investment), 1500000),
                _.greaterThan($(copy, investment), 1500000))))));
        
        return em.createQuery(q);
    }

    private <X, T> Path<T> $(From<?, X> from, SingularAttribute<X, T> attribute) {
        return from.get(attribute);
    }
    
    private Predicate allOf(CriteriaBuilder cb, Predicate... predicates) {
        Predicate conjunction = cb.conjunction();
        conjunction.getExpressions().addAll(Arrays.asList(predicates));
        return conjunction;
    }
    
}
