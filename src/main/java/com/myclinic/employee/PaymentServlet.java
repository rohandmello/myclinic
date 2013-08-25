package com.myclinic.employee;

import com.myclinic.database.Database;
import com.myclinic.patient.Save;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet(description = "Saves or updates payment Information", urlPatterns = { "/PaymentServlet" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(Save.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        Connection conn = null;
        Database db = new Database();
        String amount = request.getParameter("amount");
        String method = request.getParameter("method");
        String bank_name = request.getParameter("bname");
        String checknumber = request.getParameter("chnum");
        String company_name = request.getParameter("cname");
        String policy_num = request.getParameter("pnum");
        String copay =  request.getParameter("copay");
        String status = request.getParameter("status");
        String query1 = "";
        String query2 = "";
        int appid = Integer.parseInt(request.getParameter("appid"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	conn = DriverManager.getConnection(db.getDBURL());
        	query1 = "insert into payment_details(amount, method, status, fk_appointmentID) values(?,?,?,?)";
        	System.out.println("after query 1");
        	ps = conn.prepareStatement(query1);
        	ps.setString(1, amount);
        	ps.setString(2, method);
        	ps.setString(3, status);
        	ps.setInt(4, appid);
        	ps.executeUpdate();
        	
        	ps = conn.prepareStatement("update appointment set pay_status=? where appointment_id=?");
        	ps.setString(1, status);
        	ps.setInt(2, appid);
        	ps.executeUpdate();
        	
        	if(method.equals("Insurance")){
        		System.out.println("Method is insurance");
        		query2 = "insert into insurance(company_name, policy_number, co_pay, fk_invoiceno) values(?,?,?,?)";
        		ps = conn.prepareStatement("select invoice_no from payment_details where fk_appointmentID=?");
        		ps.setInt(1, appid);
        		rs = ps.executeQuery();
        		int invoice_no = 0;
        		while(rs.next()){
        			invoice_no = rs.getInt("invoice_no");
        		}
        		ps = conn.prepareStatement(query2);
        		ps.setString(1, company_name);
        		ps.setString(2, policy_num);
        		ps.setString(3, copay);
        		ps.setInt(4, invoice_no);
        		ps.executeUpdate();
        	}
        	String message = "Payment information added";
        	session.setAttribute("message", message);
        	response.sendRedirect("staff.jsp");
		} catch(Exception e){
        	String message = "There was some error while adding payment info";
        	session.setAttribute("message", message);
        	response.sendRedirect("staff.jsp");
        	System.out.println("There was an error while adding payment Info = "+e);
        	logger.log(Level.SEVERE, "Exception in doPost methond of PaymentServlet", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated doPost methond of PaymentServlet");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server doPost methond of PaymentServlet",e);
                   }
                
               }
        }
	}

}
