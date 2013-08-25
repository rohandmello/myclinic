/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclinic.util.Authenticate;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import com.myclinic.database.Database;
import com.myclinic.employee.PrescriptionDB;
/**
 *
 * @author Rohan
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

	 /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Logger logger = Logger.getLogger(PrescriptionDB.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        Connection conn = null;
        PreparedStatement ps = null;
        Database db = new Database();
        Authenticate auth = new Authenticate();
        try{
        	
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db.getDBURL());
	        String fist_name = request.getParameter("f_name");
	        String last_name = request.getParameter("l_name");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String designation = request.getParameter("designation");
	        String sqlEmpQuery = "INSERT INTO employee (first_name, last_name, address, designation, `fk_userID`) VALUES(?,?,?,?,?)";
	        int access_code = 0;
	        if(designation.equals("Doctor")){
	        	access_code = 1;
	        }else{
	        	access_code = 2;
	        }
	        System.out.println(designation);
	        String address = request.getParameter("address");
	        String field = request.getParameter("field");
			
	        Boolean userCreated = auth.createUser(conn, username, password, access_code);
	        if(userCreated){
	        	ps = conn.prepareStatement(sqlEmpQuery);
	        	ps.setString(1, fist_name);
	        	ps.setString(2, last_name);
	        	ps.setString(3, address);
	        	ps.setString(4, designation);
	        	ps.setString(5, username);
	        	ps.executeUpdate();
	        	
	        	if(access_code == 1){
	        		ps = conn.prepareStatement("INSERT INTO doctor (field, `fk_userID`) VALUES (?,?)");
	        		ps.setString(1, field);
	        		ps.setString(2, username);
	        		ps.executeUpdate();
	        	}
	        	response.sendRedirect("managerapp.jsp");
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Logger logger = Logger.getLogger(PrescriptionDB.class .getName());
    		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
    		logger.addHandler(fh);
    		HttpSession session = request.getSession(true);
            Connection conn = null;
            Database db = new Database();
            Authenticate auth = new Authenticate();
            String user_ID = "";
            String pass = "";
            int access_code = 0;
            int count = 0;
            String sqlQuery = ""; 
            Statement statement = null;
            ResultSet resultSet = null;
            String first_name = null;
            String last_name = null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(db.getDBURL());
                out.println(request.getParameter("username"));
                if(request.getParameter("username") != null && request.getParameter("username") != "" && 
                		request.getParameter("password") != null && request.getParameter("password") != "")
            	{
                    user_ID = request.getParameter("username").toString();
                    pass = request.getParameter("password").toString();
                    boolean bUserExist = auth.authenticate(conn, user_ID, pass);
                    if(bUserExist == true){
                    	sqlQuery = "select User_ID, access_code from login where user_ID='"+user_ID+"'";
                    }
                    else{
                    	String message = "Your Login credentials are wrong. Please Enter Valid Username and Password";
                		session.setAttribute("message", message);
                		response.sendRedirect("login.jsp");
                    }
                    statement = conn.createStatement();
                    resultSet = statement.executeQuery(sqlQuery);
                    while(resultSet.next()){
                    	access_code = resultSet.getInt("access_code");
                    	count++;
                    }
                    if(count > 0){
                    	switch (access_code){
                    	case 1:
                			String docQuery = "select first_name, last_name from employee where fk_userID='"+user_ID+"'";
                            Statement docStatement = conn.createStatement();
                            ResultSet docResult = docStatement.executeQuery(docQuery);
                            
                            while(docResult.next())
                            {
                            	first_name = docResult.getString("first_name");
                            	last_name = docResult.getString("last_name");
                            }
                            session.setAttribute("f_name",first_name);
                            session.setAttribute("l_name",last_name);
                            session.setAttribute("access_code",access_code);
                            if(first_name != null && last_name != null){
                            	response.sendRedirect("doctor.jsp");
                            }
                    		else{
                    			response.sendRedirect("login.jsp");
                    		}
                    		break;
                    		
                    	case 2:
                    		String staffQuery = "select first_name, last_name from employee where fk_userID='"+user_ID+"'";
                            Statement staffStatement = conn.createStatement();
                            ResultSet staffResult = staffStatement.executeQuery(staffQuery);
                            while(staffResult.next())
                            {
                            	first_name = staffResult.getString("first_name");
                            	last_name = staffResult.getString("last_name");
                            }
                            session.setAttribute("f_name",first_name);
                            session.setAttribute("l_name",last_name);
                            session.setAttribute("access_code",access_code);
                            if(first_name != null && last_name != null){
                            	response.sendRedirect("staff.jsp");
                            }
                    		else{
                    			response.sendRedirect("login.jsp");
                    		}
                            break;
                    		
                    	case 3:
                    		String patientQuery = "select first_name, last_name from patient where fk_userID='"+user_ID+"'";
                    		Statement patientStatement = conn.createStatement();
                    		ResultSet patientResult = patientStatement.executeQuery(patientQuery);
                            while(patientResult.next())
                            {
                                first_name = patientResult.getString("first_name");
                                last_name = patientResult.getString("last_name");
                            }
                            session.setAttribute("f_name",first_name);
                            session.setAttribute("l_name",last_name);
                            session.setAttribute("access_code",access_code);
                            session.setAttribute("userid", user_ID);
                            if(first_name != null && last_name != null){
                            	response.sendRedirect("patient.jsp");
                            }
                    		else{
                    			response.sendRedirect("login.jsp");
                    		}
                    		break;
                    	case 4:
                    		String pharmacyQuery = "select pharmacy_name from pharmacy where fk_userID='"+user_ID+"'";
                    		Statement pharmacyStatement = conn.createStatement();
                    		ResultSet pharmacyResult = pharmacyStatement.executeQuery(pharmacyQuery);
                    		String pharmacy_name= null;
                            while(pharmacyResult.next())
                            {
                                pharmacy_name = pharmacyResult.getString("pharmacy_name");
                            }
                            session.setAttribute("pharmacyname",pharmacy_name);
                            session.setAttribute("access_code",access_code);
                            session.setAttribute("phuserid", user_ID);
                            if(pharmacy_name != null){
                            	response.sendRedirect("pharmacy.jsp");
                            }
                    		else{
                    			response.sendRedirect("login.jsp");
                    		}
                    		break;
                    	default :
                    		System.out.println("inside default");
                    		response.sendRedirect("error.jsp");
                    		break;
                    		
                    	}
                    }else{
                    	String message = "Your Login credentials are wrong. Please Enter Valid Username and Password";
                		session.setAttribute("message", message);
                		response.sendRedirect("login.jsp");
                    }
                    
                }else{
                	response.sendRedirect("error.jsp");
                	System.out.println("inside second else");
                }
           }            
            catch (Exception e) {
                System.out.println("Exception Occured in Login Servlet = "+e);
                logger.log(Level.SEVERE, "Exception Occured in Login Servlet",e);
            }
            finally {            
            	if (conn != null)
                {
                    try
                       {
                           conn.close ();
                           System.out.println (" Database connection terminated in Login Servlet");
                       }
                       catch (Exception e) { 
                    	   System.out.println ("Cannot close database server in Login Servlet");
                    	   logger.log(Level.WARNING, "Cannot close database server in Login Servlet",e);
                       }
                    
                   }
            }
    }
}
