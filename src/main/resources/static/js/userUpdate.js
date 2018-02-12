$(document).ready(function() {

	$("#signIn").click(function(event) {
		event.preventDefault();
		var id = $("#id").val();
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		submitUser(id, name, email, password);
	});

});

function submitUser(id, name, email, password) {
	var object = new Object();
	object.id= id;
	object.name= name;
	object.email = email;
	object.password= password;
	
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : "/signup/user",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
			console.log(data);
//			location.href = "http://localhost:8080/login";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});

}