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
        <%
        if(session.getAttribute("access_code") == null){
        	response.sendRedirect("error.jsp");
        }else{
        	int access_code = ((Integer)session.getAttribute("access_code")).intValue();
	        	switch(access_code){
	        	case 2:
		        	if(request.getParameter("status").equals("PENDING")){
		        	%>
		    		<h1>Enter Payment Details</h1>
		    		<div style="float:right;">
						<a class="button" href="showPayments.jsp?id=<%= request.getParameter("patid") %>">Go Back</a>
					</div>
		    		<form name="payform" method="post" action="payment">
		    		<input type="hidden" name="appid" value="<%= request.getParameter("id") %>">
		    		 	<div align="left">
                        	<div class="loginBox">Amount : <input type="text" name="amount" size="32"></input></div>
                        	<div class="loginBox">Method :
                        		<select name="method" onchange="fnInsurance()">
                        		<option selected="selected"></option>
                        		<option>Cash</option>
                        		<option>Check</option>
                        		<option>Insurance</option>
                        		</select> 
                        	</div>
                        	<div class="loginBox">Bank Name : <input type="text" name="bname" id="bVisible" style="visibility:hidden" size="28"></input></div>
                        	<div class="loginBox">Check Number : <input type="text" name="chnum" id="chVisible" style="visibility:hidden" size="28"></input></div>
                        	<div class="loginBox">Company Name : <input type="text" name="cname" id="cVisible" style="visibility:hidden" size="28"></input></div>
                        	<div class="loginBox">Policy Number : <input type="text" name="pnum" id="pVisible" style="visibility:hidden" size="29"></input></div>
                        	<div class="loginBox">Co-pay : <input type="text" name="copay" id="cpVisible" style="visibility:hidden" size="37"></input></div>
                        	<div class="loginBox">Status :
                        	 	<select name="status">
                        		<option selected="selected"></option>
                        		<option>PENDING</option>
                        		<option>PAID</option>
                        		<option>RECEIVED</option>
                        		<option>IN-PROGRSS</option>
                        		</select>
							</div>
                        	<input class="button" type="submit" value="Update"></input>
                            <input class="button" type="reset" value="Reset"></input>
                       	</div>
                    </form>   	
		        	<%	
		        	}else{
		        	%>
		        	<form name="payform" method="post" action="">
		    		<input type="hidden" name="appid" value="<%= request.getParameter("id") %>">
		        	<h1>Change Status of Payment Details</h1>
		        		<div class="loginBox">Status :
                       	 	<select name="status">
                       		<option selected="selected"></option>
                       		<option>PAID</option>
                       		<option>RECEIVED</option>
                       		<option>IN-PROGRSS</option>
                       		</select>
						</div>
						<input class="button" type="submit" value="Update"></input>
						<input class="button" type="reset" value="Reset"></input>
					</form>
		        	<%
		        		response.sendRedirect("staff.jsp");
		        	}
	        	break;
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
