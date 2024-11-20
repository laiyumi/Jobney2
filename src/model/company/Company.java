package model.company;

import model.enums.INDUSTRY;
import model.job.JobCatalog;
import model.application.Application;
import java.util.ArrayList;
import java.util.List;

public class Company {

	final String prefix = "company-";
	private static int nextId = 1;
	private String companyId;
	private List<Application> applications;
	private String companyName;
	private INDUSTRY industry;
	private JobCatalog jobList;

	public Company(INDUSTRY industry, String companyName) {
		this.companyId = prefix + nextId;
		this.companyName = companyName;
		this.industry = industry;
		this.jobList = new JobCatalog();
		this.applications = new ArrayList<>();
		nextId++;
	}

	public INDUSTRY getIndustry() {
		return this.industry;
	}

	public Company(String companyName) {
		this.companyId = prefix + nextId;
		this.companyName = companyName;
		nextId++;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public JobCatalog getJobCatalog() {
		return jobList;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	// bundle company with applications
	public Company() {
		this.applications = new ArrayList<>();
	}

	public void addApplication(Application application) {
		this.applications.add(application);
	}

	public List<Application> getApplications() {
		if (applications == null) {
			applications = new ArrayList<>();
		}
		return applications;
	}

	public int getApplicationCount() {
		return applications.size();
	}

	@Override
	public String toString() {
		return "CompanyID: " + companyId + "\nCompany: " + companyName + "\nIndustry: " + industry + "\nJob catalog: "
				+ jobList;
	}

}
