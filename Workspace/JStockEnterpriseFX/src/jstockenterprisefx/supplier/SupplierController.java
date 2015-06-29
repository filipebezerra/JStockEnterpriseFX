package jstockenterprisefx.supplier;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jstockenterprisefx.base.controller.BaseController;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.jpa.JpaGenericDao;
import jstockenterprisefx.base.model.Uf;

public class SupplierController extends
		BaseController<SupplierTableModel, Supplier, Integer> {

	@FXML
	private TableColumn<SupplierTableModel, String> mCompanyNameColumn;

	@FXML
	private TableColumn<SupplierTableModel, String> mCnpjColumn;

	@FXML
	private TableColumn<SupplierTableModel, String> mPhoneNumberColumn;

	@FXML
	private TextField mTradingNameField;

	@FXML
	private TextField mCompanyNameField;

	@FXML
	private TextField mCnpjField;

	@FXML
	private TextField mPhoneNumberField;

	@FXML
	private TextField mEmailAddressField;

	@FXML
	private TextField mPublicAreaField;

	@FXML
	private TextField mDistrictField;

	@FXML
	private TextField mCityField;

	@FXML
	private ComboBox<Uf> mUfField;

	@FXML
	private TextField mCepField;

	@Override
	protected JpaGenericDao<Supplier, Integer> initializeDao() {
		return new SupplierDao(JpaEntityManager.getEntityManager());
	}

	@Override
	protected SupplierTableModel newTableModel() {
		return new SupplierTableModel(new Supplier());
	}

	@Override
	protected SupplierTableModel newTableModel(final Supplier supplier) {
		return new SupplierTableModel(supplier);
	}

	@Override
	protected String getEntityNameToDialogMessages() {
		return "fornecedor";
	}

	@Override
	protected void fillEntityFromFields(final Supplier supplier) {
		supplier.setCompanyName(mCompanyNameField.getText());
		supplier.setTradingName(mTradingNameField.getText());
		supplier.setCnpj(mCnpjField.getText());
		supplier.setPublicArea(mPublicAreaField.getText());
		supplier.setDistrict(mDistrictField.getText());
		supplier.setCity(mCityField.getText());
		supplier.setUf(mUfField.getSelectionModel().getSelectedItem());
		supplier.setCep(mCepField.getText());
		supplier.setPhoneNumber(mPhoneNumberField.getText());
		supplier.setEmailAddress(mEmailAddressField.getText());
	}

	@Override
	protected Control getDefaultFocusField() {
		return mTradingNameField;
	}

	@Override
	protected void bindTableColums() {
		super.bindTableColums();

		mCompanyNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.companyNameProperty());
		mCnpjColumn.setCellValueFactory(cellData -> cellData.getValue()
				.cnpjProperty());
		mPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue()
				.phoneNumberProperty());
	}

	@Override
	protected void resetFieldsToEmpty() {
		super.resetFieldsToEmpty();

		mCompanyNameField.setText(null);
		mTradingNameField.setText(null);
		mCnpjField.setText(null);
		mPublicAreaField.setText(null);
		mDistrictField.setText(null);
		mCityField.setText(null);
		mUfField.getSelectionModel().clearSelection();
		mCepField.setText(null);
		mPhoneNumberField.setText(null);
		mEmailAddressField.setText(null);
	}

	@Override
	protected void fillFieldsFromEntity(final SupplierTableModel tableModel) {
		super.fillFieldsFromEntity(tableModel);

		mCompanyNameField.setText(tableModel.getEntity().getCompanyName());
		mTradingNameField.setText(tableModel.getEntity().getTradingName());
		mCnpjField.setText(tableModel.getEntity().getCnpj());
		mPublicAreaField.setText(tableModel.getEntity().getPublicArea());
		mDistrictField.setText(tableModel.getEntity().getDistrict());
		mCityField.setText(tableModel.getEntity().getCity());
		mUfField.getSelectionModel().select(tableModel.getEntity().getUf());
		mCepField.setText(tableModel.getEntity().getCep());
		mPhoneNumberField.setText(tableModel.getEntity().getPhoneNumber());
		mEmailAddressField.setText(tableModel.getEntity().getEmailAddress());
	}

	@Override
	protected List<Control> getRequiredFieldList() {
		List<Control> controlsList = new ArrayList<>();
		controlsList.add(mCompanyNameField);
		controlsList.add(mTradingNameField);
		controlsList.add(mCnpjField);
		controlsList.add(mCepField);

		return controlsList;
	}

	@Override
	protected void loadRelatedData() {
		mUfField.getItems().addAll(Uf.values());
	}

	@Override
	protected void initialize() {
		super.initialize();

		mCnpjField.textProperty().addListener(
				(ChangeListener<String>) (observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*"))
						mCnpjField.setText(newValue.replaceAll("[^\\d]", ""));
				});

		mPhoneNumberField.textProperty().addListener(
				(ChangeListener<String>) (observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*"))
						mPhoneNumberField.setText(newValue.replaceAll("[^\\d]",
								""));
				});

		mCepField.textProperty().addListener(
				(ChangeListener<String>) (observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*"))
						mCepField.setText(newValue.replaceAll("[^\\d]", ""));
				});
	}

}
