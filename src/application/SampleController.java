package application;


import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SampleController {
	@FXML
	private Button button;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;


	@FXML
	private void initialize() {
		button.setOnAction((event) -> {
			String uN = usernameField.getText().toString();
			String pW = passwordField.getText().toString();
			try {
				Users.login(uN, pW);
			} catch (FileNotFoundException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("Program Error");
				a.setContentText("An error has caused this application to crash");
				a.showAndWait();
				e.printStackTrace();
			}
		});

	}
}
