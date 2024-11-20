package model.user;

import java.util.ArrayList;

import model.utilities.HashHelper;

public abstract class User {

	private String email;
	private String password;
//	private Profile profile;
	private ArrayList<String> passwrodHistory;

	public User(String email, String password) {
		this.passwrodHistory = new ArrayList<>();
		this.email = email;
		this.password = HashHelper.hashPassword(password);
//		this.profile = new Profile();
		passwrodHistory.add(this.password);

	}

//	public Profile getProfile() {
//		return profile;
//	}
//
//	public void setProfile(Profile p) {
//		this.profile = p;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	// change password
	public void changePassword(String newPassword) {
		String hashPwd = HashHelper.hashPassword(newPassword);
		if (isValidPassword(hashPwd)) {
			this.password = hashPwd;
			passwrodHistory.add(this.password);
		} else {

		}
	}

	public ArrayList<String> getPasswordHistory() {
		return this.passwrodHistory;
	}

	// input pwd is already being hashed
	public boolean isValidUser(String email, String password) {

		System.out.println("--------------check input pwd: " + password);

		return this.getEmail().equals(email) && this.getPassword().equals(password);
	}

	public boolean isValidPassword(String newPassword) {
		if (passwrodHistory.contains(newPassword)) {
			System.out.println("--------------invalid pwd");
			return false;
		}
		return true;
	}

}
