package jstockenterprisefx.item;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jstockenterprisefx.base.controller.Controller;
import jstockenterprisefx.groupitem.GroupItemTableModel;
import jstockenterprisefx.groupitem.GroupItemMockData;
import jstockenterprisefx.util.DateUtil;

public class ItemController extends Controller<ItemTableModel> {
	@FXML
	private TableColumn<ItemTableModel, String> mDescriptionColumn;

	@FXML
	private TableColumn<ItemTableModel, Integer> mStockQuantityColumn;

	@FXML
	private TableColumn<ItemTableModel, LocalDate> mLastStockUpdateColumn;
	
	@FXML
	private ComboBox<ItemSearchOption> mSearchOptionsField;

	@FXML
	private DatePicker mCreatedAtField;

	@FXML
	private TextField mDescriptionField;

	@FXML
	private ComboBox<GroupItemTableModel> mGroupField;

	@FXML
	private TextField mCostPriceField;

	@FXML
	private TextField mSalePriceField;

	@FXML
	private TextField mStockQuantityField;

	@FXML
	private TextField mLastStockUpdateField;

	@Override
	protected void initialize() {
		mDataTable.getItems().addAll(ItemMockData.getItemData());

		super.initialize();

		mDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue()
				.descriptionProperty());
		
		mStockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>(
				"stockQuantity"));
		
		mLastStockUpdateColumn.setCellValueFactory(cellData -> cellData
				.getValue().lastStockUpdateProperty());

		mSearchOptionsField.getItems().addAll(ItemSearchOption.values());
		mGroupField.getItems().addAll(GroupItemMockData.getGroupData());
	}

	@Override
	protected void handleEditAction() {
		super.handleEditAction();

		final ItemTableModel item = mEditingModelObject.get();
		mCreatedAtField.setValue(item.getCreatedAt());
		mDescriptionField.setText(item.getDescription());
		mGroupField.getSelectionModel().select(item.getGroup());
		mCostPriceField.setText(String.valueOf(item.getCostPrice()));
		mSalePriceField.setText(String.valueOf(item.getSalePrice()));
		mStockQuantityField.setText(String.valueOf(item.getStockQuantity()));
		mLastStockUpdateField
				.setText(DateUtil.format(item.getLastStockUpdate()));
	}

	@Override
	protected void handleResetFieldsAction() {
		super.handleResetFieldsAction();
		mCreatedAtField.getEditor().setText(null);
		mDescriptionField.setText(null);
		mGroupField.getSelectionModel().clearSelection();
		mCostPriceField.setText(null);
		mSalePriceField.setText(null);
		mStockQuantityField.setText(null);
		mLastStockUpdateField.setText(null);
	}

	@Override
	protected void handleTabSelectionChanged(final Tab oldTab, final Tab newTab) {
		super.handleTabSelectionChanged(oldTab, newTab);

	}
}
