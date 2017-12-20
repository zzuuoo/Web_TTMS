

//获取employee的emp_no置于select中
 function addinit(){
  $.ajax({
    type: "POST", //提交方式，也可以是get
    url: "../UserServlet",    //请求的url地址
    dataType: "json",   //返回格式为json,也可以不添加这个属性，也可以是（xml、json、script 或 html）。
    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    data: { method: "getEmp_no" },    //参数值 ,id是定义的要传的参数变量名，后台接收一致，value是页面取的传值的变量名，如果有多个，按照格式每组用逗号隔开，没有参数可以不用
    beforeSend: function() {
      //请求前的处理，如果没有可以不用写
    },
    success: function(data) {
      if(data.length>0){
               for(var i =0 ;i<data.length;i++){
               console.log(data[i].emp_no);
               document.getElementById("emp_no").add(new Option(data[i].emp_no,data[i].emp_no));
             }
      }else{
         alert("无可添加的employee,请先添加employee");
      }

    },
    complete: function() {
      //请求完成的处理 ，如果没有，可以不用
    },
    error: function() {
      alert("出错了，没取到啊!");
    }
  });

    var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}

function add(){
  $.ajax({
    type: "POST", //提交方式，也可以是get
    url: "../UserServlet",    //请求的url地址
    dataType: "json",   //返回格式为json,也可以不添加这个属性，也可以是（xml、json、script 或 html）。
    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    data: { method: "add" },    //参数值 ,id是定义的要传的参数变量名，后台接收一致，value是页面取的传值的变量名，如果有多个，按照格式每组用逗号隔开，没有参数可以不用
    beforeSend: function() {
      //请求前的处理，如果没有可以不用写
    },
    success: function(data) {
    if(data.flag=='1'){
       var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
    }else{
      alert("添加失败");
    }
    },
    complete: function() {
      //请求完成的处理 ，如果没有，可以不用
    },
    error: function() {
      alert("出错了，没取到啊!");
    }
  });

}

function hide(){
   var e2 = document.getElementById('add');      
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}


//返回按钮
function cancle(){
    var e2 = document.getElementById('add');
    e2.style.visibility =  (e2.style.visibility == "visible"  ) ? "hidden" : "visible";
}

document.getElementById('back').onclick = function(){
    cancle();
  }

//检查表单内容是否合法
  function checkFrom(){
//    var index = document.getElementById('emp_no').selectedIndex;
//    if(index<0){
//       alert("账号或密码不能为空");
//      return false;
//    }
//    console.log(document.getElementById('emp_no').Options[index].value+'---'+document.getElementById('emp_pass').value);
//    if(document.getElementById('emp_no').Options[index].value==''||document.getElementById('emp_pass').value==''){
//      alert("账号或密码不能为空");
//      return false;
//    }
    isPic();
  }
  //检查是否为图片
  function isPic()
{
    var pic=myform.head_image.value;
    var ext=pic.substring(pic.lastIndexOf(".")+1);
    //可以再添加允许类型
    if(ext.toLowerCase()=="jpg" || ext.toLowerCase()=="png" || ext.toLowerCase()=="gif")
    {
      return true;
    }
    else
    {
      alert("只能上传jpg、png、gif图像!");
        return false;
    }
    
}