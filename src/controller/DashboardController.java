package controller;

import model.user.RegularUser;
import javafx.scene.Node;

import model.utilities.FxmlLoader;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardController {

	private RegularUser user;

	private HBox hbox;

	@FXML
	private Button btn_application;

	@FXML
	private Button btn_companies;

	@FXML
	private Button btn_log_out;

	@FXML
	private Button btn_overview;

	@FXML
	private Button btn_questions;

	@FXML
	private Button btn_settings;

	@FXML
	private ImageView icon_application;

	@FXML
	private ImageView icon_companies;

	@FXML
	private ImageView icon_log_out;

	@FXML
	private ImageView icon_overview;

	@FXML
	private ImageView icon_question;

	@FXML
	private ImageView icon_settings;

	@FXML
	private ImageView logo;

	@FXML
	private Pane mainPane;

	@FXML
	private VBox navigation;

	@FXML
	private HBox root;

	public DashboardController(RegularUser user, HBox hbox) {
		this.user = user;
		this.hbox = hbox;

	}

	public void initialize() {

		// add hover style
		btn_overview.setOnMouseEntered(e -> btn_overview.setStyle("-fx-background-color: rgba(54, 159, 255, 0.1)")); // blue
		btn_overview.setOnMouseExited(e -> btn_overview.setStyle("-fx-background-color: #F5F5F5;")); // default

		btn_questions.setOnMouseEntered(e -> btn_questions.setStyle("-fx-background-color: rgba(54, 159, 255, 0.1)")); // blue
		btn_questions.setOnMouseExited(e -> btn_questions.setStyle("-fx-background-color: #F5F5F5;")); // default

		btn_settings.setOnMouseEntered(e -> btn_settings.setStyle("-fx-background-color: rgba(54, 159, 255, 0.1)")); // blue
		btn_settings.setOnMouseExited(e -> btn_settings.setStyle("-fx-background-color: #F5F5F5;")); // default

		btn_companies.setOnMouseEntered(e -> btn_companies.setStyle("-fx-background-color: rgba(54, 159, 255, 0.1)")); // blue
		btn_companies.setOnMouseExited(e -> btn_companies.setStyle("-fx-background-color: #F5F5F5;")); // default

		btn_application
				.setOnMouseEntered(e -> btn_application.setStyle("-fx-background-color: rgba(54, 159, 255, 0.1)")); // blue
		btn_application.setOnMouseExited(e -> btn_application.setStyle("-fx-background-color: #F5F5F5;")); // default

	}

	@FXML
	void btnApplicationClicked(ActionEvent event) {
		System.out.println("Switching to Application page!");

		try {
			URL fileUrl = getClass().getResource("/view/ApplicationUI.fxml");
			FXMLLoader loader = new FXMLLoader(fileUrl);
			ApplicationController appController = new ApplicationController(user, this);
			loader.setController(appController);
			Pane view = loader.load();

			// Remove old child
			hbox.getChildren().remove(1);

			// Add new child at the correct position
			hbox.getChildren().add(1, view);

		} catch (IOException ex) {
			System.out.println("Error: Unable to load the Application view.");
			ex.printStackTrace();
		}

	}

	@FXML
	void btnCompaniesClicked(ActionEvent event) {
		System.out.println("Switching to Companies page!");

		try {
			URL fileUrl = getClass().getResource("/view/CompanyUI.fxml");
			FXMLLoader loader = new FXMLLoader(fileUrl);
			CompanyController companyController = new CompanyController(user);
			loader.setController(companyController);
			Pane view = loader.load();

			hbox.getChildren().remove(1);
			hbox.getChildren().add(1, view);

		} catch (IOException ex) {
			System.out.println("Error: Unable to load the Company view.");
			ex.printStackTrace();
		}

	}

	@FXML
	void btnOverviewClicked(ActionEvent event) {
		System.out.println("Switching to Overview page!");

		try {
			// load and configure right pane(dashboard pane)
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../view/OverviewUI.fxml"));
			loader2.setController(new OverviewController(user, hbox));
			Pane overview = loader2.load();

			hbox.getChildren().remove(1);
			hbox.getChildren().add(1, overview);

		} catch (IOException ex) {
			System.out.println("Error: Unable to load the Company view.");
			ex.printStackTrace();
		}

	}

	@FXML
	void btnQuestionsClicked(ActionEvent event) {
		System.out.println("Switching to Questions page!");

		try {
			URL fileUrl = getClass().getResource("/view/QuestionUI.fxml");
			FXMLLoader loader = new FXMLLoader(fileUrl);
			QuestionController questionsController = new QuestionController(user);
			loader.setController(questionsController);
			Pane view = loader.load();

			hbox.getChildren().remove(1);
			hbox.getChildren().add(1, view);
		} catch (IOException ex) {
			System.out.println("Error: Unable to load the Questions view.");
			ex.printStackTrace();
		}
	}

	@FXML
	void btnSettingsClicked(ActionEvent event) {
		System.out.println("Switching to Settings page!");

		try {
			URL fileUrl = getClass().getResource("/view/SettingsUI.fxml");
			FXMLLoader loader = new FXMLLoader(fileUrl);
			SettingsController settingsController = new SettingsController(user);
			loader.setController(settingsController);
			Pane view = loader.load();

			hbox.getChildren().remove(1);
			hbox.getChildren().add(1, view);
		} catch (IOException ex) {
			System.out.println("Error: Unable to load the Settings view.");
			ex.printStackTrace();
		}
	}

	@FXML
	void click(MouseEvent event) {

	}

	@FXML
	void btnLogOutClicked(ActionEvent event) {
		System.out.println("Logging out!");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginUI.fxml"));
			loader.setController(new LoginController());
			Parent loginView = loader.load();

			Stage stage = (Stage) btn_log_out.getScene().getWindow();
			stage.setScene(new Scene(loginView));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to load the login view.");
		}

	}

	public Pane getHbox() {
		return this.hbox;
	}

}
