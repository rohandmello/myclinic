package com.myclinic.patient;

import com.myclinic.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Delete
 */
@WebServlet(description = "Deletes Patient Data from Database", urlPatterns = { "/Delete" })
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		int patient_id = Integer.parseInt(request.getParameter("id"));
    	Database db = new Database();
    	Connection con = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		con = DriverManager.getConnection(db.getDBURL());
    		PreparedStatement ps = null;
//    		PreparedStatement ps = con.prepareStatement("delete from patient where patient_id= ?");
//			ps.setInt(1,patient_id);
//			int status = ps.executeUpdate();

			//Executing SQL query using the simple statement
			
			String deleteQuery = "delete from login where User_ID= (select fk_userID from patient where patient_id="+patient_id+")";
			ps = con.prepareStatement(deleteQuery);
			int status1 = ps.executeUpdate();
			System.out.println(status1);
			response.sendRedirect("doctor.jsp");
    	}catch(Exception e){
    		System.out.println("Exception occured in Delete Servlet "+e);
    	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<h1>hello post</h1>");
		//response.sendRedirect("MyClinic/doctor.html");
	}

}
