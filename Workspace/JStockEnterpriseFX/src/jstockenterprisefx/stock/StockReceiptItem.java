package jstockenterprisefx.stock;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author Filipe Bezerra
 *
 */
@NamedQueries({
		@NamedQuery(name = "StockReceiptItem.findAll", query = "SELECT s FROM StockReceiptItem s"),
		@NamedQuery(name = "StockReceiptItem.findById", query = "SELECT s FROM StockReceiptItem s WHERE s.stockReceiptItemPK.id = :id"),
		@NamedQuery(name = "StockReceiptItem.findByStockReceiptId", query = "SELECT s FROM StockReceiptItem s WHERE s.stockReceiptItemPK.stockReceiptId = :stockReceiptId"),
		@NamedQuery(name = "StockReceiptItem.findByQuantity", query = "SELECT s FROM StockReceiptItem s WHERE s.quantity = :quantity") })
public class StockReceiptItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StockReceiptItemPK stockReceiptItemPK;

	@Basic(optional = false)
	@Column(nullable = false)
	private short quantity;

	@JoinColumn(name = "stockReceiptId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private StockReceipt stockReceipt;

	public StockReceiptItem() {
	}

	public StockReceiptItem(final StockReceiptItemPK stockReceiptItemPK) {
		this.stockReceiptItemPK = stockReceiptItemPK;
	}

	public StockReceiptItem(final StockReceiptItemPK stockReceiptItemPK,
			final short quantity) {
		this.stockReceiptItemPK = stockReceiptItemPK;
		this.quantity = quantity;
	}

	public StockReceiptItem(final Long id, final Long stockreceiptid) {
		stockReceiptItemPK = new StockReceiptItemPK(id, stockreceiptid);
	}

	public StockReceiptItemPK getStockReceiptItemPK() {
		return stockReceiptItemPK;
	}

	public void setStockReceiptItemPK(
			final StockReceiptItemPK stockReceiptItemPK) {
		this.stockReceiptItemPK = stockReceiptItemPK;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(final short quantity) {
		this.quantity = quantity;
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
		hash += (stockReceiptItemPK != null ? stockReceiptItemPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockReceiptItem))
			return false;
		StockReceiptItem other = (StockReceiptItem) object;
		if ((stockReceiptItemPK == null && other.stockReceiptItemPK != null)
				|| (stockReceiptItemPK != null && !stockReceiptItemPK
						.equals(other.stockReceiptItemPK)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append("[ stockReceiptItemPK=")
				.append(stockReceiptItemPK).append(", quantity=")
				.append(quantity).append(" ]")
				.toString();
	}
}
