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
public class Patient {
    
    //declarations
    int pID;
    String pFirst; 
    String pLast;
    String pGender;
    int pAge;
    String pProblem;
    int roomID;
    String pDoctor; 
    
    //constructor
    public Patient(int pID, String pFirst, String pLast, String pGender, int pAge, String pProblem, int roomID, String pDoctor) {
        this.pID = pID;
        this.pFirst = pFirst;
        this.pLast = pLast;
        this.pGender = pGender;
        this.pAge = pAge;
        this.pProblem = pProblem;
        this.roomID = roomID;
        this.pDoctor = pDoctor;
    }
 
    
    //getters and setters
    public int getPID() {
        return pID;
    }

    public void setPID(int pID) {
        this.pID = pID;
    }

    public String getPFirst() {
        return pFirst;
    }

    public void setPFirst(String pFirst) {
        this.pFirst = pFirst;
    }

    public String getPLast() {
        return pLast;
    }

    public void setPLast(String pLast) {
        this.pLast = pLast;
    }

    public String getPGender() {
        return pGender;
    }

    public void setPGender(String pGender) {
        this.pGender = pGender;
    }

    public int getPAge() {
        return pAge;
    }

    public void setPAge(int pAge) {
        this.pAge = pAge;
    }

    public String getPProblem() {
        return pProblem;
    }

    public void setPProblem(String pProblem) {
        this.pProblem = pProblem;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getPDoctor() {
        return pDoctor;
    }

    public void setPDoctor(String pDoctor) {
        this.pDoctor = pDoctor;
    }
    
}
