package com.myclinic.employee;

import com.myclinic.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


/**
 * Servlet implementation class SendEmail
 */
@WebServlet(name = "email", description = "Send Email to Patients", urlPatterns = { "/email" })
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmail() {
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
	@SuppressWarnings("restriction")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(SendEmail.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
		Connection conn = null;
		PreparedStatement ps = null;
		Database db = new Database();
		int access_code = ((Integer)session.getAttribute("access_code")).intValue();
		String from = request.getParameter("from");
		if(request.getParameter("from") != null){
			from = request.getParameter("from");
		}else{
			from ="rock21586@gmail.com";
		}
		String name = "";
		if(request.getParameter("name") != null){
			name = "Name of the Patient : "+request.getParameter("name");
		}
		String to = "";
		if(request.getParameter("to") != null){
			to = request.getParameter("to");
		}else{
			to = "rock21586@gmail.com";
		}
		String[] recipents = to.split(",");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String entireEmail = "From : "+from +"\n" + name + "\n" + body;
		
		//Getting refill values to update in prescription table
		int presccription_id = Integer.parseInt(request.getParameter("prescid"));
		int refill = Integer.parseInt(request.getParameter("refill"));
		try{
			if(request.getParameter("prescid") != null && request.getParameter("refill") != null){
				if(refill <= 0){
					
				}else{
					System.out.println("Inside else for refill"+refill);
					refill--;
					System.out.println("Inside else for refill--"+refill);
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(db.getDBURL());
					ps = conn.prepareStatement("update prescription_details set refill=? where fk_prescriptionID=?");
					ps.setInt(1, refill);
					System.out.println("After refill set int"+ps);
					ps.setInt(2, presccription_id);
					System.out.println("After prescription set int"+ps);
					int status = ps.executeUpdate();
					System.out.println("Status"+ status);
				}
				
			}
			
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			db.sendSSLMessage(recipents, subject, entireEmail, from);
			System.out.println("Sucessfully Sent mail to All Users");
			String message = "Sucessfully Sent Email to All Users";
			session.setAttribute("message", message);
			switch(access_code){
				case 2:
					response.sendRedirect("staff.jsp");
					break;
				case 3:
					response.sendRedirect("patient.jsp");
					break;
				case 4:
					response.sendRedirect("pharmacy.jsp");
					break;
			}
			
        }catch (Exception e){
            System.out.println ("Exception Occured while sending email : "+e);
            String message = "There was some error while sending message";
   			session.setAttribute("message", message);
   			switch(access_code){
   				case 2:
   					response.sendRedirect("staff.jsp");
   					break;
   				case 3:
   					response.sendRedirect("patient.jsp");
   					break;
   				case 4:
   					response.sendRedirect("pharmacy.jsp");
   					break;
   			}
        }
	}

}
