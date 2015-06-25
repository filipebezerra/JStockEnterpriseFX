package jstockenterprisefx.base.jpa;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaEntityManager {
	private static final Logger LOGGER = Logger
			.getLogger(JpaEntityManager.class.getName());

	private static EntityManager mEntityManager;

	private static EntityManagerFactory mEntityManagerFactory;

	private static final String PU_NAME = "h2";

	static {
		mEntityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
		mEntityManager = mEntityManagerFactory.createEntityManager();
	}

	public static synchronized EntityManager getEntityManager() {
		if (mEntityManager == null || !mEntityManager.isOpen())
			recreateEntittyManager();
		return mEntityManager;
	}

	private static synchronized void recreateEntittyManager() {
		mEntityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
		mEntityManager = mEntityManagerFactory.createEntityManager();
	}

	public static void beginTransaction() {
		if (mEntityManager.isOpen()
				&& !mEntityManager.getTransaction().isActive())
			mEntityManager.getTransaction().begin();
	}

	public static void commit() {
		if (mEntityManager.isOpen()
				&& mEntityManager.getTransaction().isActive())
			mEntityManager.getTransaction().commit();
	}

	public static void rollback() {
		if (mEntityManager.isOpen()
				&& mEntityManager.getTransaction().isActive())
			mEntityManager.getTransaction().rollback();
	}

	public static synchronized void close() {
		if (mEntityManager.isOpen()) {
			mEntityManager.close();
			LOGGER.info("Entity Manager was closed and released");
		}

		if (mEntityManagerFactory.isOpen()) {
			mEntityManagerFactory.close();
			LOGGER.info("Entity Manager Factory was closed and released");
		}

		if (!mEntityManager.isOpen() && !mEntityManagerFactory.isOpen())
			LOGGER.info("All JPA resources was closed and released");
	}
}
