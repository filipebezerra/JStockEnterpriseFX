package jstockenterprisefx.item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jstockenterprisefx.base.controller.Controller;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupItemTableModel;
import jstockenterprisefx.util.DateUtil;

public class ItemController extends Controller<ItemTableModel> {
	@FXML
	private TableColumn<ItemTableModel, String> mDescriptionColumn;

	@FXML
	private TableColumn<ItemTableModel, Integer> mStockQuantityColumn;

	@FXML
	private TableColumn<ItemTableModel, LocalDateTime> mLastStockUpdateColumn;

	@FXML
	private ComboBox<ItemSearchOption> mSearchOptionsField;

	@FXML
	private TextField mDescriptionField;

	@FXML
	private TextField mCostPriceField;

	@FXML
	private TextField mSalePriceField;

	@FXML
	private TextField mStockQuantityField;

	@FXML
	private TextField mLastStockUpdateField;

	@FXML
	private DatePicker mCreatedAtField;

	@FXML
	private ComboBox<GroupItemTableModel> mGroupItemField;

	private ItemDao dao = new ItemDao(JpaEntityManager.getEntityManager());

	@Override
	protected void initialize() {
		// mDataTable.getItems().addAll(ItemMockData.getItemData());

		super.initialize();

		mDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue()
				.descriptionProperty());

		mStockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>(
				"stockQuantity"));

		mLastStockUpdateColumn.setCellValueFactory(cellData -> cellData
				.getValue().lastStockUpdateProperty());

		mSearchOptionsField.getItems().addAll(ItemSearchOption.values());
		// mGroupField.getItems().addAll(GroupItemMockData.getGroupData());
		mGroupItemField.getItems().addAll(getGroupItemsList());
	}

	private ObservableList<GroupItemTableModel> getGroupItemsList() {
		ObservableList<GroupItemTableModel> groupItemsList = FXCollections
				.emptyObservableList();

		GroupItemDao groupItemDao = new GroupItemDao(
				JpaEntityManager.getEntityManager());

		List<GroupItem> list = groupItemDao.read();

		list.forEach(item -> {
			groupItemsList.add(new GroupItemTableModel(item.getName(), item
					.getGroupType(), item.getObservation()));
		});

		return groupItemsList;
	}

	@Override
	protected void handleResetFieldsAction(final ActionEvent event) {
		super.handleResetFieldsAction(event);
		mCreatedAtField.getEditor().setText(null);
		mDescriptionField.setText(null);
		mGroupItemField.getSelectionModel().clearSelection();
		mCostPriceField.setText(null);
		mSalePriceField.setText(null);
		mStockQuantityField.setText(null);
		mLastStockUpdateField.setText(null);
	}

	@Override
	protected void handleSaveAction(final ActionEvent event) {
		super.handleSaveAction(event);

		GroupItemTableModel groupItemModel = mGroupItemField
				.selectionModelProperty().get().getSelectedItem();

		GroupItem groupItem = new GroupItem(groupItemModel.getId(),
				groupItemModel.getGroupType(), groupItemModel.getName());

		Item newItem = new Item(mDescriptionField.getText(), new BigDecimal(
				mCostPriceField.getText()), new BigDecimal(
				mSalePriceField.getText()), Integer.valueOf(mStockQuantityField
				.getText()), groupItem);

		dao.create(newItem);

		mIdField.setText(String.valueOf(newItem.getId()));

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Novo registro de Item");
		alert.setTitle("Cadastro realizado com sucesso!");
		alert.setContentText("Seu novo registro foi incluído com sucesso.");
	}

	@Override
	protected void handleSearchAction(final ActionEvent event) {
		super.handleEditAction(event);

		final ItemTableModel item = mEditingModelObject.get();
		mCreatedAtField.setValue(item.getCreatedAt().toLocalDate());
		mDescriptionField.setText(item.getDescription());
		mGroupItemField.getSelectionModel().select(item.getGroup());
		mCostPriceField.setText(String.valueOf(item.getCostPrice()));
		mSalePriceField.setText(String.valueOf(item.getSalePrice()));
		mStockQuantityField.setText(String.valueOf(item.getStockQuantity()));
		mLastStockUpdateField
				.setText(DateUtil.format(item.getLastStockUpdate()));
	}
}
