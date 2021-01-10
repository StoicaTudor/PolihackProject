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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import problem.Problem;

import java.io.IOException;
import java.util.ArrayList;

public class StudentMenu {

	@FXML
	private Button myAccountButton;
	@FXML
	private Button logOut;
	@FXML
	private Button showTasks;
	@FXML
	private Button[] solveButton = new Button[5];
	@FXML
	private TextField[] taskField = new TextField[5];
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

	public static int sessionUserID;
	public static DataFromDatabase data;
	ArrayList<Problem> problems = new ArrayList<>();
	private int currentPage = 1;
	static Scene scene;

	public void setScene(ActionEvent event, DataFromDatabase data, int userId) {

		this.data = data;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("studentMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		initializeChoiceBoxes();
		sessionUserID = userId;
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void goToMyAcc(ActionEvent event) throws IOException {

		MyAccountStudent myacc = new MyAccountStudent();
		myacc.setScene(event, data, sessionUserID);
	}

	public void logOut(ActionEvent event) throws Exception {
		LogIn login = new LogIn();
		login.setScene(event, data);
	}

	public void solve(ActionEvent event) throws Exception {

		for (int i = 0; i < 5; i++) {
			
			String problemTask = this.problems.get((currentPage - 1) * 5 + i + 1).getTask();
			int problemID = this.problems.get((currentPage - 1) * 5 + i + 1).getProblemID();
			
			SolveProblem solveProblem = new SolveProblem();
			solveProblem.setScene(event, data, problemTask, problemID, sessionUserID);
		}
	}

	public void previousPage(ActionEvent event) {
		if (currentPage > 1) {
			currentPage--;
			showPage();
		}
	}

	public void nextPage(ActionEvent event) {

		currentPage++;
		showPage();
	}

	public void showTasks(ActionEvent event) {

		problems = data.getProblemsByFiltersFromStudentID(subject.getValue(), section.getValue(),
				difficulty.getValue());

		showFilteredTasks();
	}

	public void initializeChoiceBoxes() {

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
		problems = data.getInitialListOfProblems();
		showPage();

		System.out.println(problems.size());

		for (int i = 0; i < problems.size(); i++) {
			System.out.println(i + " -----> " + problems.get(i).getTask());
		}
	}

	public void showFilteredTasks() {

		if (problems.size() > 0) {

			currentPage = 1;
			showPage();
		}
	}

	public void showPage() {

//		System.out.println(problems.size());
//
//		for (int i = 0; i < problems.size(); i++) {
//			System.out.println(i + " -----> " + problems.get(i).getTask());
//		}

		for (int i = 0; i < 5; i++) {

//			try {
//				System.out.println(problems.get((currentPage - 1) * 5 + i).getTask());
//			} catch (Exception ex) {
//				System.out.println((currentPage - 1) * 5 + i);
//			}

			// if (problems.size() % 5 >= i + 1) {
			try {

				taskField[i] = (TextField) scene.lookup(new StringBuilder("#task").append(i + 1).toString());
				taskField[i].setText(problems.get((currentPage - 1) * 5 + i).getTask());

			} catch (Exception ex) {
				taskField[i] = (TextField) scene.lookup(new StringBuilder("#task").append(i + 1).toString());
				taskField[i].setText("");
			}
			// }
		}
	}
}
