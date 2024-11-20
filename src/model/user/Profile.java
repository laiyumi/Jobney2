package model.user;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Profile {

	private String userName;

	private ObjectProperty<Image> avatar = new SimpleObjectProperty<>();

	public ObjectProperty<Image> avatarProperty() {
		return avatar;
	}

	public Image getAvatar() {
		return avatar.get();
	}

	public void setAvatar(Image avatar) {
		this.avatar.set(avatar);
	}
	
	public Profile() {

	}

	public Profile(String userName) {
		this.userName = userName;
		this.avatar.set(new Image("/images/User 03C.png")); // default avatar

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
