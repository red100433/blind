function updateEvent() {
	$("[name=updateComment]").click(function(event) {
		var id = $(this).closest(".panel-footer")[0].id;
		console.log(id);
		//미구현
	});
}

function deleteEvent() {
	$("[name=deleteComment]").click(function(event) {
		var parent = $(this).closest(".panel-default");
		var id = $(this).closest(".panel-footer")[0].id;
		var boardId = $(this).closest(".panel-default")[0].id;
		
		deleteComment(id, boardId, function(data) {
			renderComment(parent, data);
			
			deleteEvent();
		});
	});
}


function deleteComment(id, boardId, callback) {
	var object = new Object();
	object.id = id;
	object.boardId = boardId;
	$.ajax({
		type : "DELETE",
		contentType: "application/json",
		url : "/comment",
		timeout : 600000,
		data : JSON.stringify(object),
		success : function(data) {
			callback(data);
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

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


//function BoardComment(id, callback) {
//	$.ajax({
//		type : "GET",
//		url : "/view/board/" + id,
//		timeout : 600000,
//		success : function(data) {
//			callback(data);
//		},
//		error : function(e) {
//			console.log("ERROR : ", e);
//		}
//	});
//}



