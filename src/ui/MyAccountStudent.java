package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAccountStudent {

	@FXML
	private Label name;
	String nameString=new String("name from DB");
	@FXML
	private Label occupation;
	String occupString=new String("occup from DB");
	@FXML
	private Label country;
	String countryString=new String("country from DB");
	@FXML
	private Label email;
	String emailString=new String("email from DB");
	@FXML
	private Label date_joined;
	String date_joinedString=new String("date from DB");
	@FXML
	private Label section;
	String sectionString=new String("sect.  from DB");
	@FXML
	private Label rank;
	String rankString=new String("rank from DB");
	@FXML
	private Label allTimeRating;
	String allTimeRatingString=new String("alltime rate from DB");
	@FXML
	private Label attemptedTasks;
	String attemptedTasksString=new String("attemped from DB");
	@FXML
	private Label succTasks;
	String succTaskString=new String("succ from DB");
	@FXML
	private Label failedTasks;
	String failedTasksString=new String("failed from DB");


	public void setScene(ActionEvent event) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("myAccountStudent.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		initializeText(scene);
		window.show();

	}


	private void initializeText(Scene scene){
		name=(Label)scene.lookup("#name");
		name.setText(nameString);

		occupation=(Label)scene.lookup("#occupation");
		occupation.setText(occupString);

		country=(Label)scene.lookup("#country");
		country.setText(countryString);

		email=(Label)scene.lookup("#email");
		email.setText(emailString);

		date_joined=(Label)scene.lookup("#date_joined");
		date_joined.setText(date_joinedString);

		section=(Label)scene.lookup("#section");
		section.setText(sectionString);

		rank=(Label)scene.lookup("#rank");
		rank.setText(rankString);

		allTimeRating=(Label)scene.lookup("#allTimeRating");
		allTimeRating.setText(allTimeRatingString);

		attemptedTasks=(Label)scene.lookup("#attemptedTasks");
		attemptedTasks.setText(attemptedTasksString);

		succTasks=(Label)scene.lookup("#succTasks");
		succTasks.setText(succTaskString);

		failedTasks=(Label)scene.lookup("#failedTasks");
		failedTasks.setText(failedTasksString);
	}

	public MyAccountStudent() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
