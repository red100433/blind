$(document).ready(function() {
	$("#top").click(function(event) {
		 $('html, body').animate( { scrollTop : 0 }, 300 );
	});
	boardFunction();
	
	$("[name=continueFlag]").click(function(event) {
		if( $(this).val() == 'NoUpdate' ) {
		      $(this).val('YesUpdate');
		}
		else {
		      $(this).val('NoUpdate');
		}
	});
	
	$("#next").click(function(event) {
		var parent = $(".starter-template");
		var lastIndexId = $(".panel-default:last")[0].id;
		console.log(lastIndexId);
		if(lastIndexId == 1) {
			alert("글이 없습니다.");
		} else {
			getBoardList(lastIndexId, function(data) {
				parent.append(renderBoard(data));
				boardFunction();
			});
		}
	});
	
	
	
});

function getBoardList(lastIndexId, callback) {
	$.ajax({
		type : "GET",
		url : "/view/" + lastIndexId,
		success : function(data) {
			callback(data);
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}


function deleteBoard(boardId) {
	var object = new Object();
	object.id = boardId;
	$.ajax({
		type : "DELETE",
		url : "/view/board",
		contentType: "application/json",
		data : JSON.stringify(object),
		success : function(data) {
			console.log(data);
			location.href = "/view";
		},
		error : function(e) {
			alert('Access is denied');
			console.log("ERROR : ", e);
		}
	});
}

function BoardComment(id, callback) {
	$.ajax({
		type : "GET",
		url : "/comment/" + id,
		success : function(data) {
			callback(data);
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function boardFunction() {
	$(".collapsed").off("click");
	
	$(".collapsed").click(function(event) {
		var id = $(this).closest("a")[0].id;
		
		$("#collapse" + id).click();
	});
	
	$(".panel-collapse.collapse").off("click");
	
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
				
				deleteCommentEvent();
			});
		}
		
		
	});
	
	$("[name=send]").off("click");
	
	$("[name=send]").click(function(event) {
		var parent = $(this).closest(".panel-default");
		var comment = $(this).closest(".form-group").children(".col-sm-10").children(".form-control").val();
		var boardId = $(this).closest(".panel-default")[0].id;
		
		//Enter하면 값을 저장 못받음
		addComment(boardId, comment, function(data) {
			renderComment(parent, data);
			
			deleteCommentEvent();
		});
	});
	
	$("[name=updateBoard]").off("click");
	
	$("[name=updateBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		console.log("update");
		location.href = "/view/board/" + boardId;
	});
	
	$("[name=deleteBoard]").off("click");
	
	$("[name=deleteBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		deleteBoard(boardId);
	});
}
