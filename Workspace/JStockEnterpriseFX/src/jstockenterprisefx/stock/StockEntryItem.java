package jstockenterprisefx.stock;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jstockenterprisefx.item.ItemTableModel;

public class StockEntryItem extends BaseStockItem {
	public StockEntryItem(final ItemTableModel item, final Integer quantity) {
		super(item, quantity);
	}

	private ObjectProperty<StockEntry> stockEntry = new SimpleObjectProperty<StockEntry>(
			this, "stockEntry", null);

	public StockEntry getStockEntry() {
		return stockEntry.get();
	}

	public void setStockEntry(final StockEntry stockEntry) {
		this.stockEntry.set(stockEntry);
	}

	public ObjectProperty<StockEntry> stockEntryProperty() {
		return stockEntry;
	}
}
