<%-- 
    Document   : scheduleAppointment
    Created on : Aug 10, 2011, 8:44:38 PM
    Author     : Rohan
--%>

<%@page import="patient.AppointmentBean"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "employee.*" %>
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

        <title>Schedule an Appointment - My Clinic</title>
        <link href="css/colors.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/fonts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/layout.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/print.css" rel="stylesheet" type="text/css" media="print">
        <link href='http://fonts.googleapis.com/css?family=Ubuntu:regular,regularitalic,bold&v1' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="js/SiteMasterScripts.js"></script>
        <script type="text/javascript" src="js/Validation.js"></script>
        <script type="text/javascript" src="js/calendar.js"></script>

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
                if(session.getAttribute("access_code") == null){
                	response.sendRedirect("error.jsp");
                }else{
                	int access_code = ((Integer)session.getAttribute("access_code")).intValue();
                	int patient_id = Integer.parseInt(request.getParameter("patient_id"));
                	session.setAttribute("patient_id", patient_id);
                	if(access_code == 2){
                    	if(Integer.parseInt(request.getParameter("id")) == 0){
               	%>	
                    <h1>Select Doctor to see the schedule</h1>
	                    <div style="float:right;">
									<a class="button" href="result.jsp">Go Back</a>
						</div>
				<%
					DoctorDB doctor = new DoctorDB();
					ArrayList<DoctorBean> docList = doctor.getDoctorInfo();
					if(docList.size() != 0){
				%>
					<form name="scheduleForm" method="get" action="appointment">
						<select class="loginBox" name="docname">
							<option selected="selected"></option>
				<%
							for(int i = 0; i < docList.size(); i++){	
				%>			
							<option>Dr. <%= docList.get(i).getFirstName() %> <%= docList.get(i).getLastName() %></option>	
				<%
								}
				%>
						</select>
						<input class="button" type="submit" value="Show">
                	</form>
				<%
							}else{
				%>
						There is nothing to show.
				<%								
							}
						}	
						if(Integer.parseInt(request.getParameter("id")) == 1){
						ArrayList<AppointmentBean> appList = (ArrayList<AppointmentBean>)session.getAttribute("appList");
                %>
                    <h1>Make an Appointment with Doctor</h1>
                    	<div style="float:right;">
									<a class="button" href="result.jsp">Go Back</a>
						</div>
						<p>Doctor has following appointments. Please make your appointment accordingly.</p>
                    <table class="table1">
                    	<tr>
                    		<th>Date</th>
                    		<th>Time</th>
                    	</tr>
                   	<% if(appList.size() != 0 ){
                   	%>
                   		<tr>
                   			<td colspan="2"><%= appList.get(0).getDocName() %></td>
                   		</tr>
                   	<%	
                   		if(appList.get(0).getAppDate() != null){
                    		for(int i = 0; i < appList.size(); i++){
            		%>
               			<tr>
               				<td><%= appList.get(i).getAppDate() %></td>
							<td><%= appList.get(i).getAppTime() %></td>
               			</tr>
               		<%
                    		}
                    	}else{
                   	%>
                   		<tr>
                   			<td colspan="2">No Appointment's Scheduled </td>
                   		</tr>
                   	<%
                   		}
                   	}
                    %>
                    </table>
                    <p>Choose a date and time suitable to you.</p>
					<form method="post" action="appointment">
					<input type="hidden" name="docid" value="<%= appList.get(0).getDocID() %>">
						<div class="loginBox"><script>DateInput('orderdate', true, 'DD-MON-YYYY')</script></div>
						<select class="loginBox" name="time">
							<option selected="selected"></option>
							<option>9:00 AM - 9:30 AM</option>
							<option>9:30 AM - 10:00 AM</option>
							<option>10:00 AM - 10:30 AM</option>
							<option>10:30 AM - 11:00 AM</option>
							<option>11:00 AM - 11:30 AM</option>
							<option>11:30 AM - 12:30 PM</option>
							<option>1:30 PM - 2:00 PM</option>
							<option>2:00 PM - 2:30 PM</option>
							<option>2:30 PM - 3:00 PM</option>
							<option>3:00 PM - 3:30 PM</option>
							<option>3:30 PM - 4:00 PM</option>
							<option>4:00 PM - 5:00 PM</option>
						</select>
						<input class="button" type="submit" value="Make Appointment">
					</form>
        		<%	
                    	}
						if(Integer.parseInt(request.getParameter("id")) > 1){
							response.sendRedirect("staff.jsp");
						}
                    }else{
                    	switch(access_code){
                    	case 1:
                    		response.sendRedirect("doctor.jsp");
                    		break;
                    	case 3:
                    		response.sendRedirect("patient.jsp");
                    		break;
                    	case 4:
                    		response.sendRedirect("pharmacy.jsp");
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

