/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.employee;

/**
 *
 * @author Rohan
 */
public class DoctorBean {
    private String first_name;
    private String last_name;
    private String designation;
    private int employee_ID;
    private int docID;
    
    public void setEmployeeID(int empID){
        this.employee_ID = empID;
    }
    
    public int getEmployeeID(){
        return employee_ID;
    }
    
    public void setFirstName(String f_name){
        this.first_name = f_name;
    }
    
    public String getFirstName(){
        return first_name;
    }
    
    public void setLastName(String l_name){
        this.last_name = l_name;
    }
    
    public String getLastName(){
        return last_name;
    }
    
    public void setDesignation(String desig){
        this.designation = desig;
    }
    
    public String getDesignation(){
        return designation;
    }
    
    public int getDoctorID(){
        return docID;
    }
    
    public void setDocID(int docID){
        this.docID = docID;
    }
}
