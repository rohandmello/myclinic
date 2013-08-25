/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.login;

/**
 *
 * @author Rohan
 */
public class LoginBean {
    String userid;
    String password;
    public void setUserID(String userID){
        this.userid = userID;
    }
    
    public String getUserID(){
        return userid;
    }
    
    public void setPassword(String pwd){
        this.password = pwd;
    }
    
    public String getPassword(){
        return password;
    }
}
