package com.jcpv.example.service.impl;

import com.jcpv.example.entity.Person;
import com.jcpv.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.jcpv.example.service.PersonService;

import java.util.List;

/**
 * Created by JanCarlo on 21/08/2017.
 */
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository repository;

    public List<Person> getAll(){
        return  repository.findAll();
    }
}
