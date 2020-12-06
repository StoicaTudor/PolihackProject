package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import database.DataFromDatabase;

public class StudentMenu {

	DataFromDatabase data;

	@FXML
	private Button myAccountButton;
	@FXML
	private Button logOut;
	@FXML
	private Button showTasks;
	@FXML
	private Button solve1;
	@FXML
	private Button solve2;
	@FXML
	private Button solve3;
	@FXML
	private Button solve4;
	@FXML
	private Button solve5;
	@FXML
	private Button previousPage;
	@FXML
	private Button nextPage;
	@FXML
	private ChoiceBox<String> subject;
	@FXML
	private ChoiceBox<Integer> section;
	@FXML
	private ChoiceBox<String> difficulty;
	@FXML
	private TextField task1;
	@FXML
	private TextField task2;
	@FXML
	private TextField task3;
	@FXML
	private TextField task4;
	@FXML
	private TextField task5;

	private String t1 = "bsvdfvbsdvfhvsadgfhadshkgfhaghdfksdghf";
	private String t2 = "bsvdfvbsdvfhvsfhahgjfadshkgfhaghdfksdghf";
	private String t3 = "bsvdfvbsdvfhvsadgfhadshkgfhrghuerahjghaerhgiewrgaghdfksdghf";
	private String t4 = "bsvdfvbsdvfhvsadgfhaderhgvgufgkfherfhgjshkgfhaghdfksdghf";
	private String t5 = "bsvdfvbsdvfhvsadawefergfgfhadshkgfhaghdfksdghf";

	public StudentMenu(DataFromDatabase data) {
		this.data = data;
	}

	public void setScene(ActionEvent event) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("studentMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		initializeChoiceBoxes(scene);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void goToMyAcc(ActionEvent event) throws IOException {

		MyAccount myacc = new MyAccount();
		myacc.setScene(event);

	}

	public void logOut(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void showTasks(ActionEvent event) throws IOException {

	}

	public void initializeChoiceBoxes(Scene scene) {
		subject = (ChoiceBox<String>) scene.lookup("#subject");
		subject.setValue("Mathematics");
		subject.getItems().addAll("Mathematics", "Physics", "ComputerScience", "English", "German", "Spanish", "French",
				"Chemistry", "Economy", "Biology", "History", "Geography");
		section = (ChoiceBox<Integer>) scene.lookup("#section");
		section.setValue(12);
		section.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		difficulty = (ChoiceBox<String>) scene.lookup("#difficulty");
		difficulty.setValue("medium");
		difficulty.getItems().addAll("easy", "medium", "hard");
		task1 = (TextField) scene.lookup("#task1");
		task1.setText(t1 + t2);
		task2 = (TextField) scene.lookup("#task2");
		task2.setText(t2);
		task3 = (TextField) scene.lookup("#task3");
		task3.setText(t3);
		task4 = (TextField) scene.lookup("#task4");
		task4.setText(t4);
		task5 = (TextField) scene.lookup("#task5");
		task5.setText(t5);
	}

	public void solve1(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void solve2(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void solve3(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void solve4(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void solve5(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

}
