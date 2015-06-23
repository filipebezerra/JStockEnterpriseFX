package jstockenterprisefx.item;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class ItemDao extends JpaGenericDao<Item, Long> {
	public ItemDao(final EntityManager entityManager) {
		super(entityManager);
	}
}
