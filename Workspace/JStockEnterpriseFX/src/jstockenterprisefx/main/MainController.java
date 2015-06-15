package jstockenterprisefx.main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jstockenterprisefx.MainApp;

public class MainController {
	private MainApp mMainApp;

	public MainController() {
		super();
	}

	@FXML
	private void showGroupsView() {
		try {
			AnchorPane groupsView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("groups/GroupsView.fxml"));
			mMainApp.getMainView().setCenter(groupsView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}
}
