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

	@FXML
	private void showSupplierView() {
		try {
			AnchorPane supplierView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("../supplier/SupplierView.fxml"));
			mMainApp.getMainView().setCenter(supplierView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}

	@FXML
	private void showItemView() {
		try {
			AnchorPane itemView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("../item/ItemView.fxml"));
			mMainApp.getMainView().setCenter(itemView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}

	@FXML
	private void showStockView() {
		try {
			AnchorPane stockView = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("../stock/StockView.fxml"));
			mMainApp.getMainView().setCenter(stockView);
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}
}
