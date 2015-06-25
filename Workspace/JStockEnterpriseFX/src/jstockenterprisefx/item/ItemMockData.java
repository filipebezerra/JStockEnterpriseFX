package jstockenterprisefx.item;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jstockenterprisefx.groupitem.GroupItemMockData;

@Deprecated
public class ItemMockData {
	private static ObservableList<ItemTableModel> itemData = FXCollections
			.observableArrayList();

	static {
		itemData.add(new ItemTableModel("Item A", GroupItemMockData
				.getGroupData().get(0).getEntity(), 12.5, 105, LocalDateTime
				.now()));
		itemData.add(new ItemTableModel("Item ABC", GroupItemMockData
				.getGroupData().get(1).getEntity(), 125d, 22, LocalDateTime
				.now()));
		itemData.add(new ItemTableModel("Item CBA", GroupItemMockData
				.getGroupData().get(2).getEntity(), 20.5, 2000, LocalDateTime
				.now()));
		itemData.add(new ItemTableModel("Item XYZ", GroupItemMockData
				.getGroupData().get(3).getEntity(), 1200d, 5, LocalDateTime
				.now()));
		itemData.add(new ItemTableModel("Item DIY", GroupItemMockData
				.getGroupData().get(4).getEntity(), 10.5, 10, LocalDateTime
				.now()));
	}

	public static ObservableList<ItemTableModel> getItemData() {
		return itemData;
	}

}
