$(document).ready(function() {

	$("#submit").click(function(event) {
		event.preventDefault();
		var crud = $("#Crud").val();
		console.log(crud);
		var id = $("#id").val();
		var name = $("#name").val();
		var birth = $("#birth").val();
		
		if(crud === "INSERT") {
			saveStudent(name, birth);
		} else if (crud === "UPDATE") {
			updateStudent(id, name, birth);
		} else if (crud === "DELETE") {
			deleteStudent(id);
		} else {
			location.href = "http://localhost:8080/students"
		}
	});

});

function saveStudent(name, birth) {
	var object = new Object();
	object.name= name;
	object.birth= birth;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/students",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/students";
		},
		error : function(e) {
			console.log("ERROR : ", e);
			window.location = "error";
		}
	});
}
function updateStudent(id, name, birth) {
	var object = new Object();
	object.id= id;
	object.name= name;
	object.birth= birth;
	console.log(id);
	console.log(name);
	console.log(birth);
	
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : "/students",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/students";
		},
		error : function(e) {
			console.log("ERROR : ", e);
			window.location = "error";
		}
	});
}

function deleteStudent(id) {
	
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/students/"+id,
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/students";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}


function crudchange() {

	var insert = [ "name", "birth" ];
	var update = [ "id", "name", "birth" ];
	var dele = [ "id" ];
	var select = [];

	var selectItem = $('#Crud').val();
	var changeItem;

	if (selectItem == "INSERT") {
		changeItem = insert;
	} else if (selectItem == "UPDATE") {
		changeItem = update;
	} else if (selectItem == "DELETE") {
		changeItem = dele;
	} else if (selectItem == "SELECT") {
		changeItem = select;
	}

	$('#textBox').empty();

	for (var count = 0; count < changeItem.length; count++) {
		var input = $("<input type=text id=" + changeItem[count]
				+ " placeholder=" + changeItem[count] + "><br>");
		$('#textBox').append(input);
	}

}