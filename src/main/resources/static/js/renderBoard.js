
function renderBoard(list){
	var followBoard="";
	list.forEach(function (data) {
		followBoard += makeBoardDom(data);
	});
	return followBoard;
}

function makeBoardDom(getBoard) {
	var date  = new Date(getBoard.date);
	date = date.toISOString();
	date = date.replace('T', ' ');
	date = date.substring(0, 21);
	
	var dom = '<div class="panel-group" id="accordion" role="tablist"'
		+'aria-multiselectable="true" >'
		+'<div class="panel panel-default" id="'+ getBoard.id + '">'
					+'<div class="panel-heading" role="tab" id="headingTwo">'
					+	'<h4 class="panel-title">'
					+		'<a class="collapsed" data-toggle="collapse" id="' + getBoard.id + '" data-index="' + getBoard.id + '"'
					+			'data-parent="#accordion" href="#collapse' + getBoard.id + '"'
					+			'aria-expanded="false" aria-controls="collapseTwo">' + getBoard.title
					+			'</a>'
					+			'<span class="crudButton">'
					+				'<button type=button class="btn btn-sm" name=updateBoard>U</button>'
					+				'<button type=button class="btn btn-sm" name=deleteBoard>D</button>'
					+				'<span class="boardDate" text-align="right;">' + date + '</span>'
					+			'</span>'
					+	'</h4>'
					+'</div>'
					+'<div id="collapse' + getBoard.id + '" class="panel-collapse collapse"'
					+	'role="tabpanel" aria-labelledby="headingTwo">'
					+	'<div class="panel-body">' + getBoard.content
					+	'</div>'
					+'</div>'
					+	'<form class="form-horizontal">'
					+	'<div class="form-group">'
					+		'<div class="col-sm-10">'
					+			'<input type="text" class="form-control" placeholder="댓글" />'
					+		'</div>'
					+		'<button type="button" class="btn btn-default" name="send">send</button>'
					+	'</div>'
					+	'</form>'
				+'</div></div>';
				
			return dom;
}