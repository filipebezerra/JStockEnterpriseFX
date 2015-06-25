package jstockenterprisefx.groupitem;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import jstockenterprisefx.base.controller.NamedController;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.JpaGenericDao;

public class GroupItemController extends
		NamedController<GroupItemTableModel, GroupItem, Short> {

	@FXML
	private TableColumn<GroupItemTableModel, GroupType> mGroupTypeColumn;

	@FXML
	private ComboBox<GroupType> mGroupTypeField;

	@FXML
	private TextArea mObservationField;

	@Override
	protected JpaGenericDao<GroupItem, Short> initializeDao() {
		return new GroupItemDao(JpaEntityManager.getEntityManager());
	}

	@Override
	protected GroupItemTableModel newTableModel() {
		return new GroupItemTableModel();
	}

	@Override
	protected GroupItemTableModel newTableModel(final GroupItem groupItem) {
		return new GroupItemTableModel(groupItem);
	}

	@Override
	protected String getEntityNameToDialogMessages() {
		return "grupo";
	}

	@Override
	protected void fillEntityFromFields(final GroupItem groupItem) {
		super.fillEntityFromFields(groupItem);

		groupItem.setGroupType(mGroupTypeField.getSelectionModel()
				.getSelectedItem());
		groupItem.setObservation(mObservationField.getText());
	}

	@Override
	protected void loadRelatedData() {
		mGroupTypeField.getItems().addAll(GroupType.values());
	}

	@Override
	protected void bindTableColums() {
		super.bindTableColums();

		mGroupTypeColumn.setCellValueFactory(cellData -> cellData.getValue()
				.groupTypeProperty());
	}

	@Override
	protected void resetFieldsToEmpty() {
		super.resetFieldsToEmpty();

		mGroupTypeField.getSelectionModel().clearSelection();
		mObservationField.setText(null);
	}

	@Override
	protected void fillFieldsFromEntity(final GroupItemTableModel tableModel) {
		super.fillFieldsFromEntity(tableModel);

		mGroupTypeField.getSelectionModel().select(
				tableModel.getEntity().getGroupType());
		mObservationField.setText(tableModel.getEntity().getObservation());
	}

	@Override
	protected List<Control> getRequiredFieldList() {
		List<Control> controlsList = super.getRequiredFieldList();

		controlsList.add(mGroupTypeField);

		return controlsList;
	}
}