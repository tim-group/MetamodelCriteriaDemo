package com.timgroup.jpa.insert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import com.timgroup.jpa.Author;
import com.timgroup.jpa.Company;
import com.timgroup.jpa.Idea;
import com.timgroup.jpa.PersistenceUnit;
import com.timgroup.jpa.PersistenceUnit.UnitOfWork;

public class CreateData {
    
    public static void main(String... args) {
        final Random rnd = new Random(1340372188L);
        PersistenceUnit.execute(new UnitOfWork() {
            @Override
            public void perform(EntityManager em) {
                Company megabank = persist(em, new Company(nextId(rnd), "MegaBank Equity Sales"));
                Company oldfellows = persist(em, new Company(nextId(rnd), "Oldfellows Brokerage"));
                Company quant = persist(em, new Company(nextId(rnd), "MIQ Quant Wizardry"));
                persistNewAuthor(em, rnd, "John Q. Pinstripe", megabank);
                persistNewAuthor(em, rnd, "Gunther von Domicile", megabank);
                persistNewAuthor(em, rnd, "Godfrey Smith-Smythe", oldfellows);
                persistNewAuthor(em, rnd, "TRADEROBOT 9000", quant);
                persistNewAuthor(em, rnd, "TRADEROBOT YMP", quant);
                persistNewAuthor(em, rnd, "TRADEROBOT R2", quant);
            }
        });
    }
    
    protected static void persistNewAuthor(EntityManager em, Random rnd, String name, Company company) {
        Author author = persist(em, new Author(nextId(rnd), name, company));
        int numberOfIdeas = Math.max(10 + (int) (rnd.nextGaussian() * 3), 1);
        for (int i = 0; i < numberOfIdeas; i++) {
            String stockTicker = randomTicker(rnd);
            int investment = (10 + (int) (rnd.nextGaussian() * 5)) * 100000;
            Date date = new Date(System.currentTimeMillis() - (long) Math.abs(rnd.nextGaussian() * (7 * 24 * 60 * 60 * 1000)));
            persist(em, new Idea(nextId(rnd), author, stockTicker, investment, date));
        }
    }
    
    private static List<String> tickers = new ArrayList<String>();
    
    private static String randomTicker(Random rnd) {
        String ticker;
        if (rnd.nextDouble() < Math.pow(2, -tickers.size())) {
            ticker = new String(new char[] {randomChar(rnd), randomChar(rnd), randomChar(rnd)});
            tickers.add(ticker);
        }
        else {
            System.out.println("reusing ticker");
            ticker = tickers.get(rnd.nextInt(tickers.size()));
        }
        return ticker;
    }
    
    private static char randomChar(Random rnd) {
        return (char) ('A' + rnd.nextInt('Z' - 'A'));
    }

    protected static <T> T persist(EntityManager em, T entity) {
        em.persist(entity);
        return entity;
    }

    private static int nextId(final Random rnd) {
        return Math.abs(rnd.nextInt());
    }
    
}
