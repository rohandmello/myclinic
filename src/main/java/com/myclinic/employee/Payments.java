package com.myclinic.employee;

import com.myclinic.database.Database;
import com.myclinic.patient.AppointmentBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Payments {
	
	/*
	 * Gets the information of all the appointments made to the particaular patient
	 * @param patient_id Id of patient for which we need information
	 * @return appList  list of appointments that are for this patient
	 * @throws SQLException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public ArrayList<AppointmentBean> getPaymentInfo(int patient_id)
			throws SQLException, SecurityException, IOException{
		ArrayList<AppointmentBean> appList = new  ArrayList<AppointmentBean>();
		Database db = new  Database();
		Connection conn = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		String query = "";
		String docname = "";
		ResultSet rsin = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db.getDBURL());
			query = "select * from appointment where fk_patientID=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, patient_id);
			rs = ps.executeQuery();
				
			while(rs.next()){
				AppointmentBean bean = new AppointmentBean();
				bean.setAppID(rs.getInt("appointment_id"));
				bean.setAppDate(rs.getString("date"));
				bean.setAppTime(rs.getString("time"));
				bean.setDocID(rs.getInt("fk_docID"));
				
				//getting name of the doctor
				ps = conn.prepareStatement("select first_name, last_name from employee where fk_userID=(select fk_userID from doctor where doc_id=?)");
				ps.setInt(1, rs.getInt("fk_docID"));
				rsin = ps.executeQuery();
				while(rsin.next()){
					docname = rsin.getString("first_name")+ " " +rsin.getString("last_name"); 
				}
				
				bean.setDocName(docname);
				bean.setPaymentStatus(rs.getString("pay_status"));
				appList.add(bean);
			}
			return appList;
			
		} catch (Exception e){
            System.err.println("Exception occured in in getPaymentInfo in Payments"+e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated in getPaymentInfo in Payments");
                   }
                   catch (Exception e) { 
                        System.out.println ("Error closing Database in getPaymentInfo in Payments"+e); 
                   }
               }
        }
		return appList;
		
	}
}
