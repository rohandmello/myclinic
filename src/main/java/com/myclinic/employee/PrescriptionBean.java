/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.employee;
/**
 *
 * @author Rohan
 */
public class PrescriptionBean {
    private String medicine_name;
    private String date;
    private String consumption_duration;
    private String consumption_per_day;
    private String comments;
    private int refill; 
    private int prescription_id;
    
    public void setPrescriptionID(int prescritpion_id){
    	this.prescription_id = prescritpion_id;
    }
    
    public int getPrescriptionID(){
    	return prescription_id;
    }
    
    public void setMedicineName(String medicineName){
        this.medicine_name = medicineName;
    }
    
    public String getMedicineName(){
        return medicine_name;
    }
    
    public String getPrescDate(){
        return date;
    }
    
    public void setPrescDate(String getdate){
        this.date = getdate;
    }
    
    public void setDuration(String duration){
        this.consumption_duration = duration;
    }
    
    public String getDuration(){
        return consumption_duration;
    }
    
    public void setConsumptioPerDay(String consumptionPerDay){
        this.consumption_per_day = consumptionPerDay;
    }
    
    public String getConsumptioPerDay(){
        return consumption_per_day;
    }
    
    public void setComments(String comments){
        this.comments = comments;
    }
    
    public String getComments(){
        return comments;
    }
    
    public void setRefill(int refill){
    	this.refill = refill;
    }
    
    public int getRefill(){
    	return refill;
    }

}

