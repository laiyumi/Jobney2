package controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import model.user.RegularUser;

public class BaseLayoutController {

	RegularUser user;

	@FXML
	private Pane contentPane;

	@FXML
	private ImageView avatar_icon;

	@FXML
	private Pane avatar_pane;

	@FXML
	private StackPane stackPane;

	public BaseLayoutController(RegularUser user) {
		this.user = user;
	}

	@FXML
	public void initialize() {
		// Load your image and set it to ImageView.
		Image image = user.getAssociatedProfile().getAvatar();
		avatar_icon.setImage(image);

		Circle clip = new Circle();
		clip.setCenterX(avatar_icon.getLayoutX() + avatar_icon.getFitWidth() / 2);
		clip.setCenterY(avatar_icon.getLayoutY() + avatar_icon.getFitHeight() / 2);
		clip.setRadius(100); // Sets radius Property of Circle to 100

		// set clip for imageView
		avatar_icon.setImage(image);
		avatar_icon.setClip(clip);

		// Set the initial image if available
		if (user.getAssociatedProfile().getAvatar() != null) {
			avatar_icon.setImage(user.getAssociatedProfile().getAvatar());
		}

		// add a listener to track the change of avatar
		user.getAssociatedProfile().avatarProperty().addListener((obs, oldImage, newImage) -> Platform.runLater(() -> {
			avatar_icon.setImage(newImage);
			avatar_icon.setClip(clip);
		}));

	}

	public void updateAvatar() {
		Image image = user.getAssociatedProfile().getAvatar();
		avatar_icon.setImage(image);

	}

	public Pane getContentPane() {
		return this.contentPane;
	}

}
