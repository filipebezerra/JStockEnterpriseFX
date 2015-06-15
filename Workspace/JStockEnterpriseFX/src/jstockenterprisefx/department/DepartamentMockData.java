package jstockenterprisefx.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartamentMockData {
	private static ObservableList<Department> departamentData = FXCollections
			.observableArrayList();

	static {
		departamentData.add(new Department("Suporte", "Vinícius Solo"));
		departamentData.add(new Department("Recursos Humanos",
				"Fernanda Karoline"));
		departamentData.add(new Department("Diretoria", "Lucivânia Alves"));
		departamentData.add(new Department("Capacitação", "Ana Flávia"));
		departamentData.add(new Department("Ana Flávia", "Bruna Alice"));
		departamentData.add(new Department("Pesquisa e Desenvolvimento",
				"Nasser Ali"));
		departamentData.add(new Department("Fabricação", "Sandro Mandela"));
		departamentData
				.add(new Department("Teste e Validação", "Fausto Mendes"));
		departamentData.add(new Department("Qualidade", "Bruno Mineglia"));
		departamentData.add(new Department("Monitoria", "Nonato Borges"));
	}

	public static ObservableList<Department> getDepartamentData() {
		return departamentData;
	}
}
