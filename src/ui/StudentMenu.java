package ui;

import database.DataFromDatabase;
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
import problem.Problem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentMenu {

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

	private DataFromDatabase data;
	ArrayList<Problem> problems = new ArrayList<>();
	private int nrOfPages = 0;
	private int currentPage = 1;
	Scene scene;

	public void setScene(ActionEvent event, DataFromDatabase data) {

		this.data = data;

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("studentMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		initializeChoiceBoxes();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void goToMyAcc(ActionEvent event) throws IOException {

		MyAccountStudent myacc = new MyAccountStudent();
		myacc.setScene(event,data);

	}

	public void logOut(ActionEvent event) throws IOException {
		LogIn login = new LogIn();
		login.setScene(event);
	}

	public void solve1(ActionEvent event) throws IOException {
		int problemID = problems.get((currentPage - 1) * 5 + 0).getProblemID();
		SolveProblem solveProblem = new SolveProblem();
		solveProblem.setScene(event, data,problemID);
	}

	public void solve2(ActionEvent event) throws IOException {
		int problemID = problems.get((currentPage - 1) * 5 + 1).getProblemID();
		SolveProblem solveProblem = new SolveProblem();
		solveProblem.setScene(event, data,problemID);
	}

	public void solve3(ActionEvent event) throws IOException {
		int problemID = problems.get((currentPage - 1) * 5 + 2).getProblemID();
		SolveProblem solveProblem = new SolveProblem();
		solveProblem.setScene(event, data,problemID);
	}

	public void solve4(ActionEvent event) throws IOException {
		int problemID = problems.get((currentPage - 1) * 5 + 3).getProblemID();
		SolveProblem solveProblem = new SolveProblem();
		solveProblem.setScene(event, data,problemID);
	}

	public void solve5(ActionEvent event) throws IOException {
		int problemID = problems.get((currentPage - 1) * 5 + 4).getProblemID();
		SolveProblem solveProblem = new SolveProblem();
		solveProblem.setScene(event, data,problemID);
	}

	public void pressedPrevious(ActionEvent event) {
		if (currentPage > 1) {
			currentPage--;
			showPage();
		}
	}

	public void pressedNext(ActionEvent event) {
		if (currentPage < nrOfPages) {
			currentPage++;
			if (currentPage + 1 == nrOfPages) {
				showLastPage();
			} else {
				showPage();
			}
		}
	}

	public void showTasks(ActionEvent event){

		problems=database.getProblemsByFiltersFromStudentID(String subject.getValue(), Integer section.getValue(), String difficulty.getValue());//implement in backend
		showInitialTasks();
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
		problems = database.getInitialListOfProblems();// implement in backend
		showInitialTasks();

	}

	public void showInitialTasks() {

		if (problems.size() > 0) {
			nrOfPages = (problems.size() - 1) / 5 + 1;
			if (nrOfPages == 1) {
				showLastPage();
			}
		}
	}

	public void showPage() {
		task1 = (TextField) scene.lookup("#task1");
		task1.setText(problems.get((currentPage - 1) * 5 + 0).getTask());
		task2 = (TextField) scene.lookup("#task2");
		task2.setText(problems.get((currentPage - 1) * 5 + 1).getTask());
		task3 = (TextField) scene.lookup("#task3");
		task3.setText(problems.get((currentPage - 1) * 5 + 2).getTask());
		task4 = (TextField) scene.lookup("#task4");
		task4.setText(problems.get((currentPage - 1) * 5 + 3).getTask());
		task5 = (TextField) scene.lookup("#task5");
		task5.setText(problems.get((currentPage - 1) * 5 + 4).getTask());
	}

	public void showLastPage() {
		task1 = (TextField) scene.lookup("#task1");
		task1.setText(problems.get((currentPage - 1) * 5 + 0).getTask());

		if (problems.size() % 5 >= 2) {
			task2 = (TextField) scene.lookup("#task2");
			task2.setText(problems.get((currentPage - 1) * 5 + 1).getTask());
		}
		if (problems.size() % 5 >= 3) {
			task3 = (TextField) scene.lookup("#task3");
			task3.setText(problems.get((currentPage - 1) * 5 + 2).getTask());
		}
		if (problems.size() % 5 >= 4) {
			task4 = (TextField) scene.lookup("#task4");
			task4.setText(problems.get((currentPage - 1) * 5 + 3).getTask());
		}
		if (problems.size() % 5 >= 5) {
			task5 = (TextField) scene.lookup("#task5");
			task5.setText(problems.get((currentPage - 1) * 5 + 4).getTask());
		}
	}

}
