/* Validation For UserName */

function fnUserName()
{
var val=document.Login.username.value;
	if(val=="")
	{
		alert("Username is Mandatory cannot be left blank");
		document.Login.username.focus();
		return false;
	}
	
			
return true;
}


/* Validation For PassWord */

function fnPassword()
{
var val=document.Login.password.value;
	if(val=="")
	{
		alert("Password is Mandatory cannot be left blank");
		document.Login.password.focus();
		return false;
	}
			
return true;
}


/* Validation For LoginPage */

function fnValidateLogin()
{
    var Ret_val;
    Ret_val = fnUserName();
    if(Ret_val == false)
        return Ret_val;
    else
        Ret_val = fnPassword();
    return Ret_val;
}

	
/**********************************************************************/

/* Validation For UserName */

function fnCUserName()
{
var val=document.ChangePassword.username1.value;
	if(val=="")
	{
		alert("Username is Mandatory cannot be left blank");
		document.ChangePassword.username1.focus();
		return false;
	}
	
			
return true;
}


/* Validation For PassWord */

function fnCPassword()
{
var val=document.ChangePassword.password1.value;
	if(val=="")
	{
		alert("Password is Mandatory cannot be left blank");
		document.ChangePassword.password1.focus();
		return false;
	}
			
return true;
}


/* Validation For New PassWord */

function fnNewPassword()
{
var val=document.ChangePassword.password2.value;
	if(val=="")
	{
		alert("Password is Mandatory cannot be left blank");
		document.ChangePassword.password2.focus();
		return false;
	}
			
return true;
}


/* Validation For ReEnter New PassWord */

function fnReNewPassword()
{
var val=document.ChangePassword.password3.value;
	if(val=="")
	{
		alert("Password is Mandatory cannot be left blank");
		document.ChangePassword.password3.focus();
		return false;
	}
			
return true;
}

/* Validation For Match PassWord */

function fnMatchPassword()
{
var val1=document.ChangePassword.password2.value;
var val2=document.ChangePassword.password3.value;

	if(val1!=val2)
	{
		alert("New Password and ReEntered Password Should Match");
		document.ChangePassword.password2.value = "";
		document.ChangePassword.password3.value = "";
		document.ChangePassword.password2.focus();
		return false;
	}
			
return true;
}


/* Validation For ChangePassword */

function fnValidateChangePassword()
{
    var Ret_val;

    Ret_val = fnCUserName();

    if(Ret_val == false)
        return Ret_val;

    else
        Ret_val = fnCPassword();
   if(Ret_val == false)
         return Ret_val;

    else
        Ret_val = fnNewPassword();
    if(Ret_val == false)
        return Ret_val;

    else
        Ret_val = fnReNewPassword();
    if(Ret_val == false)
        return Ret_val;

    else
        Ret_val = fnMatchPassword();
    if(Ret_val == false)
        return Ret_val;

    return Ret_val;
}


/**********************************************************************/

/* Validation For ChangePasswordMgr */

function fnValidateChangePasswordMgr()
{
var Ret_val;

Ret_val = fnCUserName();

if(Ret_val == false)
return Ret_val;

else
Ret_val = fnNewPassword();
if(Ret_val == false)
return Ret_val;

else
Ret_val = fnReNewPassword();
if(Ret_val == false)
return Ret_val;

else
Ret_val = fnMatchPassword();
if(Ret_val == false)
return Ret_val;


return Ret_val;

}


/**********************************************************************/



/* Validation For Order Reports*/
function fnValidateOrderId()
{
var val=document.report.orderid.value;
	if(val=="")
	{
		alert("Mandatory field cannot be left blank");
		document.report.orderid.focus();
		return false;
	}
	if(isNaN(val))
	{
		alert("PLEASE ENTER NUMBERS ONLY");
		document.report.orderid.value="";
		document.report.orderid.focus();
		return false;
	}
	
	if(val.length<4)
	{
		alert("Order Id cannot be less than 4 digits");
		document.report.orderid.value="";
		document.report.orderid.focus();
		return false;
	}
		
	
return true;
}


/**********************************************************************/



/* Validation Fore Invoice(Priyanka(DBA))*/

function fnValidateCustomerId()
{
   var val;
	val = document.InvoiceForm.CustomerId.value ;

if (val == "")
{
alert("Mandatory Field Cannot Be Blank");
document.InvoiceForm.CustomerId.focus();
return (false);
}

//no blank spaces allowed

// only upto 4 digits can be entered
if (val.length < 4)
{
alert("Should Contain 4 Digits");
document.InvoiceForm.CustomerId.focus();
return (false);
}
	if(isNaN(val))
	{
				alert("PLEASE ENTER NUMBERS  ONLY");
				val=" ";
				return false;
	}

if ((val(0) == 32) ||(val(1) == 32 )||(val(2) == 32)||(val(3) == 32))
{
alert("NO BLANK SPACES ALLOWED");
document.InvoiceForm.CustomerId.focus();
return (false);
}

}


/**********************************************************************/


/* Validating FromDate > To Date (ML) */
 

function check()
{
	var fromday=document.ODForm.DateFrom_Day.value;
	//alert(fromday);
	var frommonth=document.ODForm.DateFrom_Month.value;
	//alert(frommonth);
	var fromyear=document.ODForm.DateFrom_Year.value;
	//alert(fromyear);
	var today=document.ODForm.DateTo_Day.value;
	//alert(today);
	var tomonth=document.ODForm.DateTo_Month.value;
	//alert(tomonth);
	var toyear=document.ODForm.DateTo_Year.value;
	//alert(toyear);
	if(fromyear>toyear)
	{
		alert("Enter a valid Year, 'To' year cannot be less than 'From' year");
		return false;
	}
	switch(frommonth)
	{
		case 'JAN': frommonth=1;
		                  break;
		case 'FEB': frommonth=2;
		                  break;
		case 'MAR': frommonth=3;
		                  break;
		case 'APR': frommonth=4;
		                  break;
		case 'MAY': frommonth=5;
		                  break;
		case 'JUN': fromomnth=6;
		                  break;
		case 'JUL': frommonth=7;
		                  break;
		case 'AUG': frommonth=8;
		                  break;
		case 'SEP': frommonth=9;
		                  break;
		case 'OCT': frommonth=10;
		                  break;
		case 'NOV': frommonth=11;
		                  break;
		case 'DEC': frommonth=12;
		                  break;
	}
	switch(tomonth)
	{
		case 'JAN': tomonth=1;
		                  break;
		case 'FEB': tomonth=2;
		                  break;
		case 'MAR': tomonth=3;
		                  break;
		case 'APR': tomonth=4;
		                  break;
		case 'MAY': tomonth=5;
		                  break;
		case 'JUN': tomnth=6;
		                  break;
		case 'JUL': tomonth=7;
		                  break;
		case 'AUG': tomonth=8;
		                  break;
		case 'SEP': tomonth=9;
		                  break;
		case 'OCT': tomonth=10;
		                  break;
		case 'NOV': tomonth=11;
		                  break;
		case 'DEC': tomonth=12;
		                  break;
	}
	if(frommonth>tomonth)
	{
		alert("Enter a valid Month, 'To' month cannot be less than 'From' month");
		return false;
	}
	if((fromyear==toyear)&&(frommonth==tomonth)&&(fromday>today))
	{
		alert("Enter a valid Day, 'To' day cannot be less than 'From' day");
		return false;
	}
	return true;
}


/**********************************************************************/


/* Validation For date */



function checkdate(date)
{
            if((isFinite(date)))
            {
               return false;
            }
            if(date.length==11)
            {     
                  var dat=date.substr(0,2);
                  var month=date.substr(3,3);
                  var year=date.substr(7,4);
                  var dash1=date.substr(2,1);
                  var dash2=date.substr(6,1);
                  var month=month.toLowerCase();
                  if(!(dat>0 && dat<32))
                  {
                	  alert("Enter a valid date between 1 and 31");
                	  return false;
                  }
                  if(!((dash1=="-")&&(dash2=="-")))
                  {
	                  alert("You missed symbol - ");
	                  return false;
                  }
                  if(!(year>1900 && year<2099))
                  {
	                  alert("please enter a valid year");
	                  return false;
                  }
                  if(!((month=="FEB")||(month=="JAN")||(month=="MAR")||(month=="APR")||(month=="MAY")||(month=="JUN")||(month=="JUL")||(month=="AUG")||(month=="SEP")||(month=="OCT")||(month=="NOV")||(month=="DEC")||(month=="jan")||(month=="feb")||(month=="mar")||(month=="apr")||(month=="may")||(month=="jun")||(month=="jul")||(month=="aug")||(month=="sep")||(month=="oct")||(month=="nov")||(month=="dec")))
                  {
	                  alert("Please enter a valid month");
	                  return false;
                  }
                 else
                  {
                       if(((month=="feb")||(month=="FEB"))&&(year%4==0)&&(dat>29))
                        {
	                        alert("The year is a leap year and cannot have date 29");
	                        return false;
                        }
                        else if(((month=="feb")||(month=="FEB"))&&(year%4!=0)&&(dat>28))
                        {
                        alert("The month cannot have date > 28");
                        return false;
                        }
                        else
                        {
                        return true;
                        }
                  }
            }
                  else
	              {
	               return false;
	              }
}
     function check(date)
      {
                  dateflag=checkdate(date)
                  if(dateflag==false)
                  {
                              alert("Please enter a date!(DD-MON-YYYY)");
                              return false;
                  }
                  // validate email here
                  return true;
      }
      
      

/* Validation for Order Status Report Form */

function fnOrderStatusReport()
{
var Ret_val;
var FromDate = document.OrderForm.fromdate.value;
var ToDate = document.OrderForm.todate.value;

Ret_val = fnorderstatus();

if(Ret_val == false)
	return false;

/*else 
Ret_val1 = check(FromDate);
if(Ret_val1 == false)
return Ret_val1;

else
Ret_val1 = check(ToDate);
if(Ret_val1 == false)
return Ret_val1;
*/
else
return Ret_val1;


}



function fnorderstatus()
{
var val=document.OrderForm.orderStatus.value;
var val1=document.OrderForm.fromdate.value;
var val2=document.OrderForm.todate.value;
     if((val=="") && (val1=="") && (val2==""))
      {
            alert("All fields cannot be blank. Enter Either Status Or Dates");
            document.OrderForm.orderStatus.focus();
            return false;
      }     
      if(val)
     {
     if((val!='A') && (val!='D') && (val!='P') && (val!='R'))
      {
            alert("Status Can Be Either A,D,P,R Only");
            document.OrderForm.orderStatus.focus();
            return false;
      }
      }
      if((val1=="") && (val2!=""))
      {
            alert("Enter Either Both Dates Or None");
            document.OrderForm.orderStatus.focus();
           return false;
      }
      if((val1!="") && (val2==""))
      {
            alert("Enter Either Both Dates Or None");
            document.OrderForm.orderStatus.focus();
            return false;
      }


}


/**********************************************************************/


function fnCustomerId()
{
var val=document.CustomerReport.CustId.value;
var val1=document.CustomerReport.fromdate.value;
var val2=document.CustomerReport.todate.value;
	if((val=="") && (val1=="") && (val2==""))
	{
		alert("All fields Are Mandatory.");
		document.CustomerReport.CustId.focus();
		return false;
	}	
	if(isNaN(val))
	{
		alert("Please Enter Numbers Only");
		document.CustomerReport.CustId.value="";
		document.CustomerReport.CustId.focus();
		return false;
	}
	if(val.length<4)
	{
		alert("Customer Id cannot be less than 4 digits");
		document.CustomerReport.CustId.value="";
		document.CustomerReport.CustId.focus();
		return false;
	}
	if((val) && (val1=="") && (val2==""))
	{
		alert("Enter Both Dates");
		document.CustomerReport.fromdate.focus();
		return false;
	}	
	if((val) && (val1=="") && (val2))
	{
		alert("Enter Both Dates");
		document.CustomerReport.fromdate.focus();
		return false;
	}		
	if((val) && (val1) && (val2==""))
	{
		alert("Enter Both Dates");
		document.CustomerReport.fromdate.focus();
		return false;
	}	

return true;

}






 
/* Validation for CustomerReport Form */

function fnValidateCustomerReport()
{
var Ret_val;
var FromDate = document.CustomerReport.fromdate.value;
var ToDate = document.CustomerReport.todate.value;

Ret_val = fnCustomerId();

if(Ret_val == false)
return Ret_val;

else
Ret_val = check(FromDate);
if(Ret_val == false)
return Ret_val;

else
Ret_val = check(ToDate);
if(Ret_val == false)
return Ret_val;

return Ret_val;

}





/**********************************************************************/

/* Validation For Modify Order (SQA) */

 
 function fnValidateOrderId1()
{
   var val;
      val = document.modifyOrderDetailshtml.OrderId.value ;
if (val == "")
{
alert("Mandatory Field Cannot Be Blank");
document.modifyOrderDetailshtml.OrderId.focus();
return (false);
}
// only upto 4 digits can be entered
if (val.length < 4)
{
alert("Maximum of 4 digits only.");
document.modifyOrderDetailshtml.OrderId.focus();
return (false);
}
if (val.length > 4)
{
alert("Maximum of 4 digits only.");
document.modifyOrderDetailshtml.OrderId.focus();
return (false);
}
// allow ONLY numeric keys, no symbols or punctuation
       if(isNaN(val))
      {
                       alert("PLEASE ENTER NUMBERS  ONLY");
                        val=" ";
                        return false;
      }
      //no blank spaces allowed
if ((val(0) ==32)||(val(1) ==32)||(val(2) ==32)||(val(3) ==32))
{
alert("NO BLANK SPACES ALLOWED");
document.modifyOrderDetailshtml.OrderId.focus();
return (false);
}
return true;
}


 
 
 


