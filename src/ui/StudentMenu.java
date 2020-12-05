package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentMenu {



	public StudentMenu(){

	}
	public void setScene(ActionEvent event){

		Parent root= null;
		try {
			root = FXMLLoader.load(getClass().getResource("studentMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}


}

