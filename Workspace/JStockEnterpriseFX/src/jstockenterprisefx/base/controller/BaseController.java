package jstockenterprisefx.base.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import jstockenterprisefx.base.entity.BaseEntity;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.JpaGenericDao;
import jstockenterprisefx.base.tablemodel.BaseTableModel;
import jstockenterprisefx.util.DialogUtils;
import jstockenterprisefx.util.TooltipUtils;

public abstract class BaseController<TM extends BaseTableModel<? extends BaseEntity<ID>, ? extends Serializable>, T extends BaseEntity<ID>, ID extends Serializable> {

	@FXML
	protected TabPane mTabPane;

	@FXML
	protected Tab mSearchTab;

	@FXML
	protected TableView<TM> mDataTable;

	@FXML
	protected TableColumn<TM, ID> mIdColumn;

	@FXML
	protected Button mEditButton;

	@FXML
	protected Button mNewButton;

	@FXML
	protected Button mDeleteButton;

	@FXML
	protected TextField mSearchField;

	@FXML
	protected Tab mRegisterTab;

	@FXML
	protected TextField mIdField;

	@FXML
	protected Button mSaveButton;

	protected JpaGenericDao<T, ID> mDao;

	protected ObjectProperty<TM> mEditingModelObject = new SimpleObjectProperty<>(
			this, "editingModelObject", null);

	protected FilteredList<TM> mFilterableModelList;

	protected ObservableList<TM> mObservableModelList = FXCollections
			.observableArrayList();

	protected List<Control> mRequiredFieldList = new ArrayList<>();

	protected Control mDefaultFocusField;

	@SuppressWarnings("unchecked")
	@FXML
	protected void handleDeleteAction(final ActionEvent event) {
		Optional<ButtonType> result = DialogUtils.showConfirmation(String
				.format("Confirma��o de exclus�o de %s",
						getEntityNameToDialogMessages()), null, String.format(
				"Confirma exclus�o do %s selecionado?",
				getEntityNameToDialogMessages()));

		if (result.isPresent() && result.get().equals(ButtonType.YES)) {
			TM seletecModelItem = mDataTable.getSelectionModel()
					.selectedItemProperty().getValue();

			if (mEditingModelObject.get() != null)
				if (mEditingModelObject.get().equals(seletecModelItem)) {
					mEditingModelObject.set(null);
					mSaveButton.setDisable(true);
				}

			JpaEntityManager.beginTransaction();
			mDao.delete((T) seletecModelItem.getEntity());
			JpaEntityManager.commit();

			mObservableModelList.remove(seletecModelItem);

			DialogUtils.showInformation(null, String.format("Exclus�o de %s",
					getEntityNameToDialogMessages()), String.format(
					"O %s selecionado foi exclu�do com sucesso",
					getEntityNameToDialogMessages()));
		}
	}

	@FXML
	protected void handleEditAction(final ActionEvent event) {
		mEditingModelObject.set(mDataTable.getSelectionModel()
				.selectedItemProperty().getValue());
		mTabPane.getSelectionModel().select(mRegisterTab);
	}

	@FXML
	protected void handleNewAction(final ActionEvent event) {
		if (mEditingModelObject.get() != null)
			mEditingModelObject.set(null);

		mEditingModelObject.set(newTableModel());
	}

	@FXML
	protected void handleSaveAction(final ActionEvent event) {
		AtomicBoolean hasInvalidFields = new AtomicBoolean(false);

		mRequiredFieldList
				.forEach(f -> {
					f.setStyle(null);

					if (f instanceof TextInputControl) {
						final TextInputControl textControl = (TextInputControl) f;

						if (textControl.textProperty().isEmpty().get()) {
							textControl
									.setStyle("-fx-border-color: red; -fx-border-width:  1.4");
							hasInvalidFields.set(true);
						}
					} else if (f instanceof ComboBox) {
						final ComboBox<?> comboControl = (ComboBox<?>) f;

						if (comboControl.getSelectionModel().getSelectedItem() == null) {
							comboControl
									.setStyle("-fx-border-color: red; -fx-border-width:  1.4");
							hasInvalidFields.set(true);
						}
					} else if (f instanceof DatePicker) {
						final DatePicker dateControl = (DatePicker) f;

						if (dateControl.getEditor().textProperty().isEmpty()
								.get()) {
							dateControl
									.setStyle("-fx-border-color: red; -fx-border-width:  1.4");
							hasInvalidFields.set(true);
						}
					}
				});

		if (hasInvalidFields.get()) {
			DialogUtils.showInformation(String.format("Cria��o de %s",
					getEntityNameToDialogMessages()), null,
					"Preencha os campos obrigat�rios");
			return;
		}

		T entity = (T) mEditingModelObject.get().getEntity();

		fillEntityFromFields(entity);

		boolean isCreateEntity = entity.getId() == null;

		JpaEntityManager.beginTransaction();
		if (isCreateEntity)
			try {
				mDao.create(entity);
			} catch (Exception e) {
				JpaEntityManager.rollback();
				e.printStackTrace();
				return;
			}
		else
			mDao.update(entity);
		JpaEntityManager.commit();

		if (isCreateEntity) {
			DialogUtils.showInformation(null, String.format("Cria��o de %s",
					getEntityNameToDialogMessages()), String.format(
					"O %s foi criado com sucesso",
					getEntityNameToDialogMessages()));

			TM newTableModel = newTableModel(entity);

			mObservableModelList.add(newTableModel);

			mEditingModelObject.set(newTableModel);

			mDataTable.getSelectionModel().selectLast();
		} else
			DialogUtils.showInformation(null, String.format(
					"Atualiza��o de %s", getEntityNameToDialogMessages()),
					String.format("O %s foi atualizado com sucesso",
							getEntityNameToDialogMessages()));
	}

	@FXML
	protected void handleSearchAction(final KeyEvent e) {
		mFilterableModelList
				.setPredicate(p -> p.filter(mSearchField.getText()));
	}

	protected void handleTableSelectionChanged(final TM newValue) {
		mEditButton.setDisable(newValue == null);
		mDeleteButton.setDisable(newValue == null);
	}

	protected void handleEditingModelObjectChanged(final TM newValue) {
		mRequiredFieldList.forEach(f -> {
			f.setStyle(null);
		});

		if (newValue == null)
			resetFieldsToEmpty();
		else {
			if (!mTabPane.getSelectionModel().getSelectedItem()
					.equals(mRegisterTab))
				mTabPane.getSelectionModel().select(mRegisterTab);

			if (newValue.getId() != null)
				fillFieldsFromEntity(newValue);

			mSaveButton.setDisable(false);
			mDefaultFocusField.requestFocus();
		}
	}

	@FXML
	protected void initialize() {
		loadTooltips();
		bindTableColums();
		mDao = initializeDao();

		List<T> entityList = mDao.read();

		mFilterableModelList = new FilteredList<>(mObservableModelList,
				p -> true);

		entityList.forEach(e -> mObservableModelList.add(newTableModel(e)));

		mDataTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> handleTableSelectionChanged(newValue));
		mDataTable.setItems(mFilterableModelList);

		mDataTable.getSelectionModel().selectFirst();

		loadRelatedData();

		mEditingModelObject
				.addListener((observable, oldValue, newValue) -> handleEditingModelObjectChanged(newValue));

		mDefaultFocusField = getDefaultFocusField();

		mRequiredFieldList.addAll(getRequiredFieldList());
	}

	protected void bindTableColums() {
		mIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	}

	public void loadTooltips() {
		mSearchField.setTooltip(TooltipUtils.TOOLTIP_BUTTON_SEARCH);
		mEditButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_EDIT);
		mDeleteButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_DELETE);
		mNewButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_NEW);
		mSaveButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_SAVE);
	}

	protected void resetFieldsToEmpty() {
		mIdField.setText(null);
	}

	protected void fillFieldsFromEntity(final TM tableModel) {
		mIdField.setText(String.valueOf(tableModel.getEntity().getId()));
	}

	protected abstract JpaGenericDao<T, ID> initializeDao();

	protected abstract TM newTableModel();

	protected abstract TM newTableModel(T entity);

	protected abstract String getEntityNameToDialogMessages();

	protected abstract void fillEntityFromFields(T entity);

	protected abstract Control getDefaultFocusField();

	protected abstract List<Control> getRequiredFieldList();

	protected abstract void loadRelatedData();

}
