package jstockenterprisefx.stock;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;
import jstockenterprisefx.item.Item;

public class StockItemTableModel extends NamedTableModel<Item, Long> {

	private final ReadOnlyLongWrapper itemId = new ReadOnlyLongWrapper(this,
			"itemId", 0);

	private final ReadOnlyStringWrapper itemName = new ReadOnlyStringWrapper(
			this, "itemName", null);

	private final ReadOnlyIntegerWrapper itemQuantity = new ReadOnlyIntegerWrapper(
			this, "itemQuantity", 0);

	private final ObjectProperty<Item> item = new SimpleObjectProperty<>(this,
			"item", null);

	public StockItemTableModel(final Item item, final Integer quantity) {
		super(item);

		setItemId(item.getId());
		setItemName(item.getName());
		setItemQuantity(quantity);
	}

	public Long getItemId() {
		return itemId.get();
	}

	public void setItemId(final Long itemId) {
		this.itemId.set(itemId);
	}

	public ReadOnlyLongWrapper itemIdProperty() {
		return itemId;
	}

	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(final String itemName) {
		this.itemName.set(itemName);
	}

	public ReadOnlyStringWrapper itemNameProperty() {
		return itemName;
	}

	public Integer getItemQuantity() {
		return itemQuantity.get();
	}

	public void setItemQuantity(final Integer itemQuantity) {
		this.itemQuantity.set(itemQuantity);
	}

	public ReadOnlyIntegerWrapper itemQuantityProperty() {
		return itemQuantity;
	}

	public Item getItem() {
		return item.get();
	}

	public void setItem(final Item item) {
		this.item.set(item);
	}

	public ObjectProperty<Item> itemProperty() {
		return item;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockItemTableModel other = (StockItemTableModel) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (itemId.get() != other.itemId.get())
			return false;
		return true;
	}
}