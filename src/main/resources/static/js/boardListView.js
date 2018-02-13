$(document).ready(function() {
	$(".collapsed").click(function(event) {
		var id = $(this).closest("a")[0].id;
		
		$("#collapse" + id).click();
	});
	
	$(".panel-collapse.collapse").click(function(event){
		var parent = $(this).closest(".panel-default");
		var id = $(this).closest(".panel-default")[0].id;
		
		console.log(id);
		if($(this).hasClass('in')){
			$(".panel-footer").remove();
		} else if($(this).hasClass("collapsing")) {
			console.log("loading");
		} else{
			BoardComment(id, function(data) {
				renderComment(parent, data);
				
				deleteEvent();
			});
		}
		
		
	});
	
	$("[name=send]").click(function(event) {
		var parent = $(this).closest(".panel-default");
		var comment = $(this).closest(".form-group").children(".col-sm-10").children(".form-control").val();
		var boardId = $(this).closest(".panel-default")[0].id;
		
		//Enter하면 값을 저장 못받음
		addComment(boardId, comment, function(data) {
			renderComment(parent, data);
			
			updateEvent();
			deleteEvent();
		});
	});
	
	$("[name=updateBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		console.log("update");
		location.href = "http://localhost:8080/view/" + boardId;
	});
	
	$("[name=deleteBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		deleteBoard(boardId);
	});
	
});


function deleteBoard(boardId) {
	var object = new Object();
	object.id = boardId;
	$.ajax({
		type : "DELETE",
		url : "/view/board",
		contentType: "application/json",
		timeout : 600000,
		data : JSON.stringify(object),
		success : function(data) {
			location.href = "http://localhost:8080/view";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function BoardComment(id, callback) {
	$.ajax({
		type : "GET",
		url : "/comment/" + id,
		timeout : 600000,
		success : function(data) {
			callback(data);
//			location.href = "http://localhost:8080/login";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

