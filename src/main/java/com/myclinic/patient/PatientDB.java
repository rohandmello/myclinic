/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.patient;

import com.myclinic.database.Database;
import com.myclinic.util.Authenticate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Rohan
 */
public class PatientDB {
    
    Database db = new Database();
    //saving patient information
    public boolean savePatientInfo(PatientBean pb, String User_ID){
        Logger logger = Logger.getLogger(this.getClass());
        Connection conn = null;
        
        String checkSql = null;
        Statement checkStatement = null;
        ResultSet checkResultSet = null;
        PreparedStatement insertPatient = null;
        int count = 0;

        try{
            //loading database driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            //Establishing connection with database
            conn = DriverManager.getConnection(db.getDBURL());
            checkSql = "select p.first_name, p.last_name, l.User_ID from login as l, patient as p where p.first_name='"+pb.getFirstName()+
                    "'and p.last_name='"+pb.getLastName()+"'and l.User_ID='"+pb.getUserName()+"'";
            checkStatement = conn.createStatement();
            checkResultSet = checkStatement.executeQuery(checkSql);
            while(checkResultSet.next()){
            	count++;
            }
            System.out.println("Count = "+count);
            if(count > 0){
            	return false;
            }
            else{
            	int access_code = 3;
            	Authenticate create = new Authenticate();
            	Boolean bCreated = create.createUser(conn, pb.getUserName(), pb.getPassword(), access_code);
            	/*PreparedStatement insertpatient = conn.prepareStatement("insert into login values (?,?,3)");
            	insertpatient.setString(1, pb.getUserName());
            	insertpatient.setString(2, pb.getPassword());
            	insertpatient.executeUpdate();*/
            	
                insertPatient = conn.prepareStatement("INSERT INTO patient (first_name, last_name, birth_date, address, phone_num, email_id, `fk_userID`) VALUES (?,?,?,?,?,?,?)");
                insertPatient.setString(1,pb.getFirstName());
                insertPatient.setString(2,pb.getLastName());
                //date conversion 
                String birthdate = pb.getBirthDate();
//                SimpleDateFormat formater = new SimpleDateFormat("MM-DD-yyyy");
//                java.util.Date birthDate = formater.parse(birthdate);
//                java.sql.Date sqldate = new java.sql.Date(birthDate.getTime());
                insertPatient.setString(3,birthdate);
                
                insertPatient.setString(4,pb.getAddress());
                insertPatient.setString(5,pb.getPhoneNum());
                insertPatient.setString(6,pb.getEmailID());
                insertPatient.setString(7,pb.getUserName());
                int status = insertPatient.executeUpdate();
                if(bCreated == true && status > 0){
                	return true;
                }else{
                	return false;
                }
                
            }
        }
        catch (Exception e){
               System.out.println ("Cannot connect to database server "+e);
               logger.debug("Follwing Error Happened in savePatientInfo = "+e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println (" Database connection terminated in PatientDB");
                   }
                   catch (Exception e) { 
                	   System.out.println ("Cannot close database server");
                	   logger.debug("Cannot close database server in savePatientInfo = "+e);
                   }
                
               }
        }
        return false;
    }
    
    
    //modify patient information
    public boolean updatePatientInfo(){
        
        Connection conn = null;
        Statement updateStatment = null;
        ResultSet updateResult = null;
        String updateSql = null;
        try{
            //loading database driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Establishing connection with database 
            conn = DriverManager.getConnection(db.getDBURL());
            
        }catch (Exception e){
               System.err.println (e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated");
                   }
                   catch (Exception e) { 
                        System.err.println (e); 
                   }
               }
        }
        return false;
    }
    
    
    //Searched patient depending on its name
    public void searchPatient(){
        Connection conn = null;
        Statement statement = null;
        try{
            //load database driver
            Class.forName("com.mysql.jdbc.Driver");
            //Establish connection
            conn = DriverManager.getConnection(db.getDBURL());
            String searchSql = "select first_name, last_name, email from patient where";
            
        }catch (Exception e){
            System.err.println(e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated");
                   }
                   catch (Exception e) { 
                        System.err.println (e); 
                   }
               }
        }
    }
    
    //get all patient information for dropdown menu
    public ArrayList<PatientBean> getPatientInfo(){
    	ArrayList<PatientBean> patientList = new ArrayList<PatientBean>();
    	Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //load database driver
            Class.forName("com.mysql.jdbc.Driver");
            //Establish connection
            conn = DriverManager.getConnection(db.getDBURL());
            String searchSql = "select * from patient";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(searchSql);
            while(resultSet.next()){
            	PatientBean bean = new PatientBean();
        		bean.setPatientID(resultSet.getInt("patient_id"));
        		bean.setFirstName(resultSet.getString("first_name"));
        		bean.setLastName(resultSet.getString("last_name"));
        		bean.setPhoenNum(resultSet.getString("phone_num"));
        		bean.setBirthDate(resultSet.getString("birth_date"));
        		bean.setEmailID(resultSet.getString("email_id"));
        		patientList.add(bean);
            }
            return patientList;
        }catch (Exception e){
            System.err.println("Exceptio occured in in getPatientInfo in PatientDB"+e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated in getPatientInfo in PatientDB");
                   }
                   catch (Exception e) { 
                        System.err.println ("in getPatientInfo in PatientDB"+e); 
                   }
               }
        }
    	return patientList;
    }
    
    
    //get particular patinet info based on the userid
    public ArrayList getPatientInfo(String userid){
    	ArrayList<PatientBean> patientInfo = new ArrayList<PatientBean>();
    	Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
    	try{
    		//load database driver
            Class.forName("com.mysql.jdbc.Driver");
            //Establish connection
            conn = DriverManager.getConnection(db.getDBURL());
            String searchSql = "select * from patient where fk_userID='"+userid+"'";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(searchSql);
            while(resultSet.next()){
            	PatientBean bean = new PatientBean();
        		bean.setPatientID(resultSet.getInt("patient_id"));
        		bean.setFirstName(resultSet.getString("first_name"));
        		bean.setLastName(resultSet.getString("last_name"));
        		bean.setPhoenNum(resultSet.getString("phone_num"));
        		bean.setBirthDate(resultSet.getString("birth_date"));
        		bean.setEmailID(resultSet.getString("email_id"));
        		patientInfo.add(bean);
            }
    		return patientInfo;
    	}catch (Exception e){
            System.err.println("Exceptio occured in in getPatientInfo in PatientDB"+e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated in getPatientInfo in PatientDB");
                   }
                   catch (Exception e) { 
                        System.err.println ("in getPatientInfo in PatientDB"+e); 
                   }
               }
        }
		return patientInfo;
    }

    
  //get particular patinet info based on the userid
    public ArrayList<PatientBean> getPatientInfo(int patientid){
    	ArrayList<PatientBean> patientInfo = new ArrayList<PatientBean>();
    	Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
    	try{
    		//load database driver
            Class.forName("com.mysql.jdbc.Driver");
            //Establish connection
            conn = DriverManager.getConnection(db.getDBURL());
            String searchSql = "select * from patient where patient_id="+patientid;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(searchSql);
            while(resultSet.next()){
            	PatientBean bean = new PatientBean();
        		bean.setPatientID(resultSet.getInt("patient_id"));
        		bean.setFirstName(resultSet.getString("first_name"));
        		bean.setLastName(resultSet.getString("last_name"));
        		bean.setPhoenNum(resultSet.getString("phone_num"));
        		bean.setBirthDate(resultSet.getString("birth_date"));
        		bean.setEmailID(resultSet.getString("email_id"));
        		patientInfo.add(bean);
            }
    		return patientInfo;
    	}catch (Exception e){
            System.err.println("Exception occured in in getPatientInfo in PatientDB"+e);
        }
        finally{
            if (conn != null)
            {
                try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated in getPatientInfo in PatientDB");
                   }
                   catch (Exception e) { 
                        System.err.println ("in getPatientInfo in PatientDB"+e); 
                   }
               }
        }
		return patientInfo;
    }
}


