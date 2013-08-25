package com.myclinic.employee;

public class PaymentsBean {
	int invoice_no;
	String amount;
	String method;
	String stauts;
	
	public void setInvoiceNo(int invoice_no){
		this.invoice_no = invoice_no;
	}
	
	public int getInvoiceNo(){
		return invoice_no;
	}
	
	public void setAmount(String amount){
		this.amount = amount;
	}
	
	public String getAmount(){
		return amount;
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
	public String getMethod(){
		return method;
	}
	
	public void setStatus(String status){
		this.stauts = status;
	}
	
	public String getStatus(){
		return stauts;
	}
}
