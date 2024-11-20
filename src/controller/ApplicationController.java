package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.application.Application;
import model.application.ApplicationList;
import model.user.RegularUser;
import model.utilities.DataUpdateInterface;

public class ApplicationController implements DataUpdateInterface {

	private RegularUser user;

	private DashboardController dashboardController;

	@FXML
	private TextField search_bar;
	@FXML
	private Button btn_search;
	@FXML
	private Button btn_addApplication;
	@FXML
	private TableView<Application> application_table;
	@FXML
	private TableColumn<Application, String> JobPositionColumn;
	@FXML
	private TableColumn<Application, String> companyColumn;
	@FXML
	private TableColumn<Application, String> StatusColumn;
	@FXML
	private TableColumn<Application, String> DateAppliedColumn;
	@FXML
	private TableColumn<Application, String> DateAddedColumn;
	@FXML
	private TableColumn<Application, String> RateColumn;

	Integer index;

	public ApplicationController(RegularUser user, DashboardController dashboardController) {
		this.user = user;
		this.dashboardController = dashboardController;
	}

	public HBox getHbox() {
		return (HBox) this.dashboardController.getHbox();
	}

	@FXML
	void openSelectedApplication(MouseEvent event) {

		if (event.getClickCount() == 2) {
			// check double click
			Application selectedApplication = application_table.getSelectionModel().getSelectedItem();

			if (selectedApplication != null) {
				// navigate to application page
				System.out.println("open application: " + selectedApplication.getJobName());

				try {
					// load and configure middle pane
					URL fileUrl1 = getClass().getResource("/view/ManageApplicationUI.fxml");
					FXMLLoader loader1 = new FXMLLoader(fileUrl1);
					ManageApplicationController controller1 = new ManageApplicationController(selectedApplication,
							this);
					loader1.setController(controller1);
					Pane view1 = loader1.load();

					// load and configure right pane
					URL fileUrl2 = getClass().getResource("/view/LinkedinAndQuestionsUI.fxml");
					FXMLLoader loader2 = new FXMLLoader(fileUrl2);
					LinkedinAndQuestionsController controller2 = new LinkedinAndQuestionsController(
							selectedApplication);
					loader2.setController(controller2);
					Pane view2 = loader2.load();

					HBox newbox = new HBox();
					newbox.getChildren().addAll(view1, view2);

					dashboardController.getHbox().getChildren().remove(1);
					dashboardController.getHbox().getChildren().add(newbox);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void updateTable() {

		// refresh the data
		application_table.getItems().clear();
		populateTable();
	}

	public void populateTable() {
		// set up columns
		JobPositionColumn.setCellValueFactory(new PropertyValueFactory<>("jobName"));
		companyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		DateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
		DateAppliedColumn.setCellValueFactory(new PropertyValueFactory<>("dateApplied"));
		RateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));

		// Get the existing data
		ObservableList<Application> data = FXCollections
				.observableArrayList(user.getApplicationList().getApplicationList());

		// Populate the TableView
		application_table.setItems(data);
	}

	public void initialize() {

		updateTable();

		btn_search.setOnAction(e -> search(e));
		btn_addApplication.setOnAction(e -> addApplication(e));

		// add hover effect

	}

	public void search(ActionEvent event) {

		String searchfield = search_bar.getText();
		List<Application> results = new ArrayList();

		for (Application app : user.getApplicationList().getApplicationList()) { // made static method and attribute
			if (app.getAssociatedJob().getJobName().toLowerCase().contains(searchfield)) {
				results.add(app);
			}

		}
		if (results.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Search Result");
			alert.setHeaderText(null);
			alert.setContentText("No applications found for the job: " + searchfield);

			alert.showAndWait();
		} else {
			application_table.setItems(FXCollections.observableArrayList(results));
		}
	}

	public void addApplication(ActionEvent event) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddApplicationUI.fxml"));
			AddApplicationWindowController controller = new AddApplicationWindowController(user, this);
			fxmlLoader.setController(controller);
			Parent root1 = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Create an Application");
			Scene scene = new Scene(root1);

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			System.out.println("Can't load new window");
			e.printStackTrace();
		}
	}

	public DashboardController getDashboardController() {
		return this.dashboardController;
	}

}
