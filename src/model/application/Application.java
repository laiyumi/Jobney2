package model.application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import model.connection.ConnectionList;
import model.document.DocumentList;
import model.enums.APPLICATIONSTATUS;
import model.enums.RATE;
import model.job.Job;
import model.note.Note;
import model.note.NoteList;
import model.question.QuestionList;
import model.user.RegularUser;

public class Application {

	final String prefix = "application-";
	private RegularUser user;
	private static int nextId = 1;
	private String applicationId;
	private Date dateAdded;
	private String dateApplied;
	private Job associatedJob;
	private RATE rate;
	private Date applyDeadline;
	private APPLICATIONSTATUS status;
	private Set<APPLICATIONSTATUS> statusChangeHistory;

	private QuestionList questionList;
	private NoteList noteList;
	private ConnectionList connectionList;
	private DocumentList documentList;

	public Application(Job associatedJob, RegularUser user) {

		this.associatedJob = associatedJob;
		this.user = user;
		this.applicationId = prefix + nextId;
		nextId++;

		this.dateAdded = new Date();
		this.status = APPLICATIONSTATUS.TOAPPLY;
		this.statusChangeHistory = new HashSet<APPLICATIONSTATUS>();
		this.dateApplied = "N/A";

		this.questionList = new QuestionList(this);
		this.noteList = new NoteList();
		this.connectionList = new ConnectionList();
		this.documentList = new DocumentList();

	}

	public String getJobName() {
		return this.associatedJob.getJobName();
	}

	public Job getAssociatedJob() {
		return this.associatedJob;
	}

	public String getCompanyName() {
		return this.associatedJob.getAssociatedCompany().getCompanyName();
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public RATE getRate() {
		return this.rate;
	}

	public Date getApplyDeadline() {
		return this.applyDeadline;
	}

	public APPLICATIONSTATUS getStatus() {
		return this.status;
	}

	public Set<APPLICATIONSTATUS> getStatusChangeHistory() {
		return this.statusChangeHistory;
	}

	public String getDateAdded() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		return formatter.format(this.dateAdded);
	}

	public Date getDateCreated() {
		return this.dateAdded;
	}

	public void setDateAdded(Date date) {
		this.dateAdded = date;

	}

	public Long getDaysSinceAdded() {

		Date currentDate = new Date();
		long diffInMillies = Math.abs(currentDate.getTime() - this.dateAdded.getTime());
		Long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		return diff;
	}

	public void setDateApplied(String date) {
		this.dateApplied = date;
	}

	public String getDateApplied() {
		return this.dateApplied;

	}

	public void setStatus(APPLICATIONSTATUS status) {
		this.status = status;
		statusChangeHistory.add(status);
	}

	public RegularUser getUser() {
		return this.user;
	}

	public QuestionList getQuestionList() {
		return this.questionList;
	}

	public ConnectionList getConnectionList() {
		return this.connectionList;
	}

	public NoteList getNotesList() {
		return this.noteList;
	}

}
