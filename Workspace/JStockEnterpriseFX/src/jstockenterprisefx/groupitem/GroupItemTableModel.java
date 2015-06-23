package jstockenterprisefx.groupitem;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;

public class GroupItemTableModel extends NamedTableModel {
	private ObjectProperty<GroupType> groupType = new SimpleObjectProperty<GroupType>(
			this, "groupType", null);
	private StringProperty observation = new SimpleStringProperty(this,
			"observation", null);;

	public GroupItemTableModel() {
		this(null, null, null);
	}

	public GroupItemTableModel(final String name, final GroupType groupType,
			final String observation) {
		this.name.set(name);
		this.groupType.set(groupType);
		this.observation.set(observation);
	}

	public GroupType getGroupType() {
		return groupType.get();
	}

	public void setGroupType(final GroupType groupType) {
		this.groupType.set(groupType);
	}

	public ObjectProperty<GroupType> groupTypeProperty() {
		return groupType;
	}

	public String getObservation() {
		return observation.get();
	}

	public void setObservation(final String observation) {
		this.observation.set(observation);
	}

	public StringProperty observationProperty() {
		return observation;
	}

	@Override
	public String toString() {
		return getName();
	}
}
