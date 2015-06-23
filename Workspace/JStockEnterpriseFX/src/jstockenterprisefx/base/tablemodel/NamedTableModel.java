package jstockenterprisefx.base.tablemodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class NamedTableModel extends BaseTableModel {
	protected final StringProperty name = new SimpleStringProperty(this,
			"name", null);

	public String getName() {
		return name.get();
	}

	public void setName(final String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	@Override
	public String toString() {
		return new StringBuffer(super.toString().replace("}", ""))
				.append(", name : ").append(name.get()).append("}").toString();
	}
}
