package jstockenterprisefx.base.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jstockenterprisefx.base.entity.BaseEntity;

public abstract class JpaGenericDao<T extends BaseEntity<ID>, ID extends Serializable> {
	protected EntityManager mEntityManager;

	protected final Class<T> mEntityClass;

	@SuppressWarnings("unchecked")
	public JpaGenericDao(final EntityManager entityManager) {
		mEntityManager = entityManager;
		mEntityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void create(final T entity) {
		mEntityManager.persist(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> read() {
		return mEntityManager
				.createQuery(
						String.format("SELECT e FROM %s",
								mEntityClass.getSimpleName())).getResultList();
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

	public void delete(final T entity) {
		mEntityManager.getReference(mEntityClass, entity.getId());
		mEntityManager.remove(entity);
	}
}
