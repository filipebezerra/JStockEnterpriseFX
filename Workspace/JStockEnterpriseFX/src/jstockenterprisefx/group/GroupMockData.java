package jstockenterprisefx.group;

import static jstockenterprisefx.group.GroupType.PRODUCT;
import static jstockenterprisefx.group.GroupType.SERVICE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GroupMockData {
	private static ObservableList<Group> groupData = FXCollections
			.observableArrayList();

	static {
		groupData.add(new Group("Caixas", PRODUCT, ""));
		groupData.add(new Group("Capacitores", PRODUCT, ""));
		groupData.add(new Group("Chaveadores", PRODUCT,
				"Necessita ser importado"));
		groupData.add(new Group("Diodos", PRODUCT, ""));
		groupData.add(new Group("Etiquetas", PRODUCT, ""));
		groupData.add(new Group("Fios", PRODUCT, ""));
		groupData
				.add(new Group("Leds", PRODUCT, "Em falta na produção interna"));
		groupData.add(new Group("Resistores", PRODUCT, ""));
		groupData.add(new Group("Transitores", PRODUCT, ""));
		groupData.add(new Group("Sacos Pláticos", PRODUCT, ""));
		groupData.add(new Group("Placas de circuito impresso", PRODUCT,
				"Necessita ser importado"));
		groupData.add(new Group("Automação", SERVICE, "Executado sob demanda"));
	}

	public static ObservableList<Group> getGroupData() {
		return groupData;
	}

}
