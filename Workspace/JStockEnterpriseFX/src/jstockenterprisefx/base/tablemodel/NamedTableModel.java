package jstockenterprisefx.base.tablemodel;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.entity.NamedEntity;

public abstract class NamedTableModel<T extends NamedEntity<ID>, ID extends Serializable>
		extends BaseTableModel<T, ID> {

	protected final StringProperty name = new SimpleStringProperty(this,
			"name", null);

	public NamedTableModel() {
		super();
	}

	public NamedTableModel(final String name, final ID id) {
		super(id);

		setName(name);
	}

	public NamedTableModel(final T entity) {
		super(entity);

		setName(entity.getName());
	}

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
