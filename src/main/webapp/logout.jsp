<%-- 
    Document   : logout
    Created on : Aug 10, 2011, 9:02:23 PM
    Author     : Rohan
--%>

<%@ page import="com.mysql.jdbc.MySQLConnection"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en-US" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

        <title>My Clinic</title>
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
            <div class="pageBody">
                <div class="contentWrapper">
                    <a name="MainContent"></a>
                    <div class="content">
                        <h1>LogOut</h1>
                    <%
                    try {                        
                    	Integer access = (Integer) session.getAttribute("access_code");
                    	int access_code = access.intValue();
                    	
                        switch (access_code)
                        {
                            case 1:
                            	session.removeAttribute("access_code");
                            	session.invalidate();
                            	response.sendRedirect("doctor.html");
                            break;
                            case 2: 
                            	session.removeAttribute("access_code");
                           		session.invalidate();
                        		response.sendRedirect("staff.html");
                            break;       
                            case 3:
                            	session.removeAttribute("access_code");
                            	session.invalidate();
                        		response.sendRedirect("patient.html");
                            break;      
                            case 4: 
                            	session.removeAttribute("access_code");
                            	session.invalidate();
                       			response.sendRedirect("pharmacy.html"); 
                            break;
                            default:
                            	response.sendRedirect("error.jsp");
                        	break;
                        }
                    }
                    catch(Exception e)
                    {
                        out.println("error "+e);
                        System.out.println("Exception Caught in logout page"+e);
                        response.sendRedirect("error.jsp");
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
