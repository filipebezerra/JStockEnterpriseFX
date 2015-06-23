package jstockenterprisefx.supplier;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jstockenterprisefx.base.model.Uf;
import jstockenterprisefx.base.tablemodel.BaseTableModel;

public class SupplierTableModel extends BaseTableModel {
	private StringProperty nomeFantasia = new SimpleStringProperty(this,
			"nomeFantasia", null);

	private StringProperty razaoSocial = new SimpleStringProperty(this,
			"razaoSocial", null);

	private StringProperty cnpj = new SimpleStringProperty(this, "cnpj", null);

	private StringProperty telefone = new SimpleStringProperty(this,
			"telefone", null);

	private StringProperty email = new SimpleStringProperty(this, "email", null);


	private StringProperty logradouro = new SimpleStringProperty(this,
			"logradouro", null);

	private StringProperty bairro = new SimpleStringProperty(this, "bairro",
			null);

	private StringProperty cidade = new SimpleStringProperty(this, "cidade",
			null);

	private ObjectProperty<Uf> uf = new SimpleObjectProperty<>(this, "uf", null);

	private StringProperty cep = new SimpleStringProperty(this, "cep", null);

	public SupplierTableModel(final String razaoSocial, final String cnpj,
			final String telefone) {
		this.razaoSocial.set(razaoSocial);
		this.cnpj.set(cnpj);
		this.telefone.set(telefone);
	}

	public String getNomeFantasia() {
		return nomeFantasia.get();
	}

	public void setNomeFantasia(final String nomeFantasia) {
		this.nomeFantasia.set(nomeFantasia);
	}

	public StringProperty nomeFantasiaProperty() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial.get();
	}

	public void setRazaoSocial(final String razaoSocial) {
		this.razaoSocial.set(razaoSocial);
	}

	public StringProperty razaoSocialProperty() {
		return razaoSocial;
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

	public String getTelefone() {
		return telefone.get();
	}

	public void setTelefone(final String telefone) {
		this.telefone.set(telefone);
	}

	public StringProperty telefoneProperty() {
		return telefone;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(final String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getLogradouro() {
		return logradouro.get();
	}

	public void setLogradouro(final String logradouro) {
		this.logradouro.set(logradouro);
	}

	public StringProperty logradouroProperty() {
		return logradouro;
	}

	public String getBairro() {
		return bairro.get();
	}

	public void setBairro(final String bairro) {
		this.bairro.set(bairro);
	}

	public StringProperty bairroProperty() {
		return bairro;
	}

	public String getCidade() {
		return cidade.get();
	}

	public void setCidade(final String cidade) {
		this.cidade.set(cidade);
	}

	public StringProperty cidadeProperty() {
		return cidade;
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
		return getRazaoSocial();
	}
}
