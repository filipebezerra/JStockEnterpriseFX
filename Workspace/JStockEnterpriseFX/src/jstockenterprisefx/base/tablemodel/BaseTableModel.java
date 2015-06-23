package jstockenterprisefx.base.tablemodel;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.ReadOnlyIntegerWrapper;

public abstract class BaseTableModel {
	private static AtomicInteger idSequence = new AtomicInteger(0);
	
	protected final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(
			this, "id", idSequence.incrementAndGet());

	public int getId() {
		return id.get();
	}

	public void setId(final int id) {
		this.id.set(id);
	}

	public ReadOnlyIntegerWrapper idProperty() {
		return id;
	}

	@Override
	public String toString() {
		return new StringBuffer(getClass().getSimpleName()).append(" {id : ")
				.append(String.valueOf(id.get())).append("}").toString();
	}
}
