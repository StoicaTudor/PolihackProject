package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField country;
    @FXML
    private RadioButton student;
    @FXML
    private RadioButton tutor;



    public void setScene(ActionEvent event) {
        Parent menu=null;
        try {
            menu = FXMLLoader.load(getClass().getResource("signup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(menu);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void pressButtonScene2(ActionEvent event) throws IOException {

        if(student.isSelected()){
        StudentMenu studentMenu =new StudentMenu();
        studentMenu.setScene(event);
        }
        else{
            TutorMenu tutorMenu=new TutorMenu();
            tutorMenu.setScene(event);
        }
    }


}

