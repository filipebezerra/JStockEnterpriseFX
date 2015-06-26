package jstockenterprisefx.stock.receipt;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jstockenterprisefx.base.entity.BaseEntity;
import jstockenterprisefx.item.Item;

/**
 * 
 * @author Filipe Bezerra
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "StockReceiptItem.findAll", query = "SELECT s FROM StockReceiptItem s"),
		@NamedQuery(name = "StockReceiptItem.findById", query = "SELECT s FROM StockReceiptItem s WHERE s.id = :id"),
		@NamedQuery(name = "StockReceiptItem.findByStockReceiptId", query = "SELECT s FROM StockReceiptItem s WHERE s.stockReceipt.id = :stockReceiptId"),
		@NamedQuery(name = "StockReceiptItem.findByQuantity", query = "SELECT s FROM StockReceiptItem s WHERE s.quantity = :quantity") })
public class StockReceiptItem extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Short quantity;

	@JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Item item;

	@JoinColumn(name = "stockReceiptId", referencedColumnName = "id", nullable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private StockReceipt stockReceipt;

	public StockReceiptItem() {
	}

	public StockReceiptItem(final Long id) {
		this.id = id;
	}

	public StockReceiptItem(final Short quantity, final Item item) {
		this(quantity, item, null);
	}

	public StockReceiptItem(final Short quantity, final Item item,
			final StockReceipt stockReceipt) {
		this.quantity = quantity;
		this.item = item;
		this.stockReceipt = stockReceipt;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(final Short quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(final Item item) {
		this.item = item;
	}

	public StockReceipt getStockReceipt() {
		return stockReceipt;
	}

	public void setStockReceipt(final StockReceipt stockReceipt) {
		this.stockReceipt = stockReceipt;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockReceiptItem))
			return false;
		StockReceiptItem other = (StockReceiptItem) object;
		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append("[ id=")
				.append(id).append(", quantity=").append(quantity).append(" ]")
				.toString();
	}
}
