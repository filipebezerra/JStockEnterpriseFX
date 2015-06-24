package jstockenterprisefx.base.jpa;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.Query;

import jstockenterprisefx.base.entity.BaseEntity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore
public class BaseJpaPersistenceTest<T extends BaseEntity<ID>, ID extends Serializable> {

	protected ObjectProperty<JpaGenericDao<T, ID>> dao = new SimpleObjectProperty<JpaGenericDao<T, ID>>(
			null);

	protected static Logger baseLogger = Logger
			.getLogger(BaseJpaPersistenceTest.class.getName());

	protected static Logger daoLogger;

	public BaseJpaPersistenceTest() {
		dao.addListener((observable, oldValue, newValue) -> {
			if (oldValue == null && newValue != null) {
				daoLogger = Logger.getLogger(dao.get().getEntityClass()
						.getName());
				daoLogger.setLevel(Level.ALL);
			}
		});
	}

	@Before
	public void before() {
		daoLogger.info(String.format("JUnit call to before() from %s", dao
				.get().getEntityClass().getName()));
		deleteAll();
	}

	@After
	public void after() {
		daoLogger.info(String.format("JUnit call to after() from %s", dao.get()
				.getEntityClass().getName()));
		deleteAll();
	}

	@BeforeClass
	public static void beforeClass() {
		baseLogger
				.info("JUnit call to beforeClass() to open JPA resources if needed");
		JpaEntityManager.getEntityManager();
	}

	@AfterClass
	public static void afterClass() {
		baseLogger.info("JUnit call to afterClass() to release JPA resources");
		JpaEntityManager.close();
	}

	public void deleteAll() {
		daoLogger.info(String.format("Call to deleteAll() from %s", dao.get()
				.getEntityClass().getName()));
		final Class<T> entityClass = dao.get().getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + entityClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();

		daoLogger.info(String.format(
				"All entities from %s are excluded from database source.",
				entityClass.getSimpleName()));
	}

	public T testCreate(final T entity) {
		daoLogger.info(String.format("Call to testCreate(%s)", entity
				.getClass().getName()));
		JpaEntityManager.beginTransaction();
		dao.get().create(entity);
		JpaEntityManager.commit();

		final ID id = entity.getId();

		assertNotNull("Created entity returns null ID", id);

		T entityFound = dao.get().read(id);

		assertNotNull("Previously entity created not found", entityFound);
		assertEquals(
				"ID from entity found is not equals to ID of entity created",
				id, entityFound.getId());

		return entityFound;
	}

	public T testUpdate(final T entity) {
		daoLogger.info(String.format("Call to testUpdate(%s)", entity
				.getClass().getName()));
		JpaEntityManager.beginTransaction();
		dao.get().update(entity);
		JpaEntityManager.commit();

		final ID id = entity.getId();

		assertNotNull("Updated entity returns null ID", id);

		T entityFound = dao.get().read(id);

		assertNotNull("Previously entity updated not found", entityFound);
		assertEquals(
				"ID from entity found is not equals to ID of entity updated",
				id, entityFound.getId());

		return entityFound;
	}

	public void testDelete(final T entity) {
		daoLogger.info(String.format("Call to testDelete(%s)", entity
				.getClass().getName()));
		final ID id = entity.getId();

		JpaEntityManager.beginTransaction();
		dao.get().delete(entity);
		JpaEntityManager.commit();

		T deletedEntity = dao.get().read(id);
		assertNull("Entity not deleted", deletedEntity);
	}

	public void testList(final int expectedSize) {
		daoLogger.info(String.format("Call to testList(%d) from %s",
				expectedSize, dao.get().getEntityClass().getName()));
		List<T> list = dao.get().read();
		assertNotNull("List is null", list);
		assertTrue("List is empty", !list.isEmpty());
		assertTrue("List size not expected", list.size() == expectedSize);
	}

}
