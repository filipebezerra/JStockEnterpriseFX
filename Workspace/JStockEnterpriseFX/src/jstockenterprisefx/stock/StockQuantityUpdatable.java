package jstockenterprisefx.stock;

import jstockenterprisefx.base.jpa.exceptions.BusinessConstraintViolation;
import jstockenterprisefx.base.jpa.exceptions.NonExistentEntityException;

public interface StockQuantityUpdatable {

	void updateStockQuantity(Long id, Integer quantity,
			OperationType operationType) throws NonExistentEntityException,
			BusinessConstraintViolation;

}
