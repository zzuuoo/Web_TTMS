

 function addEmployee(){
  // window.open();
    var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}

  // String emp_no = request.getParameter("emp_no");
	 //        String emp_name = request.getParameter("emp_name");
	 //        String emp_tel_num = request.getParameter("emp_tel_num");
	 //        String emp_addr = request.getParameter("emp_addr");
	 //        String emp_email = request.getParameter("emp_email");
function submitEmployee(){

	 $.ajax({
    type: "POST", //提交方式，也可以是get
    url: "../EmployeeServlet",    //请求的url地址
    dataType: "json",   //返回格式为json,也可以不添加这个属性，也可以是（xml、json、script 或 html）。
    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    data: { 
    method: "add" ,
    emp_no:$("#emp_no").val(),
    emp_email:$("#emp_email").val(),
    emp_name:$("#emp_name").val(),
    emp_addr:$("#emp_addr").val(),
    emp_tel_num:$("#emp_tel_num").val()


    },    //参数值 ,id是定义的要传的参数变量名，后台接收一致，value是页面取的传值的变量名，如果有多个，按照格式每组用逗号隔开，没有参数可以不用
    beforeSend: function() {
      //请求前的处理，如果没有可以不用写
    },
    success: function(data) {
    if(data.flag=='1'){
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