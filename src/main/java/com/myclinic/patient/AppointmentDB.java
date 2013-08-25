package com.myclinic.patient;

import com.myclinic.database.Database;
import com.myclinic.employee.SendEmail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDB {
	
	public ArrayList<AppointmentBean> getAppointmentInfo(String[] docname) throws SecurityException, IOException{
		Logger logger = Logger.getLogger(SendEmail.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		Database db = new Database();
		Connection conn= null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		String docUserID = null;
		String fullname = docname[0]+" "+docname[1]+" "+docname[2];
		int doc_id = 0;
		ArrayList<AppointmentBean> appList = new ArrayList<AppointmentBean>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db.getDBURL());
			query = "select fk_userID from employee where first_name='"+docname[1]+"' and last_name='"+docname[2]+"'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				docUserID = rs.getString("fk_userID");
			}
			
			query = "select doc_id from doctor where fk_userID='"+docUserID+"'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				doc_id = rs.getInt("doc_id");
			}
			
			query = "select date, time from appointment where fk_docID="+doc_id;
			st = conn.createStatement();
			rs = st.executeQuery(query);
			int count = 0;
			while(rs.next()){
				count++;
			}
			System.out.println("Value of count = "+count);
			if(count > 0){
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()){
					AppointmentBean bean = new AppointmentBean();
					bean.setAppDate(rs.getString("date"));
					bean.setAppTime(rs.getString("time"));
					bean.setDocName(fullname);
					bean.setDocID(doc_id);
					appList.add(bean);
				}
				System.out.println("Inside if Size of App list = "+appList.size());
				System.out.println("Inside if date = "+appList.size());
			}else{
				AppointmentBean bean = new AppointmentBean();
				bean.setDocName(fullname);
				bean.setDocID(doc_id);
				appList.add(bean);
				System.out.println("Inside else Size of App list = "+appList.size());
			}
			return appList;
		}catch(Exception e){
			System.out.println("Exception Occured in getAppointmentInfo of AppointmentDB"+e);
			logger.log(Level.SEVERE, "Exception Occured in getAppointmentInfo of AppointmentDB", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in AppointmentDB");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.log(Level.WARNING, "Cannot close database server in getAppointmentInfo of AppointmentDB",e);
                   }
                
               }
        }

		return appList;
	}
	
}
