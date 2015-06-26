package jstockenterprisefx.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.exceptions.BusinessConstraintViolation;
import jstockenterprisefx.base.jpa.exceptions.NonExistentEntityException;
import jstockenterprisefx.department.Department;
import jstockenterprisefx.department.DepartmentDao;
import jstockenterprisefx.item.Item;
import jstockenterprisefx.item.ItemDao;
import jstockenterprisefx.stock.issue.StockIssue;
import jstockenterprisefx.stock.issue.StockIssueDao;
import jstockenterprisefx.stock.issue.StockIssueItem;
import jstockenterprisefx.stock.receipt.StockReceipt;
import jstockenterprisefx.stock.receipt.StockReceiptDao;
import jstockenterprisefx.stock.receipt.StockReceiptItem;
import jstockenterprisefx.supplier.Supplier;
import jstockenterprisefx.supplier.SupplierDao;
import jstockenterprisefx.util.DialogUtils;

public class StockController {

	@FXML
	private Label mSupplierOrDepartmentLabel;

	@FXML
	private DatePicker mReceiptDateField;

	@FXML
	private ComboBox<Supplier> mSupplierField;

	@FXML
	private ComboBox<OperationType> mOperationTypeField;

	@FXML
	private ComboBox<Department> mDepartamentField;

	@FXML
	private TextField mItemIdField;

	@FXML
	private TextField mItemQuantityField;

	@FXML
	private TextField mItemNameField;

	@FXML
	private Button mAddItemButton;

	@FXML
	private Button mResetItemFields;

	@FXML
	private TableView<StockItemTableModel> mItemsDataTable;

	@FXML
	private TableColumn<StockItemTableModel, Long> mItemIdColumn;

	@FXML
	private TableColumn<StockItemTableModel, String> mItemNameColumn;

	@FXML
	private TableColumn<StockItemTableModel, Integer> mItemQuantityColumn;

	@FXML
	private Button mRemoveItemButton;

	@FXML
	private Button mFinishButton;

	@FXML
	private Button mNewButton;

	@FXML
	private GridPane mStockOperationPane;

	private ItemDao mItemDao;

	private StockReceiptDao mStockReceiptDao;

	private StockIssueDao mStockIssueDao;

	// TODO usar uma propriedade com binding para escutar mudanças e remover
	// estilos dos campos requeridos
	private List<Control> mRequiredFieldList = new ArrayList<>();

	private List<Control> mStockItemRequiredFieldList = new ArrayList<>();

	private ObjectProperty<Item> mSearchedItem = new SimpleObjectProperty<>(
			this, "mSearchedItem", null);

	@FXML
	void initialize() {
		mOperationTypeField.getItems().addAll(OperationType.values());

		SupplierDao supplierDao = new SupplierDao(
				JpaEntityManager.getEntityManager());
		List<Supplier> supplierList = supplierDao.read();

		mSupplierField.getItems().addAll(supplierList);

		DepartmentDao departmentDao = new DepartmentDao(
				JpaEntityManager.getEntityManager());
		List<Department> departmentList = departmentDao.read();

		mDepartamentField.getItems().addAll(departmentList);

		mOperationTypeField
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> handleOperationTypeChanged(
								oldValue, newValue));

		mItemIdField.textProperty()
				.addListener(
						(ChangeListener<String>) (observable, oldValue,
								newValue) -> {
							if (!newValue.matches("\\d*"))
								mItemIdField.setText(newValue.replaceAll(
										"[^\\d]", ""));
						});

		mItemQuantityField.textProperty().addListener(
				(ChangeListener<String>) (observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*"))
						mItemQuantityField.setText(newValue.replaceAll(
								"[^\\d]", ""));
				});

		mItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		mItemNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.itemNameProperty());
		mItemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>(
				"itemQuantity"));

		mItemsDataTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> handleTableSelectionChanged(newValue));

		mSearchedItem
				.addListener((observable, oldValue, newValue) -> handleSearchedItemChanged(newValue));

		loadTooltips();
		initializeControls();
	}

	private void handleSearchedItemChanged(final Item newValue) {
		if (mSearchedItem.get() == null) {
			mItemIdField.clear();
			mItemNameField.clear();
			mItemQuantityField.clear();
		} else {

		}
	}

	private void handleTableSelectionChanged(final StockItemTableModel newValue) {
		mRemoveItemButton.setDisable(newValue == null);
	}

	private List<Control> getRequiredFieldList() {
		List<Control> controlsList = new ArrayList<>();

		if (mOperationTypeField.getSelectionModel().getSelectedItem()
				.equals(OperationType.ENTRY))
			controlsList.add(mSupplierField);
		else
			controlsList.add(mDepartamentField);

		controlsList.add(mReceiptDateField);
		controlsList.add(mItemsDataTable);

		return controlsList;
	}

	private List<Control> getStockItemRequiredFieldList() {
		List<Control> controlsList = new ArrayList<>();
		controlsList.add(mItemNameField);
		controlsList.add(mItemQuantityField);

		return controlsList;
	}

	private void handleOperationTypeChanged(final OperationType oldValue,
			final OperationType newValue) {
		mSupplierOrDepartmentLabel
				.setText(newValue.equals(OperationType.ENTRY) ? "Fornecedor"
						: "Setor");

		if (newValue.equals(OperationType.ENTRY)) {
			mSupplierField.toFront();
			mRequiredFieldList.add(mSupplierField);
			mDepartamentField.toBack();
			mDepartamentField.styleProperty().set(null);
			mRequiredFieldList.remove(mDepartamentField);
		} else {
			mDepartamentField.toFront();
			mRequiredFieldList.add(mDepartamentField);
			mSupplierField.toBack();
			mSupplierField.styleProperty().set(null);
			mRequiredFieldList.remove(mSupplierField);
		}
	}

	@FXML
	void handleAddItem(final ActionEvent event) {
		AtomicBoolean hasInvalidFields = new AtomicBoolean(false);

		mStockItemRequiredFieldList
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
			DialogUtils.showInformation("Inclusão de item na lista", null,
					"Preencha os campos obrigatórios");
			return;
		}

		AtomicBoolean isItemDuplicated = new AtomicBoolean(false);

		mItemsDataTable.getItems().forEach(
				i -> {
					isItemDuplicated.set(i.equals(new StockItemTableModel(
							mSearchedItem.get(), 0)));
				});

		if (isItemDuplicated.get()) {
			DialogUtils.showWarning("Inclusão de item na lista", null,
					"Este item já está incluído na lista!");
			mItemIdField.requestFocus();
			mItemIdField.selectAll();
			return;
		}

		final int itemQuantity = Integer.valueOf(mItemQuantityField.getText());
		final Item itemSearched = mSearchedItem.get();

		if (mOperationTypeField.getSelectionModel().getSelectedItem()
				.equals(OperationType.OUT))
			if (itemSearched.getStockQuantity() < itemQuantity) {
				DialogUtils
						.showWarning(
								"Inclusão de item na lista",
								null,
								String.format(
										"A quantidade está acima do disponível no estoque. Este item tem %d unidade(s) disponível(is)!",
										itemSearched.getStockQuantity()));
				mItemQuantityField.requestFocus();
				mItemQuantityField.selectAll();
				return;
			}

		if (mItemsDataTable.getItems().add(
				new StockItemTableModel(itemSearched, itemQuantity)))
			mSearchedItem.set(null);
	}

	@FXML
	void handleFinishButton(final ActionEvent event) {
		AtomicBoolean hasInvalidFields = new AtomicBoolean(false);

		mRequiredFieldList
				.forEach(f -> {
					f.setStyle(null);

					if (f instanceof TableView<?>) {
						final TableView<?> textControl = (TableView<?>) f;

						if (textControl.itemsProperty().get().isEmpty()) {
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
			DialogUtils.showInformation("Conclusão do movimento no estoque",
					null, "Preencha os campos obrigatórios");
			return;
		}

		final OperationType operationType = mOperationTypeField
				.getSelectionModel().getSelectedItem();

		if (operationType.equals(OperationType.ENTRY)) {
			StockReceipt newStockReceipt = new StockReceipt(
					mReceiptDateField.getValue(), mSupplierField
							.getSelectionModel().getSelectedItem());

			mItemsDataTable.getItems().forEach(
					i -> newStockReceipt
							.addStockReceiptItem(new StockReceiptItem(i
									.getItemQuantity().shortValue(), i
									.getEntity())));

			mStockReceiptDao = new StockReceiptDao(
					JpaEntityManager.getEntityManager());

			JpaEntityManager.beginTransaction();
			mStockReceiptDao.create(newStockReceipt);
			JpaEntityManager.commit();
		} else {
			StockIssue newStockIssue = new StockIssue(
					mReceiptDateField.getValue(), mDepartamentField
							.getSelectionModel().getSelectedItem());

			mItemsDataTable.getItems().forEach(
					i -> newStockIssue.addStockIssueItem(new StockIssueItem(i
							.getItemQuantity().shortValue(), i.getEntity())));

			mStockIssueDao = new StockIssueDao(
					JpaEntityManager.getEntityManager());

			JpaEntityManager.beginTransaction();
			mStockIssueDao.create(newStockIssue);
			JpaEntityManager.commit();
		}

		mItemsDataTable.getItems().forEach(
				i -> {
					final Item item = i.getEntity();
					final Integer quantity = i.getItemQuantity();

					try {
						JpaEntityManager.beginTransaction();
						((StockQuantityUpdatable) mItemDao)
								.updateStockQuantity(item.getId(), quantity,
										operationType);
						JpaEntityManager.commit();
					} catch (NonExistentEntityException e) {
						// TODO: handle exception
						e.printStackTrace();
					} catch (BusinessConstraintViolation e) {
						// TODO: handle exception
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		mStockOperationPane.setDisable(true);
		DialogUtils.showInformation("Conclusão do movimento no estoque", null,
				"Movimentação do estoque concluída com sucesso!");
	}

	@FXML
	void handleItemSearchById(final ActionEvent event) {
		if (mItemIdField.getText() != null) {
			if (mItemDao == null)
				mItemDao = new ItemDao(JpaEntityManager.getEntityManager());

			Item item = mItemDao.read(Long.valueOf(mItemIdField.getText()));

			if (item == null) {
				mItemIdField.requestFocus();
				mItemIdField.selectAll();
				DialogUtils.showInformation("Pesquisa por item pelo Id...",
						null, "Nenhum item foi localizado");
			} else {
				mSearchedItem.set(item);
				mItemNameField.setText(item.getName());
				mItemQuantityField.requestFocus();
			}
		}
	}

	@FXML
	void handleNewButton(final ActionEvent event) {
		initializeControls();
	}

	void initializeControls() {
		mOperationTypeField.getSelectionModel().select(OperationType.ENTRY);
		mReceiptDateField.getEditor().clear();

		mSearchedItem.set(null);

		mItemsDataTable.getItems().clear();

		mStockOperationPane.setDisable(false);

		mRequiredFieldList = getRequiredFieldList();

		mStockItemRequiredFieldList = getStockItemRequiredFieldList();
	}

	@FXML
	void handleRemoveItemButton(final ActionEvent event) {
		mItemsDataTable.getItems().remove(
				mItemsDataTable.getSelectionModel().getSelectedItem());
	}

	@FXML
	void handleResetItemFields(final ActionEvent event) {
		mSearchedItem.set(null);
	}

	private void loadTooltips() {
		mItemIdField.setTooltip(new Tooltip(
				"Digite o código do item e pressione ENTER para pesquisar!"));
		mItemQuantityField.setTooltip(new Tooltip(
				"Digite a quantidade de itens à movimentar!"));
		mAddItemButton.setTooltip(new Tooltip(
				"Inclua o item pesquisado na lista!"));
		mResetItemFields.setTooltip(new Tooltip(
				"Limpe os valores digitados nos campos de pesquisa do item!"));
		mRemoveItemButton.setTooltip(new Tooltip(
				"Remova o item selecionado na lista!"));
		mFinishButton.setTooltip(new Tooltip(
				"Conclua e efetive o movimento no estoque!"));
		mNewButton.setTooltip(new Tooltip(
				"Inicie uma nova movimentação no estoque!"));
	}
}
