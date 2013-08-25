package com.myclinic.patient;

public class AppointmentBean {
	String date =  null;
	String time = null;
	String docnmae = null;
	String paymentStatus = null;
	int docid = 0;
	int appid = 0;
	
	public void setAppID(int appid){
		this.appid = appid;
	}
	
	public int getAppID(){
		return appid;
	}
	
	public void setAppDate(String date){
		this.date = date;
	}
	
	public String getAppDate(){
		return date;
	}
	
	public void setAppTime(String time){
		this.time = time;
	}
	
	public String getAppTime(){
		return time;
	}
	
	public void setDocName(String docname){
		this.docnmae = docname;
	}
	
	public String getDocName() {
		return docnmae;
	}
	
	public void setDocID(int docid){
		this.docid = docid;
	}
	
	public int getDocID() {
		return docid;
	}
	
	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}
	
	public String getPaymentStatus(){
		return paymentStatus;
	}
}
