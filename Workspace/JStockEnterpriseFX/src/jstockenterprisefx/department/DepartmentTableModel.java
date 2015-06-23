package jstockenterprisefx.department;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;

public class DepartmentTableModel extends NamedTableModel {
	private StringProperty responsable = new SimpleStringProperty(this,
			"responsable", null);

	public DepartmentTableModel() {
		this(null, null);
	}

	public DepartmentTableModel(final String name, final String responsable) {
		this.name.set(name);
		this.responsable.set(responsable);
	}
	
	public String getResponsable() {
		return responsable.get();
	}

	public void setResponsable(final String responsable) {
		this.responsable.set(responsable);
	}

	public StringProperty responsableProperty() {
		return responsable;
	}

	@Override
	public String toString() {
		return getName();
	}
}
