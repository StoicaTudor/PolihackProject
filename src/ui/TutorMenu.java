package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;



public class TutorMenu {

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


    public void setScene(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("tutorMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void myAccPressed (ActionEvent action){
        MyAccountTutor myacc = new MyAccountTutor();
        myacc.setScene(action);
    }
    public void reviewSolutionsPressed (ActionEvent action){
        ReviewSolution review= new ReviewSolution();
        review.setScene(action);

    }
    public void proposeNewPressed (ActionEvent action){
        ProposeProblem propose=new ProposeProblem();
        propose.setScene(action);

    }
    public void feedbackTutorPressed (ActionEvent action){
        ReviewFeedback reviewFeedback=new ReviewFeedback();
        reviewFeedback.setScene(action);

    }
    public void logOutPressed (ActionEvent action){
        LogIn backToSignIn=new LogIn();
        backToSignIn.setScene(action);
    }
}
