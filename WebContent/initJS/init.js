  //页面加载初始化
  function init()
  {

    if(getCookie("head_path")!=""){
          var head = "<img src = \"../"+getCookie("head_path")+ "\" class=\"layui-nav-img\" id = \"head_img\">";
         
    }else{
        var head= "<img src=\"../images/用户1.png\" class=\"layui-nav-img\" id = \"head_img\">";
    }
    if(getCookie("emp_name")!=""){
        document.getElementById("emp_name").innerHTML=head+getCookie("emp_name");
    }else{
        document.getElementById("emp_name").innerHTML=head+'游客';
    }
       
  }
  //获取cookie
  function getCookie(c_name)
  {
  if (document.cookie.length>0)
    {
    c_start=document.cookie.indexOf(c_name + "=")
    if (c_start!=-1)
      { 
      c_start=c_start + c_name.length+1 
      c_end=document.cookie.indexOf(";",c_start)
      if (c_end==-1) c_end=document.cookie.length
      return unescape(document.cookie.substring(c_start,c_end))
      } 
    }
  return ""
  }