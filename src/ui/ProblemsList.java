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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import problem.Problem;

import java.io.IOException;
import java.util.ArrayList;

public class ProblemsList {

    DataFromDatabase data;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Label grade;
    String gradeString=new String("grade for task from DB");
    @FXML
    private TextArea taskDescription;
    String taskString=new String("task description from DB");
    @FXML
    private TextArea studentSolution;
    String solutionString=new String("student solution from DB");
    @FXML
    private TextArea tutorFeedback;
    String feedbackString=new String("feedback from DB");

    int currentPostion=0;
    ArrayList<Problem>problems=new ArrayList<>();

    public void setScene(ActionEvent event, DataFromDatabase data) throws Exception {

        this.data=data;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("problemsList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        initialize(scene);
        window.show();

    }

    public void initialize(Scene scene) throws Exception{
        problems= data.getSolvedProblemsForStudent();
        showInitialSolvedTasks(scene);

    }
    public void showInitialSolvedTasks(Scene scene) throws Exception {
        if (problems.size()==0)
            System.out.println("No problems that are solved yet"); //de implementat ceva eroare
        else showTaskForNumber(currentPostion,scene);
    }

    public void showTaskForNumber(int taskNr,Scene scene) throws Exception{

        grade=(Label)scene.lookup("#occupation");
        grade.setText("");//get grade for solved problem number taskNR

        taskDescription=(TextArea)scene.lookup("#taskDescription");
        taskDescription.setText("");//get task description for solved problem nr taskNR

        studentSolution=(TextArea)scene.lookup("#studentSolution");
        studentSolution.setText("");//get student solution for solved problem nr taskNR

        tutorFeedback=(TextArea)scene.lookup("#tutorFeedback");
        tutorFeedback.setText("");//get tutor feedback for solved problem nr taskNR;

        grade=(Label)scene.lookup("#grade");
        grade.setText("");//get grade fo

        if (currentPostion==problems.size())
            next.setVisible(false);

        if (currentPostion==0)
            prev.setVisible(false);

        currentPostion++;

    }

    public void nextButton(ActionEvent event,Scene scene) throws Exception{
            showTaskForNumber(currentPostion,scene);
    }

    public void prevButton(ActionEvent event,Scene scene)throws Exception{
        currentPostion--;
        showTaskForNumber(currentPostion,scene);
    }


    public void goToMyAcc(ActionEvent event) throws Exception{
        MyAccountStudent myAcc =new MyAccountStudent();
        myAcc.setScene(event,data);
    }

}
