<%-- 
    Document   : doctorLogin
    Created on : May 25, 2011, 7:34:12 PM
    Author     : Rohan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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

        <title><%=session.getAttribute("pharmacyname") %> - My Clinic</title>
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
        	if(session.getAttribute("access_code") == null){
        		response.sendRedirect("error.jsp");
        	}else{
        		int access_code = ((Integer)session.getAttribute("access_code")).intValue();
        		switch(access_code){
        			case 4:
        %>
            <h1><%=session.getAttribute("pharmacyname") %></h1>
            <div class="wrap">
            	<div class="floatLeft">
            		<ul class="noBullets">
		            	<li><a class="button" href="search.jsp">See Prescription</a></li>
            		</ul>
            	</div>
            	<div class="floatLeft">
            		<ul class="noBullets">
            			<li><a class="button" href="sendEmail.jsp">Send Email</a></li>
		            </ul>
		        </div>
            </div>
	            <%
	            	if(session.getAttribute("message") != null){
		       	%>
		        		<p><span class="message"><%= session.getAttribute("message") %></span></p>
		        <%
		        		session.removeAttribute("message");
	                }
            		break;
        		case 1: 
        			response.sendRedirect("doctor.jsp");
        			break;
        		case 2:
        			response.sendRedirect("staff.jsp");
        			break;
        		case 3:
        			response.sendRedirect("patient.jsp");
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
