package jstockenterprisefx.item;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jstockenterprisefx.group.GroupMockData;

public class ItemMockData {
	private static ObservableList<Item> itemData = FXCollections
			.observableArrayList();

	static {
		itemData.add(new Item("Item A", GroupMockData.getGroupData().get(0),
				12.5, 105, LocalDate.now()));
		itemData.add(new Item("Item ABC", GroupMockData.getGroupData().get(2),
				125d, 22, LocalDate.now()));
		itemData.add(new Item("Item CBA", GroupMockData.getGroupData().get(3),
				20.5, 2000, LocalDate.now()));
		itemData.add(new Item("Item XYZ", GroupMockData.getGroupData().get(4),
				1200d, 5, LocalDate.now()));
		itemData.add(new Item("Item DIY", GroupMockData.getGroupData().get(5),
				10.5, 10, LocalDate.now()));
	}

	public static ObservableList<Item> getItemData() {
		return itemData;
	}

}
