function crudchange(){
 
  var insert = ["name"];
  var update = ["id", "name"];
  var dele = ["id"];
  var select = [];
 
  var selectItem = $('#Crud').val();
  console.log(selectItem);
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
 console.log(changeItem);
  $('#textBox').empty();

  for(var count = 0; count < changeItem.length; count++){                
    var input = $("<input type=text name=" + changeItem[count] +" placeholder="+changeItem[count] +"><br>");
    $('#textBox').append(input);
  }
 
}