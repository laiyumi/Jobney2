package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import model.application.Application;
import model.connection.Connection;
import model.connection.ConnectionList;
import model.question.Question;
import model.question.QuestionList;

public class LinkedinAndQuestionsController {

	Application currentApplication;

	@FXML
	private Button btn_add_linkedin;

	@FXML
	private Button btn_save_questions;

	@FXML
	private TextField name;

	@FXML
	private TextField linkedin_link;

	@FXML
	private ListView<Connection> linkedinListView;

	private ObservableList<Connection> observableConnectionList;

	@FXML
	private TextField question;

	@FXML
	private ListView<Question> questionListView;

	@FXML
	private TextField response;

	private ObservableList<Question> observableQuestionList;

	public LinkedinAndQuestionsController(Application application) {
		this.currentApplication = application;

		this.observableConnectionList = FXCollections
				.observableArrayList(application.getConnectionList().getConnectionList());
		this.observableQuestionList = FXCollections.observableArrayList(application.getQuestionList().getQuestions());
	}

	public void initialize() {
		linkedinListView.setItems(observableConnectionList);
		questionListView.setItems(observableQuestionList);

		// Add listener for the linkedin list
		linkedinListView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) { // Double-click detection
				Connection selectedConnection = linkedinListView.getSelectionModel().getSelectedItem();
				if (selectedConnection != null && !selectedConnection.getLink().isEmpty()) {
					openWebpage(selectedConnection.getLink());
				}
			}
		});

		// Add listener for the questions list
		questionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// Set the text field to the selected question
				question.setText(newValue.getQuestion());
				// If there are answers, set the response field to the first answer
				if (!newValue.getAnswerList().isEmpty()) {
					response.setText(newValue.getAnswerList().get(0));
				} else {
					response.clear();
				}
			}
		});
	}

	@FXML
	void addLinkedin(ActionEvent event) {
		String nameText = name.getText();
		String linkText = linkedin_link.getText();
		Connection newConnection = new Connection(nameText, linkText);
		observableConnectionList.add(newConnection);
		currentApplication.getConnectionList().addConnection(newConnection);

		name.clear();
		linkedin_link.clear();
	}

	private void openWebpage(String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				showErrorAlert("Failed to open URL", "Could not open the specified URL: " + url);
			}
		} else {
			showErrorAlert("Unsupported Operation", "Desktop operations not supported on this platform.");
		}
	}

	private void showErrorAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@FXML
	void saveQuestion(ActionEvent event) {
		Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
		String questionText = question.getText();
		String answerText = response.getText();

		if (selectedQuestion != null) {
			// Update existing question
			selectedQuestion.setQuestion(questionText);

			if (!answerText.isEmpty()) {
				selectedQuestion.addAnswer(answerText);
				selectedQuestion.addFrequency();
			}

			questionListView.refresh();
		} else {
			// No question is selected. Add a new question
			if (!questionText.isEmpty()) {
				Question newQuestion = new Question(questionText);
				if (!answerText.isEmpty()) {
					newQuestion.addAnswer(answerText);
					newQuestion.addFrequency();
				}
				observableQuestionList.add(newQuestion);
				currentApplication.getQuestionList().addQuestion(newQuestion);
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error！");
				alert.setHeaderText("Failed to Save Question");
				alert.setContentText("The question text cannot be empty.");
				alert.showAndWait();
			}
		}

		// Clear the text fields and the selection
		question.clear();
		response.clear();
		questionListView.getSelectionModel().clearSelection();
	}

}
