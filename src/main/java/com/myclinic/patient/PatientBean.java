/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.patient;

/**
 *
 * @author Rohan
 */
public class PatientBean {
    private String first_name;
    private String last_name;
    private String birth_date;
    private String user_name;
    private String password;
    private String address;
    private String email_ID;
    private String phone_num;
    private int patient_id;
    
    public void setPatientID(int patient_id){
    	this.patient_id = patient_id;
    }
    
    public int getPatientID(){
    	return patient_id;
    }
    
    public void setFirstName(String first_name){
        this.first_name = first_name;
    }
    
    public String getFirstName(){
        return first_name;
    }
    
    public void setLastName(String lsat_name){
        this.last_name = lsat_name;
    }
    
    public String getLastName(){
        return last_name;
    }
    
    public void setBirthDate(String birth_date){
        this.birth_date = birth_date;
    }
    
    public String getBirthDate(){
        return birth_date;
    }
    
    public void setEmailID(String email_ID){
        this.email_ID = email_ID;
    }
    
    public String getEmailID(){
        return email_ID;
    }
    
    public void setPhoenNum(String phone_num){
        this.phone_num = phone_num;
    }
    
    public String getPhoneNum(){
        return phone_num;
    }
    
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    
    public String getUserName(){
    	return user_name;
        
    }
        
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
        
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getAddress(){
        return address;
    }
    
}
