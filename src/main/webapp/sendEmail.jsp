<%-- 
    Document   : doctor
    Created on : Aug 10, 2011, 8:44:38 PM
    Author     : Rohan
--%>

<%@page import="patient.PatientDB"%>
<%@page import="patient.PatientBean"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
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

        <title>Send Email - My Clinic</title>
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
					<%
						Integer access = (Integer)session.getAttribute("access_code");
						if(access == null){
							response.sendRedirect("error.jsp");
						}else{
		           		int access_code = access.intValue();
		           		switch(access_code){
           					case 1:
           						response.sendRedirect("doctor.jsp");
           						break;
           					case 2:
           						int patientid = Integer.parseInt(request.getParameter("id"));
           						ArrayList<PatientBean> patient = new ArrayList<PatientBean>();
           						PatientDB db = new PatientDB();
           						patient = db.getPatientInfo(patientid);
           				%>
							<div style="float:right;">
								<a class="button" href="result.jsp">Go Back</a>
							</div>
							<form action="email" method="post">
	                            <div align="left">
	                                <div class="loginBox">Send Email to Patient</div>
	                                <div class="loginBox">To : <input type="text" name="to" size="42" value="<%= patient.get(0).getEmailID() %>"></div>
	                                <div class="loginBox">Subject : <input type="text" name="subject" size="36"></div>
	                                <div class="loginBox">Body :<br> <textarea rows="10" cols="36" name="body"></textarea> </div>
	                                <input class="button" type="submit" value="Send"></input>
	                                <input class="button" type="reset" value="Reset"></input>
	                            </div>
                        	</form>
       					<%
           						break;
           					case 3:
           						String userid = (String)session.getAttribute("userid");
           						ArrayList<PatientBean> patientInfo = new ArrayList<PatientBean>();
           						PatientDB pdb = new PatientDB();
           						patientInfo = pdb.getPatientInfo(userid);
	           			%>
							<div style="float:right;">
								<a class="button" href="patient.jsp">Go Back</a>
							</div>
							<form action="email" method="post">
	                            <div align="left">
	                                <div class="loginBox">Send Email</div>
	                                <div class="loginBox">From : <input type="text" name="from" size="39" value="<%= patientInfo.get(0).getEmailID() %>"><br>
	                                Your Email Address</div>
	                                <div class="loginBox">Name : <input type="text" name="name" size="38" value="<%= session.getAttribute("f_name") %> <%= session.getAttribute("l_name") %>"></div>
	                                <div class="loginBox">Subject : <input type="text" name="subject" size="36"></div>
	                                <div class="loginBox">Body :<br> <textarea rows="10" cols="36" name="body"></textarea> </div>
	                                <input class="button" type="submit" value="Send"></input>
	                                <input class="button" type="reset" value="Reset"></input>
	                            </div>
                        	</form>
	       				<%
	       						break;
	       					case 4:
	       				%>
							<div style="float:right;">
								<a class="button" href="pharmacy.jsp">Go Back</a>
							</div>
							<form action="email" method="post">
	                            <div align="left">
	                                <div class="loginBox">Send Email</div>
	                                <div class="loginBox">From : <input type="text" name="from" size="39"><br>
	                                Your Email Address</div>
	                                <div class="loginBox">Name : <input type="text" name="name" size="38" value="<%= session.getAttribute("pharmacyname") %>"></div>
	                                <div class="loginBox">Subject : <input type="text" name="subject" size="36"></div>
	                                <div class="loginBox">Body :<br> <textarea rows="10" cols="36" name="body"></textarea> </div>
	                                <input class="button" type="submit" value="Send"></input>
	                                <input class="button" type="reset" value="Reset"></input>
	                            </div>
                        	</form>
	       				<%	
	       						break;
		           			}
						}
	       				%>
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

