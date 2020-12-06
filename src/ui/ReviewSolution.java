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
	DataFromDatabase data;



    public void setScene(ActionEvent event,DataFromDatabase data) {
		this.data=data;
		Parent root= null;
		try {
			root = FXMLLoader.load(getClass().getResource("reviewSolution.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		initialize(scene);
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
    }

    void initialize(Scene scene ){
		taskDescription=(javafx.scene.control.TextArea)scene.lookup("#taskDescription");
		taskDescription.setText("");

		offSolution=(javafx.scene.control.TextArea)scene.lookup("#offSolution");
		offSolution.setText("");

		studentSolution=(javafx.scene.control.TextArea)scene.lookup("#studentSolution");
		studentSolution.setText("");

		tutorFeedback=(javafx.scene.control.TextArea)scene.lookup("#tutorFeedback");
		tutorFeedback.setText("");

		gradeSlider=(javafx.scene.control.Slider)scene.lookup("#gradeSlider");
		gradeSlider.setValue(0);

		backButton=(javafx.scene.control.Button)scene.lookup("#backButton");
		backButton.setVisible(true);

		submitButton=(javafx.scene.control.Button)scene.lookup("#submitButton");
		submitButton.setVisible(true);
	}
	public void submitPressed(ActionEvent action){
		double grade=gradeSlider.getValue();//faceti ce vreti cu val asta o pui in db
		System.out.println(grade);
		String feedback=tutorFeedback.getText();
		System.out.println(feedback);

		TutorMenu menu=new TutorMenu();
		menu.setScene(action,data);
	}
	public void backButtonPressed(ActionEvent action){
		TutorMenu menu=new TutorMenu();
		menu.setScene(action,data);
	}

}
