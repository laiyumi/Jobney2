package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.AdminUser;
import model.user.Profile;
import model.user.RegularUser;
import model.utilities.HashHelper;

public class SignUpController implements HashHelper {

	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField password1;
	@FXML
	private Button btn_signup;
	@FXML
	private Label btn_signin;

	@FXML
	private void initialize() {
		btn_signup.setOnAction(e -> handleSignUp());
		btn_signin.setOnMouseClicked(e -> goToLoginView());
	}

	@FXML
	private void handleSignUp() {
		String userEmail = email.getText();
		String userPassword = password.getText();
		String confirmPassword = password1.getText();

		if (userEmail.isEmpty() || userPassword.isEmpty() || confirmPassword.isEmpty()) {
			showAlert("Error", "All fields must be filled out.");
			return;
		}

		if (!HashHelper.isStrongPassword(userPassword)) {
			String message = HashHelper.checkPasswordStrength(userPassword);
			showAlert("Error", message);
			return;
		}

		if (!userPassword.equals(confirmPassword)) {
			showAlert("Error", "Passwords do not match.");
			return;
		}

		RegularUser newUser = new RegularUser(userEmail, userPassword);
		// use user id to create a profile
		Profile newProfile = new Profile(newUser.getUserId());
		newUser.setAssociatedProfile(newProfile);

		// gets a reference to the singleton AdminUser instance
		AdminUser admin = AdminUser.getAdministrator();
		boolean success = admin.getUserDirectory().addUser(newUser);

		if (success) {
			showAlert("Success", "Account created successfully. Please log in.");
			goToLoginView();
		} else {
			showAlert("Error", "An account could not be created. Please try again.");
		}
	}

	private void goToLoginView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginUI.fxml"));
			loader.setController(new LoginController());
			Parent loginView = loader.load();

			Stage stage = (Stage) btn_signup.getScene().getWindow();
			stage.setScene(new Scene(loginView));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to load the login view.");
		}
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
