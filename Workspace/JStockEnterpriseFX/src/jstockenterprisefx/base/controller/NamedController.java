package jstockenterprisefx.base.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jstockenterprisefx.base.entity.NamedEntity;
import jstockenterprisefx.base.tablemodel.NamedTableModel;
import jstockenterprisefx.groupitem.GroupItemTableModel;

public abstract class NamedController<TM extends NamedTableModel<? extends NamedEntity<ID>, ? extends Serializable>, T extends NamedEntity<ID>, ID extends Serializable>
		extends BaseController<TM, T, ID> {

	@FXML
	protected TableColumn<GroupItemTableModel, String> mNameColumn;

	@FXML
	protected TextField mNameField;

	@Override
	protected void fillEntityFromFields(final T entity) {
		entity.setName(mNameField.getText());
	}

	@Override
	protected void bindTableColums() {
		super.bindTableColums();

		mNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nameProperty());
	}

	@Override
	protected void resetFieldsToEmpty() {
		super.resetFieldsToEmpty();

		mNameField.setText(null);
	}

	@Override
	protected void fillFieldsFromEntity(final TM tableModel) {
		super.fillFieldsFromEntity(tableModel);

		mNameField.setText(tableModel.getEntity().getName());
	}

	@Override
	protected List<Control> getRequiredFieldList() {
		List<Control> controlsList = new ArrayList<>();
		controlsList.add(mNameField);

		return controlsList;
	}

}
