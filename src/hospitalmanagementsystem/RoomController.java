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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * 
 * @author dannylam
 */
public class RoomController implements Initializable {
    
    @FXML
    private Button goBack;
    @FXML
    private TableView<Room> tableRoom;
    @FXML
    private TableColumn<Room, Integer> roomID;
    @FXML
    private TableColumn<Room, String> roomType;
    @FXML
    private TableColumn<Room, String> roomPatient;
    
    //declarations
    private Connection conn = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private ObservableList<Room> roomList;
    
    //go back to home page
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
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(conn);
        
         //start OList
        roomList = FXCollections.observableArrayList();
        
        //run these methods to load table at start of page to load table
        loadRoomData();
        loadRoomTable();
        
    }  


    
    //then we LOAD THE DATA onto the tableview
    private void loadRoomTable() {
        
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomPatient.setCellValueFactory(new PropertyValueFactory<>("roomPatient"));
    
        //set data to tableview
        tableRoom.setItems(roomList);
        
    }

    //load doctor data
    private void loadRoomData() {
        roomList.clear();
        try {
            conn= DBConnect.getConnection();
            prst = conn.prepareStatement("SELECT * FROM room");
            rs = prst.executeQuery();
            while(rs.next()){
                roomList.add(new Room(rs.getInt("roomID"),rs.getString("roomType"),
                                          rs.getString("roomPatient")));
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}