package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIn {

    //DataFromDatabase data;
    // public Controller(DataFromDatabase data){
    //     this.data=data;
    // }


    @FXML
    public void pressLogIn(ActionEvent event) throws IOException {
        // if(data.validateSignIn(username.getText(),password.getText())!=null){
        StudentMenu studentMenu =new StudentMenu();
        studentMenu.setScene(event);
         }
       // else{
        //implement popup sign in invalid
      // }



    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    public void pressSignUp(ActionEvent event) throws IOException {
        SignUp signUp=new SignUp();
        signUp.setScene(event);
    }

}

