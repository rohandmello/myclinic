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
        
        <title>Search - My Clinic</title>
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
           		try{
           		int access_code = access.intValue();
           		switch(access_code){
           			case 1:
           				%>
							<h1>Search Patient</h1>
							<div style="float:right;">
								<a class="button" href="doctor.jsp">Go Back</a>
							</div>
							<form name="searchForm" method = "post" action="search">
			                    <div class="loginBox">
			                        First Name : <input type="text" name="pf_name" size="33"></input>
			                    </div>
			                    <div class="loginBox">
			                        Last Name : <input type="text" name ="pl_name" size="33"></input>
			                    </div>
			                    <input class="button" type="submit" value="Search"></input>
			                    <input class="button" type="reset" value="Reset"></input>
                			</form>
           				<%
           				break;
           			case 2:
           				%>	<h1>Search Patient</h1>
							<div style="float:right;">
								<a class="button" href="staff.jsp">Go Back</a>
							</div>
							<form name="searchForm" method = "post" action="search">
			                    <div class="loginBox">
			                        First Name : <input type="text" name="pf_name" size="33"></input>
			                    </div>
			                    <div class="loginBox">
			                        Last Name : <input type="text" name ="pl_name" size="33"></input>
			                    </div>
			                    <input class="button" type="submit" value="Search"></input>
			                    <input class="button" type="reset" value="Reset"></input>
                			</form>
       					<%
           				break;
           			case 3:
           				%>
          					<h1>Search Prescription Details</h1>
							<div style="float:right;">
								<a class="button" href="patient.jsp">Go Back</a>
							</div>
							<form name="searchPresc" method = "get" action="prescription">
			                    <div class="loginBox">
			                        Date of Prescription : <input type="text" name="pdate" maxlength="11" size="23"> (Format : "DD-MON-YYYY") eg. 22-SEP-2011
			                    </div>
                    			<input class="button" type="submit" value="Search"></input>
                    			<input class="button" type="reset" value="Reset"></input>
                			</form>
       					<%
           				break;
           			case 4:
           				%>	<h1>Search Patient</h1>
							<div style="float:right;">
								<a class="button" href="pharmacy.jsp">Go Back</a>
							</div>
							<form name="searchForm" method = "post" action="search">
			                    <div class="loginBox">
			                        First Name : <input type="text" name="pf_name" size="33"></input>
			                    </div>
			                    <div class="loginBox">
			                        Last Name : <input type="text" name ="pl_name" size="33"></input>
			                    </div>
			                    <input class="button" type="submit" value="Search"></input>
			                    <input class="button" type="reset" value="Reset"></input>
                			</form>
       					<%
           				break;
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

