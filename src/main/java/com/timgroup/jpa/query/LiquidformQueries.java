package com.timgroup.jpa.query;

import static com.google.code.liquidform.LiquidForm.alias;
import static com.google.code.liquidform.LiquidForm.eq;
import static com.google.code.liquidform.LiquidForm.select;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.code.liquidform.SubQuery;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class LiquidformQueries {
	
	public static void main(String... args) {
		Idea i = alias(Idea.class, "i");
		SubQuery<Idea> q = select(i).from(Idea.class).as(i).where(eq(i.getStockTicker(), "NXJ"));
		final String qString = q.toString();
		
		PersistenceUnit.execute(new UnitOfWork() {
			@Override
			public void perform(EntityManager em) {
				TypedQuery<Idea> query = em.createQuery(qString, Idea.class);
				PersistenceUnit.dumpQueryResults(query);
			}
		});
	}
}
