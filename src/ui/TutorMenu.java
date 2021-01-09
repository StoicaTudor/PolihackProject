package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import database.DataFromDatabase;
import user.UserType;

import static user.UserType.MODERATOR;
import static user.UserType.TUTOR;

public class TutorMenu {
	
	public static DataFromDatabase data;

	@FXML
	private Button myAcc;
	@FXML
	private Button reviewSolutions;
	@FXML
	private Button proposeNew;
	@FXML
	private Button feedbackTutor;
	@FXML
	private Button logOut;
	public static int sessionUserId;

	public void setScene(ActionEvent event, DataFromDatabase data,int userId) {

		this.data = data;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("tutorMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		feedbackTutor = (Button) scene.lookup("#feedbackTutor");
		sessionUserId=userId;

		try {
			if (this.data.isModerator() == false) {
				feedbackTutor.setVisible(false);
			} else {
				feedbackTutor.setVisible(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	public void myAccPressed(ActionEvent action)throws Exception {
		MyAccountTutor myacc = new MyAccountTutor();

		myacc.setScene(action,data,sessionUserId);
	}

	public void reviewSolutionsPressed(ActionEvent action) {
		ReviewSolution review = new ReviewSolution();
		review.setScene(action, data,sessionUserId);

	}

	public void proposeNewPressed(ActionEvent action) {
		ProposeProblem propose = new ProposeProblem();
		propose.setScene(action, data,sessionUserId);

	}

	public void feedbackTutorPressed(ActionEvent action) throws Exception {
		if (data.isModerator()) {
			ReviewFeedback reviewFeedback = new ReviewFeedback();
			reviewFeedback.setScene(action, data,sessionUserId);
		}

	}

	public void logOutPressed(ActionEvent action) {
		LogIn backToSignIn = new LogIn();
		backToSignIn.setScene(action, data);
	}
}
