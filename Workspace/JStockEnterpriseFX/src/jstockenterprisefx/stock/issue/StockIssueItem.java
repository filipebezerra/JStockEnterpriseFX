package jstockenterprisefx.stock.issue;

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
		@NamedQuery(name = "StockIssueItem.findAll", query = "SELECT s FROM StockIssueItem s"),
		@NamedQuery(name = "StockIssueItem.findById", query = "SELECT s FROM StockIssueItem s WHERE s.id = :id"),
		@NamedQuery(name = "StockIssueItem.findByStockIssuetId", query = "SELECT s FROM StockIssueItem s WHERE s.stockIssue.id = :stockIssueId"),
		@NamedQuery(name = "StockIssueItem.findByQuantity", query = "SELECT s FROM StockIssueItem s WHERE s.quantity = :quantity") })
public class StockIssueItem extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Short quantity;

	@JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Item item;

	@JoinColumn(name = "stockIssueId", referencedColumnName = "id", nullable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private StockIssue stockIssue;

	public StockIssueItem() {
	}

	public StockIssueItem(final Long id) {
		this.id = id;
	}

	public StockIssueItem(final Short quantity, final Item item) {
		this(quantity, item, null);
	}

	public StockIssueItem(final Short quantity, final Item item,
			final StockIssue stockIssue) {
		this.quantity = quantity;
		this.item = item;
		this.stockIssue = stockIssue;
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

	public StockIssue getStockIssue() {
		return stockIssue;
	}

	public void setStockIssue(final StockIssue stockReceipt) {
		stockIssue = stockReceipt;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockIssueItem))
			return false;
		StockIssueItem other = (StockIssueItem) object;
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
