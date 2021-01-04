package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ProposeProblem {

	@FXML
	private javafx.scene.control.TextArea taskText;
	@FXML
	private javafx.scene.control.TextArea solutionText;
	@FXML
	private ChoiceBox<String> subjectChoice;
	@FXML
	private ChoiceBox<Integer> sectionChoice;
	@FXML
	private ChoiceBox<String> difficultyChoice;
	@FXML
	private javafx.scene.control.Button submitButton;
	@FXML
	private Button backButton;
	@FXML
	private javafx.scene.control.ScrollBar scrollbarTask;
	@FXML
	private Scrollbar scrollbarSolution;

	public static DataFromDatabase data;

	public void setScene(ActionEvent event, DataFromDatabase data) {
		this.data = data;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("proposeProblem.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		initializeChoiceBoxesAndText(scene);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	private void initializeChoiceBoxesAndText(Scene scene) {
		subjectChoice = (ChoiceBox<String>) scene.lookup("#subjectChoice");
		subjectChoice.setValue("Mathematics");
		subjectChoice.getItems().addAll("Mathematics", "Physics", "ComputerScience", "English", "German", "Spanish",
				"French", "Chemistry", "Economy", "Biology", "History", "Geography");

		sectionChoice = (ChoiceBox<Integer>) scene.lookup("#sectionChoice");
		sectionChoice.setValue(12);
		sectionChoice.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

		difficultyChoice = (ChoiceBox<String>) scene.lookup("#difficultyChoice");
		difficultyChoice.setValue("medium");
		difficultyChoice.getItems().addAll("easy", "medium", "hard");

		taskText = (javafx.scene.control.TextArea) scene.lookup("#taskText");
		taskText.setText("");

		solutionText = (javafx.scene.control.TextArea) scene.lookup("#solutionText");
		solutionText.setText("");

		submitButton = (javafx.scene.control.Button) scene.lookup("#submitButton");
		submitButton.setVisible(true);

		backButton = (javafx.scene.control.Button) scene.lookup("#backButton");
		backButton.setVisible(true);
	}

	public void clickSubmit(ActionEvent event) {
		// submit button clicked
		// insert problem into DB

		TutorMenu tutorMenu = new TutorMenu();
		tutorMenu.setScene(event, data);
	}

	public void clickBack(ActionEvent event) {
		TutorMenu tutorMenu = new TutorMenu();
		tutorMenu.setScene(event, data);
	}

}
