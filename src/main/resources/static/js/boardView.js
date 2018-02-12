$(document).ready(function() {

	$("[name=updateBoard]").click(function(event) {
//		var title = $("#title").val();
//		var content = $("#content").val();
//		addBoard(title, content);
		
		//미구현
	});
	$("[name=deleteBoard]").click(function(event) {
		var id = $("#boardId").val();
		console.log(id);
		deleteBoard(id);
	});

	$("[name=addBoard]").click(function(event) {
		var title = $("#title").val();
		var content = $("#content").val();
		addBoard(title, content);
	});
	$("[name=cancel]").click(function(event) {
		history.back();
	});
});

function deleteBoard(id) {
	var object = new Object();
	object.id= id;
	
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/view/board",
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

function addBoard(title, content) {
	var object = new Object();
	
	object.title= title;
	object.content= content;
	
	console.log(title);
	console.log(content);
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/view/board",
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