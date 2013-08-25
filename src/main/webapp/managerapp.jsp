<%-- 
    Document   : doctorLogin
    Created on : May 25, 2011, 7:34:12 PM
    Author     : Rohan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

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
            <h1>Manager App</h1>
            
			<%
			if(request.getParameter("id") ==  null){
			%>
			<div style="float:right;">
				<a class="button" href="index.html">Home</a>
			</div>
			<ul class="noBullets">
				<li><a href="managerapp.jsp?id=1" class="button">Create User for Doctor</a></li>
				<li><a href="managerapp.jsp?id=2" class="button">Create User for Staff</a></li>
			</ul>
			<%
			}else{
				if(Integer.parseInt(request.getParameter("id")) == 1){
					
			%><div style="float:right;">
				<a class="button" href="managerapp.jsp">Back</a>
			</div>
			<form name="Login" method = "get" action="login">
				<div class="loginBox">First Name : <input type="text" size="33" name="f_name"></input></div>
				<div class="loginBox">Last Name : <input type="text" size="33" name="l_name"></input></div>
				<div class="loginBox">Username : <input type="text" size="33" name="username"></input></div>
				<div class="loginBox">Password : <input type="password" size="33" name="password"></input></div>
				<div class="loginBox">Address : <input type="text" size="36" name="address"></input></div>
				<div class="loginBox">Designation : 
				<select class="loginBox" name="designation">
					<option selected="selected"></option>
					<option>Doctor</option>
					<option>Nurse</option>
					<option>Receptionist</option>
				</select>
				</div>
				<div class="loginBox">Field. : <input type="text" size="35" name="field"></input></div>
				<input class="button" type="submit" value="Create"></input>
				<input class="button" type="reset" value="Reset"></input>		
			</form>
			<%
				}
				if(Integer.parseInt(request.getParameter("id")) == 2){
			%><div style="float:right;">
				<a class="button" href="managerapp.jsp">Back</a>
			</div>
			<form name="Login" method = "get" action="login">
				<div class="loginBox">First Name : <input type="text" size="33" name="f_name"></input></div>
				<div class="loginBox">Last Name : <input type="text" size="33" name="l_name"></input></div>
				<div class="loginBox">Username : <input type="text" size="33" name="username"></input></div>
				<div class="loginBox">Password : <input type="password" size="33" name="password"></input></div>
				<div class="loginBox">Address : <input type="text" size="36" name="address"></input></div>
				<div class="loginBox">Designation : 
				<select class="loginBox" name="designation">
					<option selected="selected"></option>
					<option>Nurse</option>
					<option>Receptionist</option>
				</select>
				</div>
				<input class="button" type="submit" value="Create"></input>
				<input class="button" type="reset" value="Reset"></input>		
			</form>
			<%	
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

