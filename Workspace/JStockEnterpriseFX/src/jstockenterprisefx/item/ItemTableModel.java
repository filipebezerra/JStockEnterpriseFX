package jstockenterprisefx.item;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.tablemodel.BaseTableModel;
import jstockenterprisefx.groupitem.GroupItemTableModel;

public class ItemTableModel extends BaseTableModel<Long> {
	private ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>(
			this, "createdAt", null);

	private StringProperty description = new SimpleStringProperty(this,
			"description", null);
	
	private DoubleProperty costPrice = new SimpleDoubleProperty(this,
			"costPrice", 0);

	private DoubleProperty salePrice = new SimpleDoubleProperty(this,
			"salePrice", 0);
	
	private ReadOnlyIntegerWrapper stockQuantity = new ReadOnlyIntegerWrapper(
			this, "stockQuantity", 0);

	private ReadOnlyObjectWrapper<LocalDateTime> lastStockUpdate = new ReadOnlyObjectWrapper<LocalDateTime>(
			this, "lastStockUpdate", null);

	private ObjectProperty<GroupItemTableModel> group = new SimpleObjectProperty<>(this,
			"group", null);

	public ItemTableModel(final String description, final GroupItemTableModel group,
			final Double salePrice, final Integer stockQuantity,
			final LocalDateTime lastStockUpdate) {
		this.description.set(description);
		this.group.set(group);
		this.salePrice.set(salePrice);
		this.stockQuantity.set(stockQuantity);
		this.lastStockUpdate.set(lastStockUpdate);
	}

	public LocalDateTime getCreatedAt() {
		return createdAt.get();
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt.set(createdAt);
	}

	public ObjectProperty<LocalDateTime> createdAtProperty() {
		return createdAt;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(final String description) {
		this.description.set(description);
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public Double getCostPrice() {
		return costPrice.get();
	}
	
	public void setCostPrice(final Double costPrice) {
		this.costPrice.set(costPrice);
	}
	
	public DoubleProperty costPriceProperty() {
		return costPrice;
	}
	
	public Double getSalePrice() {
		return salePrice.get();
	}
	
	public void setSalePrice(final Double salePrice) {
		this.salePrice.set(salePrice);
	}
	
	public DoubleProperty salePriceProperty() {
		return salePrice;
	}

	public Integer getStockQuantity() {
		return stockQuantity.get();
	}

	public void setStockQuantity(final Integer stockQuantity) {
		this.stockQuantity.set(stockQuantity);
	}

	public ReadOnlyIntegerWrapper stockQuantityProperty() {
		return stockQuantity;
	}

	public LocalDateTime getLastStockUpdate() {
		return lastStockUpdate.get();
	}

	public void setLastStockUpdate(final LocalDateTime lastStockUpdate) {
		this.lastStockUpdate.set(lastStockUpdate);
	}

	public ReadOnlyObjectWrapper<LocalDateTime> lastStockUpdateProperty() {
		return lastStockUpdate;
	}

	public GroupItemTableModel getGroup() {
		return group.get();
	}
	
	public void setGroup(final GroupItemTableModel group) {
		this.group.set(group);
	}
	
	public ObjectProperty<GroupItemTableModel> groupProperty() {
		return group;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
}
