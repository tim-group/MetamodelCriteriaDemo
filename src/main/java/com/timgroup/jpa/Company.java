package com.timgroup.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
    
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "company")
    private Set<Author> authors;
    
    public Company(int id, String name) {
        this.id = id;
        this.name = name;
        this.authors = new HashSet<Author>();
    }
    
    protected Company() {}
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Author> getAuthors() {
        return authors;
    }
    
    @Override
    public String toString() {
        return "Company {id = " + id + ", name = " + name + "}";
    }
    
}
