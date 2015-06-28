package jstockenterprisefx.item;

import java.math.BigDecimal;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jstockenterprisefx.base.controller.NamedController;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.JpaGenericDao;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.util.DateUtils;

public class ItemController extends NamedController<ItemTableModel, Item, Long> {

	@FXML
	private TableColumn<ItemTableModel, Integer> mStockQuantityColumn;

	@FXML
	private TableColumn<ItemTableModel, String> mLastStockUpdateColumn;

	@FXML
	private ComboBox<ItemSearchOption> mSearchOptionsField;

	@FXML
	private TextField mCostPriceField;

	@FXML
	private TextField mSalePriceField;

	@FXML
	private TextField mStockQuantityField;

	@FXML
	private TextField mLastStockUpdateField;

	@FXML
	private TextField mCreatedAtField;

	@FXML
	private ComboBox<GroupItem> mGroupItemField;

	@Override
	protected JpaGenericDao<Item, Long> initializeDao() {
		return new ItemDao(JpaEntityManager.getEntityManager());
	}

	@Override
	protected ItemTableModel newTableModel() {
		return new ItemTableModel();
	}

	@Override
	protected ItemTableModel newTableModel(final Item item) {
		return new ItemTableModel(item);
	}

	@Override
	protected String getEntityNameToDialogMessages() {
		return "item";
	}

	@Override
	protected void loadRelatedData() {
		final List<GroupItem> groupItemList = new GroupItemDao(
				JpaEntityManager.getEntityManager()).read();

		mGroupItemField.getItems().addAll(groupItemList);

		mSearchOptionsField.getItems().addAll(ItemSearchOption.values());
	}

	@Override
	protected void fillEntityFromFields(final Item item) {
		super.fillEntityFromFields(item);

		item.setCostPrice(new BigDecimal(mCostPriceField.getText()));
		item.setSalePrice(new BigDecimal(mSalePriceField.getText()));
		item.setStockQuantity(Integer.valueOf(mStockQuantityField.getText()));
		item.setLastStockUpdate(DateUtils.parseDateTime(mLastStockUpdateField
				.getText()));
		item.setGroupItem(mGroupItemField.getSelectionModel().getSelectedItem());
	}

	@Override
	protected void bindTableColums() {
		super.bindTableColums();

		mStockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>(
				"stockQuantity"));

		mLastStockUpdateColumn.setCellValueFactory(cellData -> cellData
				.getValue().lastStockUpdateProperty());
	}

	@Override
	protected void resetFieldsToEmpty() {
		super.resetFieldsToEmpty();

		mCreatedAtField.setText(null);
		mGroupItemField.getSelectionModel().clearSelection();
		mCostPriceField.setText(null);
		mSalePriceField.setText(null);
		mStockQuantityField.setText(null);
		mLastStockUpdateField.setText(null);
	}

	@Override
	protected void fillFieldsFromEntity(final ItemTableModel tableModel) {
		super.fillFieldsFromEntity(tableModel);

		mCreatedAtField.setText(DateUtils.format(tableModel.getEntity()
				.getCreatedAt()));
		mGroupItemField.getSelectionModel().select(
				tableModel.getEntity().getGroupItem());
		mCostPriceField.setText(String.valueOf(tableModel.getEntity()
				.getCostPrice()));
		mSalePriceField.setText(String.valueOf(tableModel.getEntity()
				.getSalePrice()));
		mStockQuantityField.setText(String.valueOf(tableModel.getEntity()
				.getStockQuantity()));

		if (tableModel.getEntity().getLastStockUpdate() != null)
			mLastStockUpdateField.setText(DateUtils.format(tableModel
					.getEntity().getLastStockUpdate()));
	}

	@Override
	protected List<Control> getRequiredFieldList() {
		List<Control> controlsList = super.getRequiredFieldList();

		controlsList.add(mCostPriceField);
		controlsList.add(mSalePriceField);
		controlsList.add(mStockQuantityField);
		controlsList.add(mGroupItemField);

		return controlsList;
	}

	@Override
	protected void handleEditingModelObjectChanged(final ItemTableModel newValue) {
		super.handleEditingModelObjectChanged(newValue);

		if (newValue != null)
			if (newValue.getId() == null) {
				mStockQuantityField.setDisable(false);
				mStockQuantityField.setEditable(true);
				mStockQuantityField.setOpacity(1);
			} else {
				mStockQuantityField.setDisable(true);
				mStockQuantityField.setEditable(false);
				mStockQuantityField.setOpacity(0.5);
			}
	}

}
