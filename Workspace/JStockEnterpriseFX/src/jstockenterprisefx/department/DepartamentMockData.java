package jstockenterprisefx.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartamentMockData {
	private static ObservableList<DepartmentTableModel> departamentData = FXCollections
			.observableArrayList();

	static {
		departamentData.add(new DepartmentTableModel("Suporte", "Vinícius Solo"));
		departamentData.add(new DepartmentTableModel("Recursos Humanos",
				"Fernanda Karoline"));
		departamentData.add(new DepartmentTableModel("Diretoria", "Lucivânia Alves"));
		departamentData.add(new DepartmentTableModel("Capacitação", "Ana Flávia"));
		departamentData.add(new DepartmentTableModel("Pesquisa e Desenvolvimento",
				"Nasser Ali"));
		departamentData.add(new DepartmentTableModel("Fabricação", "Sandro Mandela"));
		departamentData
				.add(new DepartmentTableModel("Teste e Validação", "Fausto Mendes"));
		departamentData.add(new DepartmentTableModel("Qualidade", "Bruno Mineglia"));
		departamentData.add(new DepartmentTableModel("Monitoria", "Nonato Borges"));
	}

	public static ObservableList<DepartmentTableModel> getDepartamentData() {
		return departamentData;
	}
}
