/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author dannylam
 */
public class TableController implements Initializable {

    //DOCTOR TABLE
    @FXML
    private TableView<?> tableDoctor;
    @FXML
    private TableColumn<?,?> col_drID;
    @FXML
    private TableColumn<?,?> col_drFirst;
    @FXML
    private TableColumn<?,?> col_drLast;
    @FXML
    private TableColumn<?,?> col_drGender;
    @FXML
    private TableColumn<?,?> col_drAge;
    @FXML
    private TableColumn<?,?> col_drSpecialization;
    @FXML
    private TableColumn<?,?> col_drSalary;
    
    //PATIENT TABLE
    @FXML
    private TableView<?> tablePatient;
    @FXML
    private TableColumn<?,?> col_patID;
    @FXML
    private TableColumn<?,?> col_patFirst;
    @FXML
    private TableColumn<?,?> col_patLast;
    @FXML
    private TableColumn<?,?> col_patGender;
    @FXML
    private TableColumn<?,?> col_patAge;
    @FXML
    private TableColumn<?,?> col_patProblem;
    @FXML
    private TableColumn<?,?> col_patRoom;
    @FXML
    private TableColumn<?,?> col_patDoctor;
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
