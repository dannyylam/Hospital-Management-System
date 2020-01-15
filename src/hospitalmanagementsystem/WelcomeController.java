/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dannylam
 */
public class WelcomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button doctorDetails;
    @FXML
    private Button patientDetails;
    @FXML
    private Button roomDetails;
    @FXML
    private Button logout;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //button will take you to Doctor Details
    @FXML
    public void doctorButtonPushed(ActionEvent e) throws IOException {
        doctorDetails.getScene().getWindow().hide();
           
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    //button will take you to Patient Details
    @FXML
    public void patientButtonPushed(ActionEvent e) throws IOException {
        patientDetails.getScene().getWindow().hide();
           
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    //button will take you to Room Details 
    @FXML
    public void roomButtonPushed(ActionEvent e) throws IOException {
        roomDetails.getScene().getWindow().hide();
           
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Room.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    //button will log you out back to login page
    @FXML
    public void logOutButtonPushed(ActionEvent e) throws IOException {
        logout.getScene().getWindow().hide();
           
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
