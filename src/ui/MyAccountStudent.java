package ui;

import database.DataFromDatabase;
import database.Database;
import javafx.concurrent.Task;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAccountStudent {

	@FXML
	private Label name;
	String nameString = new String("name from DB");
	@FXML
	private Label occupation;
	String occupString = new String("Student");
	@FXML
	private Label country;
	String countryString = new String("country from DB");
	@FXML
	private Label email;
	String emailString = new String("email from DB");
	@FXML
	private Label date_joined;
	String date_joinedString = new String("date from DB");
	@FXML
	private Label section;
	String sectionString = new String("sect.  from DB");
	@FXML
	private Label rank;
	String rankString = new String("rank from DB");
	@FXML
	private Label allTimeRating;
	String allTimeRatingString = new String("alltime rate from DB");
	@FXML
	private Label attemptedTasks;
	String attemptedTasksString = new String("attemped from DB");
	@FXML
	private Label succTasks;
	String succTaskString = new String("succ from DB");
	@FXML
	private Label failedTasks;
	String failedTasksString = new String("failed from DB");
	@FXML
	Button solvedProblems;
	@FXML
	Button backToMainPage;
	public static DataFromDatabase data;
	public static int sessionUserId;

	public void setScene(ActionEvent event, DataFromDatabase data, int userID) {
		this.data = data;

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("MyAccountStudent.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		sessionUserId = userID;
		initialize(scene, data);
		window.show();

	}

	public void initialize(Scene scene, DataFromDatabase data) {
		name = (Label) scene.lookup("#name");
		name.setText(data.getUserByID(sessionUserId).getFullname());

		occupation = (Label) scene.lookup("#occupation");
		occupation.setText(occupString);

		country = (Label) scene.lookup("#country");
		country.setText(data.getUserByID(sessionUserId).getCountry());

		email = (Label) scene.lookup("#email");
		email.setText(data.getUserByID(sessionUserId).getEmail());

		date_joined = (Label) scene.lookup("#date_joined");
		date_joined.setText("2021");

		section = (Label) scene.lookup("#section");
		section.setText(Integer.toString(data.getUserByID(sessionUserId).getGrade()));

		rank = (Label) scene.lookup("#rank");
		rank.setText(rankString);

		allTimeRating = (Label) scene.lookup("#allTimeRating");
		allTimeRating.setText(allTimeRatingString);

		attemptedTasks = (Label) scene.lookup("#attemptedTasks");
		attemptedTasks.setText(attemptedTasksString);

		succTasks = (Label) scene.lookup("#succTasks");
		succTasks.setText(succTaskString);

		failedTasks = (Label) scene.lookup("#failedTasks");
		failedTasks.setText(failedTasksString);

	}

	public void backToMainPage(ActionEvent event) throws Exception {
		StudentMenu studentMenu = new StudentMenu();
		studentMenu.setScene(event, data, sessionUserId);
	}

	public void goToSolvedProblems(ActionEvent event) throws Exception {
		ProblemsList problemsList = new ProblemsList();
		problemsList.setScene(event, data, sessionUserId);
	}

}
