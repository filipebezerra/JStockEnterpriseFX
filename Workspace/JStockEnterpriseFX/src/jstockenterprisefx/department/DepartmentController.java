package jstockenterprisefx.department;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jstockenterprisefx.base.controller.Controller;

public class DepartmentController extends Controller<DepartmentTableModel> {
	@FXML
	private TableColumn<DepartmentTableModel, String> mResponsableColumn;

	@FXML
	private TextField mResponsableField;

	@Override
	protected void initialize() {
		mDataTable.getItems().addAll(DepartamentMockData.getDepartamentData());

		super.initialize();

		mResponsableColumn.setCellValueFactory(cellData -> cellData.getValue()
				.responsableProperty());
	}

	@Override
	protected void handleEditAction() {
		super.handleEditAction();

		final DepartmentTableModel department = mEditingModelObject.get();
		mNameField.setText(department.getName());
		mResponsableField.setText(department.getResponsable());
	}

	@Override
	protected void handleResetFieldsAction() {
		super.handleResetFieldsAction();
		mNameField.setText(null);
		mResponsableField.setText(null);
	}
}
