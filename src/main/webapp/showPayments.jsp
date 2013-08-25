<%-- 
    Document   : showPayments
    Created on : May 25, 2011, 7:34:12 PM
    Author     : Rohan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "patient.*" %>
<%@ page import = "employee.*" %>
<%@ page import = "java.util.Calendar" %>
<%@ page import = "java.text.SimpleDateFormat;" %>
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

        <title><%=session.getAttribute("f_name") %> <%=session.getAttribute("l_name") %> - My Clinic</title>
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
            <% int patient_id = Integer.parseInt(request.getParameter("id"));
            Payments pay = new Payments();
            ArrayList<AppointmentBean> appList = pay.getPaymentInfo(patient_id);
            PatientDB pdb = new PatientDB();
            ArrayList<PatientBean> patientInfo = pdb.getPatientInfo(patient_id);
            if(patientInfo.size() != 0){
            %>
            <h1><%= patientInfo.get(0).getFirstName() %> <%= patientInfo.get(0).getLastName() %>'s Payment Information</h1>
            <div style="float:right; width: 700px;">
				<a class="button" href="result.jsp">Go Back</a>
			</div><br>
            <%
            }
            %>
            <table class="table1">
            	<tr>
            		<th>Doctor's Name</th>
            		<th>Appointment Date</th>
            		<th>Appointment Time</th>
            		<th>Payment Status</th>
            		<th>Edit</th>
            	</tr>
            <%
            if(appList.size() != 0){
            	for(int i = 0; i<appList.size(); i++){
           %>
           		<tr>
            		<td>Dr. <%= appList.get(i).getDocName() %></td>
            		<td><%= appList.get(i).getAppDate() %></td>
            		<td><%= appList.get(i).getAppTime() %></td>
            		<td><%= appList.get(i).getPaymentStatus() %></td>
            		<%
            		System.out.println(appList.get(i).getPaymentStatus().equals("PAID"));
            		if(appList.get(i).getPaymentStatus().equals("PAID")){
	            		%>
	            		<td></td>
	            		<%
            		}else{
	            		%>
	            		<td><a class="button" href="editPayments.jsp?id=<%= appList.get(i).getAppID() %>&status=<%= appList.get(i).getPaymentStatus() %>&patid=<%= patient_id %>">Make Payment</a></td>
	            		<%	
            		}
            		%>
            		
            	</tr>
           <%
            	}
            }else{
            %>
           		<tr>
            		<td colspan="5">There are no appointments made so no payment is due</td>
            	</tr>
            <%
            }
            %>	
            </table>
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
