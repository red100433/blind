$(document).ready(function() {
	var ajaxCall=function(){
		var check = $("[name=continueFlag]").val();
	    if(check === "YesUpdate") {
//	    	ajax_submit();
	    }
	}
	setInterval(ajaxCall,1000);
	
	
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
	
	boardFunction();
	
});

function getBoardList(lastIndexId, callback) {
	$.ajax({
		type : "GET",
		url : "/view/" + lastIndexId,
		timeout : 600000,
		success : function(data) {
			callback(data);
//			location.href = "http://localhost:8080/view";
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
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

//flag On 위에 글 삽입 아직 미구현
function ajax_submit(){
	$.ajax({
		type : "GET",
		url : "/view/" + lastIndexId,
		timeout : 600000,
		success : function(data) {
			callback(data);
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}

function boardFunction() {
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
				
				deleteCommentEvent();
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
			
			deleteCommentEvent();
		});
	});
	
	$("[name=updateBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		console.log("update");
		location.href = "http://localhost:8080/view/board/" + boardId;
	});
	
	$("[name=deleteBoard]").click(function(event) {
		var boardId = $(this).closest(".panel-default")[0].id;
		deleteBoard(boardId);
	});
}
