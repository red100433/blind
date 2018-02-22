$(document).ready(function() {

	$("#signIn").click(function(event) {
		event.preventDefault();
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		submitUser(name, email, password);
	});

	$("[name=userUpdate]").click(function(event) {
		var val = $(this).closest("tr");
		console.log(val[0].cells);
		var id = val[0].cells[0].innerText;
		console.log(id);
		location.href = "http://10.67.8.248:8080/signup/"+ id;
	});

	$("[name=userDelete]").click(function(event) {
		var val = $(this).closest("tr");
		console.log(val[0].cells);
		var id = val[0].cells[0].innerText;
		console.log(id);
		deleteUser(id);
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

function deleteUser(id) {
	var object = new Object();
	object.id= id;
	$.ajax({
		type : "DELETE",
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