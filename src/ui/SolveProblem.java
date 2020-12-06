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
import javafx.stage.Stage;

import java.io.IOException;

public class SolveProblem {

	@FXML
	Button submit;
	@FXML
	Button back;
	@FXML
	Label IDLabel;
	DataFromDatabase data;

	public void setScene(ActionEvent event, DataFromDatabase data, int problemID) {
		this.data = data;
		Parent menu = null;
		try {
			menu = FXMLLoader.load(getClass().getResource("solveProblem.fxml")); // pui fxml de la scena pe care vrei sa
																					// o incarci
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(menu);
		IDLabel = (Label) scene.lookup("#IDLabel");
		StringBuilder id = new StringBuilder(problemID);
		id.toString();
		IDLabel.setText("ID #" + id);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	public void clickSubmit(ActionEvent event) {

		data.submitSolution(problemID, solution);

		StudentMenu studentMenu = new StudentMenu();
		studentMenu.setScene(event, data);
	}

	public void clickBack(ActionEvent event) {
		StudentMenu studentMenu = new StudentMenu();
		studentMenu.setScene(event, data);
	}

}
