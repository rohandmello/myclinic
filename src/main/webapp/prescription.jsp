<!-- 
    Document   : prescription
    Created on : Jul 2, 2011, 8:37:28 PM
    Author     : Rohan
-->
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="patient.*"%>
<html lang="en-US" class="no-js">
    <head>
    <%
    response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate"); // no-store 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", 0); 
    response.setHeader("Pragma", "no-cache");
    %>
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
                   			case 1:
                   		%>
                   		
                        <h1>Prescription</h1>
                        <div style="float:right;">
							<a class="button" href="doctor.jsp">Go Back</a>
						</div>
                        <form name="prescription" method="post" action="prescription">
                        	<div class="loginBox">First Name : <input type="text" name="pf_name" size="33"></input></div>
                        	<div class="loginBox">Last Name : <input type="text" name="pl_name" size="33"></input></div>
							<div class="loginBox">Email ID :
							<br><select name="email">
							<option selected="selected"></option>
							<%
 							PatientDB pdb =  new PatientDB();
							ArrayList<PatientBean> patientList = new ArrayList<PatientBean>();
							patientList = pdb.getPatientInfo();
							if(patientList.size() != 0){
								for(int i = 0; i < patientList.size(); i++){
								%>
								<option><%= patientList.get(i).getEmailID()%></option>
								<%
 								} 
 							} 
 							%>
							</select>
							</div>
                        	<div class="loginBox">Medicine Name : <input type="text" name="m_name" size="28"></input></div>
                        	<div class="loginBox">Consumption Duration : <input type="text" name="c_duration" size="21"></input></div>
                        	<div class="loginBox">Consumption Per Day : <input type="text" name="consumptionperday" size="21"></input></div>
                        	<div class="loginBox">Comments : <input type="text" name="comments" size="33"></input></div>
                        	<div class="loginBox">Refills :
                        	<select name="refill">
                        		<option selected="selected" ></option>
                        		<option>0</option>
                        		<option>1</option>
                        		<option>2</option>
                        		<option>3</option>
                        		<option>4</option>
                        	</select> 
                        	</div>
                        	<input class="button" type="submit" value="Save"></input>
                   			<input class="button" type="reset" value="Reset"></input>
                        </form>
                        <%
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
        