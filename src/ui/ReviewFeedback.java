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

import java.io.IOException;
import java.util.ArrayList;

public class ReviewFeedback {

	@FXML
	private javafx.scene.control.TextArea taskDescription;
	@FXML
	private javafx.scene.control.TextArea offSolution;
	@FXML
	private javafx.scene.control.TextArea studentSolution;
	@FXML
	private javafx.scene.control.TextArea tutorFeedback;
	@FXML
	private javafx.scene.control.TextArea tutorRating;
	@FXML
	private javafx.scene.control.Button backButton;
	@FXML
	private Button submitButton;
	@FXML
	private Slider gradeSlider;
	public static DataFromDatabase data;
	public static int sessionUserId;

	public void setScene(ActionEvent event, DataFromDatabase data, int userId) throws Exception {
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

	void initialize(Scene scene) throws Exception {

		ArrayList<String> problems = data.getAProblemToReviewForTutor();
		taskDescription = (javafx.scene.control.TextArea) scene.lookup("#taskDescription");
		taskDescription.setText(problems.get(3));

		offSolution = (javafx.scene.control.TextArea) scene.lookup("#offSolution");
		offSolution.setText(problems.get(3));

		studentSolution = (javafx.scene.control.TextArea) scene.lookup("#studentSolution");
		studentSolution.setText(problems.get(0));

//		tutorFeedback = (javafx.scene.control.TextArea) scene.lookup("#tutorFeedback");
//		tutorFeedback.setText("");
//
//		tutorRating = (javafx.scene.control.TextArea) scene.lookup("#tutorRating");
//		tutorFeedback.setText(problems.get(2));

		gradeSlider = (javafx.scene.control.Slider) scene.lookup("#gradeSlider");
		gradeSlider.setValue(0);

		backButton = (javafx.scene.control.Button) scene.lookup("#backButton");
		backButton.setVisible(true);

		submitButton = (javafx.scene.control.Button) scene.lookup("#submitButton");
		submitButton.setVisible(true);
	}

	public void submitPressed(ActionEvent action) {
		double grade = gradeSlider.getValue();// faceti ce vreti cu val asta o pui in db
		String feedback = tutorFeedback.getText();

		TutorMenu menu = new TutorMenu();
		menu.setScene(action, data, sessionUserId);
	}

	public void backButtonPressed(ActionEvent action) {
		TutorMenu menu = new TutorMenu();
		menu.setScene(action, data, sessionUserId);
	}

}
