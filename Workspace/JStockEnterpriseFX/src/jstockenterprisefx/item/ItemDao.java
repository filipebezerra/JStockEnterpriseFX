package jstockenterprisefx.item;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jstockenterprisefx.base.jpa.JpaGenericDao;
import jstockenterprisefx.base.jpa.exceptions.BusinessConstraintViolation;
import jstockenterprisefx.base.jpa.exceptions.NonExistentEntityException;
import jstockenterprisefx.stock.OperationType;
import jstockenterprisefx.stock.StockQuantityUpdatable;

public class ItemDao extends JpaGenericDao<Item, Long> implements
		StockQuantityUpdatable {

	public ItemDao(final EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public synchronized void updateStockQuantity(final Long id,
			final Integer quantity, final OperationType operationType)
			throws NonExistentEntityException, BusinessConstraintViolation {
		Item itemFound = read(id);

		if (itemFound == null)
			throw new NonExistentEntityException(String.format(
					"O código %d não foi localizado.", id));

		final int updateQuantity;

		if (operationType.equals(OperationType.OUT)) {
			if (itemFound.getStockQuantity() < quantity)
				throw new BusinessConstraintViolation(
						"Quantidade do estoque não pode ser menor que zero (0)",
						"A quantidade à ser atualizada é maior que a quantidade atual no estoque");

			updateQuantity = itemFound.getStockQuantity() - quantity;
		} else
			updateQuantity = itemFound.getStockQuantity() + quantity;

		final String update = "UPDATE Item SET stockQuantity = :stockQuantity, lastStockUpdate = :lastStockUpdate WHERE id = :id";

		Query updateQuery = getEntityManager().createQuery(update);
		updateQuery.setParameter("stockQuantity", updateQuantity);
		updateQuery.setParameter("lastStockUpdate", LocalDateTime.now());
		updateQuery.setParameter("id", id);

		updateQuery.executeUpdate();
	}
}
