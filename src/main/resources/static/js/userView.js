$(document).ready(function() {

	$("#signIn").click(function(event) {
		event.preventDefault();
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		submitUser(name, email, password);
	});

});

function submitUser(name, email, password) {
	var object = new Object();
	object.name= name;
	object.email= email;
	object.password= password;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/signup/user",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
			location.href = "http://10.67.8.248:8080/login";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}
