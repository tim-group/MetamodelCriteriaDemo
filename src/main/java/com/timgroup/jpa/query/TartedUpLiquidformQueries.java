package com.timgroup.jpa.query;

import static com.google.code.liquidform.LiquidForm.alias;
import static com.google.code.liquidform.LiquidForm.eq;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.code.liquidform.FromClause;
import com.google.code.liquidform.LiquidForm;
import com.google.code.liquidform.SubQuery;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class TartedUpLiquidformQueries {
	
	public static void main(String... args) {
		Idea i = anInstanceOf(Idea.class);
		SubQuery<Idea> q = select(i).where(eq(i.getStockTicker(), "NXJ"));
		final String qString = q.toString();
		
		PersistenceUnit.execute(new UnitOfWork() {
			@Override
			public void perform(EntityManager em) {
				TypedQuery<Idea> query = em.createQuery(qString, Idea.class);
				PersistenceUnit.dumpQueryResults(query);
			}
		});
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
}
