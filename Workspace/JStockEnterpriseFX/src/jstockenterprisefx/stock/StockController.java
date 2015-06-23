package jstockenterprisefx.stock;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jstockenterprisefx.department.DepartamentMockData;
import jstockenterprisefx.department.DepartmentTableModel;
import jstockenterprisefx.item.ItemTableModel;
import jstockenterprisefx.item.ItemMockData;
import jstockenterprisefx.supplier.SupplierTableModel;
import jstockenterprisefx.supplier.SupplierMockData;

public class StockController {
	@FXML
	private ComboBox<OperationType> mOperationTypeField;

	@FXML
	private Label mSupplierOrDepartmentLabel;

	@FXML
	private ComboBox<SupplierTableModel> mSupplierField;

	@FXML
	private ComboBox<DepartmentTableModel> mDepartamentField;

	@FXML
	private DatePicker mRegistryDateField;

	@FXML
	private TextField mItemIdField;

	@FXML
	private TextField mItemDescriptionField;

	@FXML
	private TextField mItemQuantityField;

	@FXML
	private Button mAddItemButton;

	@FXML
	private Button mResetItemFields;

	@FXML
	private TableView<BaseStockItem> mItensDataTable;

	@FXML
	private TableColumn<BaseStockItem, Integer> mEntryIdColumn;

	@FXML
	private TableColumn<BaseStockItem, ItemTableModel> mItemColumn;

	@FXML
	private TableColumn<BaseStockItem, Integer> mItemQuantityColumn;

	@FXML
	private Button mResetFieldsButton;

	@FXML
	private Button mFinishButton;

	private ObjectProperty<ItemTableModel> mSearchedItem = new SimpleObjectProperty<ItemTableModel>(
			this, "mSearchedItem", null);

	@FXML
	private void initialize() {
		mOperationTypeField.getItems().addAll(OperationType.values());
		mSupplierField.getItems().addAll(SupplierMockData.getSupplierData());
		mDepartamentField.getItems().addAll(
				DepartamentMockData.getDepartamentData());

		mOperationTypeField.getSelectionModel().select(OperationType.ENTRY);
		mSupplierField.toFront();
		mDepartamentField.toBack();

		mOperationTypeField
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> handleOperationTypeChanged(
								oldValue, newValue));

		mSearchedItem
				.addListener((observable, oldValue, newValue) -> handleSearchedItemChanged(newValue));

		mEntryIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		mItemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
		mItemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>(
				"quantity"));
	}

	private void handleSearchedItemChanged(final ItemTableModel newValue) {
		mAddItemButton.setDisable(mSearchedItem == null);
	}

	private void handleOperationTypeChanged(final OperationType oldValue,
			final OperationType newValue) {
		mSupplierOrDepartmentLabel
				.setText(newValue.equals(OperationType.ENTRY) ? "Fornecedor"
						: "Setor");

		if (newValue.equals(OperationType.ENTRY)) {
			mSupplierField.toFront();
			mDepartamentField.toBack();
		} else {
			mDepartamentField.toFront();
			mSupplierField.toBack();
		}
	}

	@FXML
	private void handleItemSearchById() {
		if (mItemIdField.getText() != null) {
			final ObservableList<ItemTableModel> itemData = ItemMockData.getItemData();
			FilteredList<ItemTableModel> filtered = itemData
					.filtered(p -> p.getId() == Integer.valueOf(mItemIdField
							.getText()));

			if (filtered.size() == 0) {
				mItemIdField.selectAll();
				Alert alert = new Alert(AlertType.INFORMATION,
						"Nenhum item foi localizado", ButtonType.OK);
				alert.setHeaderText("Pesquisa por item pelo Id...");
				alert.show();
			} else {
				mSearchedItem = new SimpleObjectProperty<ItemTableModel>(filtered.get(0));
				mItemDescriptionField.setText(mSearchedItem.get()
						.getDescription());
				mItemQuantityField.requestFocus();
				mAddItemButton.setDisable(false);
			}
		}
	}

	@FXML
	private void handleAddItem() {
		if (mItensDataTable.getItems().add(
				new BaseStockItem(mSearchedItem.get(), Integer
						.valueOf(mItemQuantityField.getText())))) {
			handleResetItemFields();
			mAddItemButton.setDisable(true);
		}
	}

	@FXML
	private void handleResetItemFields() {
		mItemIdField.clear();
		mItemDescriptionField.clear();
		mItemQuantityField.clear();
		mItemIdField.requestFocus();
	}

	@FXML
	private void handleResetAllFields() {
		mOperationTypeField.getSelectionModel().select(OperationType.ENTRY);
		mRegistryDateField.getEditor().clear();

		mItemIdField.clear();
		mItemDescriptionField.clear();
		mItemQuantityField.clear();
	}

}
