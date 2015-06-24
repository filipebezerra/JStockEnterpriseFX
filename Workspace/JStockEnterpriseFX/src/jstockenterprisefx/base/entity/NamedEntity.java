package jstockenterprisefx.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntity<ID> extends BaseEntity<ID> {

	@Column(nullable = false, length = 80, unique = true)
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
