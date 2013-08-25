<%-- 
    Document   : doctor
    Created on : Aug 10, 2011, 8:44:38 PM
    Author     : Rohan
--%>

<%@page import="java.util.logging.Logger"%>
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
		
        <title><%= session.getAttribute("f_name") %> <%= session.getAttribute("l_name") %></title>
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
                    	if(access == null ){
                    		response.sendRedirect("index.html");
                    	}
                    	else{
						try{           			
		           			if(session.getAttribute("f_name") != null && session.getAttribute("l_name") != null){
		           %>
                        <h1><%= session.getAttribute("f_name") %> <%= session.getAttribute("l_name") %></h1>
                        <ul class="noBullets">
                            <li><a class="button" href="createNewPatient.jsp">Create New Patient</a></li>
                            <li><a class="button" href="search.jsp">Search for Patient</a></li>
                            <li><a class="button" href="addPharmacy.jsp">Add Pharmacy</a></li>
                        </ul>
                   <%
                   			if(session.getAttribute("message") != null){
              				%>
                   				<p><span class="message"><%= session.getAttribute("message") %></span></p>
              				<%
              				session.removeAttribute("message");
                   			}
		           			}else{
		           				String message = "Please Login Again.";
		           				session.setAttribute("message",message);
		           				response.sendRedirect("login.jsp");
		           			}
		           			
						}catch(Exception e){
							response.sendRedirect("error.jsp");
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
