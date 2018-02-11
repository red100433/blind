$(document).ready(function() {

	$("#submit").click(function(event) {
		event.preventDefault();
		var crud = $("#Crud").val();
		console.log(crud);
		var id = $("#id").val();
		var name = $("#name").val();
		var birth = $("#birth").val();
		var subId = $("#subId").val();
		
		if(crud === "INSERT") {
			saveTeacher(name, birth, subId);
		} else if (crud === "UPDATE") {
			updateTeacher(id, name, birth, subId);
		} else if (crud === "DELETE") {
			deleteTeacher(id);
		} else {
			location.href = "http://localhost:8080/teachers"
		}
	});

});

function saveTeacher(name, birth, subId) {
	var object = new Object();
	object.name= name;
	object.birth= birth;
	object.subId= subId;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/teachers",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			if(data === "dateError") {
				location.href = "http://localhost:8080/html/dataInvaild";
			} else if (data === "personSizeError") {
				location.href = "http://localhost:8080/html/size";
			} else {
				location.href = "http://localhost:8080/teachers";
			}
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}
function updateTeacher(id, name, birth, subId) {
	var object = new Object();
	object.id= id;
	object.name= name;
	object.birth= birth;
	object.subId= subId;
	
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : "/teachers",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			if(data === "dateError") {
				location.href = "http://localhost:8080/html/dataInvaild";
			} else if (data === "personSizeError") {
				location.href = "http://localhost:8080/html/size";
			} else {
				location.href = "http://localhost:8080/teachers";
			}
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function deleteTeacher(id) {
	
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/teachers/"+id,
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/teachers";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}


function crudchange(){
 
  var insert = ["name","birth","subId"];
  var update = ["id", "name","birth","subId"];
  var dele = ["id"];
  var select = [];
 
  var selectItem = $('#Crud').val();
  var changeItem;
  
  if(selectItem == "INSERT"){
    changeItem = insert;
  }
  else if(selectItem == "UPDATE"){
    changeItem = update;
  }
  else if(selectItem == "DELETE"){
    changeItem =  dele;
  }
  else if(selectItem == "SELECT"){
    changeItem = select;
  }
 
  $('#textBox').empty();

  for(var count = 0; count < changeItem.length; count++){                
    var input = $("<input type=text id=" + changeItem[count] +" placeholder="+changeItem[count] +"><br>");
    $('#textBox').append(input);
  }
 
}