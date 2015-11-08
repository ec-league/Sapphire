package com.sapphire.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {
   private static final String PERSISTENCE_NAME = "as-persistence-unit";

   public long saveOrMerge(User user) {
      return 0;
   }

   public User getUserByUserName(String username) {
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

      EntityManager em = factory.createEntityManager();
      Query q =
            em.createQuery("select u from User u where u.uidpk = :username");
      q.setParameter("username", username);
      List<User> users = q.getResultList();


      return users.get(0);
   }

   public User getUserById(long id) {
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

      EntityManager em = factory.createEntityManager();
      Query q = em.createQuery("select u from UserImpl as u where u.uidPk = 1");

      List<User> users = q.getResultList();

      em.close();
      factory.close();
      return users.get(0);
   }
}
