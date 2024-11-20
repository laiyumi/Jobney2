package model.user;

import java.util.Date;

import model.application.ApplicationList;
import model.company.CompanyList;
import model.question.QuestionDirectory;

public class RegularUser extends User {
	final String prefix = "user-";
	private static int nextId = 1;
	private String userId;
	private Date registerDate;
	private CompanyList myCompanyList;
	private ApplicationList myApplicationList;
	private QuestionDirectory myQuestionDirectory;

	private Profile associatedProfile;

	public RegularUser(String email, String password) {
		super(email, password);
		this.registerDate = new Date();
		this.userId = prefix + nextId;
		nextId++;
		this.myApplicationList = new ApplicationList();
		this.myCompanyList = new CompanyList();
		this.myQuestionDirectory = new QuestionDirectory();
	}

	public CompanyList getCompanyList() {
		return myCompanyList;
	}

	public ApplicationList getApplicationList() {
		return myApplicationList;
	}

	public QuestionDirectory getQuestionDirectory() {
		return myQuestionDirectory;
	}

	public String getUserId() {
		return this.userId;
	}

	public Profile getAssociatedProfile() {
		return associatedProfile;
	}

	public void setAssociatedProfile(Profile profile) {
		this.associatedProfile = profile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	@Override
	public String toString() {
		return "UserID: " + this.getUserId() + "\nPassword: " + this.getPassword() + "\nRegisterDate: "
				+ this.getRegisterDate();
	}

}
