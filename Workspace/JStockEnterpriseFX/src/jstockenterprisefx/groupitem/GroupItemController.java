package jstockenterprisefx.groupitem;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import jstockenterprisefx.base.controller.Controller;

public class GroupItemController extends Controller<GroupItemTableModel> {
	@FXML
	private TableColumn<GroupItemTableModel, GroupType> mGroupTypeColumn;

	@FXML
	private ComboBox<GroupType> mGroupTypeField;

	@FXML
	private TextArea mObservationField;
	
	@Override
	protected void initialize() {
		mDataTable.getItems().addAll(GroupItemMockData.getGroupData());

		super.initialize();

		mGroupTypeColumn.setCellValueFactory(cellData -> cellData.getValue()
				.groupTypeProperty());
		
		mGroupTypeField.getItems().addAll(GroupType.values());

		// mSearchField.setOnInputMethodTextChanged(value);
	}
	
	@Override
	protected void handleEditAction() {
		super.handleEditAction();
		
		final GroupItemTableModel group = mEditingModelObject.get();
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
