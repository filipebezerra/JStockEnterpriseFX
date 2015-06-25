package jstockenterprisefx.groupitem;

import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.util.DialogUtils;
import jstockenterprisefx.util.TooltipUtils;

public class GroupItemController {

	@FXML
	private AnchorPane groupItemView;

	@FXML
	private TabPane mTabPane;

	@FXML
	private Tab mSearchTab;

	@FXML
	private TableView<GroupItemTableModel> mDataTable;

	@FXML
	private TableColumn<GroupItemTableModel, Short> mIdColumn;

	@FXML
	private TableColumn<GroupItemTableModel, String> mNameColumn;

	@FXML
	private TableColumn<GroupItemTableModel, GroupType> mGroupTypeColumn;

	@FXML
	private Button mEditButton;

	@FXML
	private Button mNewButton;

	@FXML
	private Button mDeleteButton;

	@FXML
	private TextField mSearchField;

	@FXML
	private Tab mRegisterTab;

	@FXML
	private TextField mIdField;

	@FXML
	private TextField mNameField;

	@FXML
	private ComboBox<GroupType> mGroupTypeField;

	@FXML
	private TextArea mObservationField;

	@FXML
	private Button mSaveButton;

	private GroupItemDao mDao = new GroupItemDao(
			JpaEntityManager.getEntityManager());

	private ObjectProperty<GroupItemTableModel> mEditingModelObject = new SimpleObjectProperty<>(
			this, "editingModelObject", null);

	private FilteredList<GroupItemTableModel> mFilterableGroupItemList;

	private ObservableList<GroupItemTableModel> mObservableGroupItemList = FXCollections
			.observableArrayList();

	@FXML
	void handleDeleteAction(final ActionEvent event) {
		Optional<ButtonType> result = DialogUtils.showConfirmation(
				"Confirmação de exclusão de grupo", null,

				"Confirma exclusão do grupo selecionado?");

		if (result.isPresent() && result.get().equals(ButtonType.YES)) {
			GroupItemTableModel seletecGroupItem = mDataTable
					.getSelectionModel().selectedItemProperty().getValue();

			JpaEntityManager.beginTransaction();
			mDao.delete(seletecGroupItem.getEntity());
			JpaEntityManager.commit();

			mObservableGroupItemList.remove(seletecGroupItem);

			DialogUtils.showInformation(null, "Exclusão de grupo",
					"O grupo selecionado foi excluído com sucesso");
		}
	}

	@FXML
	void handleEditAction(final ActionEvent event) {
		mEditingModelObject.set(mDataTable.getSelectionModel()
				.selectedItemProperty().getValue());
		mTabPane.getSelectionModel().select(mRegisterTab);
	}

	@FXML
	void handleNewAction(final ActionEvent event) {
		if (mEditingModelObject.get() != null)
			mEditingModelObject.set(null);

		mEditingModelObject.set(new GroupItemTableModel());
	}

	@FXML
	void handleSaveAction(final ActionEvent event) {
		boolean hasInvalidFields = false;

		mNameField.styleProperty().set(null);
		mGroupTypeField.styleProperty().set(null);

		if (mNameField.textProperty().isEmpty().get()) {
			mNameField.styleProperty().set(
					"-fx-border-color: red; -fx-border-width:  1.4");
			hasInvalidFields = true;
		}

		if (mGroupTypeField.getSelectionModel().getSelectedItem() == null) {
			mGroupTypeField.styleProperty().set(
					"-fx-border-color: red; -fx-border-width:  1.4");
			hasInvalidFields = true;
		}

		if (hasInvalidFields) {
			DialogUtils.showInformation("Criação de grupo", null,
					"Preencha os campos obrigatórios");
			return;
		}

		GroupItem entity = mEditingModelObject.get().getEntity();

		entity.setName(mNameField.getText());
		entity.setGroupType(mGroupTypeField.getSelectionModel()
				.getSelectedItem());
		entity.setObservation(mObservationField.getText());

		boolean isCreateEntity = mEditingModelObject.get().getEntity().getId() == null;

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
			DialogUtils.showInformation(null, "Criação de grupo",
					"O grupo foi criado com sucesso");
			GroupItemTableModel newGroupItemTableModel = new GroupItemTableModel(
					entity);
			mObservableGroupItemList.add(newGroupItemTableModel);
			mEditingModelObject.set(newGroupItemTableModel);
			mDataTable.getSelectionModel().selectLast();
		} else
			DialogUtils.showInformation(null, "Atualização de grupo",
					"O grupo foi atualizado com sucesso");
	}

	@FXML
	void handleSearchAction(final KeyEvent e) {
		mFilterableGroupItemList.setPredicate(p -> p.filter(mSearchField
				.getText()));
	}

	void handleTableSelectionChanged(final GroupItemTableModel newValue) {
		mEditButton.setDisable(newValue == null);
		mDeleteButton.setDisable(newValue == null);
	}

	void handleEditingModelObjectChanged(final GroupItemTableModel newValue) {
		mNameField.styleProperty().set(null);
		mGroupTypeField.styleProperty().set(null);

		if (newValue == null) {
			mIdField.setText(null);
			mNameField.setText(null);
			mGroupTypeField.getSelectionModel().clearSelection();
			mObservationField.setText(null);
		} else {
			if (!mTabPane.getSelectionModel().getSelectedItem()
					.equals(mRegisterTab))
				mTabPane.getSelectionModel().select(mRegisterTab);

			if (newValue.getId() != null) {
				mIdField.setText(String.valueOf(newValue.getEntity().getId()));
				mNameField.setText(newValue.getEntity().getName());
				mGroupTypeField.getSelectionModel().select(
						newValue.getEntity().getGroupType());
				mObservationField
						.setText(newValue.getEntity().getObservation());
			}

			mSaveButton.setDisable(false);
			mNameField.requestFocus();
		}
	}

	@FXML
	void initialize() {
		loadTooltips();
		bindTableColums();

		mGroupTypeField.getItems().addAll(GroupType.values());

		List<GroupItem> entityList = mDao.read();

		mFilterableGroupItemList = new FilteredList<>(mObservableGroupItemList,
				p -> true);

		entityList.forEach(e -> mObservableGroupItemList
				.add(new GroupItemTableModel(e)));

		mDataTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> handleTableSelectionChanged(newValue));
		mDataTable.setItems(mFilterableGroupItemList);
		mDataTable.getSelectionModel().selectFirst();

		mEditingModelObject
				.addListener((observable, oldValue, newValue) -> handleEditingModelObjectChanged(newValue));
	}

	void bindTableColums() {
		mIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		mNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nameProperty());
		mGroupTypeColumn.setCellValueFactory(cellData -> cellData.getValue()
				.groupTypeProperty());
	}

	void loadTooltips() {
		mSearchField.setTooltip(TooltipUtils.TOOLTIP_BUTTON_SEARCH);
		mEditButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_EDIT);
		mDeleteButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_DELETE);
		mNewButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_NEW);
		mSaveButton.setTooltip(TooltipUtils.TOOLTIP_BUTTON_SAVE);
	}

}