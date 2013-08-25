<%-- 
    Document   : view
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
            <%
            try{
            ArrayList<PrescriptionBean> presList = (ArrayList)session.getAttribute("presriptionList");
    		ArrayList<DoctorBean> docList = (ArrayList)session.getAttribute("doctorList");
            if(request.getParameter("id") == null){
            %>
            <h1>List of Prescription for <%=session.getAttribute("f_name") %> <%=session.getAttribute("l_name") %></h1>
            <div style="float:right;">
				<a class="button" href="search.jsp">Go Back</a>
			</div>
            <table class="table1">
            	<tr>
            		<th>Prescription Date</th>
            		<th>View</th>
            	</tr>
            	
            	<%
            		if(presList.size() != 0){
            			for(int i = 0; i < presList.size(); i++){
            	%>
            	<tr>
            		<td><%= presList.get(i).getPrescDate() %></td>
            		<td><a class="button" href="view.jsp?id=<%= i %>">View</a></td>
            	</tr>
            	<%
            			}
            		}
            	%>
            </table>
            <%
            }else{
            	int id = Integer.parseInt(request.getParameter("id"));
            %>
            <h1>Prescription for <%=session.getAttribute("f_name") %> <%=session.getAttribute("l_name") %> on <%= presList.get(id).getPrescDate() %></h1>
            <div style="float:left; width: 700px;">
				<a class="button" href="view.jsp">Go Back</a>
			</div>
            <table class="table1">
            	<tr>
            		<th>Doctor's Name</th>
            		<th>Medicine Name</th>
            		<th>Consumption Duration</th>
            		<th>Consumption Per Day</th>
            		<th>Comments</th>
            		<th>Refill</th>
            	</tr>
            	<tr>
            		<td>Dr. <%= docList.get(id).getFirstName() %> <%= docList.get(id).getLastName() %></td>
            		<td><%= presList.get(id).getMedicineName() %></td>
            		<td><%= presList.get(id).getDuration() %></td>
            		<td><%= presList.get(id).getConsumptioPerDay() %></td>
            		<td><%= presList.get(id).getComments() %></td>
            		<td><%= presList.get(id).getRefill() %></td>
            	</tr>
            <%
            }
            }catch(Exception e){
            	response.sendRedirect("patient.jsp");
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
