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
            <h1>Login</h1>
            <div style="float:right;">
								<a class="button" href="index.html">Home</a>
			</div>
           <%
      		Integer access = (Integer)session.getAttribute("access_code");
				try{           			
           			if(access == null ){
           %>
                <form name="Login" method = "post" action="login" onSubmit = "return fnValidateLogin()">
                    <div class="loginBox">
                        User Name : <input type="text" name="username" size="33"></input>
                    </div>
                    <div class="loginBox">
                        Password : <input type="password" name ="password" size="34"></input>
                    </div>
                    <input class="button" type="submit" value="Login"></input>
                    <input class="button" type="reset" value="Reset"></input>
                </form>
			<%		
							if(session.getAttribute("message") != null){
				  				%>
				   				<p><span class="message"><%= session.getAttribute("message") %></span></p>
								<%
								session.removeAttribute("message");
				   			}
           			}
	           		else{
	           			int accesscode = access.intValue();
	           			switch(accesscode){
	           				case 1:
	           					response.sendRedirect("doctor.jsp");
	           					break;
	           				case 2:
	           					response.sendRedirect("staff.jsp");
	           					break;
	           				case 3:
	           					response.sendRedirect("patient.jsp");
	           					break;
	           				case 4:
	           					response.sendRedirect("pharmacy.jsp");
	           					break;
	        				default:
	        					session.invalidate();
	        					response.sendRedirect("index.html");
	           						
	           			}
           			}
				}catch(Exception e){
					response.sendRedirect("error.jsp");
					System.out.println("Inside Login.jsp = "+e);
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

