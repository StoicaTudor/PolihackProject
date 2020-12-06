package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolveProblem {

	public void setScene(ActionEvent event, int problemID) {
		Parent menu=null;
		try {
			menu = FXMLLoader.load(getClass().getResource("solveProblem.fxml")); //pui fxml de la scena pe care vrei sa o incarci
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(menu);
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

}
