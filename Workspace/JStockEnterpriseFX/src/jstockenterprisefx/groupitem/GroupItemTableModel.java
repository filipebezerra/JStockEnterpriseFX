package jstockenterprisefx.groupitem;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.tablemodel.NamedTableModel;

public class GroupItemTableModel extends NamedTableModel<GroupItem, Short> {

	private final ObjectProperty<GroupType> groupType = new SimpleObjectProperty<GroupType>(
			this, "groupType", null);

	private final StringProperty observation = new SimpleStringProperty(this,
			"observation", null);

	public GroupItemTableModel() {
		super(new GroupItem());
	}

	public GroupItemTableModel(final GroupItem groupItem) {
		super(groupItem);

		setGroupType(groupItem.getGroupType());
		setObservation(groupItem.getObservation());
	}

	public GroupItemTableModel(final String name, final GroupType groupType,
			final String observation) {
		setName(name);
		setGroupType(groupType);
		setObservation(observation);
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

	@Override
	public boolean filter(final String filterText) {
		return getName().toUpperCase().contains(filterText.toUpperCase());
	}

}
