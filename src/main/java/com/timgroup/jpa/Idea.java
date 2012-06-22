package com.timgroup.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Idea {
    
    @Id
    private int id;
    @ManyToOne
    private Author author;
    private String stockTicker;
    private int investment;
    private Date date;
    
    public Idea(int id, Author author, String stockTicker, int investment, Date date) {
        this.id = id;
        this.author = author;
        this.stockTicker = stockTicker;
        this.investment = investment;
        this.date = date;
        author.getIdeas().add(this);
    }
    
    protected Idea() {}
    
    public int getId() {
        return id;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public String getStockTicker() {
        return stockTicker;
    }
    
    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }
    
    public int getInvestment() {
        return investment;
    }
    
    public void setInvestment(int investment) {
        this.investment = investment;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
}
