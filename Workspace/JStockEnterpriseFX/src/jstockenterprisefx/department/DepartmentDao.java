package jstockenterprisefx.department;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class DepartmentDao extends JpaGenericDao<Department, Short> {
	public DepartmentDao(final EntityManager entityManager) {
		super(entityManager);
	}
}
