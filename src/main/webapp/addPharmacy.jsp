<!-- 
    Document   : addPharmacy
    Created on : Jul 2, 2011, 8:36:58 PM
    Author     : Rohan
-->
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

        <title>Add Pharmacy Details - My Clinic</title>
        <link href="css/colors.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/fonts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/layout.css" rel="stylesheet" type="text/css" media="screen">
        <link href="css/print.css" rel="stylesheet" type="text/css" media="print">
        <link href='http://fonts.googleapis.com/css?family=Cabin:regular,regularitalic,bold&v1' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="js/SiteMasterScripts.js"></script>
        <script type="text/javascript" src="js/modernizr.js"></script>

    </head>
    <body>
<!--        <div class="logOutBox">
            <a href="">Logout</a>
        </div>-->
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
                    	int access_code = access.intValue();
                    	switch(access_code){
                    	case 2:
                    %>
                        <h1>Add Pharmacy Details</h1>
                        <div style="float:right;">
							<a class="button" href="staff.jsp">Go Back</a>
						</div>
                        <form name="pharmacy" action="save" method="get" onSubmit = "return fnPharmacy()">
                            <div align="left">
                                <div class="loginBox">Pharmacy Name : <input type="text" name="ph_name" ></input></div>
                                <div class="loginBox">User Name : <input type="text" name="u_name" ></input></div>
                                <div class="loginBox">Password : <input type="password" name="pwd" ></input></div>
                                <hr></hr>
                                <div class="loginBox">Email : <input type="text" name="email" ></input></div>
                                <div class="loginBox">Ph Num. : <input type="text" name="phnum" maxlength="10"></input></div>
                                <div class="loginBox">Address : <input type="text" name="address" ></input></div>
                                <input class="button" type="submit" value="Save"></input>
                                <input class="button" type="reset" value="Reset"></input>
                            </div>
                        </form>
                        <%
                        if(session.getAttribute("mesage") != null){
                        %>
               				<p><span class="message"><%= session.getAttribute("message") %></span></p>
          				<%
          				session.removeAttribute("message");
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
