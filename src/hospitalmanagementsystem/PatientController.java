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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dannylam
 */
public class PatientController implements Initializable {

    //fillDoctorCBox();
    
    //add patient page controllers
    @FXML
    private TextField txtPatID;
    @FXML
    private TextField txtPatFirst;
    @FXML
    private TextField txtPatLast;
    @FXML
    private TextField txtPatAge;
    @FXML
    private Label displayGender;
    @FXML
    private ComboBox CBoxProblem;
    @FXML
    private ComboBox CBoxRoom;
    @FXML
    private ComboBox CBoxDoctor;
    @FXML
    private Button checkInPatient;
    @FXML
    private Button updatePatient;
    @FXML
    private Button checkOutPatient;
    @FXML
    private Button goBack;
    @FXML 
    private ToggleGroup genderGroup;
    //PATIENT TABLE
    @FXML
    private TableView<Patient> tablePatient;
    @FXML
    private TableColumn<Patient, Integer> col_patID;
    @FXML
    private TableColumn<Patient, String> col_patFirst;
    @FXML
    private TableColumn<Patient, String> col_patLast;
    @FXML
    private TableColumn<Patient, String> col_patGender;
    @FXML
    private TableColumn<Patient, Integer> col_patAge;
    @FXML
    private TableColumn<Patient, String> col_patProblem;
    @FXML
    private TableColumn<Patient, Integer> col_patRoom;
    @FXML
    private TableColumn<Patient, Integer> col_patDoctor;
    
    
    
    //declarations
    private Connection conn = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private ObservableList<Patient> patientList;
    
    //create obserable list for medical problems
    ObservableList<String> problemList =FXCollections.observableArrayList(
					"Abdominal Pain",
                                        "Back Pain",
                                        "Breathing",
					"Chest Pain",
					"Headache",
					"Infection",
                                        "Laceration",
                                        "Stroke Symptoms",
                                        "Trauma"
					);
    //create OL for room numbers
    ObservableList<String> roomList =FXCollections.observableArrayList(
					"1",
                                        "2",
                                        "3",
					"4",
					"5",
					"6",
                                        "7",
                                        "8",
                                        "9"
					);
    
    
    //CHECK IN PATIENT to the Hospital 
    public void checkInAction (ActionEvent e)throws IOException{
        
        //declare strings
        String patientID = txtPatID.getText();
        String patientFirst = txtPatFirst.getText();
        String patientLast = txtPatLast.getText();
        String patientAge = txtPatAge.getText();
        String patientProblem = (String) CBoxProblem.getValue();
        String patientRoom = (String) CBoxRoom.getValue();
        String patientDoctor = (String) CBoxDoctor.getValue();
         
        //select gender from radio buttons
        String patientGender="";
        if (genderGroup.getSelectedToggle() !=null){
            RadioButton button = (RadioButton) genderGroup.getSelectedToggle();
            //get text of selected radio button
            patientGender=button.getText();
        }
        //display gender on app
        displayGender.setText(patientGender);
       
        
        //get all inputed data and insert into database
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DBConnect.getConnection();

            Statement stmt=conn.createStatement();

            String insertQuery = "SELECT pID FROM patient";

            prst = conn.prepareStatement(insertQuery);
            rs = prst.executeQuery();
            while (rs.next()){
                String patID = rs.getString("pID");
                if(patID.equals(patientID)){
                    MessagePopup.display("Checking in...", "SORRY! ID is taken. Try a new one.");
                }
            }

            stmt.executeUpdate("INSERT INTO patient VALUES ('"+patientID+"', '"
                                                            +patientFirst+"','" 
                                                            +patientLast+ "','" 
                                                            +patientAge+ "','"
                                                            +patientGender+ "','" 
                                                            +patientProblem+ "', '" 
                                                            +patientRoom+ "', '"
                                                            +patientDoctor+"')");
            //popup when a patient is added
            MessagePopup.display("Checking in...", "Patient has been checked into the hospital!");
            
         conn.close();
        }
        catch(Exception ex){ 
            System.out.println(ex);
            MessagePopup.display("Checking in...", "Please enter a patient ID.");
        }
        
        //TESTING CODE
        System.out.println(patientID+", "
                           +patientFirst+", " 
                           +patientLast+ ", " 
                           +patientAge+ ", "
                           +patientGender+ ", " 
                           +patientProblem+ ", " 
                           +patientRoom);
        
        
        //refresh table when patient added
        loadPatientTable();
        loadPatientData();
    }
    
    //UPDATE AN EXISTING PATIENT
    public void updatePatientAction(ActionEvent e)throws IOException{
        
        //declare strings
        String patientID = txtPatID.getText();
        String patientFirst = txtPatFirst.getText();
        String patientLast = txtPatLast.getText();
        String patientAge = txtPatAge.getText();
        String patientProblem = (String) CBoxProblem.getValue();
        String patientRoom = (String) CBoxRoom.getValue();
        String patientDoctor = (String) CBoxDoctor.getValue();
        
        //select gender from radio buttons
        String patientGender="";
        if (genderGroup.getSelectedToggle() !=null){
            RadioButton button = (RadioButton) genderGroup.getSelectedToggle();
            //get text of selected radio button
            patientGender=button.getText();
        }
        //display gender on app
        displayGender.setText(patientGender);
        
        //SQL UPDATE query
        String updateQuery = "UPDATE patient "
                           + "SET pFirst = ?, pLast = ?, pAge = ?, pGender = ?, pProblem = ?, roomID = ?, pDoctor = ? "
                           + "WHERE pID = ? AND pID <> 0;";
        
        //connecting to database to update patient data
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DBConnect.getConnection();

            Statement stmt=conn.createStatement();
            prst = conn.prepareStatement(updateQuery);
            prst.setString(1,patientFirst);
            prst.setString(2,patientLast);
            prst.setString(3,patientAge);
            prst.setString(4,patientGender);
            prst.setString(5,patientProblem);
            prst.setString(6,patientRoom);
            prst.setString(7,patientDoctor);
            prst.setString(8,patientID);

            int i = prst.executeUpdate();
            if(i==1) {
                MessagePopup.display("Updating...", "Update Complete!");
            }
        }
        catch(Exception ex){ 
            System.out.println(ex);
        }
        
        //refresh table when patient updated
        loadPatientTable();
        loadPatientData();
    }
        
    //CHECK OUT A PATIENT from the Hospital
    public void checkOutAction (ActionEvent e) throws IOException{

        //declare connections
        String patientID = txtPatID.getText();

        //SQL DELETE query
        String deleteQuery = "DELETE FROM patient WHERE pID = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DBConnect.getConnection();

            Statement stmt=conn.createStatement();
            prst = conn.prepareStatement(deleteQuery);
            prst.setString(1,patientID);
            int i = prst.executeUpdate();
            if(i==1)
            MessagePopup.display("Checking out...", "Patient has been checked out of hospital!");

        }
        catch(Exception ex){
            System.out.println(ex);
            MessagePopup.display("Checking out...", "This Patient ID doesn't exist.");
        }
        
        //refresh table when patient deleted
        loadPatientTable();
        loadPatientData();
    }
    
    //Gives the patient the recommended treatment to help health problem
    //press treatment button to get popup
    public void treatmentAction (ActionEvent e) throws IOException{
        
        String patientProblem = (String) CBoxProblem.getValue();
        
        switch (patientProblem){
            case "Abdominal Pain":    
                MessagePopup.display("Abdominal Pain Treatment", "Acetaminophen, Antacid, Antibiotics");
                break;
            case "Back Pain": 
                MessagePopup.display("Back Pain Treatment", "Narcotics, PT Exercise, Laminectomy");
                break; 
            case "Breathing": 
                MessagePopup.display("Breathing Treatment", "Inhaler, Nebulizers");
                break; 
            case "Chest Pain": 
                MessagePopup.display("Chest Pain Treatment", "Angioplasty, Blood Thinners, Bypass Surgery");
                break; 
            case "Headache": 
                MessagePopup.display("Headache Treatment", "Ibuprofen, Naproxen, Indomethacin");
                break; 
            case "Infection": 
                MessagePopup.display("Infection Treatment", "Tests then prescribe Antibiotics");
                break;
            case "Laceration": 
                MessagePopup.display("Laceration Treatment", "Put pressure to the bleeding, Clean injury");
                break;
            case "Stroke Symptoms": 
                MessagePopup.display("Stroke Treatment", "Alteplase(tPA), Catheter to remove blood clot");
                break;
            case "Truama": 
                MessagePopup.display("Truama Treatment", "Probably Surgery...");
                break;
            default: 
                MessagePopup.display("Treatment...", "NONE!!");
        }
        
        
        
    }
    
    
    
    //GO BACK to home page
    public void goBackAction(ActionEvent e) throws IOException {
        goBack.getScene().getWindow().hide();
           
        Stage back = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        back.setScene(scene);
        back.show();
        back.setResizable(false);
    }
    
    
    //FILL DOCTOR LAST NAME ---> COMBOBOX
    public void fillDoctorCBox(){
       
        try {
            ObservableList <String> fillDoctor = FXCollections.observableArrayList();
            String query = "SELECT * FROM doctor";
            conn = DBConnect.getConnection();
            prst = conn.prepareStatement(query);
            rs = prst.executeQuery();
            
            while(rs.next()){
                fillDoctor.add("Dr. "+rs.getString("drLast"));
                CBoxDoctor.setItems(fillDoctor);
            }
            prst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HospitalManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //these will populate the list into the comboboxes
        CBoxProblem.setItems(problemList);
        CBoxRoom.setItems(roomList);
        fillDoctorCBox();
        
        //start OList
        patientList = FXCollections.observableArrayList();
        
        //run these methods to load table at start of page
        loadPatientTable();
        loadPatientData();
    } 
    
    //then we LOAD THE DATA onto the tableview
    private void loadPatientTable() {
        
        col_patID.setCellValueFactory(new PropertyValueFactory<>("pID"));
        col_patFirst.setCellValueFactory(new PropertyValueFactory<>("pFirst"));
        col_patLast.setCellValueFactory(new PropertyValueFactory<>("pLast"));
        col_patGender.setCellValueFactory(new PropertyValueFactory<>("pGender"));
        col_patAge.setCellValueFactory(new PropertyValueFactory<>("pAge"));
        col_patProblem.setCellValueFactory(new PropertyValueFactory<>("pProblem"));
        col_patRoom.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        col_patDoctor.setCellValueFactory(new PropertyValueFactory<>("pDoctor"));
        
        //set data to tableview
        tablePatient.setItems(patientList);
    }
    
    //LOAD PATIENT DATA from database
    private void loadPatientData() {
        patientList.clear();
        try {
            conn= DBConnect.getConnection();
            prst = conn.prepareStatement("SELECT * FROM patient");
            rs = prst.executeQuery();
            while(rs.next()){
                patientList.add(new Patient(rs.getInt("pID"),rs.getString("pFirst"),
                                          rs.getString("pLast"),rs.getString("pGender"),
                                          rs.getInt("pAge"), rs.getString("pProblem"),
                                          rs.getInt("roomID"), rs.getString("pDoctor")));
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    
}
