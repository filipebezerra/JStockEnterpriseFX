package jstockenterprisefx.department;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jstockenterprisefx.base.controller.NamedController;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.JpaGenericDao;

public class DepartmentController extends
		NamedController<DepartmentTableModel, Department, Short> {

	@FXML
	private TableColumn<DepartmentTableModel, String> mPersonResponsibleColumn;

	@FXML
	private TextField mPersonResponsibleField;

	@Override
	protected JpaGenericDao<Department, Short> initializeDao() {
		return new DepartmentDao(JpaEntityManager.getEntityManager());
	}

	@Override
	protected DepartmentTableModel newTableModel() {
		return new DepartmentTableModel();
	}

	@Override
	protected DepartmentTableModel newTableModel(final Department department) {
		return new DepartmentTableModel(department);
	}

	@Override
	protected String getEntityNameToDialogMessages() {
		return "departamento";
	}

	@Override
	protected void fillEntityFromFields(final Department department) {
		super.fillEntityFromFields(department);

		department.setPersonResponsible(mPersonResponsibleField.getText());
	}

	@Override
	protected void loadRelatedData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void bindTableColums() {
		super.bindTableColums();

		mPersonResponsibleColumn.setCellValueFactory(cellData -> cellData
				.getValue().personResponsibleProperty());
	}

	@Override
	protected void resetFieldsToEmpty() {
		super.resetFieldsToEmpty();

		mPersonResponsibleField.setText(null);
	}

	@Override
	protected void fillFieldsFromEntity(final DepartmentTableModel tableModel) {
		super.fillFieldsFromEntity(tableModel);

		mPersonResponsibleField.setText(tableModel.getEntity()
				.getPersonResponsible());
	}

	@Override
	protected List<Control> getRequiredFieldList() {
		List<Control> controlsList = super.getRequiredFieldList();

		controlsList.add(mPersonResponsibleField);

		return controlsList;
	}

}
