package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewSolution {

	@FXML
	private javafx.scene.control.TextArea taskDescription;
	@FXML
	private javafx.scene.control.TextArea offSolution;
	@FXML
	private javafx.scene.control.TextArea studentSolution;
	@FXML
	private javafx.scene.control.TextArea tutorFeedback;
	@FXML
	private javafx.scene.control.Button backButton;
	@FXML
	private Button submitButton;
	@FXML
	private Slider gradeSlider;
	public static DataFromDatabase data;
	public static int sessionUserId;
	public static ArrayList<String> problems;
	public static int problemId;

	public void setScene(ActionEvent event, DataFromDatabase data, int userId) {

		this.data = data;

		Parent root = null;

		try {
			root = FXMLLoader.load(getClass().getResource("reviewSolution.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		initialize(scene);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		sessionUserId = userId;
		window.show();
	}

	void initialize(Scene scene) {

		try {
			problems = data.getAProblemToReviewForTutor();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		problemId=Integer.parseInt(problems.get(5));
		taskDescription = (javafx.scene.control.TextArea) scene.lookup("#taskDescription");
		taskDescription.setText(problems.get(3));

		offSolution = (javafx.scene.control.TextArea) scene.lookup("#offSolution");
		offSolution.setText(problems.get(4));

		studentSolution = (javafx.scene.control.TextArea) scene.lookup("#studentSolution");
		studentSolution.setText(problems.get(0));

		tutorFeedback = (javafx.scene.control.TextArea) scene.lookup("#tutorFeedback");


		gradeSlider = (javafx.scene.control.Slider) scene.lookup("#gradeSlider");
		gradeSlider.setValue(0);

		backButton = (javafx.scene.control.Button) scene.lookup("#backButton");
		backButton.setVisible(true);

		submitButton = (javafx.scene.control.Button) scene.lookup("#submitButton");
		submitButton.setVisible(true);

	}

	public void submitPressed(ActionEvent action) throws SQLException {
		int grade = (int) gradeSlider.getValue();// faceti ce vreti cu val asta o pui in db
		System.out.println(grade);
		String feedback = tutorFeedback.getText();
		System.out.println(feedback);
		data.submitTutorFeedback(grade,tutorFeedback.getText(),problemId,sessionUserId);
		TutorMenu menu = new TutorMenu();
		menu.setScene(action, data, sessionUserId);
	}

	public void backButtonPressed(ActionEvent action) {
		TutorMenu menu = new TutorMenu();
		menu.setScene(action, data, sessionUserId);
	}

}
