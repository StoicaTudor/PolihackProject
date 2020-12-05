package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import user.Student;
import user.Statistics;

import java.io.IOException;


public class StudentsRanking {

	public void setScene(ActionEvent event) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("StudentRanking.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public StudentsRanking() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
