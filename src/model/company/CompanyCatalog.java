package model.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.user.RegularUser;

public class CompanyCatalog {

	private static List<Company> companies;

	public CompanyCatalog() {
		this.companies = new ArrayList<>();
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

	public Company findCompanyByName(String name) {
		for (Company company : companies) {
			if (company.getCompanyName().equals(name)) {
				return company;
			}
		}
		return null;
	}

	public void updateCompany(Company company) {
		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).getCompanyId().equals(company.getCompanyId())) {
				companies.set(i, company);
				break;
			}

		}

	}

	public void deleteCompany(Company company) {
		companies.remove(company);
	}

	public int countCompany() {
		return companies.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Company c : companies) {
			sb.append(c);
		}
		return sb.toString();
	}

}
