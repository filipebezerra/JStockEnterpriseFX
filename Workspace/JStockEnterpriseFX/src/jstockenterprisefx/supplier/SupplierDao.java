package jstockenterprisefx.supplier;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class SupplierDao extends JpaGenericDao<Supplier, Integer> {

	public SupplierDao(final EntityManager entityManager) {
		super(entityManager);
	}

}
