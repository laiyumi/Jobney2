package model.application;

import java.util.Date;
import model.enums.APPLICATIONSTATUS;

public class StatusChange {

	private APPLICATIONSTATUS status;
	private Date changeDate;
	private Application associatedApplication; // Reference to the Application for which the status is changing

	public StatusChange(APPLICATIONSTATUS status, Application associatedApplication) {
		this.status = status;
		this.changeDate = new Date(); // The change date is set to the current date/time
		this.associatedApplication = associatedApplication;
	}

	// Getter and setter methods
	public APPLICATIONSTATUS getStatus() {
		return status;
	}

	public void setStatus(APPLICATIONSTATUS status) {
		this.status = status;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Application getAssociatedApplication() {
		return associatedApplication;
	}

	public void setAssociatedApplication(Application associatedApplication) {
		this.associatedApplication = associatedApplication;
	}

}
