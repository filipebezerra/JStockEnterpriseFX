package jstockenterprisefx.stock;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import jstockenterprisefx.base.tablemodel.BaseTableModel;
import jstockenterprisefx.supplier.SupplierTableModel;

public class StockEntry extends BaseTableModel {
	private ObjectProperty<SupplierTableModel> supplier = new SimpleObjectProperty<SupplierTableModel>(
			this, "supplier", null);

	private ObjectProperty<LocalDate> registryDate = new SimpleObjectProperty<>(
			this, "registryDate", LocalDate.now());
	
	private ListProperty<StockEntryItem> items = new SimpleListProperty<>(this,
			"items", FXCollections.emptyObservableList());

	public SupplierTableModel getSupplier() {
		return supplier.get();
	}

	public void setSupplier(final SupplierTableModel supplier) {
		this.supplier.set(supplier);
	}

	public ObjectProperty<SupplierTableModel> supplierProperty() {
		return supplier;
	}

	public LocalDate getRegistryDate() {
		return registryDate.get();
	}

	public void setRegistryDate(final LocalDate registryDate) {
		this.registryDate.set(registryDate);
	}
	
	public ObjectProperty<LocalDate> registryDateProperty() {
		return registryDate;
	}

	public ListProperty<StockEntryItem> getItems() {
		return items;
	}

	public void setItems(final ListProperty<StockEntryItem> items) {
		this.items = items;
	}
}
