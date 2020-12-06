package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAccountTutor {

    @FXML
    private Label name;
    String nameString=new String("name from DB");
    @FXML
    private Label occupation;
    String occupString=new String("occup from DB");
    @FXML
    private Label country;
    String countryString=new String("country from DB");
    @FXML
    private Label email;
    String emailString=new String("email from DB");
    @FXML
    private Label date_joined;
    String date_joinedString=new String("date from DB");
    @FXML
    private Label allTimeRating;
    String allTimeRatingString=new String("alltime rate from DB");
    @FXML
    private Label sentTasks;
    String sentTasksString=new String("nr sent tasks from DB");
    @FXML
    private Label feedbackGiven;
    String feedbackGivenString=new String("feedbackGiven nr from DB");
    @FXML
    private ProgressBar moderatorProgress;
    @FXML
    private Button goBack;
    DataFromDatabase data;

    public void setScene(ActionEvent event,DataFromDatabase data){

        this.data=data;
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("myAccountTutor.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        initializeText(scene);
        window.show();
    }

    private void initializeText(Scene scene){
        name=(Label)scene.lookup("#name");
        name.setText(nameString);

        occupation=(Label)scene.lookup("#occupation");
        occupation.setText(occupString);

        country=(Label)scene.lookup("#country");
        country.setText(countryString);

        email=(Label)scene.lookup("#email");
        email.setText(emailString);

        date_joined=(Label)scene.lookup("#date_joined");
        date_joined.setText(date_joinedString);

        allTimeRating=(Label)scene.lookup("#allTimeRating");
        allTimeRating.setText(allTimeRatingString);

        sentTasks=(Label)scene.lookup("#sentTasks");
        sentTasks.setText(sentTasksString);

        allTimeRating=(Label)scene.lookup("#allTimeRating");
        allTimeRating.setText(allTimeRatingString);

        feedbackGiven=(Label)scene.lookup("#feedbackGiven");
        feedbackGiven.setText(feedbackGivenString);

        moderatorProgress=(ProgressBar)scene.lookup("#moderatorProgress");
        moderatorProgress.setProgress(0.5);
        //aici trebuie sa pui progress in functie de ce constrainturi am stabilit
        //,i.e cat ii mai ia sa ajunga un moderator
    }

    public void backToMain(ActionEvent event){
        TutorMenu tutorMenu =new TutorMenu();
        tutorMenu.setScene(event,data);
    }
}
