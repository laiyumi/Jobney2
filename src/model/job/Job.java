package model.job;

import model.company.*;

public class Job {

	// job id format: note-1, note-2, etc.
	final String prefix = "job-";
	private static int nextId = 1;
	private String jobId;

	private Company associatedCompany;
	private String jobName;
	private String jobLink;

	public Job(Company associatedCompany, String jobName, String jobLink) {
		this.jobId = prefix + nextId;
		this.associatedCompany = associatedCompany;
		this.jobName = jobName;
		this.jobLink = jobLink;
		nextId++;
	}

	public String getJobId() {
		return jobId;
	}

	public Company getAssociatedCompany() {
		return associatedCompany;
	}

	public String getJobName() {
		return jobName;
	}

	public String getJobLink() {
		return this.jobLink;
	}

	public void setAssociatedCompany(Company associatedCompany) {
		this.associatedCompany = associatedCompany;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobLink(String jobLink) {
		this.jobLink = jobLink;
	}

	@Override
	public String toString() {
		return "JobID: " + jobId + "\nJob: " + jobName + "\nJob link: " + jobLink;
	}

}
