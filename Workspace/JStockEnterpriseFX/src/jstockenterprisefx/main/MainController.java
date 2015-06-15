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

	public void setMainApp(final MainApp mainApp) {
		mMainApp = mainApp;
	}

	@FXML
	private void showGroupView() {
		try {
			AnchorPane groupView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("../group/GroupView.fxml"));
			mMainApp.getMainView().setCenter(groupView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}

	@FXML
	private void showDepartamentView() {
		try {
			AnchorPane departmentView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("../department/DepartmentView.fxml"));
			mMainApp.getMainView().setCenter(departmentView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}
}
