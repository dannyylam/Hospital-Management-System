/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dannylam
 */
public class DoctorController implements Initializable {

    //add doctor page controllers
    @FXML
    private TextField txtDrID;
    @FXML
    private TextField txtDrFirst;
    @FXML
    private TextField txtDrLast;
    @FXML
    private TextField txtDrAge;
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private Label displayGender;
    @FXML
    private ComboBox<String> CBoxSpecialization;
    @FXML
    private TextField txtSalary;
    @FXML
    private Button hireDoctor;
    @FXML
    private Button updateDoctor;
    @FXML
    private Button fireDoctor;
    @FXML
    private Button goBack;
    @FXML 
    private ToggleGroup genderGroup;
    //DOCTOR TABLE
    @FXML
    private TableView<Doctor> tableDoctor;
    @FXML
    private TableColumn<Doctor, Integer> col_drID;
    @FXML
    private TableColumn<Doctor, String> col_drFirst;
    @FXML
    private TableColumn<Doctor, String> col_drLast;
    @FXML
    private TableColumn<Doctor, String> col_drGender;
    @FXML
    private TableColumn<Doctor, Integer> col_drAge;
    @FXML
    private TableColumn<Doctor, String> col_drSpecialization;
    @FXML
    private TableColumn<Doctor, Integer> col_drSalary;
    
    //declarations
    private Connection conn = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private ObservableList<Doctor> doctorList;
    
    
    //create observable list for medical specializations
    ObservableList<String> specialList =FXCollections.observableArrayList(
					"Anesthesiology",
					"Cardiology",
					"Dermatology",
					"Emergency Medicine",
					"Family Medicine",					
					"Gynaecology",
					"Neurology",
                                        "Pediatrics",
                                        "Radiology",
                                        "Surgery",
                                        "Urology"
					);
        
    
    //HIRE NEW DOCTOR to the Hospital 
    public void hireDoctorAction (ActionEvent e)throws IOException{
        
        //declare strings
        String doctorID = txtDrID.getText();
        String doctorFirst = txtDrFirst.getText();
        String doctorLast = txtDrLast.getText();
        String doctorAge = txtDrAge.getText();
        String doctorSpecialty = CBoxSpecialization.getValue();
        String doctorSalary = txtSalary.getText();
         
        //select gender from radio buttons
        String doctorGender="";
        if (genderGroup.getSelectedToggle() !=null){
            RadioButton button = (RadioButton) genderGroup.getSelectedToggle();
            //get text of selected radio button
            doctorGender=button.getText();
        }
        //display gender on app
        displayGender.setText(doctorGender);
       
        
        //get all inputed data and insert into database
        try{
            //get connection
            Class.forName("com.mysql.jdbc.Driver");
            conn = DBConnect.getConnection();

            Statement stmt=conn.createStatement();

            String insertQuery = "SELECT drID FROM doctor";

            prst = conn.prepareStatement(insertQuery);
            rs = prst.executeQuery();
            while (rs.next()){
                String drID = rs.getString("drID");
                if(drID.equals(doctorID)){
                    MessagePopup.display("Hiring Proccess...", "SORRY! ID is taken. Try a new one.");
                }
            }
            //System.out.println(Gender);

            stmt.executeUpdate("INSERT INTO doctor VALUES ('"+doctorID+"', '"
                                                            +doctorFirst+"','" 
                                                            +doctorLast+ "','" 
                                                            +doctorAge+ "','"
                                                            +doctorGender+ "','" 
                                                            +doctorSpecialty+ "', '" 
                                                            +doctorSalary+ "')");
            //popup when add doctor
            MessagePopup.display("Hiring Proccess...", "Congrats! Doctor Hired!");
            
        
        }
        catch(Exception ex){ 
            System.out.println(ex);
            //MessagePopup.display("Hiring Proccess...", "Please enter a doctor ID.");
        }
        
        //TESTING CODE
        System.out.println(doctorID+", "
                           +doctorFirst+", " 
                           +doctorLast+ ", " 
                           +doctorAge+ ", "
                           +doctorGender+ ", " 
                           +doctorSpecialty+ ", " 
                           +doctorSalary);
        
        //refresh table data when add doctor
            loadDoctorTable();
            loadDoctorData();
    }
    
    //UPDATE EXISTING DOCTOR
    public void updateDoctorAction(ActionEvent e)throws IOException{
        
        //declare strings
        String doctorID = txtDrID.getText();
        String doctorFirst = txtDrFirst.getText();
        String doctorLast = txtDrLast.getText();
        String doctorAge = txtDrAge.getText();
        String doctorSpecialty = CBoxSpecialization.getValue();
        String doctorSalary = txtSalary.getText();
        
        //select gender from radio buttons
        String doctorGender="";
        if (genderGroup.getSelectedToggle() !=null){
            RadioButton button = (RadioButton) genderGroup.getSelectedToggle();
            //get text of selected radio button
            doctorGender=button.getText();
        }
        //display gender on app
        displayGender.setText(doctorGender);
        
        //SQL UPDATE query
        String updateQuery = "UPDATE doctor "
                           + "SET drFirst=?, drLast=?, drAge=?, drGender=?, drSpecialization=?, drSalary=? "
                           + "WHERE drID=? AND drID <> 0;";
        
        //connecting to database to update doctor data
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DBConnect.getConnection();
            
            Statement stmt=conn.createStatement();
            prst = conn.prepareStatement(updateQuery);
            prst.setString(1,doctorFirst);
            prst.setString(2,doctorLast);
            prst.setString(3,doctorAge);
            prst.setString(4,doctorGender);
            prst.setString(5,doctorSpecialty);
            prst.setString(6,doctorSalary);
            prst.setString(7,doctorID);
            

            int i = prst.executeUpdate(updateQuery);
            if(i==1) {
                MessagePopup.display("Updating...", "Update Complete!");
            }
        }
        catch(Exception ex){ 
            System.out.println(ex);
        }
        //refresh table when update doctor 
        loadDoctorTable();
        loadDoctorData();
        
    }
        
    //FIRE A DOCTOR from the hospital database
    public void fireDoctorAction (ActionEvent e) throws IOException{

        String doctorID = txtDrID.getText();

        //SQL DELETE query
        String deleteQuery = "DELETE FROM doctor WHERE drID = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DBConnect.getConnection();

            Statement stmt=conn.createStatement();
            prst = conn.prepareStatement(deleteQuery);
            prst.setString(1,doctorID);
            int i = prst.executeUpdate();
            if(i==1){
                MessagePopup.display("Firing Doctor..", "Doctor has been fired!");
            }
            

        }
        catch(Exception ex){
            System.out.println(ex);
        }
        //refresh table when doctor deleted
        loadDoctorTable();
        loadDoctorData();
    }
    
    //GO BACK to home page
    public void backAction(ActionEvent e) throws IOException {
        goBack.getScene().getWindow().hide();
           
        Stage back = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        back.setScene(scene);
        back.show();
        back.setResizable(false);
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize  (URL url, ResourceBundle rb) {
         System.out.println(conn);
        
         //start OList
        doctorList = FXCollections.observableArrayList();
       
        //run these methods to load table at start of page
        loadDoctorTable();
        loadDoctorData();
            
            
        //this will populate the list into the combobox
        CBoxSpecialization.setItems(specialList);
        
    }    
    
    
    //then we LOAD THE DATA onto the tableview
    private void loadDoctorTable() {
        
        col_drID.setCellValueFactory(new PropertyValueFactory<>("drID"));
        col_drFirst.setCellValueFactory(new PropertyValueFactory<>("drFirst"));
        col_drLast.setCellValueFactory(new PropertyValueFactory<>("drLast"));
        col_drGender.setCellValueFactory(new PropertyValueFactory<>("drGender"));
        col_drAge.setCellValueFactory(new PropertyValueFactory<>("drAge"));
        col_drSpecialization.setCellValueFactory(new PropertyValueFactory<>("drSpecialization"));
        col_drSalary.setCellValueFactory(new PropertyValueFactory<>("drSalary"));
    
        //set data to tableview
        tableDoctor.setItems(doctorList);
        
    }

    
    //load doctor data
    private void loadDoctorData() {
        doctorList.clear();
        try {
            conn= DBConnect.getConnection();
            prst = conn.prepareStatement("SELECT * FROM doctor");
            rs = prst.executeQuery();
            while(rs.next()){
                doctorList.add(new Doctor(rs.getInt("drID"),rs.getString("drFirst"),
                                          rs.getString("drLast"),rs.getString("drGender"),
                                          rs.getInt("drAge"), rs.getString("drSpecialization"),
                                          rs.getInt("drSalary")));
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
}
