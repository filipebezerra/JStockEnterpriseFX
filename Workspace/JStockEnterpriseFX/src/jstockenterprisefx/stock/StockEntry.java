package jstockenterprisefx.stock;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import jstockenterprisefx.base.model.Entity;
import jstockenterprisefx.supplier.Supplier;

public class StockEntry extends Entity {
	private ObjectProperty<Supplier> supplier = new SimpleObjectProperty<Supplier>(
			this, "supplier", null);

	private ObjectProperty<LocalDate> registryDate = new SimpleObjectProperty<>(
			this, "registryDate", LocalDate.now());
	
	private ListProperty<StockEntryItem> items = new SimpleListProperty<>(this,
			"items", FXCollections.emptyObservableList());

	public Supplier getSupplier() {
		return supplier.get();
	}

	public void setSupplier(final Supplier supplier) {
		this.supplier.set(supplier);
	}

	public ObjectProperty<Supplier> supplierProperty() {
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
