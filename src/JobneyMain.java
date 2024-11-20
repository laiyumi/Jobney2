import java.io.IOException;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.user.AdminUser;

public class JobneyMain extends Application {

	@Override
	public void start(Stage primaryStage) {

		AdminUser administrator = AdminUser.getAdministrator();

		// Initialize the data
		Initializer.initializeData(administrator);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginUI.fxml"));
		loader.setController(new LoginController());
		StackPane root = null;

		try {
			root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Unable to load the LoginUI.fxml.");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}