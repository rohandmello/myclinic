package com.myclinic.pharmacy;

import com.myclinic.database.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pharmacy {

	public ArrayList<PharmacyBean> getPharmacyInfo(String userid) throws SecurityException, IOException{
		Logger logger = Logger.getLogger(Pharmacy.class .getName());
		FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
		logger.addHandler(fh);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Database db = new Database();
		ArrayList<PharmacyBean> pharmacyInfo = new ArrayList<PharmacyBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db.getDBURL());
			ps = conn.prepareStatement("select * from pharmacy where fk_userID=?");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while(rs.next()){
				PharmacyBean bean = new PharmacyBean();
				bean.setAddress(rs.getString("address"));
				bean.setEmailID(rs.getString("email_id"));
				bean.setPharmacyID(rs.getInt("pharmacy_id"));
				bean.setPharmacyName(rs.getString("pharmacy_name"));
				bean.setPhoneNum(rs.getString("phone_num"));
				pharmacyInfo.add(bean);
			}
			return pharmacyInfo;
		}catch(Exception e){
        	System.out.println("Exception occured in getPharmacy in Pharmacy = "+e);
        	logger.log(Level.SEVERE, "Exception occured in getPharmacy in Pharmacy = ", e);
        	
        }finally {            
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in getPharmacy in Pharmacy");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server in getPharmacy in Pharmacy" + e);
                	   logger.log(Level.WARNING, "Cannot close database server in getPharmacy in Pharmacy = ",e);
                   }
               }
        }
		return pharmacyInfo;
	}
}
