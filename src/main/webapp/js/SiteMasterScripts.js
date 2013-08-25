$(document).ready(function() {
//    rotateBanner(5);
//    addMouseOversToTopNav();
//    addMouseOversToImage($("#topLevelBanner"));
//    updateLastModifiedDate();
});

$(function() {
	$( "#datepicker" ).datepicker();
});

function goTo()
{
  document.frm.submit();
}

function deleteRecord(url){
	window.location.href="http://localhost:8098/MyClinic/"+url;
	return false;
}

function fnInsurance(){
	if(document.payform.method.value=='Insurance'){
		document.getElementById('cVisible').style.visibility='visible';
		document.getElementById('pVisible').style.visibility='visible';
		document.getElementById('cpVisible').style.visibility='visible';
	}else{
		document.getElementById('cVisible').style.visibility='hidden';
		document.getElementById('pVisible').style.visibility='hidden';
		document.getElementById('cpVisible').style.visibility='hidden';
	}
	
	if(document.payform.method.value=='Check'){
		document.getElementById('bVisible').style.visibility='visible';
		document.getElementById('chVisible').style.visibility='visible';
	}else{
		document.getElementById('bVisible').style.visibility='hidden';
		document.getElementById('chVisible').style.visibility='hidden';
	}

}
//Function to validate login information
function fnValidateLogin(){
	var username = document.Login.username.value;
	if(username == "")
	{
		alert("Username is Mandatory cannot be left blank");
		document.Login.username.focus();
		return false;
	}
	var password = document.Login.password.value;
	if(password == "")
	{
		alert("Password is Mandatory cannot be left blank");
		document.Login.password.focus();
		return false;
	}
	return true;
}

//Function to check pharmacy page inputs
function fnPharmacy(){
	var phname = document.pharmacy.ph_name.value;
	var u_name = document.pharmacy.u_name.value;
	var pwd = document.pharmacy.pwd.value;
	var email = document.pharmacy.email.value;
	var phnum = document.pharmacy.phnum.value;
	var address = document.pharmacy.address.value;
	
	if(phname==""){
		alert("Pharmacy Name is Mandatory cannot be left blank");
		document.pharmacy.ph_name.focus();
		return false;
	}
	if(u_name==""){
		alert("Username Name is Mandatory cannot be left blank");
		document.pharmacy.u_name.focus();
		return false;
	}
	if(pwd==""){
		alert("Password is Mandatory cannot be left blank");
		document.pharmacy.pwd.focus();
		return false;
	}
	if(email==""){
		alert("Email is Mandatory cannot be left blank");
		document.pharmacy.email.focus();
		return false;
	}
	if(phnum==""){
		alert("Phone Number Mandatory cannot be left blank");
		document.pharmacy.phnum.focus();
		return false;
	}
	if(phnum.length < 10){
		alert("Please Enter proper phone number");
		document.pharmacy.phnum.value="";
		document.pharmacy.phnum.focus();
		return false;
	}
	if(address==""){
		alert("Address is Mandatory cannot be left blank");
		document.pharmacy.phnum.focus();
		return false;
	}
	
	return true;
}