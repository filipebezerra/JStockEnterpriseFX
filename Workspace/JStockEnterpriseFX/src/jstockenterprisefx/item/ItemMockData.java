package jstockenterprisefx.item;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jstockenterprisefx.groupitem.GroupItemMockData;

public class ItemMockData {
	private static ObservableList<ItemTableModel> itemData = FXCollections
			.observableArrayList();

	static {
		itemData.add(new ItemTableModel("Item A", GroupItemMockData.getGroupData().get(0),
				12.5, 105, LocalDate.now()));
		itemData.add(new ItemTableModel("Item ABC", GroupItemMockData.getGroupData().get(2),
				125d, 22, LocalDate.now()));
		itemData.add(new ItemTableModel("Item CBA", GroupItemMockData.getGroupData().get(3),
				20.5, 2000, LocalDate.now()));
		itemData.add(new ItemTableModel("Item XYZ", GroupItemMockData.getGroupData().get(4),
				1200d, 5, LocalDate.now()));
		itemData.add(new ItemTableModel("Item DIY", GroupItemMockData.getGroupData().get(5),
				10.5, 10, LocalDate.now()));
	}

	public static ObservableList<ItemTableModel> getItemData() {
		return itemData;
	}

}
