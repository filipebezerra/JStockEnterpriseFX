package jstockenterprisefx.groupitem;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jstockenterprisefx.base.entity.BaseEntity;

/**
 *
 * @author Filipe Bezerra
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "GroupItem.findAll", query = "SELECT i FROM GroupItem i"),
    @NamedQuery(name = "GroupItem.findById", query = "SELECT i FROM GroupItem i WHERE i.id = :id"),
    @NamedQuery(name = "GroupItem.findByName", query = "SELECT i FROM GroupItem i WHERE i.name = :name")})
public class GroupItem extends BaseEntity<Short> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private GroupType groupType;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 80, unique = true)
    private String name;
    
    @Column(length = 300)
    private String observation;

    public GroupItem() {
    }

    public GroupItem(final Short id) {
        this.id = id;
    }
    
    public GroupItem(final GroupType groupType, final String name) {
        this(null, groupType, name);
    }

    public GroupItem(final Short id, final GroupType groupType, final String name) {
        this.id = id;
        this.groupType = groupType;
        this.name = name;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(final GroupType groupType) {
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(final String observation) {
        this.observation = observation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof GroupItem))
			return false;
        GroupItem other = (GroupItem) object;
        if ((id == null && other.id != null) || (id != null && !id.equals(other.id)))
			return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
