package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import problem.Solution;

import java.io.IOException;
import java.sql.SQLException;

public class SolveProblem {

	@FXML
	Button submit;
	@FXML
	Button back;
	@FXML
	TextArea task;
	@FXML
	TextArea solution;
	public static DataFromDatabase data;
	public static int problemID;
	public static Scene scene;
	public static int sessionUserId;

	public void setScene(ActionEvent event, DataFromDatabase data, String problemTask, int problemID, int userId) {
		
		this.data = data;
		this.problemID = problemID;
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("solveProblem.fxml")); // pui fxml de la scena pe care vrei sa
																					// o incarci
		} catch (IOException e) {
			e.printStackTrace();
		}

		sessionUserId = userId;
		scene = new Scene(menu);
		initialize(problemTask);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	public void initialize(String problemTask) {
		task = (TextArea) scene.lookup("#task");
		task.setText(problemTask);
	}

	public void clickSubmit(ActionEvent event) {

		// submit button clicked
		// insert problem into DB
		task = (TextArea) scene.lookup("#task");
		// data.submitSolution(problemID,task.getText());
		StudentMenu studentMenu = new StudentMenu();
		studentMenu.setScene(event, data, sessionUserId);

		data.solutions.add(new Solution(-1, sessionUserId, problemID, task.getText(), -1, -1, "", -1, "", -1));

		try {
			System.out.println("PROOOOOOOOBLEM ID ESTE  " + problemID);
			data.statement0.executeUpdate(new StringBuilder("INSERT INTO pandemicspecial.attemptedproblemss "
					+ "(id, studentID, problemID, studentSolution, tutorID, moderatorID, tutorFeedback, tutorRating, moderatorFeedback, moderatorRating) VALUES "
					+ "(NULL,").append(sessionUserId).append(',').append(problemID).append(',').append('"')
							.append(solution.getText()).append('"').append(",-1,-1,").append('"').append('"')
							.append(",-1,").append('"').append('"').append(",-1)").toString());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void clickBack(ActionEvent event) {

		StudentMenu studentMenu = new StudentMenu();
		studentMenu.setScene(event, data, sessionUserId);
	}

}
