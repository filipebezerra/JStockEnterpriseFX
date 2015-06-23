package jstockenterprisefx.base.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import jstockenterprisefx.base.entity.BaseEntity;

public abstract class BaseJpaPersistenceTest<T extends BaseEntity<ID>, ID extends Serializable> {
	protected JpaGenericDao<T, ID> dao;

	protected ID testCreate(final T entity) {
		JpaEntityManager.beginTransaction();
		dao.create(entity);
		JpaEntityManager.commit();

		assertNotNull("Created entity returns null ID", entity.getId());

		return entity.getId();
	}

	protected void testDelete(final T entity) {
		final ID id = entity.getId();

		JpaEntityManager.beginTransaction();
		dao.delete(entity);
		JpaEntityManager.commit();

		T deletedEntity = dao.read(id);
		assertNull("Entity not deleted", deletedEntity);
	}

	protected void testList(final int expectedSize) {
		List<T> list = dao.read();
		assertNotNull("List is null", list);
		assertTrue("List is empty", !list.isEmpty());
		assertTrue("List size not expected", list.size() == expectedSize);
	}
}
