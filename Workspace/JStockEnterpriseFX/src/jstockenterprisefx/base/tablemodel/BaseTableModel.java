package jstockenterprisefx.base.tablemodel;

import javafx.beans.property.ReadOnlyObjectWrapper;

public abstract class BaseTableModel<ID> {
	protected final ReadOnlyObjectWrapper<ID> id = new ReadOnlyObjectWrapper<>(this, "id", null);

	public ID getId() {
		return id.get();
	}

	public void setId(final ID id) {
		this.id.set(id);
	}

	public ReadOnlyObjectWrapper<ID> idProperty() {
		return id;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append(" {id : ")
				.append(String.valueOf(id.get())).append("}").toString();
	}
}
