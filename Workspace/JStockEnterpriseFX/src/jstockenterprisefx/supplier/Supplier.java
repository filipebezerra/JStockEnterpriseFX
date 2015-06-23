package jstockenterprisefx.supplier;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jstockenterprisefx.base.entity.BaseEntity;

/**
 *
 * @author Filipe Bezerra
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findById", query = "SELECT s FROM Supplier s WHERE s.id = :id"),
    @NamedQuery(name = "Supplier.findByCompanyName", query = "SELECT s FROM Supplier s WHERE s.companyName = :companyName")})
public class Supplier extends BaseEntity<Integer> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String companyName;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 120)
    private String tradingName;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;
    
    @Column(length = 11)
    private String phoneNumber;
    
    @Column(length = 120, unique = true)
    private String emailAddress;
    
    @Basic(optional = true)
    @Column(nullable = true, length = 100)
    private String publicArea;
    
    @Basic(optional = true)
    @Column(nullable = true, length = 80)
    private String district;
    
    @Basic(optional = true)
    @Column(nullable = true, length = 80)
    private String city;
    
    @Basic(optional = true)
    @Column(nullable = true, length = 2)
    private String uf;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String cep;

    public Supplier() {
    }

    public Supplier(final Integer id) {
        this.id = id;
    }
    
    public Supplier(final String companyName, final String tradingName,
    		final String cnpj, final String cep) {
        this(null, companyName, tradingName, cnpj, null, null, null, null, cep);
    }
    
    public Supplier(final String companyName, final String tradingName,
    		final String cnpj, final String publicArea, final String district, final String city,
    		final String uf, final String cep) {
        this(null, companyName, tradingName, cnpj, publicArea, district, city, uf, cep);
    }

    public Supplier(final Integer id, final String companyName, final String tradingName,
    		final String cnpj, final String publicArea, final String district, final String city,
    		final String uf, final String cep) {
        this.id = id;
        this.companyName = companyName;
        this.tradingName = tradingName;
        this.cnpj = cnpj;
        this.publicArea = publicArea;
        this.district = district;
        this.city = city;
        this.uf = uf;
        this.cep = cep;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(final String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(final String publicArea) {
        this.publicArea = publicArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(final String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Supplier))
			return false;
        Supplier other = (Supplier) object;
        if ((id == null && other.id != null) || (id != null && !id.equals(other.id)))
			return false;
        return true;
    }

    @Override
    public String toString() {
        return companyName;
    }
    
}