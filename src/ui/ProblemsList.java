package ui;

import database.DataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ProblemsList {

    DataFromDatabase data;
    @FXML
    Button back;
    public void setScene(ActionEvent event, DataFromDatabase data) {

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
        window.show();

    }
    public void backToMyAcc(ActionEvent event) throws Exception{
        MyAccountStudent myAcc =new MyAccountStudent();
        myAcc.setScene(event,data);
    }

}
