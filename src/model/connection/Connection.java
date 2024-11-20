package model.connection;

import java.time.LocalDate;
import model.enums.CONNECTSTATUS;
import model.company.Company;

public class Connection {

	final String prefix = "connection-";
	private static int nextId = 1;
	private String connectionId;
	private String name;
	private LocalDate connectDate;
	private String link;
	private CONNECTSTATUS status;

	public Connection(String name, String link) {
		this.connectionId = prefix + nextId;
		this.name = name;
		this.connectDate = LocalDate.now(); // default to current date
		this.status = CONNECTSTATUS.SENT; // default = sent
		this.link = link;
		nextId++;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CONNECTSTATUS getStatus() {
		return status;
	}

	public void setStatus(CONNECTSTATUS status) {
		this.status = status;
	}

	public void updateStatus(CONNECTSTATUS newStatus) {
		this.status = newStatus;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
