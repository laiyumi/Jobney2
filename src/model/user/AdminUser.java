package model.user;

import model.company.CompanyCatalog;

public class AdminUser extends User {

	private static AdminUser administrator = null;
	private RegularUserDirectory userDirectory;
	private CompanyCatalog companyCatalog;

	private AdminUser(String email, String password) {
		super(email, password);
		this.userDirectory = new RegularUserDirectory();
		this.companyCatalog = new CompanyCatalog();
	}

	public boolean isAdminCredentials(String email, String password) {
		return this.getEmail().equals(email) && this.getPassword().equals(password);
	}

	public static AdminUser getAdministrator() {
		if (administrator == null) { // add null check for lazy loading
			administrator = new AdminUser("example@csye.com", "123456");
		}

		return administrator;
	}

	public RegularUserDirectory getUserDirectory() {
		return userDirectory;
	}

	public CompanyCatalog getCompanyCatalog() {
		return companyCatalog;
	}
}
