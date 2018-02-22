function renderComment(parent, data) {
	$(".panel-footer").remove();
	var follow = renderDom(data);
	parent.append(follow);
}

function renderDom(list) {
	var follow="";
	list.forEach(function(data){
		follow += makeDom(data);
    });
	return follow;
	
}


function makeDom(getContent) {
	var date  = new Date(getContent.date);
	date = date.toISOString();
	date = date.replace('T', ' ');
	date = date.substring(0, 19);
	
	var dom = "<div class=panel-footer id="+ getContent.id+"><span>"+getContent.comment+"</span><span class=commentDate text-align=right; ><button type=button class='btn btn-sm' name=deleteComment>삭제</button>"+date+"</span></div>"
    return dom;
}

