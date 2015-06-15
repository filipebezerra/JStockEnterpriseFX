package jstockenterprisefx.stock;

public enum OperationType {
	ENTRY("Entrada"), OUT("Sa�da");

	private String operationLabel;

	OperationType(final String operationLabel) {
		this.operationLabel = operationLabel;
	}

	@Override
	public String toString() {
		return operationLabel;
	}
}
