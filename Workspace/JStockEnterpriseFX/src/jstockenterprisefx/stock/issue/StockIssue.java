package jstockenterprisefx.stock.issue;

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
import jstockenterprisefx.department.Department;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * 
 * @author Filipe Bezerra
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "StockIssue.findAll", query = "SELECT s FROM StockIssue s"),
		@NamedQuery(name = "StockIssue.findById", query = "SELECT s FROM StockIssue s WHERE s.id = :id"),
		@NamedQuery(name = "StockIssue.findByCreateAt", query = "SELECT s FROM StockIssue s WHERE s.createdAt = :createdAt"),
		@NamedQuery(name = "StockIssue.findByIssueDate", query = "SELECT s FROM StockIssue s WHERE s.issueDate = :issueDate") })
public class StockIssue extends BaseEntity<Long> {

	@Generated(GenerationTime.ALWAYS)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDate issueDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stockIssue", fetch = FetchType.LAZY)
	private List<StockIssueItem> stockIssueItemList = new ArrayList<>();

	@JoinColumn(name = "departmentId", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Department department;

	public StockIssue() {
	}

	public StockIssue(final Long id) {
		this.id = id;
	}

	public StockIssue(final LocalDate issueDate, final Department department) {
		this.issueDate = issueDate;
		this.department = department;
	}

	public StockIssue(final Long id, final LocalDateTime createdAt,
			final LocalDate issueDate) {
		this.id = id;
		this.createdAt = createdAt;
		this.issueDate = issueDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(final LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public List<StockIssueItem> getStockIssueItemList() {
		return stockIssueItemList;
	}

	public void setStockIssueItemList(
			final List<StockIssueItem> stockReceiptItemList) {
		stockIssueItemList = stockReceiptItemList;
		stockIssueItemList.forEach(s -> s.setStockIssue(this));
	}

	public void addStockIssueItem(final StockIssueItem item) {
		item.setStockIssue(this);
		stockIssueItemList.add(item);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof StockIssue))
			return false;
		StockIssue other = (StockIssue) object;
		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append("[ id=")
				.append(id).append(", ").append("createdAt=").append(createdAt)
				.append(", ").append("itemList=").append(stockIssueItemList)
				.append(" ]").toString();
	}

}
