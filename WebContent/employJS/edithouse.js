//增加演出厅
function add(){
    var e2 = document.getElementById('add');			
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}
  //修改演出厅 
function overlay(){
    var e1 = document.getElementById('modal-overlay');			
    e1.style.visibility =  (e1.style.visibility == "visible"  ) ? "hidden" : "visible";
}
//删除演出厅
function delete_confirm() {
    if (event.srcElement.outerText == "删除") 
    {
        event.returnValue = confirm("删除是不可恢复的，你确认要删除吗？");
    }
}
 //传递数据
  //增加  修改演出厅
  $("#toaddstudio").click(function(){
    $.ajax({
    url: "",
    type: "GET",
    data: {
        addstudio: $("#toaddstudio").val()
  },
    success: function(data){    //    alert后台返回的参数
        alert(data);
 },
    error: function(){
        alert('访问失败');
  }
});
});	
$("#toeditstudio").click(function(){
    $.ajax({
    url: "",
    type: "GET",
    data: {
        editstudio: $("#toeditstudio").val()
  },
    success: function(data){    //    alert后台返回的参数
        alert(data);
 },
    error: function(){
        alert('访问失败');
  }
});toeditstudio
});

$.ajax({
url: "http://www.zuo2017.xin:8080/Web_TTMS/GetAllEmployee",
type: "GET",
success: function (data) {    //    alert后台返回的参数
alert(data);
},
error: function (err) {
console.log('访问失败');
console.log(err);
}
});