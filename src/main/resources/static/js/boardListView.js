$(document).ready(function() {
	$(".collapsed").click(function(event) {
		var id = $(this).closest("a")[0].id;
		
		$("#collapse" + id).click();
	});
	
	$(".panel-collapse.collapse").click(function(event){
		var parent = $(this).closest(".panel-default");
		var id = $(this).closest(".collapsed").prevObject[0].id.replace("collapse", "");
		
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
		var boardId = $(this).closest(".panel-default")
		.children(".panel-heading").children(".panel-title")[0]
		.children[0].id;
		
		//Enter하면 값을 저장 못받음
		addComment(boardId, comment, function(data) {
			renderComment(parent, data);
			
			updateEvent();
			deleteEvent();
		});
		
		
	});
	
});

function addComment(boardId, comment, callback) {
	var object = new Object();
	object.boardId = boardId;
	object.comment = comment;
	
	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : "/comment",
		timeout : 600000,
		data : JSON.stringify(object),
		success : function(data) {
			callback(data);
//			location.href = "http://localhost:8080/login";
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function BoardComment(id, callback) {
	$.ajax({
		type : "GET",
		url : "/view/board/" + id,
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

