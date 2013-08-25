/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.pharmacy;

/**
 *
 * @author Rohan
 */
public class PharmacyBean {
    String pharmacyName;
    String phoneNum;
    String address;
    String userid;
    String emailid;
    int pharmacyid;
    
    public void setPharmacyName(String pharmacyName){
    	this.pharmacyName = pharmacyName;
    }
    
    public String getPharmacyName(){
    	return pharmacyName;
    }
    
    public void setPhoneNum(String phoneNum){
    	this.phoneNum = phoneNum;
    }
    
    public String getPhoneNum(){
    	return phoneNum;
    }
    
    public void setAddress(String address){
    	this.address = address;
    }
    
    public String getAddress(){
    	return address;
    }
    
    public void setUserID(String userid){
    	this.userid = userid;
    }
    
    public String getUserID(){
    	return userid;
    }
    
    public void setEmailID(String emailid){
    	this.emailid = emailid;
    }
    
    public String getEmailID(){
    	return emailid;
    }
    
    public void setPharmacyID(int pharmacyid){
    	this.pharmacyid = pharmacyid;
    }
    
    public int getPharmacyID(){
    	return pharmacyid;
    }
    
}
