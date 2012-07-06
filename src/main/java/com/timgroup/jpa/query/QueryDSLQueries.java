package com.timgroup.jpa.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;
import com.timgroup.jpa.QIdea;

public class QueryDSLQueries {
	
	public static void main(String... args) {
		final QIdea idea = QIdea.idea;
		
		PersistenceUnit.execute(new UnitOfWork() {
			@Override
			public void perform(EntityManager em) {
				JPAQuery dslQuery = new JPAQuery(em).from(idea).where(idea.stockTicker.eq("NXJ"));
				TypedQuery<Idea> query = toTypedQuery(idea, dslQuery);
				PersistenceUnit.dumpQueryResults(query);
			}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	private static <T> TypedQuery<T> toTypedQuery(final EntityPath<T> root, JPAQuery query) {
		return (TypedQuery<T>) query.createQuery(root);
	}
	
}
