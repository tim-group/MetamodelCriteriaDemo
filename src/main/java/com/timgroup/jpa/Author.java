package com.timgroup.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Author {
    
    @Id
    private int id;
    private String name;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "author")
    private Set<Idea> ideas;
    
    public Author(int id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.ideas = new HashSet<Idea>();
        company.getAuthors().add(this);
    }
    
    protected Author() {}
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public Set<Idea> getIdeas() {
        return ideas;
    }
    
    @Override
    public String toString() {
        return "Author {id = " + id + ", name = " + name + ", company = " + company.getName() + "}";
    }
    
}
