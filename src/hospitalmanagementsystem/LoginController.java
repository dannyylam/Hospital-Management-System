/*
 * If MySQL time zone is off, run this in MySQL workbench: 

            SET GLOBAL time_zone = '+3:00';

 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Danny
 */
public class LoginController implements Initializable {
    //add Login page controllers
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button forgotPW;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //log user in system when login button is pressed
    @FXML
    public void loginAction(ActionEvent e) throws IOException{
        //get user input
        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();
        //uses check to see if the data is correct to login successfully
        if(check(username,password)){
            MessagePopup.display("Login Status", "Login Success!");
             
            login.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            stage.setTitle("Hospital Management System");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
        }
        else{
            MessagePopup.display("Login Status", "Username/password is wrong. Try again!");
        }
        
        
        /*
        //TESTINGGGGG
        MessagePopup.display("Login Status", "Login Success!");
        login.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            stage.setTitle("Hospital Management System");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
        */
        
    }
    
    //this will check to see if the user account is stored in the database
    public static boolean check(String usrnm, String psswrd){
         PreparedStatement pst = null;
         ResultSet rs = null;
         String query = "SELECT username, password FROM admin WHERE username=? AND password=?";
         
         try{
             //get a connection with MySQL 'hospital' database
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DBConnect.getConnection();
             pst = conn.prepareStatement(query); 
             pst.setString(1,usrnm);
             pst.setString(2,psswrd);
             rs = pst.executeQuery();
             
             if(rs.next()){
                 return true;
             }
             else{
                 return false;
             }
         }catch (Exception e){
             return false;
         }
    }
    
    //new frame to register a new user when "Register" button clicked
    @FXML
    public void registerButtonPushed(ActionEvent e) throws IOException {
        register.getScene().getWindow().hide();
           
        Stage register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        register.setScene(scene);
        register.show();
        register.setResizable(false);
    }
}
