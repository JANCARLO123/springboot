package com.jcpv.example.repository;

import com.jcpv.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JanCarlo on 21/08/2017.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

    @Query("select u from Person u where u.name like %?1")
    List<Person> findByNameEndsWith(String name);

    @Query(value = "SELECT * FROM PERSON WHERE NAME = ?1",
            nativeQuery = true)
    Person findByNameQuery(String name);
}
