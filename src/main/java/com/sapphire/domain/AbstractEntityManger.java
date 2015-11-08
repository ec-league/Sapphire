package com.sapphire.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class AbstractEntityManger {
   private static final String PERSISTENCE_NAME = "sapphire-persistence-unit";
   private static EntityManagerFactory factory;
   private static EntityManager entityManager;

   static {
      EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_NAME);

      EntityManager em = factory.createEntityManager();
   }
}
