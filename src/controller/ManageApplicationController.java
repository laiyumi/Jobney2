package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.application.Application;
import model.enums.APPLICATIONSTATUS;
import model.enums.INDUSTRY;
import model.note.Note;

public class ManageApplicationController {

	private Application currentApplication;

	private ApplicationController applicationController;

	@FXML
	private Button apply_link;

	@FXML
	private Button btn_save_note;

	@FXML
	private ComboBox<String> btn_status;

	@FXML
	private Button btn_back;

	@FXML
	private Label company_name;

	@FXML
	private CheckBox connect_checkbox;

	@FXML
	private CheckBox do_research_checkbox;

	@FXML
	private Label job_title;

	@FXML
	private TextField note_title;

	@FXML
	private TextArea note_content;

	@FXML
	private Pane note_list_pane;

	@FXML
	private HBox note_pane;

	@FXML
	private ProgressBar progress_bar;

	@FXML
	private Label progress_label;

	@FXML
	private ImageView rate;

	@FXML
	private Button btn_cover_letter;

	@FXML
	private Button btn_resume;

	@FXML
	private CheckBox submit_checkbox;

	@FXML
	private CheckBox tailor_resume_checkbox;

	@FXML
	private CheckBox tailore_cover_checkbox;

	@FXML
	private ListView<Note> notesListView;

	private ObservableList<Note> observableNotesList;

	// constructor
	public ManageApplicationController(Application application, ApplicationController applicationController) {
		this.currentApplication = application;
		this.applicationController = applicationController;
		// Initialize the observable list for notes
		observableNotesList = FXCollections.observableArrayList();
	}

	public void initialize() {
		String job = currentApplication.getJobName();
		String company = currentApplication.getCompanyName();
		APPLICATIONSTATUS currentStatus = currentApplication.getStatus();

		note_content.setWrapText(true);

		// set up the items in the combo box
		ArrayList<String> statusList = Arrays.stream(APPLICATIONSTATUS.values()).map(Enum::name)
				.collect(Collectors.toCollection(ArrayList::new));

		// Display all data on the page
		job_title.setText(job);
		company_name.setText(company);

		// Display the current status
		btn_status.setItems(FXCollections.observableArrayList(statusList));
		String status = currentStatus.name();
		btn_status.setValue(status);

		// Display current progress, by default is 0%(1 checkbox = 20%)
		progress_bar.setProgress(0.0);
		double value = 0.0;
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		String default_percent = percentFormat.format(value);
		progress_label.setText(default_percent);

		// Create a listener for checkbox selection changes
		ChangeListener<Boolean> listener = (obs, oldVal, newVal) -> {
			double progress = 0;
			if (do_research_checkbox.isSelected())
				progress += 0.2;
			if (tailor_resume_checkbox.isSelected())
				progress += 0.2;
			if (tailore_cover_checkbox.isSelected())
				progress += 0.2;
			if (submit_checkbox.isSelected())
				progress += 0.2;
			if (connect_checkbox.isSelected())
				progress += 0.2;

			progress_bar.setProgress(progress);
			String percent = percentFormat.format(progress);
			progress_label.setText(percent);
		};

		// Assign listener to each checkbox
		do_research_checkbox.selectedProperty().addListener(listener);
		tailor_resume_checkbox.selectedProperty().addListener(listener);
		tailore_cover_checkbox.selectedProperty().addListener(listener);
		submit_checkbox.selectedProperty().addListener(listener);
		connect_checkbox.selectedProperty().addListener(listener);

		// Bind the ListView to the ObservableList so that changes in the list are
		// automatically reflected in the ListView
		if (currentApplication.getNotesList() != null) {
			observableNotesList.addAll(currentApplication.getNotesList().getAllNotes());
		}
		notesListView.setItems(observableNotesList);

		// Add listener to ListView
		notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				note_title.setText(newValue.getTitle());
				note_content.setText(newValue.getContent());
			}
		});

		note_pane.setOnMouseClicked(event -> {

			Node target = (Node) event.getTarget();

			while (target != null && !(target instanceof Pane)) {
				target = target.getParent();
			}

			if (target != null) {
				Pane clickedPane = (Pane) target;
				if (clickedPane.getId() == null || !clickedPane.getId().equals("note_list_pane")) {
					notesListView.getSelectionModel().clearSelection();
					note_content.clear();
					note_title.clear();
				}
			}

		});

	}

	@FXML
	public void ChangeStatus(ActionEvent event) {

	}

	@FXML
	public void GoBack(ActionEvent event) {
		try {
			// Load the previous view
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ApplicationUI.fxml"));
			ApplicationController applicationController = this.getApplicationController();
			loader.setController(applicationController);
			Pane previousView = loader.load();

			applicationController.getHbox().getChildren().remove(1);
			applicationController.getHbox().getChildren().add(previousView);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void saveNote(ActionEvent event) {
		// Select a Note
		Note selectedNote = notesListView.getSelectionModel().getSelectedItem();

		// Create a new note if no note is selected in the notesListView
		if (selectedNote == null) {
			String title = note_title.getText();
			String content = note_content.getText();

			if (!title.isEmpty() && !content.isEmpty()) {
				Note newNote = new Note(title, content);
				observableNotesList.add(newNote);
				currentApplication.getNotesList().addNote(newNote);
				note_title.clear();
				note_content.clear();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error！");
				alert.setHeaderText("Failed to Save Note");
				alert.setContentText("Both title and content are required!");
				alert.showAndWait();
			}
		} else {
			// Update titles and contents
			selectedNote.updateTitle(note_title.getText());
			selectedNote.updateContent(note_content.getText());

			// Update UI
			notesListView.refresh();

			// Clear the text fields and the selection
			note_title.clear();
			note_content.clear();
			notesListView.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void addCoverLetter(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Cover Letter File");
		File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			// Use the file
			System.out.println("Cover Letter File: " + file.getAbsolutePath());
		}
	}

	@FXML
	public void addResume(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resume File");
		File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			// Use the file
			System.out.println("Resume File: " + file.getAbsolutePath());
		}
	}

	@FXML
	public void openApplyLink(ActionEvent event) throws IOException, URISyntaxException {
		System.out.println("link clicked!");
		String applyLink = currentApplication.getAssociatedJob().getJobLink();

		if (!applyLink.startsWith("http://") && !applyLink.startsWith("https://")) {
			applyLink = "http://" + applyLink;
		}

		Desktop.getDesktop().browse(new URI(applyLink));
	}

	public ApplicationController getApplicationController() {
		return this.applicationController;
	}

}
