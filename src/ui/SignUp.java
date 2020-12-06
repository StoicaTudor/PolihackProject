package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.Statistics;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import database.DataFromDatabase;
import database.Utility;

public class SignUp {

	DataFromDatabase data;

	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField email;
	@FXML
	private TextField country;
	@FXML
	private RadioButton student;
	@FXML
	private RadioButton tutor;

	public void setScene(ActionEvent event) {
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("signup.fxml")); // pui fxml de la scena pe care vrei sa o
																			// incarci
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(menu);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void pressButtonScene2(ActionEvent event) throws IOException {

		int userType = 1;
		Set<String> preferredSubjects = new HashSet<String>();

		if (student.isSelected()) {

			userType = 1; // USERTYPE IS STUDENT
			preferredSubjects.add("Mathematics"); // BY DEFAUlT THE USERS LIKE MATHEMATICS

			data.addNewUser(
					data.getPersonalizedUser(userType, -1, username.getText(), password.getText(), country.getText(),
							email.getText(), null, new Statistics(0, 0, 0, 0, LocalDate.now()), preferredSubjects, 5));

			StudentMenu studentMenu = new StudentMenu(data);
			studentMenu.setScene(event);

		} else if (tutor.isSelected()) {

			userType = 2; // USERTYPE IS TUTOR
			preferredSubjects.add("Mathematics"); // BY DEFAUlT THE USERS LIKE MATHEMATICS

			data.addNewUser(
					data.getPersonalizedUser(userType, -1, username.getText(), password.getText(), country.getText(),
							email.getText(), null, new Statistics(0, 0, 0, 0, LocalDate.now()), preferredSubjects, 5));

			TutorMenu tutorMenu = new TutorMenu(data);
			tutorMenu.setScene(event);
		}
	}

}
