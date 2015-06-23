package jstockenterprisefx.base.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import jstockenterprisefx.base.tablemodel.BaseTableModel;
import jstockenterprisefx.base.tablemodel.NamedTableModel;

public class Controller<T extends BaseTableModel> {

	protected static final Tooltip RESET_FIELDS_TOOLTIP = new Tooltip(
			"Resete para o valor padrão de todos os campos editáveis no cadastro!");

	protected static final Tooltip SAVE_TOOLTIP = new Tooltip(
			"Salve os dados informados no cadastro!");

	protected static final Tooltip SEARCH_TOOLTIP = new Tooltip(
			"Filtre sua pesquisa. Você pode informar somente partes do termo pesquisado!");

	protected static final Tooltip EDIT_TOOLTIP = new Tooltip(
			"Edite os dados do registro selecionado!");

	protected static final Tooltip DELETE_TOOLTIP = new Tooltip(
			"Delete o registro selecionado!");

	/*
	 * Objeto do modelo sendo editado
	 */
	protected ObjectProperty<T> mEditingModelObject = new SimpleObjectProperty<T>(
			this, "editingModelObject", null);

	protected void handleEditingModelObjectChanged(final T oldValue,
			final T newValue) {

	}

	@FXML
	protected TableView<T> mDataTable;

	@FXML
	protected TableColumn<T, Integer> mIdColumn;

	@FXML
	protected TableColumn<T, String> mNameColumn;

	@FXML
	protected TextField mIdField;

	@FXML
	protected TextField mNameField;

	@FXML
	protected TextField mSearchField;

	@FXML
	protected Button mSearchButton;

	@FXML
	protected Button mEditButton;

	@FXML
	protected Button mDeleteButton;

	@FXML
	protected Button mResetFieldsButton;

	@FXML
	protected Button mSaveButton;

	@FXML
	protected TabPane mTabPane;

	@FXML
	protected Tab mSearchTab;

	@FXML
	protected Tab mRegisterTab;

	@FXML
	protected void initialize() {
		if (mSearchButton != null)
			mSearchButton.setTooltip(SEARCH_TOOLTIP);
		if (mEditButton != null)
			mEditButton.setTooltip(EDIT_TOOLTIP);
		if (mDeleteButton != null)
			mDeleteButton.setTooltip(DELETE_TOOLTIP);
		if (mResetFieldsButton != null)
			mResetFieldsButton.setTooltip(RESET_FIELDS_TOOLTIP);
		if (mSaveButton != null)
			mSaveButton.setTooltip(SAVE_TOOLTIP);

		if (mIdColumn != null)
			mIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		if (mNameColumn != null)
			mNameColumn.setCellValueFactory(cellData -> ((NamedTableModel) cellData
					.getValue()).nameProperty());

		if (mTabPane != null)
			mTabPane.getSelectionModel()
					.selectedItemProperty()
					.addListener(
							(observable, oldTab, newTab) -> handleTabSelectionChanged(
									oldTab, newTab));

		if (mDataTable != null) {
			mDataTable
					.getSelectionModel()
					.selectedItemProperty()
					.addListener(
							(observable, oldValue, newValue) -> handleTableSelectionChanged(
									oldValue, newValue));
			mDataTable.getSelectionModel().selectFirst();
		}

		mEditingModelObject
				.addListener((observable, oldValue, newValue) -> handleEditingModelObjectChanged(
						oldValue, newValue));
	}

	@FXML
	protected void handleSearchAction() {

	}

	@FXML
	protected void handleEditAction() {
		T value = mDataTable.getSelectionModel().selectedItemProperty()
				.getValue();

		if (value == null) {

		} else {
			mEditingModelObject.set(value);
			mIdField.setText(String.valueOf(value.getId()));
			if (mNameField != null)
				mNameField.setText(((NamedTableModel) value).getName());
			mTabPane.getSelectionModel().select(mRegisterTab);
		}
	}

	@FXML
	protected void handleDeleteAction() {
		T value = mDataTable.getSelectionModel().selectedItemProperty()
				.getValue();

		if (value == null) {

		} else
			mEditingModelObject.set(null);
			mDataTable.getItems().remove(value);
	}

	@FXML
	protected void handleResetFieldsAction() {
		mEditingModelObject.set(null);
		if (mIdField != null)
			mIdField.setText(null);
	}

	@FXML
	protected void handleSaveAction() {

	}

	protected void handleTabSelectionChanged(final Tab oldTab, final Tab newTab) {
	}

	protected void handleTableSelectionChanged(final T oldValue,
			final T newValue) {
		if (mEditButton != null) mEditButton.setDisable(newValue == null);
		if (mDeleteButton != null) mDeleteButton.setDisable(newValue == null);
	}
}
