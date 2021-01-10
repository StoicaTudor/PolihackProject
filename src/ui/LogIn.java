package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.UserType;

import java.io.IOException;
import java.sql.SQLException;

import database.DataFromDatabase;
import database.Database;

public class LogIn {

	DataFromDatabase data;
	int sessionUserID;

	public void setScene(ActionEvent event, DataFromDatabase data) {

		this.data = data;

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void pressLogIn(ActionEvent event) throws SQLException {

		Database db = new Database();
		db.retrieveDataFromDatabase();
		this.data = db.data;

		sessionUserID = data.getSessionUserID(username.getText());

		UserType logInResult = data.validateSignIn(username.getText(), password.getText());


		TutorMenu tutorMenu;
		switch (logInResult) {

		case NA:
			// implement popup sign in invalid
			break;

		case STUDENT:
			StudentMenu studentMenu = new StudentMenu();
			studentMenu.setScene(event, data, sessionUserID);
			break;

		case TUTOR:
			tutorMenu = new TutorMenu();
			tutorMenu.setScene(event, data, sessionUserID);
			break;

		case MODERATOR:
			tutorMenu = new TutorMenu();
			tutorMenu.setScene(event, data, sessionUserID);
			System.out.println("");
			break;

		default:
			break;
		}
	}

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	public void pressSignUp(ActionEvent event) throws IOException {

		Database db = new Database();
		db.retrieveDataFromDatabase();
		this.data = db.data;

		SignUp signUp = new SignUp();
		signUp.setScene(event);
	}

}
