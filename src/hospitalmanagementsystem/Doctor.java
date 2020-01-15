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
public class Doctor {
    //declare variables
    int drID; 
    String drFirst; 
    String drLast;
    String drGender;
    int drAge;
    String drSpecialization;
    int drSalary;

    //add constructor
    public Doctor(int drID, String drFirst, String drLast, String drGender, int drAge, String drSpecialization, int drSalary) {
        this.drID = drID;
        this.drFirst = drFirst;
        this.drLast = drLast;
        this.drGender = drGender;
        this.drAge = drAge;
        this.drSpecialization = drSpecialization;
        this.drSalary = drSalary;
    }

    //add getters and setters
    public int getDrID() {
        return drID;
    }

    public void setDrID(int drID) {
        this.drID = drID;
    }

    public String getDrFirst() {
        return drFirst;
    }

    public void setDrFirst(String drFirst) {
        this.drFirst = drFirst;
    }

    public String getDrLast() {
        return drLast;
    }

    public void setDrLast(String drLast) {
        this.drLast = drLast;
    }

    public String getDrGender() {
        return drGender;
    }

    public void setDrGender(String drGender) {
        this.drGender = drGender;
    }

    public int getDrAge() {
        return drAge;
    }

    public void setDrAge(int drAge) {
        this.drAge = drAge;
    }

    public String getDrSpecialization() {
        return drSpecialization;
    }

    public void setDrSpecialization(String drSpecialization) {
        this.drSpecialization = drSpecialization;
    }

    public int getDrSalary() {
        return drSalary;
    }

    public void setDrSalary(int drSalary) {
        this.drSalary = drSalary;
    }
    
   
    
    
}
