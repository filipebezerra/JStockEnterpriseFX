package jstockenterprisefx.stock.receipt;

import javax.persistence.EntityManager;

import jstockenterprisefx.base.jpa.JpaGenericDao;

public class StockReceiptDao extends JpaGenericDao<StockReceipt, Long> {

	public StockReceiptDao(final EntityManager entityManager) {
		super(entityManager);
	}

}
