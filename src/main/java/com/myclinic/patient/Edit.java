package com.myclinic.patient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.myclinic.database.Database;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("In Edit Servlet page");
		Logger logger = Logger.getLogger(Search.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
        Connection conn = null;
        HttpSession session = request.getSession(true);
        int access_code = ((Integer)session.getAttribute("access_code")).intValue();
        Database db = new Database();
        String query = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	if(access_code == 1 || access_code == 2){
        		query = "select * from patient where patient_id="+request.getParameter("id");
        	}
        	if(access_code == 3){
        		query = "select * from patient where fk_userID='"+session.getAttribute("userid")+"'";
        	}
        	st = conn.createStatement();
        	rs = st.executeQuery(query);
        	PatientBean pbGet = new PatientBean();
        	while(rs.next()){
        		pbGet.setAddress(rs.getString("address"));
        		pbGet.setBirthDate(rs.getString("birth_date"));
        		pbGet.setEmailID(rs.getString("email_id"));
        		pbGet.setFirstName(rs.getString("first_name"));
        		pbGet.setLastName(rs.getString("last_name"));
        		pbGet.setPhoenNum(rs.getString("phone_num"));
        		pbGet.setPatientID(rs.getInt("patient_id"));
        	}
        	session.setAttribute("patientEditData", pbGet);
        	System.out.println("Before Redirecting to edit page");
        	response.sendRedirect("edit.jsp");
        }catch(Exception e){
//        	response.sendRedirect("error.jsp");
        	System.out.println("Exception occured in Edit servlet in get method = "+e);
        	logger.log(Level.SEVERE, "Exception occured in Edit servlet in get method = ", e);
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in Edit servlet in get method");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server in Edit servlet in get method" + e);
                	   logger.log(Level.WARNING, "Cannot close database server in Edit servlet in get method = ",e);
                   }
                
               }

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("In search page");
		Logger logger = Logger.getLogger(Search.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
        Connection conn = null;
        HttpSession session = request.getSession(true);
        int access_code = ((Integer)session.getAttribute("access_code")).intValue();
        Database db = new Database();
        PreparedStatement  pst = null;
        try{
        	PatientBean pbPost = new PatientBean();
        	pbPost.setAddress(request.getParameter("address"));
        	pbPost.setBirthDate(request.getParameter("birthdate"));
        	pbPost.setEmailID(request.getParameter("email"));
        	pbPost.setFirstName(request.getParameter("f_name"));
        	pbPost.setLastName(request.getParameter("l_name"));
        	pbPost.setPhoenNum(request.getParameter("phnum"));
        	pbPost.setPatientID(Integer.parseInt(request.getParameter("patient_id")));
        	System.out.println("First name = "+pbPost.getFirstName());
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	pst = conn.prepareStatement("update patient set first_name=?, last_name=?, birth_date=?, address=?, phone_num=?, email_id=? where patient_id=?");
        	pst.setString(1, pbPost.getFirstName());
        	pst.setString(2, pbPost.getLastName());
        	pst.setString(3, pbPost.getBirthDate());
        	pst.setString(4, pbPost.getAddress());
        	pst.setString(5, pbPost.getPhoneNum());
        	pst.setString(6, pbPost.getEmailID());
        	pst.setInt(7, pbPost.getPatientID());
        	
        	int status = pst.executeUpdate();
        	if(status >0)
        	{
        		String message = "Patient Data Updated successfully";
        		session.setAttribute("message", message);
        		switch(access_code){
        			case 1:
        				response.sendRedirect("doctor.jsp");
        				break;
        			case 2:
        				response.sendRedirect("Staff.jsp");
        				break;
        			case 3:
        				response.sendRedirect("patient.jsp");
        				break;
        		}
        	}else{
        		String message = "Patient Data was not Updated successfully";
        		session.setAttribute("message", message);
        		switch(access_code){
    			case 1:
    				response.sendRedirect("doctor.jsp");
    				break;
    			case 2:
    				response.sendRedirect("Staff.jsp");
    				break;
    			case 3:
    				response.sendRedirect("patient.jsp");
    				break;
    		}
        	}

        }catch(Exception e){
        	response.sendRedirect("error.jsp");
        	System.out.println("Exception occured in Edit servlet in post method = "+e);
        	logger.log(Level.SEVERE, "Exception occured in Edit servlet in post method = ", e);
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in Edit servlet in post method");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server in Edit servlet in post method" + e);
                	   logger.log(Level.WARNING, "Cannot close database server in Edit servlet in post method = ",e);
                   }
                
               }

        }
	}

}
