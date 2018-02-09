function crudchange(){
 
  var insert = ["name","birth","sub_Id"];
  var update = ["id", "name","birth","sub_Id"];
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
    var input = $("<input type=text name=" + changeItem[count] +" placeholder="+changeItem[count] +"><br>");
    $('#textBox').append(input);
  }
 
}