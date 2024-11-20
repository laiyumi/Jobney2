package model.utilities;

import java.net.URL;

import controller.DashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {

	private Pane view;

	public Pane getPage(URL fileUrl) {

		try {

			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file can't be found");
			}

			// load the page
			view = FXMLLoader.load(fileUrl);

		} catch (Exception e) {
			System.out.println("Cannot load the page, please check FxmlLoader!");
		}

		return view;

	}

}
