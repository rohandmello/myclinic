package com.myclinic.employee;

import com.myclinic.database.Database;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDB {
	public ArrayList getDoctorInfo()throws SQLException, SecurityException, IOException{
		Logger logger = Logger.getLogger(com.myclinic.employee.PrescriptionDB.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		ArrayList<DoctorBean> docInfoList = new ArrayList<DoctorBean>();
		Database db = new Database();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db.getDBURL());
			query = "select first_name, last_name from employee, login as l where fk_userID=l.User_ID and  l.access_code=1";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				DoctorBean bean = new DoctorBean();
				bean.setFirstName(rs.getString("first_name"));
				bean.setLastName(rs.getString("last_name"));
				docInfoList.add(bean);
			}
		}catch(Exception e){
			System.out.println("There was an error while fetching data from Database in DoctoDB method getDoctorInfo = "+e);
        	logger.log(Level.SEVERE, "There was an error while fetching data from Database in DoctoDB getDoctorInfo.", e);
		}finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in getDoctorInfo of DoctorDB");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close Database server in getDoctorInfo of DoctorDB");
                	   logger.log(Level.WARNING, "Cannot close Database server in getDoctorInfo of DoctorDB",e);
                   }
                
               }
        }
		return docInfoList;
	}
}
