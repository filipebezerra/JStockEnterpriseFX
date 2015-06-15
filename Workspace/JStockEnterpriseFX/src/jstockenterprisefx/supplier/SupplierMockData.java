package jstockenterprisefx.supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplierMockData {
	private static ObservableList<Supplier> supplierData = FXCollections
			.observableArrayList();

	static {
		supplierData.add(new Supplier("The Creative Shop",
				"14.877.648/0001-55", "213 930 548 / 84"));
		supplierData.add(new Supplier("Active Media Solutions",
				" 13.576.119/0001-59", "213 138 625"));
		supplierData.add(new Supplier("AddSolutions", "22.132.850/0001-86",
				"218 444 460"));
		supplierData.add(new Supplier("APN Publicidade Lda",
				"25.183.681/0001-00", "212 148 608"));
		supplierData.add(new Supplier("Dizplay Soundla", "33.431.254/0001-13",
				"214 197 111"));
		supplierData.add(new Supplier("Grupo Arena", "33.272.167/0001-60",
				"226 433 360"));
		supplierData.add(new Supplier("JR Design", "53.434.395/0001-05",
				"214338600"));
	}

	public static ObservableList<Supplier> getSupplierData() {
		return supplierData;
	}

}
