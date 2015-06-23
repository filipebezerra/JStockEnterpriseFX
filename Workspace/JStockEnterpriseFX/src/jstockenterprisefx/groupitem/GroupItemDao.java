package jstockenterprisefx.groupitem;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class GroupItemDao extends JpaGenericDao<GroupItem, Short> {
	public GroupItemDao(final EntityManager entityManager) {
		super(entityManager);
	}
}
