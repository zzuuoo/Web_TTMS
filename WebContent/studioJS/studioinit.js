

 function addStudio(){
  // window.open();
    var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}
 // String studio_name = request.getParameter("studio_name");
 //      int  studio_row_count = new Integer(request.getParameter("studio_row_count"));
 //      int studio_col_count = new Integer(request.getParameter("studio_col_count"));//列
 //      String studio_introduction = request.getParameter("studio_introduction");
      
 //      int studio_flag = new Integer(request.getParameter("studio_flag"));
function submitStudio(){

	 $.ajax({
    type: "POST", //提交方式，也可以是get
    url: "../StudioServlet",    //请求的url地址
    dataType: "json",   //返回格式为json,也可以不添加这个属性，也可以是（xml、json、script 或 html）。
    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    data: { 
    method: "add" ,
    studio_name:$("#studio_name").val(),
    studio_row_count:$("#studio_row_count").val(),
    studio_col_count:$("#studio_col_count").val(),
    studio_introduction:$("#studio_introduction").val(),
    studio_flag:'0'


    },    //参数值 ,id是定义的要传的参数变量名，后台接收一致，value是页面取的传值的变量名，如果有多个，按照格式每组用逗号隔开，没有参数可以不用
    beforeSend: function() {
      //请求前的处理，如果没有可以不用写
    },
    success: function(data) {
    if(data.flag=='yes'){
       var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
    alert("添加成功");
    location.reload();

    }else{
      alert("添加失败");
    }
    },
    complete: function() {
      //请求完成的处理 ，如果没有，可以不用
    },
    error: function() {
      alert("出错了");
    }
  });

}