package jstockenterprisefx.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public final class DialogUtils {

	public static Optional<ButtonType> showConfirmation(
			final String headerText, final String title,
			final String contentText) {
		return showAlert(headerText, title, contentText, AlertType.CONFIRMATION);
	}

	public static void showError(final String headerText, final String title,
			final String contentText) {
		showAlert(headerText, title, contentText, AlertType.ERROR);
	}

	public static void showWarning(final String headerText, final String title,
			final String contentText) {
		showAlert(headerText, title, contentText, AlertType.WARNING);
	}

	public static void showInformation(final String headerText,
			final String title, final String contentText) {
		showAlert(headerText, title, contentText, AlertType.INFORMATION);
	}

	private static Optional<ButtonType> showAlert(final String headerText,
			final String title, final String contentText,
			final AlertType alertType) {

		ButtonType[] buttons;

		if (alertType.equals(AlertType.CONFIRMATION)) {
			buttons = new ButtonType[2];
			buttons[0] = ButtonType.YES;
			buttons[1] = ButtonType.NO;
		} else {
			buttons = new ButtonType[1];
			buttons[0] = ButtonType.OK;
		}

		Alert alert = new Alert(alertType, contentText, buttons);

		alert.setHeaderText(headerText);
		alert.setTitle(title);

		if (alertType.equals(AlertType.CONFIRMATION))
			return alert.showAndWait();
		else {
			alert.show();
			return Optional.empty();
		}
	}

}
