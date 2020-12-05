package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {

    //DataFromDatabase data;
    // public Controller(DataFromDatabase data){
    //     this.data=data;
    // }

    public void setScene(ActionEvent event){

        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

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
