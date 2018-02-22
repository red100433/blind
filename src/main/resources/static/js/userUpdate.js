$(document).ready(function() {

	$("#signIn").click(function(event) {
		event.preventDefault();
		var id = $("#id").val();
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		submitUser(id, name, email, password);
	});

	$("[name=userDelete]").click(function(event) {
		var id = $("#id").val();
		console.log(id);
		deleteUser(id);
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
			if("putSuccess" === data) {
				location.href = "http://10.67.8.248:8080/login";
			}
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function deleteUser(id) {
	var object = new Object();
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/signup/" + id,
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
			location.href = "http://10.67.8.248:8080/logout";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}
