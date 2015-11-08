package com.sapphire.preload;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

   public static EntityManager getEntityManager() {
      return entityManager;
   }
}
