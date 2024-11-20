package model.connection;

import java.util.List;

import model.enums.CONNECTSTATUS;

public class ConnectionDirectory extends ConnectionManager {

	private static int count;
	private List<Connection> allConnections;

	public ConnectionDirectory() {
		super();
	}

	// count acceptance/information interview rate
	public double countRate(CONNECTSTATUS CONNECTSTATUS) {
		allConnections = this.getConnectionList();
		int count = 0;
		for (Connection c : allConnections) {
			if (c.getStatus() == CONNECTSTATUS) {
				count++;
			}
		}
		return (double) (count / this.getCount());

	}

	// sort company by number of connection
	// TODO: future work

}
