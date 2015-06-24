package jstockenterprisefx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.main.MainController;

public class JStockEnterpriseFXApplication extends Application {
	private static Scene sScene = null;
	private static BorderPane sRoot = null;

	public static Scene getScene() {
		if (sScene == null)
			sScene = new Scene(
					JStockEnterpriseFXApplication.load(MainController.class));
		return sScene;
	}

	public static BorderPane getRoot() {
		if (sRoot == null)
			sRoot = (BorderPane) JStockEnterpriseFXApplication.getScene()
					.getRoot();
		return sRoot;
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setScene(getScene());
		primaryStage.show();
	}

	@Override
	public void init() throws Exception {
		super.init();
		JpaEntityManager.getEntityManager();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		JpaEntityManager.close();
	}

	public static <T> T load(final Class<?> viewControllerClass) {
		final String simpleNameOfClass = viewControllerClass.getSimpleName();

		String location = "";

		if (!simpleNameOfClass.toLowerCase().endsWith("controller"))
			return null;
		else {
			int indexOfViewName = simpleNameOfClass.toLowerCase().indexOf(
					"controller");

			String viewName = simpleNameOfClass.substring(0, indexOfViewName);

			location = new StringBuffer(viewName).append("View.fxml")
					.toString();
		}

		try {
			return FXMLLoader.load(viewControllerClass.getResource(location));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
