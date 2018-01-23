package com.jcpv.example.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by JanCarlo on 21/08/2017.
 */
@Entity
@Table(name="persons")
//@org.hibernate.annotations.Cache(region = "personCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class Person {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long Id;

    @Column(name="name")
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}