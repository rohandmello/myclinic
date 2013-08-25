<%-- 
    Document   : doctor
    Created on : Aug 10, 2011, 8:44:38 PM
    Author     : Rohan
--%>

<%@page import="java.util.logging.Logger"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "patient.PatientBean" %>
<!DOCTYPE html>
<html lang="en-US" class="no-js">
    <head>
    <%
    response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate"); // no-store 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", 0); 
    response.setHeader("Pragma", "no-cache");
    %>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

        <title>Edit Patient's Information - My Clinic</title>
        <link href="css/colors.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/fonts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/layout.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/print.css" rel="stylesheet" type="text/css" media="print">
        <link href='http://fonts.googleapis.com/css?family=Ubuntu:regular,regularitalic,bold&v1' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="js/SiteMasterScripts.js"></script>
        <script type="text/javascript" src="js/Validation.js"></script>

    </head>
    <body>
        <div class="logOutBox">
            <a href="logout.jsp">Logout</a>
        </div>
        <div class="wrapper">
            <div class="banner">
                <!--<img align="middle" src="../images/dmellor_clinic.jpg" alt="">-->
                <div id="bannerTitle">
                    <em>My Clinic</em>
                </div>
            </div>
            <div class="pageBody">
                <div class="contentWrapper">
                    <a name="MainContent"></a>
                    <div class="content">
                    <h1>Edit Your Information</h1>
					<%try{
						Integer access = (Integer)session.getAttribute("access_code");
						if(access == null){
							response.sendRedirect("error.jsp");
						}else{
						PatientBean pb = (PatientBean)session.getAttribute("patientEditData");
						session.removeAttribute("patientEditData");
		           		
		           		int access_code = access.intValue();
		           		switch(access_code){
           					case 1:
           				%>
							<div style="float:right;">
								<a class="button" href="doctor.jsp">Go Back</a>
							</div>
           				<%
           					break;
           					case 2:
           				%>
							<div style="float:right;">
								<a class="button" href="staff.jsp">Go Back</a>
							</div>
       					<%
           					break;
		           			case 3:
	           			%>
							<div style="float:right;">
								<a class="button" href="patient.jsp">Go Back</a>
							</div>
	       				<%
	           					break;
			           	}
       					%>
                        <form action="edit" method="post">
                        <input type="hidden" name="patient_id" value="<%= pb.getPatientID() %>"></input>
                            <div align="left">
                                <div class="loginBox">First Name : <input type="text" size="33" name="f_name" value="<%= pb.getFirstName() %>"></input></div>
                                <div class="loginBox">Last Name : <input type="text" size="33" name="l_name" value="<%= pb.getLastName()  %>"></input></div>
                                <div class="loginBox">Birth Date : <input type="text" size="33" name="birthdate" maxlength="10" value="<%= pb.getBirthDate()  %>"> (Format : "MM-DD-YYYY")</input></div>
                                <div class="loginBox">Email : <input type="text" size="38" name="email" value="<%= pb.getEmailID()  %>"></input></div>
                                <div class="loginBox">Ph Num. : <input type="text" size="35" name="phnum" maxlength="10" value="<%= pb.getPhoneNum()  %>"></input></div>
                                <div class="loginBox">Address : <input type="text" size="36" name="address" value="<%= pb.getAddress()  %>"></input></div>
                                <input class="button" type="submit" value="Update"></input>
                                <%if(access_code != 3){
                                	%>	
                                <input class="button" type="reset" value="Reset"></input>
                                <%
                                }
						}
					}catch(Exception e){
						response.sendRedirect("error.jsp");
					}
                                %>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <footer>
                <nav>
                    <ul>
                        <li style="color:#0085cb">Developed By</li>
                        <li>Rohan DMello</li>
                        <li>TEL: (909)543-5386</li>
                        <li style="border-right: medium none; padding-right: 0px;">EMAIL: <a href="mailto:dmellor@coyote.csusb.edu">dmellor@coyote.csusb.edu</a></li>                                                            
                    </ul>
                </nav>
            </footer>    
        </div>
    </body>
</html>

