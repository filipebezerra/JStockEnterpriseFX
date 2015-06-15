package jstockenterprisefx.department;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.model.NamedEntity;

public class Department extends NamedEntity {
	private StringProperty responsable = new SimpleStringProperty(this,
			"responsable", null);

	public Department() {
		this(null, null);
	}

	public Department(final String name, final String responsable) {
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
