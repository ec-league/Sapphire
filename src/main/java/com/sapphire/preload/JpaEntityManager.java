package com.sapphire.preload;

import com.sapphire.domain.Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Objects;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public class JpaEntityManager {
   private static final String PERSISTENCE_NAME = "sapphire-persistence-unit";
   private static EntityManagerFactory factory;
   private static EntityManager entityManager;

   public static void loadProperties() {
      factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

      entityManager = factory.createEntityManager();
   }

   public synchronized static void saveOrMerge(Entity entity) {
      entityManager.getTransaction().begin();
      entityManager.persist(entity);
      entityManager.getTransaction().commit();
   }

   public static Query createQuery(String query, Object[] objects) {
      Query result = entityManager.createQuery(query);
      int i = 0;
      for (Object object : objects) {
         i++;
         result.setParameter(i, object);
      }
      return result;
   }
}
