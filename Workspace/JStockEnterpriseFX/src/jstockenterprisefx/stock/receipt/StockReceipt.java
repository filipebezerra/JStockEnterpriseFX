package jstockenterprisefx.stock.receipt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import jstockenterprisefx.base.entity.BaseEntity;
import jstockenterprisefx.supplier.Supplier;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * 
 * @author Filipe Bezerra
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "StockReceipt.findAll", query = "SELECT s FROM StockReceipt s"),
		@NamedQuery(name = "StockReceipt.findById", query = "SELECT s FROM StockReceipt s WHERE s.id = :id"),
		@NamedQuery(name = "StockReceipt.findByCreateAt", query = "SELECT s FROM StockReceipt s WHERE s.createdAt = :createdAt"),
		@NamedQuery(name = "StockReceipt.findByReceiptDate", query = "SELECT s FROM StockReceipt s WHERE s.receiptDate = :receiptDate") })
public class StockReceipt extends BaseEntity<Long> {

	@Generated(GenerationTime.ALWAYS)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDate receiptDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stockReceipt", fetch = FetchType.LAZY)
	private List<StockReceiptItem> stockReceiptItemList = new ArrayList<>();

	@JoinColumn(name = "supplierId", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Supplier supplier;

	public StockReceipt() {
	}

	public StockReceipt(final Long id) {
		this.id = id;
	}

	public StockReceipt(final LocalDate receiptDate, final Supplier supplier) {
		this.receiptDate = receiptDate;
		this.supplier = supplier;
	}

	public StockReceipt(final Long id, final LocalDateTime createdAt,
			final LocalDate receiptDate) {
		this.id = id;
		this.createdAt = createdAt;
		this.receiptDate = receiptDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(final LocalDate receiptDate) {
		this.receiptDate = receiptDate;
	}

	public List<StockReceiptItem> getStockReceiptItemList() {
		return stockReceiptItemList;
	}

	public void setStockReceiptItemList(
			final List<StockReceiptItem> stockReceiptItemList) {
		this.stockReceiptItemList = stockReceiptItemList;
		this.stockReceiptItemList.forEach(s -> s.setStockReceipt(this));
	}

	public void addStockReceiptItem(final StockReceiptItem item) {
		item.setStockReceipt(this);
		stockReceiptItemList.add(item);
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(final Supplier supplierid) {
		supplier = supplierid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockReceipt))
			return false;
		StockReceipt other = (StockReceipt) object;
		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append("[ id=")
				.append(id).append(", ").append("createdAt=").append(createdAt)
				.append(", ").append("itemList=").append(stockReceiptItemList)
				.append(" ]").toString();
	}

}
