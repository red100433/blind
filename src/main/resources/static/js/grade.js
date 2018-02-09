$(document).ready(function() {

	$("#submit").click(function(event) {
		event.preventDefault();
		var crud = $("#Crud").val();
		var id = $("#id").val();
		var stuId = $("#stuId").val();
		var subId = $("#subId").val();
		var score = $("#score").val();
		
		var selectOption = $("#selectOption").val();
		
		if(crud === "INSERT") {
			saveGrade(stuId, subId, score, selectOption);
		} else if (crud === "UPDATE") {
			updateGrade(id, stuId, subId, score, selectOption);
		} else if (crud === "DELETE") {
			deleteGrade(id, selectOption);
		} else {
			location.href = "http://localhost:8080/grades/" + selectOption;
		}
	});

});

function saveGrade(stuId, subId, score, selectOption) {
	var object = new Object();
	object.stuId= stuId;
	object.subId= subId;
	object.score= score;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/grades",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/grades/" + selectOption;
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}
function updateGrade(id, stuId, subId, score, selectOption) {
	var object = new Object();
	object.id= id;
	object.stuId= stuId;
	object.subId= subId;
	object.score= score;
	
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : "/grades",
		data : JSON.stringify(object),
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/grades/" + selectOption;
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function deleteGrade(id, selectOption) {
	
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/grades/"+id,
		timeout : 600000,
		success : function(data) {
//			console.log(data);
			location.href = "http://localhost:8080/grades/" + selectOption;
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}


function crudchange(){
 
  var insert = ["stuId","subId", "score"];
  var update = ["id", "stuId","subId", "score"];
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