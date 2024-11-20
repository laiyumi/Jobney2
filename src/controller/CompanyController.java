package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.company.Company;
import model.company.CompanyList;
import model.user.RegularUser;

public class CompanyController {

	private RegularUser user;
	@FXML
	private Button btn_search;
	@FXML
	private TableView<Company> company_table;
	@FXML
	private TextField search_bar;
	@FXML
	private TableColumn<Company, String> applicationColumn;
	@FXML
	private TableColumn<Company, String> companyColumn;
	@FXML
	private TableColumn<Company, String> industryColumn;

	public CompanyController(RegularUser user) {
		this.user = user;
	}

	public void initialize() {
		// Set cell value factories
		companyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
		industryColumn.setCellValueFactory(new PropertyValueFactory<>("industry"));
		applicationColumn.setCellValueFactory(new PropertyValueFactory<>("applicationCount"));

		// get the existing data
		ObservableList<Company> data = FXCollections.observableArrayList(user.getCompanyList().getCompanies());
		// populate the tableview
		company_table.setItems(data);

		// set action for search button
		btn_search.setOnAction(e -> searchCompany(e));

	}

	public void searchCompany(ActionEvent event) {
		String searchfield = search_bar.getText().trim().toLowerCase();
		ObservableList<Company> results = FXCollections.observableArrayList();

		for (Company company : user.getCompanyList().getCompanies()) {
			if (company.getCompanyName().toLowerCase().contains(searchfield)) {
				results.add(company);
			}
		}

		if (results.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Search Result");
			alert.setHeaderText(null);
			alert.setContentText("No companies found with the name: " + searchfield);
			alert.showAndWait();
		} else {
			company_table.setItems(results);
		}
	}

}
