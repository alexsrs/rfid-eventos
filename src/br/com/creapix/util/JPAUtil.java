package br.com.creapix.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rfid_eventos");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
