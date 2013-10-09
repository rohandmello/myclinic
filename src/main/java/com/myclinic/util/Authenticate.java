package com.myclinic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.sql.*;
import java.util.Arrays;
import java.security.SecureRandom;

@SuppressWarnings("restriction")
public class Authenticate {
  private final static int ITERATION_NUMBER = 1000;

  public Authenticate() {
	// TODO Auto-generated constructor stub
  }
  /**Any ways this class should not be used as its an internal java class that can change anytime.**/ 

  /**
   * Authenticates the user with a given login and password
   * If password and/or login is null then always returns false.
   * If the user does not exist in the database returns false.
   * @param con Connection An open connection to a databse
   * @param login String The login of the user
   * @param password String The password of the user
   * @return boolean Returns true if the user is authenticated, false otherwise
   * @throws SQLException If the database is inconsistent or unavailable (
   *           (Two users with the same login, salt or digested password altered etc.)
   * @throws NoSuchAlgorithmException If the algorithm SHA-1 is not supported by the JVM
   */
  public boolean authenticate(Connection con, String userid, String password)
          throws SQLException, NoSuchAlgorithmException{
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
          boolean userExist = true;
          // INPUT VALIDATION
          if (userid==null||password==null){
              // TIME RESISTANT ATTACK
              // Computation time is equal to the time needed by a legitimate user
              userExist = false;
              userid="";
              password="";
          }

          ps = con.prepareStatement("select password, extra FROM login where User_ID = ?");
          ps.setString(1, userid);
          rs = ps.executeQuery();
          String digest, extra;
          if (rs.next()) {
              digest = rs.getString("password");
              extra = rs.getString("extra");
              // DATABASE VALIDATION
              if (digest == null || extra == null) {
                  throw new SQLException("Database inconsistant Salt or Digested Password altered");
              }
              if (rs.next()) { // Should not append, because login is the primary key
                  throw new SQLException("Database inconsistent two CREDENTIALS with the same LOGIN");
              }
          } else { // TIME RESISTANT ATTACK (Even if the user does not exist the
              // Computation time is equal to the time needed for a legitimate user
              digest = "000000000000000000000000000=";
              extra = "00000000000=";
              userExist = false;
          }

          byte[] bDigest = base64ToByte(digest);
          byte[] bExtra = base64ToByte(extra);

          // Compute the new DIGEST
          byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bExtra);

          return Arrays.equals(proposedDigest, bDigest) && userExist;
      } catch (IOException ex){
          throw new SQLException("Database inconsistant Salt or Digested Password altered");
      }
      finally{
          close(rs);
          close(ps);
      }
  }



  /**
   * Inserts a new user in the database
   * @param con Connection An open connection to a databse
   * @param userid String The login of the user
   * @param password String The password of the user
   * @return boolean Returns true if the login and password are ok (not null and length(login)<=100
   * @throws SQLException If the database is unavailable
   * @throws NoSuchAlgorithmException If the algorithm SHA-1 or the SecureRandom is not supported by the JVM
   * @throws UnsupportedEncodingException 
   */
  public boolean createUser(Connection con, String userid, String password, int access_code)
          throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
      PreparedStatement ps = null;
      try {
          if (userid != null && password != null && userid.length() <= 20){
              // Uses a secure Random not a simple Random
              SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
              // Salt generation 64 bits long
              byte[] bExtra = new byte[8];
              random.nextBytes(bExtra);
              // Digest computation
              byte[] bDigest = getHash(ITERATION_NUMBER,password,bExtra);
              String sDigest = byteToBase64(bDigest);
              String sSalt = byteToBase64(bExtra);

              ps = con.prepareStatement("insert into login (User_ID, password, access_code, extra) VALUES (?,?,?,?)");
              ps.setString(1,userid);
              ps.setString(2,sDigest);
              ps.setInt(3,access_code);
              ps.setString(4, sSalt);
              ps.executeUpdate();
              return true;
          } else {
              return false;
          }
      } finally {
          close(ps);
      }
  }


  /**
   * From a password, a number of iterations and a salt,
   * returns the corresponding digest
   * @param iterationNb int The number of iterations of the algorithm
   * @param password String The password to encrypt
   * @param salt byte[] The salt
   * @return byte[] The digested password
   * @throws NoSuchAlgorithmException If the algorithm doesn't exist
   * @throws UnsupportedEncodingException 
   */
  public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      digest.reset();
      digest.update(salt);
      byte[] input = digest.digest(password.getBytes("UTF-8"));
      for (int i = 0; i < iterationNb; i++) {
          digest.reset();
          input = digest.digest(input);
      }
      return input;
  }


  public void creerTable(Connection con) throws SQLException{
      Statement st = null;
      try {
          st = con.createStatement();
          st.execute("CREATE TABLE CREDENTIAL (LOGIN VARCHAR(100) PRIMARY KEY, PASSWORD VARCHAR(32) NOT NULL, SALT VARCHAR(32) NOT NULL)");
      } finally {
          close(st);
      }
  }



  /**
   * Closes the current statement
   * @param ps Statement
   */
  public void close(Statement ps) {
      if (ps!=null){
          try {
              ps.close();
          } catch (SQLException ignore) {
          }
      }
  }

  /**
   * Closes the current resultset
   * @param ps Statement
   */
  public void close(ResultSet rs) {
      if (rs!=null){
          try {
              rs.close();
          } catch (SQLException ignore) {
          }
      }
  }


  /**
   * From a base 64 representation, returns the corresponding byte[] 
   * @param data String The base64 representation
   * @return byte[]
   * @throws IOException
   */
  public static byte[] base64ToByte(String data) throws IOException {
      BASE64Decoder decoder = new BASE64Decoder();
      return decoder.decodeBuffer(data);
  }

  /**
   * From a byte[] returns a base 64 representation
   * @param data byte[]
   * @return String
   * @throws IOException
   */
  public static String byteToBase64(byte[] data){
      BASE64Encoder endecoder = new BASE64Encoder();
      return endecoder.encode(data);
  }


}
