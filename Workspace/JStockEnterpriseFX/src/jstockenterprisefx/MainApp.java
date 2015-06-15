package jstockenterprisefx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jstockenterprisefx.main.MainController;

public class MainApp extends Application {
	private Stage mPrimaryStage;
	private BorderPane mMainView;

	public BorderPane getMainView() {
		return mMainView;
	}

	@Override
	public void start(final Stage primaryStage) {
		mPrimaryStage = primaryStage;
		mPrimaryStage
				.setTitle("JStockEnterpriseFX - Controle de Estoque Empresarial");
		showMainView();
	}

	private void showMainView() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader
					.setLocation(getClass().getResource("main/MainView.fxml"));
			mMainView = (BorderPane) fxmlLoader.load();
			MainController controller = fxmlLoader.getController();
			controller.setMainApp(this);

			Scene scene = new Scene(mMainView);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			mPrimaryStage.setScene(scene);
			mPrimaryStage.show();
		} catch (IOException e) {
			// TODO treat exception
			e.printStackTrace();
		}
	}
}
