/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

/**
 *
 * @author dannylam
 */
public class Room {
    
    //declaration
    int roomID;
    String roomType;
    String roomPatient;
    
    //constructor

    public Room(int roomID, String roomType, String roomPatient) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.roomPatient = roomPatient;
    }
    
    //get and set

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPatient() {
        return roomPatient;
    }

    public void setRoomPatient(String roomPatient) {
        this.roomPatient = roomPatient;
    }
    
}
