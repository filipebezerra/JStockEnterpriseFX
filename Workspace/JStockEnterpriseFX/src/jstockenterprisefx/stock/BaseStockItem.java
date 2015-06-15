package jstockenterprisefx.stock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import jstockenterprisefx.base.model.Entity;
import jstockenterprisefx.item.Item;

public class BaseStockItem extends Entity {
	private ObjectProperty<Item> item = new SimpleObjectProperty<>(this,
			"item", null);

	private IntegerProperty quantity = new SimpleIntegerProperty(this,
			"quantity", 0);

	public BaseStockItem(final Item item, final Integer quantity) {
		this.item.set(item);
		this.quantity.set(quantity);
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
