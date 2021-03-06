package jstockenterprisefx.base.tablemodel;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javafx.beans.property.ReadOnlyObjectWrapper;
import jstockenterprisefx.base.entity.BaseEntity;

public abstract class BaseTableModel<T extends BaseEntity<ID>, ID extends Serializable> {

	protected final ReadOnlyObjectWrapper<ID> id = new ReadOnlyObjectWrapper<>(
			this, "id", null);

	private final ReadOnlyObjectWrapper<T> entity = new ReadOnlyObjectWrapper<>(
			this, "entity", null);

	private final Class<T> mEntityClass;

	@SuppressWarnings("unchecked")
	public BaseTableModel() {
		mEntityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public BaseTableModel(final ID id) {
		this();

		setId(id);
	}

	public BaseTableModel(final T entity) {
		this();

		setEntity(entity);
		setId(entity.getId());
	}

	public ID getId() {
		return id.get();
	}

	public void setId(final ID id) {
		this.id.set(id);
	}

	public ReadOnlyObjectWrapper<ID> idProperty() {
		return id;
	}

	public T getEntity() {
		return entity.get();
	}

	public void setEntity(final T entity) {
		this.entity.set(entity);
	}

	public ReadOnlyObjectWrapper<T> entityProperty() {
		return entity;
	}

	public Class<T> getEntityClass() {
		return mEntityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {
		if (obj != null)
			if (getClass() == obj.getClass()) {
				BaseTableModel<T, ID> other = (BaseTableModel<T, ID>) obj;

				if (mEntityClass != null && other.mEntityClass != null)
					if (mEntityClass.equals(other.mEntityClass))
						return idProperty() != null
								&& other.idProperty() != null
								&& id.get() != null && other.id.get() != null
								&& id.get().equals(other.idProperty().get());
			}

		return false;
	}

	@Override
	public String toString() {
		return new StringBuffer(getEntityClass().getSimpleName())
				.append(" {id : ").append(String.valueOf(id.get())).append("}")
				.toString();
	}

	public boolean filter(final Serializable filterText) {
		return getId().equals(filterText);
	}

}
