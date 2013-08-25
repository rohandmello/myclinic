package com.myclinic.patient;

import com.myclinic.database.Database;
import com.myclinic.employee.DoctorBean;
import com.myclinic.employee.PrescriptionBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		System.out.println("In search page doPost Method");
		Logger logger = Logger.getLogger(Search.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
        Connection conn = null;
        HttpSession session = request.getSession(true);
        int patient_id = 0;
        String sqlQuery = "";
        int[] docID = null;
        int[] prescriptionID = null;
        String[] userid = null;
        int i = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        Database db = new Database();
        ArrayList<PrescriptionBean> presList = new ArrayList<PrescriptionBean>();
        PatientBean pb = new PatientBean();
        ArrayList<DoctorBean> doctorList = new ArrayList<DoctorBean>();

    	try{
    		if(request.getParameter("id") != null){
    			patient_id = Integer.parseInt(request.getParameter("id"));
    			Class.forName("com.mysql.jdbc.Driver").newInstance();
    			conn = DriverManager.getConnection(db.getDBURL());
    			sqlQuery = "select * from prescription where fk_patientID="+patient_id;
    			statement = conn.createStatement();
            	resultSet = statement.executeQuery(sqlQuery);
            	
            	//checking how many prescriptions are there for the this particular patient_id
            	while(resultSet.next()){
            		i++;
            	}
            	docID = new int[i];
            	prescriptionID = new int[i];
            	userid = new String[i];
            	
            	//making list of prescription_id and doctor_id who wrote prescription for this patient_id 
            	int k = 0;
            	statement = conn.createStatement();
            	resultSet = statement.executeQuery(sqlQuery);
            	while(resultSet.next()){
            		prescriptionID[k] = resultSet.getInt(1);
            		docID[k] = resultSet.getInt(2);
            		k++;
            	}
            	
            	//getting user_id's for doctors who wrote the prescription
            	for (int k2 = 0; k2 < i; k2++) {
    	        	sqlQuery = "select fk_userID from doctor where doc_id="+docID[k2];
    	        	statement = conn.createStatement();
    	        	resultSet = statement.executeQuery(sqlQuery);
    	        	while (resultSet.next()) {
    	        		userid[k2] = resultSet.getString(1);
    				}
            	}
            	
            	//getting the names of particular Dr.s
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
            	
            	//getting all information for prescription
            	for (int k2 = 0; k2 < prescriptionID.length; k2++) {
            		sqlQuery = "select * from prescription_details where fk_prescriptionID="+prescriptionID[k2];
    	        	statement = conn.createStatement();
    	        	resultSet = statement.executeQuery(sqlQuery);
    	        	
    	        	while(resultSet.next()){
    	        		PrescriptionBean pres = new PrescriptionBean();
    	        		pres.setComments(resultSet.getString("comments"));
    	        		pres.setConsumptioPerDay(resultSet.getString("consumption_per_day"));
    	        		pres.setDuration(resultSet.getString("consumption_duration"));
    	        		pres.setMedicineName(resultSet.getString("medicine_name"));
    	        		pres.setPrescDate(resultSet.getString("prescription_date"));
    	        		pres.setRefill(resultSet.getInt("refill"));
    	        		pres.setPrescriptionID(resultSet.getInt("fk_prescriptionID"));
    	        		presList.add(pres);
    	    		}
            	}
            	session.setAttribute("doctorList", doctorList);
            	session.setAttribute("presriptionList", presList);
            	response.sendRedirect("show.jsp?patid="+patient_id);
    			
    		}else{
    			String message = "There was some error while processing the request";
    			session.setAttribute("message", message);
    			response.sendRedirect("pharmacy.jsp");
    		}
    		
    	}catch(Exception e){
        	response.sendRedirect("error.jsp");
        	System.out.println("Exception occured in search servlet in get method = "+e);
        	logger.log(Level.SEVERE, "Exception occured in search servlet in get method = ", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in search servlet in get method");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server in search servlet in get method" + e);
                	   logger.log(Level.WARNING, "Cannot close database server in search servlet in get method = ",e);
                   }
                
               }

        }
    	
    }
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("In search page doPost Method");
		Logger logger = Logger.getLogger(Search.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
        Connection conn = null;
        HttpSession session = request.getSession(true);
        Database db = new Database();
        PatientBean pb = new PatientBean();
        String query = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<PatientBean> patientList = new ArrayList<PatientBean>();
        
        try{
        	if(request.getParameter("pf_name") == ""){
        		query = "select * from patient where last_name='"+request.getParameter("pl_name")+"'";
        	}
        	if(request.getParameter("pl_name") == ""){
        		query = "select * from patient where first_name='"+request.getParameter("pf_name")+"'";
        	}
        	if(request.getParameter("pf_name") == "" && request.getParameter("pl_name") == ""){
        		query = "select * from patient";
        	}
        	if(request.getParameter("pf_name") != "" && request.getParameter("pl_name") != ""){
        		query = "select * from patient where first_name='"+request.getParameter("pf_name")+"' and last_name='"+request.getParameter("pl_name")+"'";
        	}
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(query);
        	while(resultSet.next()){
        		PatientBean bean = new PatientBean();
        		bean.setPatientID(resultSet.getInt("patient_id"));
        		bean.setFirstName(resultSet.getString("first_name"));
        		bean.setLastName(resultSet.getString("last_name"));
        		bean.setPhoenNum(resultSet.getString("phone_num"));
        		bean.setBirthDate(resultSet.getString("birth_date"));
        		bean.setEmailID(resultSet.getString("email_id"));
        		patientList.add(bean);
        	}
        	session.setAttribute("patientDataBean", patientList);
        	response.sendRedirect("result.jsp");
        	
        }catch(Exception e){
        	response.sendRedirect("error.jsp");
        	System.out.println("Exception occured in search servlet in post method = "+e);
        	logger.log(Level.SEVERE, "Exception occured in search servlet in post method = ", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in search servlet in post method");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server in search servlet in post method" + e);
                	   logger.log(Level.WARNING, "Cannot close database server in search servlet in post method = ",e);
                   }
                
               }

        }
        
	}

}
