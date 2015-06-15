package jstockenterprisefx.group;

public enum GroupType {
	SERVICE("Servi�o"), PRODUCT("Produto");
	
	private String type;
	
	GroupType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
