package jstockenterprisefx.item;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.util.DateUtils;

public class ItemTableModel extends NamedTableModel<Item, Long> {

	private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>(
			this, "createdAt", null);

	private final DoubleProperty costPrice = new SimpleDoubleProperty(this,
			"costPrice", 0);

	private final DoubleProperty salePrice = new SimpleDoubleProperty(this,
			"salePrice", 0);

	private final ReadOnlyIntegerWrapper stockQuantity = new ReadOnlyIntegerWrapper(
			this, "stockQuantity", 0);

	private final ReadOnlyObjectWrapper<String> lastStockUpdate = new ReadOnlyObjectWrapper<>(
			this, "lastStockUpdate", null);

	private final ObjectProperty<GroupItem> groupItem = new SimpleObjectProperty<>(
			this, "groupItem", null);

	public ItemTableModel() {
		super(new Item());
	}

	public ItemTableModel(final Item item) {
		super(item);

		setCreatedAt(item.getCreatedAt());
		setCostPrice(item.getCostPrice().doubleValue());
		setSalePrice(item.getSalePrice().doubleValue());
		setStockQuantity(item.getStockQuantity());
		setLastStockUpdate(DateUtils.format(item.getLastStockUpdate()));
		setGroupItem(item.getGroupItem());
	}

	public ItemTableModel(final String name, final GroupItem groupItem,
			final Double salePrice, final Integer stockQuantity,
			final LocalDateTime lastStockUpdate) {
		super(name);
		setGroupItem(groupItem);
		setSalePrice(salePrice);
		setStockQuantity(stockQuantity);
		setLastStockUpdate(DateUtils.format(lastStockUpdate));
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

	public String getLastStockUpdate() {
		return lastStockUpdate.get();
	}

	public void setLastStockUpdate(final String lastStockUpdate) {
		this.lastStockUpdate.set(lastStockUpdate);
	}

	public ReadOnlyObjectWrapper<String> lastStockUpdateProperty() {
		return lastStockUpdate;
	}

	public GroupItem getGroupItem() {
		return groupItem.get();
	}

	public void setGroupItem(final GroupItem groupItem) {
		this.groupItem.set(groupItem);
	}

	public ObjectProperty<GroupItem> groupItemProperty() {
		return groupItem;
	}

	@Override
	public String toString() {
		return getName();
	}

}
