package jstockenterprisefx.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jstockenterprisefx.base.entity.BaseEntity;
import jstockenterprisefx.groupitem.GroupItem;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Filipe Bezerra
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
		@NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
		@NamedQuery(name = "Item.findByCreateAt", query = "SELECT i FROM Item i WHERE i.createAt = :createAt"),
		@NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
		@NamedQuery(name = "Item.findByStockQuantity", query = "SELECT i FROM Item i WHERE i.stockQuantity = :stockQuantity"),
		@NamedQuery(name = "Item.findByLastStockUpdate", query = "SELECT i FROM Item i WHERE i.lastStockUpdate = :lastStockUpdate"),
		@NamedQuery(name = "Item.findByGroupItemId", query = "SELECT i FROM Item i WHERE i.groupItem.id = :groupItemId") })
public class Item extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.ALWAYS)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createAt;

	@Basic(optional = false)
	@Column(nullable = false, length = 120)
	private String description;

	@Basic(optional = false)
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal costPrice;

	@Basic(optional = false)
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal salePrice;

	@Basic(optional = false)
	@Column(nullable = false)
	private int stockQuantity;

	@Column(insertable = false)
	private LocalDateTime lastStockUpdate;

	@JoinColumn(name = "groupItemId", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private GroupItem groupItem;

	public Item() {
	}

	public Item(final Long id) {
		this.id = id;
	}
	
	public Item(final String description,
			final BigDecimal costPrice, final BigDecimal salePrice,
			final int stockQuantity, final GroupItem groupItem) {
		this(null, null, description, costPrice, salePrice, stockQuantity, null, groupItem);
	}

	public Item(final Long id, final LocalDateTime createAt, final String description,
			final BigDecimal costPrice, final BigDecimal salePrice,
			final int stockQuantity, final LocalDateTime lastStockUpdate, final GroupItem groupItem) {
		this.id = id;
		this.createAt = createAt;
		this.description = description;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.stockQuantity = stockQuantity;
		this.lastStockUpdate = lastStockUpdate;
		this.groupItem = groupItem;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(final LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(final BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(final BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(final int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public LocalDateTime getLastStockUpdate() {
		return lastStockUpdate;
	}

	public void setLastStockUpdate(final LocalDateTime lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	public GroupItem getGroupItem() {
		return groupItem;
	}

	public void setGroupItem(final GroupItem itemgroupid) {
		groupItem = itemgroupid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Item))
			return false;
		Item other = (Item) object;
		if ((id == null && other.id != null)
				|| (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return description;
	}

}
