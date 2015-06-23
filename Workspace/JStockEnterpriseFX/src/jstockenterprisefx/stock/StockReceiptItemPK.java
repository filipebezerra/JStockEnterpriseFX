package jstockenterprisefx.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import jstockenterprisefx.base.entity.BaseEntity;

/**
 * 
 * @author Filipe Bezerra
 *
 */
@Embeddable
public class StockReceiptItemPK extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Long stockReceiptId;

	public StockReceiptItemPK() {
	}

	public StockReceiptItemPK(final Long id, final Long stockReceiptId) {
		this.id = id;
		this.stockReceiptId = stockReceiptId;
	}

	public Long getStockReceiptId() {
		return stockReceiptId;
	}

	public void setStockReceiptId(final Long stockReceiptId) {
		this.stockReceiptId = stockReceiptId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		hash += (stockReceiptId != null ? stockReceiptId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockReceiptItemPK))
			return false;
		StockReceiptItemPK other = (StockReceiptItemPK) object;
		if (id != other.id)
			return false;
		if (stockReceiptId != other.stockReceiptId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append(
				"[ id=").append(id).append(", stockReceiptId=")
				.append(stockReceiptId).append(" ]")
				.toString();
	}
}
