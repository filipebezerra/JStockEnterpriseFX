package jstockenterprisefx.group;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import jstockenterprisefx.base.controller.Controller;

public class GroupController extends Controller<Group> {
	@FXML
	private TableColumn<Group, GroupType> mGroupTypeColumn;

	@FXML
	private ComboBox<GroupType> mGroupTypeField;

	@FXML
	private TextArea mObservationField;
	
	@Override
	protected void initialize() {
		mDataTable.getItems().addAll(GroupMockData.getGroupData());

		super.initialize();

		mNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nameProperty());
		mGroupTypeColumn.setCellValueFactory(cellData -> cellData.getValue()
				.groupTypeProperty());
		
		mGroupTypeField.getItems().addAll(GroupType.values());

		// mSearchField.setOnInputMethodTextChanged(value);
	}

	@Override
	protected void handleTableSelectionChanged(final Group oldValue,
			final Group newValue) {
		super.handleTableSelectionChanged(oldValue, newValue);
	}
	
	@Override
	protected void handleEditAction() {
		super.handleEditAction();
		
		final Group group = mEditingModelObject.get();
		mNameField.setText(group.getName());
		mGroupTypeField.getSelectionModel().select(group.getGroupType());
		mObservationField.setText(group.getObservation());
	}

	@Override
	protected void handleResetFieldsAction() {
		super.handleResetFieldsAction();
		mNameField.setText(null);
		mGroupTypeField.getSelectionModel().clearSelection();
		mObservationField.setText(null);
	}
}
