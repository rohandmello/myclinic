<%-- 
    Document   : doctorLogin
    Created on : May 25, 2011, 7:34:12 PM
    Author     : Rohan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "patient.*" %>
<%@ page import = "database.*" %>


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
        
        <title>Result - My Clinic</title>
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
        <%
        Integer access = (Integer)session.getAttribute("access_code");
        if(access == null){
        	response.sendRedirect("error.jsp");
        }else{
        int access_code = access.intValue();
        %>
            <h1>Search Results</h1>
           <%
           ArrayList<PatientBean> patientList = (ArrayList)session.getAttribute("patientDataBean");
           if(patientList.size() != 0){
           		for(int i = 0; i < patientList.size(); i++){
        	%>
        	<dl class="contact"> 
        	   <dt>Name of the Patient : <%= patientList.get(i).getFirstName() %> <%= patientList.get(i).getLastName() %></dt>
        	   <dd>Phone Number : <%= patientList.get(i).getPhoneNum()%></dd>
        	   <dd>Birth Date : <%= patientList.get(i).getBirthDate() %></dd>
        	   <dd>Email ID : <%= patientList.get(i).getEmailID() %></dd>
       	   </dl>
        	   <%
	        	   if(access_code == 1){
	      		   %>
	      		<table class="table1">
	      			<tr>
		       			<td><a class="button" href="delete?id=<%= patientList.get(i).getPatientID() %>">Delete</a></td>
		       			<td><a class="button" href="edit?id=<%= patientList.get(i).getPatientID() %>">Edit</a></td>
		       			<td><a class="button" href="doctor.jsp">Go back</a></td>
	       			</tr>
	    		</table>
	      			<%  
	        	   }
	        	   if(access_code == 2){
	        		   %>
					 
						<a class="button" href="showPayments.jsp?id=<%= patientList.get(i).getPatientID() %>">See Payments</a>
						<a class="button" href="makeAppointment.jsp?id=0&patient_id=<%= patientList.get(i).getPatientID() %>">Make Appointment</a>
						<a class="button" href="sendEmail.jsp?id=<%= patientList.get(i).getPatientID() %>">Send Email</a>						       		   
	          			<a class="button" href="delete?id=<%= patientList.get(i).getPatientID() %>">Delete</a>
	          			<a class="button" href="edit?id=<%= patientList.get(i).getPatientID() %>">Edit</a>
	          			<a class="button" href="staff.jsp">Go back</a>
	          			<hr>
	         			<% 	   
	        	   }
	        	   if(access_code == 3){
	       			%>
	       			<table class="table1">
						<tr>
	      					<td><a href="edit?id=<%= patientList.get(i).getPatientID() %>">Edit</a></td>
	      				</tr>
	      			</table>
	       			<%
	          		}
	          		if(access_code == 4){
	                %>
	                <table class="table1">
						<tr>
	                		<td><a class="button" href="search?id=<%= patientList.get(i).getPatientID() %>">Show</a></td>
	                		<td><a class="button" href="pharmacy.jsp">Go back</a></td>
	                	</tr>
					</table>                	
	                <%
	     	 		}
           		}
           }else{
        	   switch(access_code){
        	   		case 1:
        	   
        	   %>
        	   <table class="table1">
					<tr>
						<td>No Such Patient</td>
					</tr>
					<tr>
                		<td><a class="button" href="doctor.jsp">Go Back</a></td>
                	</tr>
				</table>
              	<%
              			break;
              		case 2:
              	%>
 				<table class="table1">
					<tr>
						<td>No Such Patient</td>
					</tr>
					<tr>						
                		<td><a class="button" href="staff.jsp">Go Back</a></td>
                	</tr>
				</table>
               	<%	
              			break;
              		case 4:
				%>
         				<table class="table1">
        					<tr>
        						<td>No Such Patient</td>
        					</tr>
        					<tr>						
                        		<td><a class="button" href="pharmacy.jsp">Go Back</a></td>
                        	</tr>
        				</table>
                <%	
                      	break;
              	}
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

