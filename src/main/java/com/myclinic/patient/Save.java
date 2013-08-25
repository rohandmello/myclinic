package com.myclinic.patient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclinic.util.Authenticate;

import com.myclinic.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class Save
 */
@WebServlet("/Save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Save() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(Save.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        Connection conn = null;
        
        String ph_name = null;
        String username = null;
        String pwd = null;
        String email = null;
        String phnum = null;
        String address = null;
        int access_code = 4;
        PreparedStatement  ps = null;
        ResultSet resultSet = null;
        Database db = new Database();
        Authenticate auth = new Authenticate();
        try{
        	ph_name = request.getParameter("ph_name");
        	username = request.getParameter("u_name");
        	pwd = request.getParameter("pwd");
        	email = request.getParameter("email");
        	phnum =  request.getParameter("phnum");
        	address = request.getParameter("address");
        	
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	Boolean isAdded = auth.createUser(conn, username, pwd, access_code);
        	if(isAdded){
	        	ps = conn.prepareStatement("insert into pharmacy(pharmacy_name, address, fk_userID, phone_num, email_id) values(?,?,?,?,?)");
	        	ps.setString(1, ph_name);
	        	ps.setString(2, address);
	        	ps.setString(3, username);
	        	ps.setString(4, ph_name);
	        	ps.setString(5, email);
	        	ps.executeUpdate();
        	}else{
        		String message = "New Pharmacy was not Added";
            	session.setAttribute("message", message);
            	response.sendRedirect("staff.jsp");
        	}
        	
        	String message = "New Pharmacy Added";
        	session.setAttribute("message", message);
        	response.sendRedirect("staff.jsp");
        }catch(Exception e){
        	String message = "New Pharmacy was not Added";
        	session.setAttribute("message", message);
        	response.sendRedirect("staff.jsp");
        	System.out.println("There was an error while adding a pharmacy = "+e);
        	logger.log(Level.SEVERE, "Exception in get methond of Save", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in get method of  Save");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server get method of Save",e);
                   }
                
               }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		Logger logger = Logger.getLogger(Save.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		String userID = (String)session.getAttribute("username");
        PatientBean patient = new PatientBean();
        int access_code = ((Integer)session.getAttribute("access_code")).intValue();
        String address = " ";
		try{
	        patient.setFirstName(request.getParameter("f_name"));
	        patient.setLastName(request.getParameter("l_name"));
	        patient.setBirthDate(request.getParameter("orderdate"));
	        patient.setUserName(request.getParameter("u_name"));
	        patient.setPassword(request.getParameter("pwd"));
	        if(request.getParameter("addressL2") != ""){
	        	address = request.getParameter("addressL1")+", "+request.getParameter("addressL2")+", "+request.getParameter("city")+", "+
		        		request.getParameter("State")+", "+request.getParameter("zip");
	        }else{
	        	address = request.getParameter("addressL1")+", "+request.getParameter("city")+", "+request.getParameter("State")+", "+request.getParameter("zip");
	        }
	        System.out.println(address);
	        patient.setAddress(address);
	        patient.setEmailID(request.getParameter("email"));
	        patient.setPhoenNum(request.getParameter("phnum"));
	        
	        //calling savePatineInfo which creates patient in database
	        PatientDB patientDB = new PatientDB();
	        boolean savePatient = patientDB.savePatientInfo(patient, userID);
	        
	        if(savePatient){
	        	if(access_code == 1){
	        		response.sendRedirect("doctor.jsp");
	        	}
	        	if(access_code == 2){
	        		response.sendRedirect("staff.jsp");
	        	}
	        	String message = "Patient Data Saved Successfully";
	        	session.setAttribute("message", message);
	        }else{
	        	if(access_code == 1){
	        		response.sendRedirect("doctor.jsp");
	        	}
	        	if(access_code == 2){
	        		response.sendRedirect("staff.jsp");
	        	}
	        	String message = "Patient Data Not Saved, Contact Admin";
	        	session.setAttribute("message", message);
	        }
		}catch(Exception e){
			response.sendRedirect("error.jsp");
        	System.out.println("Exception in Save while saving patient's data ="+e);
        	logger.log(Level.SEVERE, "Exception in Save while saving patient's data", e);
		}
	}

}
