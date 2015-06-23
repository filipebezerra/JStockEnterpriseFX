package jstockenterprisefx.groupitem;

import static jstockenterprisefx.groupitem.GroupType.PRODUCT;
import static jstockenterprisefx.groupitem.GroupType.SERVICE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Deprecated
public class GroupItemMockData {
	private static ObservableList<GroupItemTableModel> groupData = FXCollections
			.observableArrayList();

	static {
		groupData.add(new GroupItemTableModel("Caixas", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Capacitores", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Chaveadores", PRODUCT,
				"Necessita ser importado"));
		groupData.add(new GroupItemTableModel("Diodos", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Etiquetas", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Fios", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Leds", PRODUCT,
				"Em falta na produção interna"));
		groupData.add(new GroupItemTableModel("Resistores", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Transitores", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Sacos Pláticos", PRODUCT, ""));
		groupData.add(new GroupItemTableModel("Placas de circuito impresso",
				PRODUCT, "Necessita ser importado"));
		groupData.add(new GroupItemTableModel("Automação", SERVICE,
				"Executado sob demanda"));
	}

	public static ObservableList<GroupItemTableModel> getGroupData() {
		return groupData;
	}

}
