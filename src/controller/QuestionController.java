package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.question.Question;
import model.question.QuestionDirectory;
import model.question.QuestionList;
import model.user.RegularUser;

public class QuestionController {

	private RegularUser user;
	@FXML
	private Button btn_search;
	@FXML
	private ListView<String> list_of_questions;
	@FXML
	private ListView<String> answerListView;
	@FXML
	private ListView<String> searchResultsListView;
	@FXML
	private ListView<String> searchResultsAnswerListView;
	@FXML
	private TextField questionNameField;
	@FXML
	private TextField searched_answers;
	@FXML
	private TextField searched_questions;
	@FXML
	private VBox searchResultsVBox;

	public QuestionController(RegularUser user) {
		this.user = user;
	}

	@FXML
	public void initialize() {
		btn_search.setOnAction(e -> searchQuestion(e));
		for (Question question : user.getQuestionDirectory().getQuestions()) { // replace with your actual QuestionList
																				// instance
			list_of_questions.getItems().add(question.getQuestion());
		}

		// Add a listener to handle ListView item clicks
		list_of_questions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				displayAnswer(newSelection);
			}
		});

		// Also add a listener to our searchResultListView
		searchResultsListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						searchResultsAnswerListView.getItems().clear();

						for (Question question : user.getQuestionDirectory().getQuestions()) {
							if (question.getQuestion().equals(newValue)) {
								List<String> answers = question.getAnswerList();
								if (answers != null) {
									for (String answer : answers) {
										searchResultsAnswerListView.getItems().add(answer);
									}
								}
								break;
							}
						}
					}
				});
	}

	private void displayAnswer(String specificQuestionText) {

		// Find the question object by its text
		for (Question question : user.getQuestionDirectory().getQuestions()) { // replace with your actual QuestionList
																				// instance
			if (question.getQuestion().contains(specificQuestionText)) {
				// Get the list of answers
				ArrayList<String> answers = question.getAnswerList();

				// Check if the list is not empty
				if (!answers.isEmpty()) {
					// Clear the ListView
					answerListView.getItems().clear();

					// Add each answer to the ListView
					for (int i = 0; i < answers.size(); i++) {
						answerListView.getItems().add((i + 1) + ". " + answers.get(i));
					}
				}
				break;
			}
		}
	}

	@FXML
	void searchQuestion(ActionEvent event) {
		String searchfield = questionNameField.getText().toLowerCase();
		List<Question> foundQuestions = new ArrayList<>();

		for (Question question : user.getQuestionDirectory().getQuestions()) {
			if (question.getQuestion().toLowerCase().contains(searchfield)) {
				foundQuestions.add(question);
			}
		}

		if (foundQuestions.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Search Result");
			alert.setHeaderText(null);
			alert.setContentText("No questions found containing: " + searchfield);
			alert.showAndWait();
		} else {
			searchResultsListView.getItems().clear(); // Clear the ListView

			for (Question question : foundQuestions) {
				searchResultsListView.getItems().add(question.getQuestion()); // Add each found question to the ListView
			}
		}
	}

}
