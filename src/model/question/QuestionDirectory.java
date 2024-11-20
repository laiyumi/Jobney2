package model.question;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.user.RegularUser;

// class to manage all questions under one user
public class QuestionDirectory extends QuestionManager {

	public QuestionDirectory() {
		super();
	}

	// list question by frequency in descending order
	public List<Question> sortQuestionByFrequency() {
		Collections.sort(questionlist, new Comparator<Question>() {
			public int compare(Question q1, Question q2) {
				return Integer.compare(q1.getFrequency(), q2.getFrequency());
			}
		});
		return questionlist;
	}
}
