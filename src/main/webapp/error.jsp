<%-- 
    Document   : error
    Created on : Aug 10, 2011, 9:38:12 PM
    Author     : Rohan
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en-US" class="no-js">
    <head>
<!--     	<META HTTP-EQUIV="refresh" CONTENT="5;URL=index.html"> -->
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

        <title>Error... Something Worng happend.</title>
        <link href="css/colors.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/fonts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/layout.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/print.css" rel="stylesheet" type="text/css" media="print">
        <link href='http://fonts.googleapis.com/css?family=Ubuntu:regular,regularitalic,bold&v1' rel='stylesheet' type='text/css'>
        
        <script type="text/javascript" src="js/SiteMasterScripts.js"></script>
        <script type="text/javascript" src="js/Validation.js"></script>

    </head>
    <body>
        <div class="wrapper">
            <div class="banner">
                <!--<img align="middle" src="../images/dmellor_clinic.jpg" alt="">-->
                <div id="bannerTitle">
                    <em>My Clinic</em>
                </div>
            </div>

            <div id="topNav">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="doctor.html">Doctor</a></li>
                    <li><a href="patient.html">Patient</a></li>
                    <li><a href="staff.html">Staff</a></li>
                    <li><a href="pharmacy.html">Pharmacy Login</a></li>
                    <li><a href="contactUs.html">Contact Us</a></li>            
                </ul>
            </div>
            <hr noshade width="100%" align="center" color="#000000">

            <div class="pageBody">
                <div class="contentWrapper">
                    <a name="MainContent"></a>
                    <div class="content">
                        <h1>Error... Something Worng happend.</h1>
                        <%
							session.invalidate();
                        	response.sendRedirect("index.html");
                        %>
                        <p>Redirecting ....</p>
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
