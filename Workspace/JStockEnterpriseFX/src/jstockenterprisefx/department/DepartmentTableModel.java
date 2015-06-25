package jstockenterprisefx.department;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;

public class DepartmentTableModel extends NamedTableModel<Department, Short> {

	private final StringProperty personResponsible = new SimpleStringProperty(
			this, "personResponsible", null);

	public DepartmentTableModel() {
		super(new Department());
	}

	public DepartmentTableModel(final Department department) {
		super(department);

		setPersonResponsible(department.getPersonResponsible());
	}

	public DepartmentTableModel(final String name,
			final String personResponsible) {
		super(name);

		setPersonResponsible(personResponsible);
	}

	public String getPersonResponsible() {
		return personResponsible.get();
	}

	public void setPersonResponsible(final String personResponsible) {
		this.personResponsible.set(personResponsible);
	}

	public StringProperty personResponsibleProperty() {
		return personResponsible;
	}

	@Override
	public String toString() {
		return getName();
	}

}
