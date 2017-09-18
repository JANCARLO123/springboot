package com.jcpv.example;

import com.jcpv.example.entity.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by JanCarlo on 22/08/2017.
 */
@Repository
@Transactional
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserInfo getActiveUser(String userName) {
        UserInfo activeUser = new UserInfo();
        short enabled = 1;
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=? and enabled=?")
                .setParameter(1, userName).setParameter(2, enabled).getResultList();
        if(!list.isEmpty()) {
            activeUser = (UserInfo)list.get(0);
        }
        return activeUser;
    }
}