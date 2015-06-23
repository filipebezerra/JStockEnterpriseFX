package jstockenterprisefx.stock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import jstockenterprisefx.base.tablemodel.BaseTableModel;
import jstockenterprisefx.item.ItemTableModel;

public class BaseStockItem extends BaseTableModel {
	private ObjectProperty<ItemTableModel> item = new SimpleObjectProperty<>(this,
			"item", null);

	private IntegerProperty quantity = new SimpleIntegerProperty(this,
			"quantity", 0);

	public BaseStockItem(final ItemTableModel item, final Integer quantity) {
		this.item.set(item);
		this.quantity.set(quantity);
	}

	public ItemTableModel getItem() {
		return item.get();
	}

	public void setItem(final ItemTableModel item) {
		this.item.set(item);
	}

	public ObjectProperty<ItemTableModel> itemProperty() {
		return item;
	}

	public Integer getQuantity() {
		return quantity.get();
	}

	public void setQuantity(final Integer quantity) {
		this.quantity.set(quantity);
	}

	public IntegerProperty quantityProperty() {
		return quantity;
	}
}
