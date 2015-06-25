package jstockenterprisefx.base.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.exception.ConstraintViolationException;

import jstockenterprisefx.base.entity.BaseEntity;

public abstract class JpaGenericDao<T extends BaseEntity<ID>, ID extends Serializable> {
	private static Logger sLogger;

	private EntityManager mEntityManager;

	private final Class<T> mEntityClass;

	@SuppressWarnings("unchecked")
	public JpaGenericDao(final EntityManager entityManager) {
		mEntityManager = entityManager;
		mEntityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		sLogger = Logger.getLogger(mEntityClass.getName());
	}

	public EntityManager getEntityManager() {
		return mEntityManager;
	}

	public Class<T> getEntityClass() {
		return mEntityClass;
	}

	public void create(final T entity) {
		try {
			// TODO treat Unique index or primary key violation
			mEntityManager.persist(entity);
		} catch (PersistenceException e) {
			Throwable throwable = e.getCause();

			while ((throwable != null)
					&& !(throwable instanceof ConstraintViolationException))
				throwable = throwable.getCause();

			if (throwable instanceof ConstraintViolationException) {

			}

			sLogger.severe(e.getCause().getMessage());

			if (e.getCause().getMessage()
					.contains("Unique index or primary key violation")) {

			}
			// Unique index or primary key violation:
			// "UK_IR1YF28XJFHN6AUQXKEPOMQK3_INDEX_4 ON PUBLIC.GROUPITEM(NAME) VALUES ('roteadores', 41)";
			// SQL statement:
			// insert into GroupItem (id, name, groupType, observation) values
			// (null, ?, ?, ?) [23505-187]

		} catch (Exception e) {
			sLogger.severe(e.getMessage());
			sLogger.severe(e.getCause().getMessage());
		}
	}

	public List<T> read() {
		final String query = new StringBuffer("SELECT e FROM ")
				.append(mEntityClass.getSimpleName()).append(" e").toString();

		return read(query);
	}

	public T read(final ID id) {
		return mEntityManager.find(mEntityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> read(final String query) {
		return mEntityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> read(final String query, final Map<String, Object> params) {
		Query jpaQuery = mEntityManager.createQuery(query);

		for (String key : params.keySet())
			jpaQuery.setParameter(key, params.get(key));

		return jpaQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> read(final String query, final Map<String, Object> params,
			final int max, final int first) {
		Query jpaQuery = mEntityManager.createQuery(query)
				.setFirstResult(first).setMaxResults(max);

		for (String key : params.keySet())
			jpaQuery.setParameter(key, params.get(key));

		return jpaQuery.getResultList();
	}

	public void update(final T entity) {
		mEntityManager.merge(entity);
	}

	public void delete(T entity) {
		entity = mEntityManager.getReference(mEntityClass, entity.getId());
		mEntityManager.remove(entity);
	}
}
