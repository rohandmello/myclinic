package com.myclinic.employee;

import com.myclinic.database.Database;
import com.myclinic.patient.PatientBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class PrescriptionDB
 */
@WebServlet("/PrescriptionDB")
public class PrescriptionDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrescriptionDB() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(PrescriptionDB.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        Connection conn = null;
        
        int[] docID = null;
        int patientID = 0;
        String[] userid = null;
        int[] prescriptionID = null;
        int i = 0;
        String sqlQuery = null;
        String date = request.getParameter("pdate");
        Statement statement = null;
        ResultSet resultSet = null;
        Database db = new Database();
        ArrayList<PrescriptionBean> presList = new ArrayList<PrescriptionBean>();
        PatientBean pb = new PatientBean();
        ArrayList<DoctorBean> doctorList = new ArrayList<DoctorBean>();
        try{
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	sqlQuery = "select * from prescription where fk_patientID=(select patient_id from patient where fk_userID='"+session.getAttribute("userid")+"')";
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		i++;
        	}
        	docID = new int[i];
        	prescriptionID = new int[i];
        	userid = new String[i];
        	
        	int k = 0;
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		prescriptionID[k] = resultSet.getInt(1);
        		docID[k] = resultSet.getInt(2);
        		patientID = resultSet.getInt(3);
        		k++;
        	}
        	for (int k2 = 0; k2 < i; k2++) {
	        	sqlQuery = "select fk_userID from doctor where doc_id="+docID[k2];
	        	statement = conn.createStatement();
	        	resultSet = statement.executeQuery(sqlQuery);
	        	while (resultSet.next()) {
	        		userid[k2] = resultSet.getString(1);
				}
        	}
        	for (int k2 = 0; k2 < i; k2++) {
	        	sqlQuery = "select first_name, last_name from employee where fk_userID='"+userid[k2]+"'";
	        	statement = conn.createStatement();
	        	resultSet = statement.executeQuery(sqlQuery);
	        	while (resultSet.next()) {
	        		DoctorBean doctor = new DoctorBean();
	        		doctor.setFirstName(resultSet.getString("first_name"));
	        		doctor.setLastName(resultSet.getString("last_name"));
	        		doctorList.add(doctor);
				}
        	}
        	System.out.println(doctorList.get(0));
        	for (int k2 = 0; k2 < prescriptionID.length; k2++) {
        		if(date == ""){
        			sqlQuery = "select * from prescription_details where fk_prescriptionID="+prescriptionID[k2];
        		}else{
                    System.out.println("String date "+date);
        			sqlQuery = "select * from prescription_details where fk_prescriptionID="+prescriptionID[k2]+" and prescription_date='"+date+"'";
        		}
	        	statement = conn.createStatement();
	        	resultSet = statement.executeQuery(sqlQuery);
	        	
	        	while(resultSet.next()){
	        		PrescriptionBean pres = new PrescriptionBean();
	        		pres.setComments(resultSet.getString("comments"));
	        		pres.setConsumptioPerDay(resultSet.getString("consumption_per_day"));
	        		pres.setDuration(resultSet.getString("consumption_duration"));
	        		pres.setMedicineName(resultSet.getString("medicine_name"));
	        		pres.setPrescDate(resultSet.getString("prescription_date"));
	        		presList.add(pres);
	    		}
        	}
        	session.setAttribute("doctorList", doctorList);
        	session.setAttribute("presriptionList", presList);
        	response.sendRedirect("view.jsp");
        }catch(Exception e){
        	String message = "No Prescriptions were for you";
        	session.setAttribute("message", message);
        	response.sendRedirect("patient.jsp");
        	System.out.println("There was an error while searching a prescription for a patient. = "+e);
        	logger.log(Level.SEVERE, "Exception in get methond pf PrescriptionDB", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in PrescriptionDB");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server get method of PrescriptionDB",e);
                   }
                
               }
        }
	}
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(PrescriptionDB.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        Connection conn = null;
        
        int docID = 0;
        int patientID = 0;
        int prescriptionID = 0;
        
        String user_ID = null;
        String sqlQuery = null;
        
        PreparedStatement insertRecord = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Database db = new Database();
        PrescriptionBean pres = new PrescriptionBean();
        PatientBean pb = new PatientBean();
        
        
        try{
        	
        	pres.setMedicineName(request.getParameter("m_name"));
        	pres.setComments(request.getParameter("comments"));
        	pres.setConsumptioPerDay(request.getParameter("consumptionperday"));
        	pres.setDuration(request.getParameter("c_duration"));
        	pres.setRefill(Integer.parseInt(request.getParameter("refill")));
        	
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("MM-DD-yyyy");
			String dateNow = formatter.format(currentDate.getTime());
			java.util.Date sysDate = formatter.parse(dateNow);
			java.sql.Date sqlDate = new java.sql.Date(sysDate.getTime());
            
        	pb.setFirstName(request.getParameter("pf_name"));
        	pb.setLastName(request.getParameter("pl_name"));
        	pb.setEmailID(request.getParameter("email"));
        	
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	sqlQuery = "select fk_userID from employee where first_name='"+session.getAttribute("f_name")+"'and last_name='"+session.getAttribute("l_name")+"'";
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		user_ID = resultSet.getString("fk_userID");
        	}
        	
        	sqlQuery = "select doc_id from doctor where fk_userID='"+user_ID+"'";
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		docID = resultSet.getInt("doc_id");
        	}
        	
        	sqlQuery = "select patient_id from patient where first_name='"+pb.getFirstName()+"' and last_name='"+pb.getLastName()+"' and email_id='"+pb.getEmailID()+"'";
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		patientID = resultSet.getInt("patient_id");
        	}
        	
        	//inserting in prescription table
        	insertRecord = conn.prepareStatement("insert into prescription(fk_docID,fk_patientID) values(?,?)");
        	insertRecord.setInt(1, docID);
        	insertRecord.setInt(2, patientID);
        	insertRecord.executeUpdate();
        	
        	sqlQuery = "select prescription_id from prescription where fk_docID='"+docID+"' and fk_patientID='"+patientID+"'";
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(sqlQuery);
        	while(resultSet.next()){
        		pres.setPrescriptionID(resultSet.getInt("prescription_id"));
        	}
        	
        	
        	//inserting in prescription_details table
        	PreparedStatement insertRec = conn.prepareStatement("insert into prescription_details values(?,?,?,?,?,?,?)");
        	insertRec.setDate(1, sqlDate);
        	insertRec.setString(2, pres.getMedicineName());
        	insertRec.setString(3, pres.getDuration());
        	insertRec.setString(4, pres.getConsumptioPerDay());
        	insertRec.setString(5, pres.getComments());
        	insertRec.setInt(6, pres.getRefill());
        	insertRec.setInt(7, pres.getPrescriptionID());
        	insertRec.executeUpdate();
        	
        	response.sendRedirect("doctor.jsp");
        	        	
        }catch(Exception e){
        	String message = "There was an error while writing a prescription for a patient.";
        	session.setAttribute("message", message);
        	response.sendRedirect("doctor.jsp");
        	System.out.println("There was an error while writing a prescription for a patient. = "+e);
        	logger.log(Level.SEVERE, "Exception in PrescriptionDB", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in PrescriptionDB");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server in PrescriptionDB",e);
                   }
                
               }
        }
	}

}
