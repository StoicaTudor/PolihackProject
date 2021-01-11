package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import problem.Solution;

import java.io.IOException;
import java.util.ArrayList;

public class ProblemsList {

	@FXML
	Button back;
	int sessionUserId;
	public static Parent root = null;

	public static DataFromDatabase data;
	public static ArrayList<Solution> solutions;

	@FXML
	public static TextArea taskDescription;

	@FXML
	public static TextArea studentSolution;

	@FXML
	public static TextArea feedback;

	public static Scene scene = null;

	public static int currentSolution = 0;

	public void setScene(ActionEvent event, DataFromDatabase data, int userId) {

		this.data = data;
		try {
			root = FXMLLoader.load(getClass().getResource("problemsList.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		sessionUserId = userId;
		window.setScene(scene);
		window.show();
		solutions = data.getSolvedProblemsFromStudentID(userId);

		studentSolution = (TextArea) scene.lookup("#studentSolution");
		taskDescription = (TextArea) scene.lookup("#taskDescription");
		feedback = (TextArea) scene.lookup("#tutorFeedback");

		showCurrentSolution();
	}

	private void showCurrentSolution() {

		try {

			studentSolution.setText(solutions.get(currentSolution).studentSolution);

			taskDescription.setText(data.getProblemByProblemID(solutions.get(currentSolution).problemID));

			feedback.setText(solutions.get(currentSolution).tutorFeedback);
		} catch (NullPointerException npe) {

		}
	}

	public void backToMyAcc(ActionEvent event) throws Exception {
		MyAccountStudent myAcc = new MyAccountStudent();
		myAcc.setScene(event, data, sessionUserId);
	}

	public void prevButton(ActionEvent event) throws Exception {

		if (currentSolution == 0) {
			return;
		}

		currentSolution--;
		showCurrentSolution();
	}

	public void nextButton(ActionEvent event) throws Exception {

		if (currentSolution == solutions.size() - 1) {
			return;
		}

		currentSolution++;
		showCurrentSolution();
	}
}
