/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class RegisterController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML 
    private TextField registerEmail;
    @FXML
    private Button cancel;
    @FXML
    private Button registerButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void registerAction(ActionEvent e) throws IOException {
    
        //name fxml code to String
        String username = registerUsername.getText();
        String password = registerPassword.getText();
        String email = registerEmail.getText();
        try{
            //GET CONNECTION
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DBConnect.getConnection();
            Statement stmt=conn.createStatement();
            //saving data onto database 
            stmt.executeUpdate("INSERT INTO admin VALUES (adminID,'"+username+"','"+password+"','"+email+"')");
            MessagePopup.display("Registration Status", "Registration Complete!");

            registerButton.getScene().getWindow().hide();

            Stage register = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            register.setTitle("Hospital Management System");
            register.setScene(scene);
            register.show();
            register.setResizable(false);
            conn.close();

        }catch(Exception ex){ 
            System.out.println(ex);
        }

    
    }
    
    @FXML
    public void cancelAction(ActionEvent e) throws IOException {
        cancel.getScene().getWindow().hide();
           
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        login.setTitle("Hospital Management System");
        login.setScene(scene);
        login.show();
        login.setResizable(false);
         
    }
    
    
}
