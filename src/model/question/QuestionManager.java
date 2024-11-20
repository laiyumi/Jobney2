package model.question;

import java.util.ArrayList;
import java.util.List;

public class QuestionManager {

	protected int count = 0;
	protected List<Question> questionlist;

	public QuestionManager() {
		this.questionlist = new ArrayList<>();
	}

	public List<Question> getQuestions() {
		return questionlist;
	}

	public void addQuestion(Question q) {
		questionlist.add(q);
		count++;
	}

	public Question searchQuestionByID(String inputId) {
		for (Question q : questionlist) {
			if (q.getQuestionID().equals(inputId)) {
				return q;
			}
		}
		return null;
	}

	public void deleteQuestion(String inputId) {
		Question q = searchQuestionByID(inputId);
		if (q != null) {
			questionlist.remove(q);
		}
	}

	public void updateAnswer(String inputId, String oldAnswer, String newAnswer) {
		Question q = searchQuestionByID(inputId);
		if (q != null) {
			q.updateAnswer(oldAnswer, newAnswer);
		}
	}

	public int getCount() {
		return count;
	}

}
