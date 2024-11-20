package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.user.RegularUser;
import model.utilities.HashHelper;

public class SettingsController {

	private RegularUser user;
	private FileChooser fileChooser;
	private File filePath;
	private Image image;

	@FXML
	private ImageView avatar_img;

	@FXML
	private Pane avatar_placeholder;

	@FXML
	private Button btn_save;

	@FXML
	private Button btn_upload_avatar;

	@FXML
	private PasswordField current_pwd_txt;

	@FXML
	private Label current_username;

	@FXML
	private PasswordField new_pwd_txt;

	@FXML
	private Label pwd_label;

	@FXML
	private PasswordField re_enter_new_pwd_txt;

	@FXML
	private TextField username_txt;

	public SettingsController(RegularUser user) {
		this.user = user;
	}

	public void initialize() {

		Image img = user.getAssociatedProfile().getAvatar();

		avatar_img.setImage(img);
		username_txt.setPromptText(user.getAssociatedProfile().getUserName());

	}

	boolean validate_current_pwd() {
		String current = HashHelper.hashPassword(current_pwd_txt.getText());
		if (user.getPassword().equals(current)) {
			return true;
		} else {
			showAlert("Passwrod Error", "Current password is wrong, please try again.");

			return false;
		}
	}

	boolean validate_new_pwd() {

		String new_pwd = new_pwd_txt.getText();

		// check if it is a strong password
		if (!HashHelper.isStrongPassword(new_pwd)) {
			String message = HashHelper.checkPasswordStrength(new_pwd);
			showAlert("Error", message);
			return false;
		}
		// check if the new password be used before and if re-enter one is the same

		if (user.isValidPassword(new_pwd)) {

			if (new_pwd.equals(re_enter_new_pwd_txt.getText())) {
				return true;
			} else {
				showAlert("Passwrod Error", "Please make sure that the re-enter password is correct.");
				return false;
			}
		} else {
			showAlert("Passwrod Error", "Please make sure that your new passwrod is not used before");
			return false;
		}
	}

	@FXML
	void save_profile(ActionEvent event) {

		if (validate_current_pwd() && validate_new_pwd()) {
			user.changePassword(new_pwd_txt.getText());
			user.getAssociatedProfile().setAvatar(image);
			user.getAssociatedProfile().setUserName(username_txt.getText());

			showAlert("Save", "Saved!");

		} else {
			showAlert("Fail to save", "Please check the information that you enter");

		}

	}

	@FXML
	void upload_img(ActionEvent event) {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();

		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image");

		// set extension filter
		FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg",
				"*.bmp");
		fileChooser.getExtensionFilters().add(ext);

		// show open file dialog
		this.filePath = fileChooser.showOpenDialog(stage);

		if (filePath != null) {
			image = new Image(filePath.toURI().toString());
			avatar_img.setImage(image);
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
