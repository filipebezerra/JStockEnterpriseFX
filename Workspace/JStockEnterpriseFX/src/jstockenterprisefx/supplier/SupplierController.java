package jstockenterprisefx.supplier;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jstockenterprisefx.base.controller.Controller;
import jstockenterprisefx.base.model.Uf;

public class SupplierController extends Controller<SupplierTableModel> {
	@FXML
	private TableColumn<SupplierTableModel, String> mRazaoSocialColumn;

	@FXML
	private TableColumn<SupplierTableModel, String> mCnpjColumn;

	@FXML
	private TableColumn<SupplierTableModel, String> mTelefoneColumn;
	
	@FXML
	private TextField mNomeFantasiaField;

	@FXML
	private TextField mRazaoSocialField;

	@FXML
	private TextField mCnpjField;

	@FXML
	private TextField mTelefoneField;

	@FXML
	private TextField mEmailField;

	@FXML
	private TextField mLogradouroField;

	@FXML
	private TextField mBairroField;

	@FXML
	private TextField mCidadeField;

	@FXML
	private ComboBox<Uf> mUfField;

	@FXML
	private TextField mCepField;

	@Override
	protected void initialize() {
		mDataTable.getItems().addAll(SupplierMockData.getSupplierData());

		super.initialize();

		mRazaoSocialColumn.setCellValueFactory(cellData -> cellData.getValue()
				.razaoSocialProperty());
		mCnpjColumn.setCellValueFactory(cellData -> cellData.getValue()
				.cnpjProperty());
		mTelefoneColumn.setCellValueFactory(cellData -> cellData.getValue()
				.telefoneProperty());

		mUfField.getItems().addAll(Uf.values());
	}

	@Override
	protected void handleEditAction() {
		super.handleEditAction();

		final SupplierTableModel supplier = mEditingModelObject.get();
		mNomeFantasiaField.setText(supplier.getNomeFantasia());
		mRazaoSocialField.setText(supplier.getRazaoSocial());
		mCnpjField.setText(supplier.getCnpj());
		mTelefoneField.setText(supplier.getTelefone());
		mEmailField.setText(supplier.getEmail());
		mLogradouroField.setText(supplier.getLogradouro());
		mBairroField.setText(supplier.getBairro());
		mCidadeField.setText(supplier.getCidade());
		mCepField.setText(supplier.getCep());
		mUfField.getSelectionModel().select(supplier.getUf());
	}

	@Override
	protected void handleResetFieldsAction() {
		super.handleResetFieldsAction();
		mNomeFantasiaField.setText(null);
		mRazaoSocialField.setText(null);
		mCnpjField.setText(null);
		mTelefoneField.setText(null);
		mEmailField.setText(null);
		mLogradouroField.setText(null);
		mBairroField.setText(null);
		mCidadeField.setText(null);
		mCepField.setText(null);
		mUfField.getSelectionModel().clearSelection();
	}
}
