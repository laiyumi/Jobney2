package model.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import model.company.Company;
import model.enums.APPLICATIONSTATUS;
import model.enums.INDUSTRY;

public class ApplicationList {

	private List<Application> applications = new ArrayList<>();

	public List<Application> getApplicationList() {
		return applications;
	}

	public void addApplication(Application application) {
		applications.add(application);
	}

	public void updateApplication(Application application) {
		// Logic to update an application in the list
	}

	public void deleteApplication(Application application) {
		applications.remove(application);
	}

	public Application findApplicationByName(String name) {
		for (Application app : applications) {
			if (app.getAssociatedJob().getJobName().equals(name)) {
				return app;
			}
		}
		return null;
	}

	public int countTotalApplications() {
		return applications.size();
	}

	public List<Application> listApplicationByIndustry(INDUSTRY industry) {
		return null;
	}

	// get all to apply applications
	public List<Application> getApplicationsByStatus(APPLICATIONSTATUS status) {
		List<Application> list = new ArrayList<>();
		for (Application app : applications) {
			if (app.getStatus().equals(status)) {
				list.add(app);
			}
		}
		return list;

	}

	// count application by status
	public List<Application> countApplicationsByStatus(APPLICATIONSTATUS status) {
		List<Application> list = new ArrayList<>();
		for (Application app : applications) {
			if (app.getStatusChangeHistory().contains(status)) {
				list.add(app);
			}
		}
		return list;

	}

	public void sortApplicationsByCreatedDate(List<Application> list) {

		Comparator<Application> compareByDate = new Comparator<Application>() {
			@Override
			public int compare(Application app1, Application app2) {
				return app1.getDaysSinceAdded().compareTo(app2.getDaysSinceAdded());
			}
		};

		// use the comparator to sort the list
		list.sort(compareByDate.reversed());

	}

	public int getSize() {
		return getApplicationList().size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Application a : applications) {
			sb.append(a.getJobName() + ",");
		}
		return sb.toString();
	}

}
