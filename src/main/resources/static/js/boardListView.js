$(document).ready(function() {
	$(".collapsed").click(function(event) {
//		var parent = $(this).closest(".panel-default");
		var id = $(this).closest("a")[0].id;
		
		$("#collapse" + id).click();
	});
	
	$('.panel-collapse.collapse').click(function(event){
		var parent = $(this).closest(".panel-default");
		var id = $(this).closest(".collapsed").prevObject[0].id.replace("collapse", "");
		
		console.log($(this));
		if($(this).hasClass('in')){
			$(".panel-footer").remove();
		} else if($(this).hasClass('collapsing')) {
			console.log("loading");
		} else{
			addBoardComment(id, function(data) {
				var follow = renderDom(data);
				parent.find(".panel-collapse").append(follow);
			});
		}
	});
	
});

function addBoardComment(id, callback) {
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

function renderDom(list) {
	var follow="";
	list.forEach(function(data){
		follow += makeDom(data);
    });
	return follow;
	
//    $('table tr:last').after(follow);
}


function makeDom(getContent) {
	var date  = new Date(getContent.date);
	date = date.toISOString();
	date = date.replace('T', ' ');
	date = date.substring(0, 21);
	var dom = "<div class=panel-footer><span>"+getContent.comment+"</span><span class=commentDate text-align=right; >"+date+"</span></div>"
    return dom;
}



