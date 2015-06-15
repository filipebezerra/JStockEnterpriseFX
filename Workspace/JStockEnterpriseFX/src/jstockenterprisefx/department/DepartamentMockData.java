package jstockenterprisefx.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartamentMockData {
	private static ObservableList<Department> departamentData = FXCollections
			.observableArrayList();

	static {
		departamentData.add(new Department("Suporte", "Vin�cius Solo"));
		departamentData.add(new Department("Recursos Humanos",
				"Fernanda Karoline"));
		departamentData.add(new Department("Diretoria", "Luciv�nia Alves"));
		departamentData.add(new Department("Capacita��o", "Ana Fl�via"));
		departamentData.add(new Department("Ana Fl�via", "Bruna Alice"));
		departamentData.add(new Department("Pesquisa e Desenvolvimento",
				"Nasser Ali"));
		departamentData.add(new Department("Fabrica��o", "Sandro Mandela"));
		departamentData
				.add(new Department("Teste e Valida��o", "Fausto Mendes"));
		departamentData.add(new Department("Qualidade", "Bruno Mineglia"));
		departamentData.add(new Department("Monitoria", "Nonato Borges"));
	}

	public static ObservableList<Department> getDepartamentData() {
		return departamentData;
	}
}
