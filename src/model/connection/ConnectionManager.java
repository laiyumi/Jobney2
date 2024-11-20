package model.connection;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

	protected static int count = 0;
	protected List<Connection> connections;

	public ConnectionManager() {
		this.connections = new ArrayList<>();
	}

	public List<Connection> getConnectionList() {
		return connections;
	}

	public void addConnection(Connection connection) {
		connections.add(connection);
		count++;
	}

	public Connection findConnectionById(String connectionId) {
		for (Connection connection : connections) {
			if (connection.getConnectionId().equals(connectionId)) {
				return connection;
			}
		}
		return null;
	}

	public void deleteConnectionBy(String connectionId) {
		Connection connectionToRemove = findConnectionById(connectionId);
		if (connectionToRemove != null) {
			connections.remove(connectionToRemove);
		}
	}

	public int getCount() {
		return count;
	}

}
