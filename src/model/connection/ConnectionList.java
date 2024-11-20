package model.connection;

// this is the connection under one application
public class ConnectionList extends ConnectionManager {

	private String applicationId;

	public ConnectionList() {
		super();
	}

	public ConnectionList(String applicationID) {
		super();
		this.applicationId = applicationID;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

}
