package jstockenterprisefx.item;

public enum ItemSearchOption {
	SEARCH_BY_ID("Código"), SEARCH_BY_CREATED_AT("Data de Cadastro"), SEARCH_BY_DESCRIPTION(
			"Descrição"), SEARCH_BY_GROUP("Grupo"), SEARCH_BY_STOCK_QUANTITY(
			"Quantidade Estoque"), SEARCH_BY_LAST_STOCK_UPDATE(
			"Ùltima Atualização");

	private String searchLabel;

	ItemSearchOption(final String searchLabel) {
		this.searchLabel = searchLabel;
	}

	@Override
	public String toString() {
		return searchLabel;
	}
}
