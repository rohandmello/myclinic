package com.myclinic.patient;

import com.myclinic.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(description = "Servelet for making Appointment", urlPatterns = { "/AppointmentServlet" })
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(AppointmentServlet.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
        String name = request.getParameter("docname");
        String[] docname = name.split("\\s");
//        String[] patientDetails = new String[3];
//        patientDetails[0] = (String)session.getAttribute("f_name");
//        patientDetails[1] = (String)session.getAttribute("l_name");
//        patientDetails[2] = (String)session.getAttribute("userid");
//        System.out.println(patientDetails[0]);
        AppointmentDB appdb =  new AppointmentDB();
        ArrayList<AppointmentBean> appList = appdb.getAppointmentInfo(docname);
        session.setAttribute("appList", appList);
        response.sendRedirect("scheduleAppointment.jsp?id=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Logger logger = Logger.getLogger(AppointmentServlet.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		HttpSession session = request.getSession(true);
		Database db = new Database();
		Connection conn= null;
		Statement st = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		String status = "PENDING";
		int patient_id = 0;
		int doc_id = Integer.parseInt(request.getParameter("docid"));
		String patientUserID = (String)session.getAttribute("userid");
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db.getDBURL());
			if(patientUserID != null){
				query = "select patient_id from patient where fk_userID='"+patientUserID+"'";
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()){
					patient_id = rs.getInt("patient_id");
				}
			}else{
				patient_id = ((Integer)session.getAttribute("patient_id")).intValue();
				session.removeAttribute("patient_id");
			}
			pst = conn.prepareStatement("insert into appointment(date,time,pay_status,fk_patientID,fk_docID) values(?,?,?,?,?)");
			pst.setString(1, request.getParameter("orderdate"));
			pst.setString(2, request.getParameter("time"));
			pst.setString(3, status);
			pst.setInt(4, patient_id);
			pst.setInt(5, doc_id);
			
			pst.executeUpdate();
			String message = "Appointment is made";
			session.setAttribute("message", message);
			if(patientUserID != null){
				response.sendRedirect("patient.jsp");
			}else{
				response.sendRedirect("staff.jsp");
			}
			
		}catch(Exception e){
			System.out.println("Exception Occured in doPost of AppointmentServlet"+e);
			logger.log(Level.SEVERE, "Exception Occured in doPost of AppointmentServlet", e);
			String message = "Appointment is not made some error occured";
			session.setAttribute("message", message);
			if(patientUserID != null){
				response.sendRedirect("patient.jsp");
			}else{
				response.sendRedirect("staff.jsp");
			}
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in doPost in AppointmentServlet");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server in doPost of AppointmentServlet",e);
                   }
                
               }
        }
	}

}
