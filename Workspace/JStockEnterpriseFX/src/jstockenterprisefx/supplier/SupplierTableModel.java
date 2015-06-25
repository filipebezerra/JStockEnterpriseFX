package jstockenterprisefx.supplier;

import java.io.Serializable;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.model.Uf;
import jstockenterprisefx.base.tablemodel.BaseTableModel;

public class SupplierTableModel extends BaseTableModel<Supplier, Integer> {

	private final StringProperty companyName = new SimpleStringProperty(this,
			"companyName", null);

	private final StringProperty tradingName = new SimpleStringProperty(this,
			"tradingName", null);

	private final StringProperty cnpj = new SimpleStringProperty(this, "cnpj",
			null);

	private final StringProperty phoneNumber = new SimpleStringProperty(this,
			"phoneNumber", null);

	private final StringProperty emailAddress = new SimpleStringProperty(this,
			"emailAddress", null);

	private final StringProperty publicArea = new SimpleStringProperty(this,
			"publicArea", null);

	private final StringProperty district = new SimpleStringProperty(this,
			"district", null);

	private final StringProperty city = new SimpleStringProperty(this, "city",
			null);

	private final ObjectProperty<Uf> uf = new SimpleObjectProperty<>(this,
			"uf", null);

	private final StringProperty cep = new SimpleStringProperty(this, "cep",
			null);

	public SupplierTableModel() {
		super(new Supplier());
	}

	public SupplierTableModel(final Supplier supplier) {
		super(supplier);

		setCompanyName(supplier.getCompanyName());
		setTradingName(supplier.getTradingName());
		setCnpj(supplier.getCnpj());
		setPhoneNumber(supplier.getPhoneNumber());
		setEmailAddress(supplier.getEmailAddress());
		setPublicArea(supplier.getPublicArea());
		setDistrict(supplier.getDistrict());
		setCity(supplier.getCity());
		setUf(supplier.getUf());
		setCep(supplier.getCep());
	}

	public SupplierTableModel(final String tradingName, final String cnpj,
			final String phoneNumber) {
		setTradingName(tradingName);
		setCnpj(cnpj);
		setPhoneNumber(phoneNumber);
	}

	public String getCompanyName() {
		return companyName.get();
	}

	public void setCompanyName(final String companyName) {
		this.companyName.set(companyName);
	}

	public StringProperty companyNameProperty() {
		return companyName;
	}

	public String getTradingName() {
		return tradingName.get();
	}

	public void setTradingName(final String tradingName) {
		this.tradingName.set(tradingName);
	}

	public StringProperty tradingNameProperty() {
		return tradingName;
	}

	public String getCnpj() {
		return cnpj.get();
	}

	public void setCnpj(final String cnpj) {
		this.cnpj.set(cnpj);
	}

	public StringProperty cnpjProperty() {
		return cnpj;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress.get();
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress.set(emailAddress);
	}

	public StringProperty emailAddressProperty() {
		return emailAddress;
	}

	public String getPublicArea() {
		return publicArea.get();
	}

	public void setPublicArea(final String publicArea) {
		this.publicArea.set(publicArea);
	}

	public StringProperty publicAreaProperty() {
		return publicArea;
	}

	public String getDistrict() {
		return district.get();
	}

	public void setDistrict(final String district) {
		this.district.set(district);
	}

	public StringProperty districtProperty() {
		return district;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(final String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	public Uf getUf() {
		return uf.get();
	}

	public void setUf(final Uf uf) {
		this.uf.set(uf);
	}

	public ObjectProperty<Uf> ufProperty() {
		return uf;
	}

	public String getCep() {
		return cep.get();
	}

	public void setCep(final String cep) {
		this.cep.set(cep);
	}

	public StringProperty cepProperty() {
		return cep;
	}

	@Override
	public String toString() {
		return getEntity().toString();
	}

	@Override
	public boolean filter(final Serializable filterText) {
		return new StringBuffer(getCompanyName().toUpperCase())
				.append(getCnpj()).toString()
				.contains(filterText.toString().toUpperCase());
	}

}
