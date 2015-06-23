package jstockenterprisefx.department;

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
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name")})
public class Department extends BaseEntity<Short> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 80, unique = true)
    private String name;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String personResponsible;

    public Department() {
    }

    public Department(final Short id) {
        this.id = id;
    }

    public Department(final String name, final String personResponsible) {
    	this(null, name, personResponsible);
	}
    
    public Department(final Short id, final String name, final String personResponsible) {
        this.id = id;
        this.name = name;
        this.personResponsible = personResponsible;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(final String personResponsible) {
        this.personResponsible = personResponsible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Department))
			return false;
        Department other = (Department) object;
        if ((id == null && other.id != null) || (id != null && !id.equals(other.id)))
			return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}