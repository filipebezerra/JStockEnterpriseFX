package jstockenterprisefx.base.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaEntityManager {
	private static EntityManager mEntityManager;
	
	private static EntityManagerFactory mEntityManagerFactory;
	
	static {
		mEntityManagerFactory = Persistence.createEntityManagerFactory("h2");
		mEntityManager = mEntityManagerFactory.createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return mEntityManager;
	}

	public static void beginTransaction() {
		mEntityManager.getTransaction().begin();
	}
	
	public static void commit() {
		mEntityManager.getTransaction().commit();
	}
	
	public static void rollback() {
		mEntityManager.getTransaction().rollback();
	}
	
	public static void close() {
		mEntityManager.close();
		mEntityManagerFactory.close();
	}
}
