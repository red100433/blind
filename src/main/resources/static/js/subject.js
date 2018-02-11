$(document).ready(function() {

	$("#submit").click(function(event) {
		event.preventDefault();
		var crud = $("#Crud").val();
		var id = $("#id").val();
		var name = $("#name").val();
		
		if(crud === "INSERT") {
			saveSubject(name);
		} else if (crud === "UPDATE") {
			updateSubject(id, name);
		} else if (crud === "DELETE") {
			deleteSubject(id);
		} else {
			location.href = "http://localhost:8080/subjects"
		}
	});

});

function saveSubject(name) {
	var object = new Object();
	object.name= name;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/subjects",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			if(data === "subjectSizeError") {
				location.href = "http://localhost:8080/html/size";
			} else {
				location.href = "http://localhost:8080/subjects";
			}
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}
function updateSubject(id, name) {
	var object = new Object();
	object.id= id;
	object.name= name;
	
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : "/subjects",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			if(data === "subjectSizeError") {
				location.href = "http://localhost:8080/html/size";
			} else {
				location.href = "http://localhost:8080/subjects";
			}
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function deleteSubject(id) {
	
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/subjects/"+id,
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/subjects";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function crudchange(){
 
  var insert = ["name"];
  var update = ["id", "name"];
  var dele = ["id"];
  var select = [];
 
  var selectItem = $('#Crud').val();
  console.log(selectItem);
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
 console.log(changeItem);
  $('#textBox').empty();

  for(var count = 0; count < changeItem.length; count++){                
    var input = $("<input type=text id=" + changeItem[count] +" placeholder="+changeItem[count] +"><br>");
    $('#textBox').append(input);
  }
 
}