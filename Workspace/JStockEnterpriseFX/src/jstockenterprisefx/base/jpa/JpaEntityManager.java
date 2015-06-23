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
	
	public EntityManager getEntityManager() {
		return mEntityManager;
	}

	public void beginTransaction() {
		mEntityManager.getTransaction().begin();
	}
	
	public void commit() {
		mEntityManager.getTransaction().commit();
	}
	
	public void rollback() {
		mEntityManager.getTransaction().rollback();
	}
	
	public void close() {
		mEntityManager.close();
		mEntityManagerFactory.close();
	}
}
