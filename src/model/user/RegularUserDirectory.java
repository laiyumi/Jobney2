package model.user;

import java.util.HashMap;
import java.util.Map;

public class RegularUserDirectory {

	private Map<String, RegularUser> users = new HashMap<>();

	public boolean addUser(RegularUser user) {
		if (users.containsKey(user.getEmail())) {
			return false;
		}
		users.put(user.getEmail(), user);
		return true;
	}

	public RegularUser getUser(String email) {
		return users.get(email);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, RegularUser> entry : users.entrySet()) {
			sb.append("Email: ").append(entry.getKey()).append("\n").append(entry.getValue().toString()).append("\n");
		}
		return sb.toString();
	}

}
