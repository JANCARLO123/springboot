package com.jcpv.example.service;

import com.jcpv.example.entity.Person;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by JanCarlo on 21/08/2017.
 */
@Service
public interface PersonService {
    public List<Person> getAll();
}
