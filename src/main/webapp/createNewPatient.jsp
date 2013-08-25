<%-- 
    Document   : createNewPatient
    Created on : Aug 10, 2011, 9:57:23 PM
    Author     : Rohan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

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

        <title>Create New Patient - My Clinic</title>
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
                        <h1>New Patient Information</h1>
                        <%
		           		Integer access = (Integer)session.getAttribute("access_code");
                        if(access == null){
                        	response.sendRedirect("error.jsp");
                        }else{
		           		int access_code = access.intValue();
		           		switch(access_code){
           					case 1:
           				%>
							<div style="float:right;">
								<a class="button" href="doctor.jsp">Go Back</a>
							</div>
           				<%
           					break;
           					case 2:
           				%>
							<div style="float:right;">
								<a class="button" href="staff.jsp">Go Back</a>
							</div>
       					<%
           					break;
           					case 3:
           						response.sendRedirect("patient.jsp");
           						break;
           					case 4:
           						response.sendRedirect("pharmacy.jsp");
           						break;
      						default:
      							response.sendRedirect("error.jsp");
           						break;
		           		}
       					%>
                        <form action="save" method="post">
                            <div align="left">
                                <div class="loginBox">First Name* : <input type="text" name="f_name" size="32"></input></div>
                                <div class="loginBox">Last Name* : <input type="text" name="l_name" size="32"></input></div>
                                <div class="loginBox">User Name* : <input type="text" name="u_name" size="32"></input></div>
                                <div class="loginBox">Password* : <input type="password" name="pwd" size="33"></input></div>
                                <hr></hr>
                                <div class="loginBox"><script>DateInput('orderdate', true, 'DD-MON-YYYY')</script></div>
                                <div class="loginBox">Email* : <input type="text" name="email" size="37"></input></div>
                                <div class="loginBox">Ph Num.* : <input type="text" name="phnum" maxlength="10" size="34"></input></div>
                                <div class="loginBox">Address Line 1* : <input type="text" name="addressL1" size="47"></input></div>
                                <div class="loginBox">Address Line 2 : <input type="text" name="addressL2" size="47"></input></div>
                                <div class="loginBox">City* : <input type="text" name="city" size="39"></input></div>
                                <div class="loginBox">State* : <input type="text" name="State" size="38" maxlength="2"></input></div>
                                <div class="loginBox">Zip* : <input type="text" name="zip" size="40" maxlength="5"></input></div>
                                <input class="button" type="submit" value="Save"></input>
                                <input class="button" type="reset" value="Reset"></input>
                            </div>
                        </form>
	                  <%
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
