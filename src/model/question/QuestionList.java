package model.question;

import model.application.Application;

// this class is to manage all the questions under one application 
public class QuestionList extends QuestionManager {

	private Application application;

	public QuestionList(Application application) {
		super();
		this.application = application;
	}

	public Application getApplication() {
		return this.application;
	}

	@Override
	public void addQuestion(Question q) {
		super.addQuestion(q);
		this.application.getUser().getQuestionDirectory().addQuestion(q);
	}

}
