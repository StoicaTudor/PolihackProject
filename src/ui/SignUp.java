package ui;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.Statistics;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import database.DataFromDatabase;
import database.Utility;
import user.UserType;

public class SignUp {

	public static DataFromDatabase data;

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextField email;
	@FXML
	private TextField country;
	@FXML
	private TextField fullname;
	@FXML
	private RadioButton student;
	@FXML
	private RadioButton tutor;
	public int userID;

	public void setScene(ActionEvent event) {
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("signup.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(menu);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);

		window.show();
	}
//	public void initializeDataFromDB(){
//		data.
//	}
	@FXML
	void pressButtonScene2(ActionEvent event) throws Exception {

		int userType = 1;
		Set<String> preferredSubjects = new HashSet<String>();

		Database db = new Database();
		db.retrieveDataFromDatabase();
		data = db.data;

		if (student.isSelected()) {

			userType = 1; // USERTYPE IS STUDENT
			preferredSubjects.add("Mathematics"); // BY DEFAUlT THE USERS LIKE MATHEMATICS

			data.addNewUser(
					data.getPersonalizedUser(userType, -1, username.getText(),fullname.getText(), password.getText(), country.getText(),
							email.getText(), null, new Statistics(0, 0, 0, 0, LocalDate.now()), preferredSubjects, 5));

			StudentMenu studentMenu = new StudentMenu();
			studentMenu.setScene(event, data,data.getSessionUserID(username.getText()));

		} else if (tutor.isSelected()) {

			userType = 2; // USERTYPE IS TUTOR
			preferredSubjects.add("Mathematics"); // BY DEFAUlT THE USERS LIKE MATHEMATICS

			data.addNewUser(
					data.getPersonalizedUser(userType, -1, username.getText(),fullname.getText(), password.getText(), country.getText(),
							email.getText(), null, new Statistics(0, 0, 0, 0, LocalDate.now()), preferredSubjects, 5));

			TutorMenu tutorMenu = new TutorMenu();
			tutorMenu.setScene(event, data,data.getSessionUserID(username.getText()));
		}
	}

}
