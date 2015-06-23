package jstockenterprisefx.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(final ID id) {
		this.id = id;
	}
}
