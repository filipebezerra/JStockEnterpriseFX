package jstockenterprisefx.stock.issue;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class StockIssueDao extends JpaGenericDao<StockIssue, Long> {

	public StockIssueDao(final EntityManager entityManager) {
		super(entityManager);
	}

}
