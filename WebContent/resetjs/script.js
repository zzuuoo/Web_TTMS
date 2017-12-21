// user
var user_Boolean = true;
var password_Boolean = true;
var varconfirm_Boolean = true;
var emaile_Boolean = false;
var Mobile_Boolean = false;
// $('.reg_user').blur(function(){
//   if ((/^[a-z0-9_-]{4,8}$/).test($(".reg_user").val())){
//     $('.user_hint').html("✔").css("color","green");
//     user_Boolean = true;
//   }else {
//     $('.user_hint').html("×").css("color","red");
//     user_Boolean = false;
//   }
// });
// // password
// $('.reg_password').blur(function(){
//   if ((/^[a-z0-9_-]{6,16}$/).test($(".reg_password").val())){
//     $('.password_hint').html("✔").css("color","green");
//     password_Boolean = true;
//   }else {
//     $('.password_hint').html("×").css("color","red");
//     password_Boolean = false;
//   }
// });


// // password_confirm
// $('.reg_confirm').blur(function(){
//   if (($(".reg_password").val())==($(".reg_confirm").val())){
//     $('.confirm_hint').html("✔").css("color","green");
//     varconfirm_Boolean = true;
//   }else {
//     $('.confirm_hint').html("×").css("color","red");
//     varconfirm_Boolean = false;
//   }
// });


// Email
$('.reg_email').blur(function(){
  if ((/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/).test($(".reg_email").val())){
    $('.email_hint').html("✔").css("color","green");
    emaile_Boolean = true;
  }else {
    $('.email_hint').html("×").css("color","red");
    emaile_Boolean = false;
  }
});


// Mobile
$('.reg_mobile').blur(function(){
  if ((/^1[34578]\d{9}$/).test($(".reg_mobile").val())){
    $('.mobile_hint').html("✔").css("color","green");
    Mobile_Boolean = true;
  }else {
    $('.mobile_hint').html("×").css("color","red");
    Mobile_Boolean = false;
  }
});


// click
$('.red_button').click(function(){
	if ((/^1[34578]\d{9}$/).test($(".reg_mobile").val())){
	    Mobile_Boolean = true;
	  }
	if ((/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/).test($(".reg_email").val())){
	    
	    emaile_Boolean = true;
	  }
	
  if(emaile_Boolean && Mobile_Boolean){
      $.ajax({
          url: "../EmployeeServlet",
          type: "POST",
          dataType:'json',
          data: {
              method: "update",
              emp_no: getCookie1("emp_no"),
              emp_id:getCookie1("emp_id"),
              emp_name:document.getElementById("emp_name1").value,
              emp_tel_num: document.getElementById("emp_tel_num").value,
              emp_email:document.getElementById("emp_email").value,
              emp_addr:document.getElementById("emp_addr").value,
              edit:"1"
      },
          success: function(data){    //    alert后台返回的参数
            console.log(data);
      },
        complete: function() {
        //请求完成的处理 ，如果没有，可以不用
         // alert(data.flag+data.type);
        	alert("修改成功");
            console.log("更新成功");
            window.history.go(-1);
            
              // layer.msg('[emp_no: '+ data.emp_no +'] ' + field + ' 字段更改为：'+ value); 
               
          },
          error: function(){
              // alert('访问失败');
        }
          });

      if(document.getElementById("head_path").value!=""){
        alert("头像可以上传");


      //    $.ajax({
      //     url: "../UserServlet",
      //     type: "POST",
      //     dataType:'json',
      //     data: {
      //         method: "edit",
      //         emp_no: getCookie1("emp_no"),
      //         emp_pass:getCookie1("emp_pass"),
      //         emp_name:document.getElementById("emp_name1").value,
      //         emp_tel_num: document.getElementById("emp_tel_num").value,
      //         emp_email:document.getElementById("emp_email").value,
      //         emp_addr:document.getElementById("emp_addr").value,
      //         edit:"1"
      // },
      //     success: function(data){    //    alert后台返回的参数
      //       console.log(data);
      // },
      //   complete: function() {
      //   //请求完成的处理 ，如果没有，可以不用
      //    // alert(data.flag+data.type);
      //     alert("修改成功");
      //       console.log("更新成功");
      //       window.history.go(-1);
            
      //         // layer.msg('[emp_no: '+ data.emp_no +'] ' + field + ' 字段更改为：'+ value); 
               
      //     },
      //     error: function(){
      //         // alert('访问失败');
      //   }
      //     });

      }

  }else {
    alert("请完善信息");
  }
});
function initmessage(){
  init();

  document.getElementById("emp_name1").value=getCookie1('emp_name');
  document.getElementById("emp_addr").value=getCookie1('emp_addr');
  document.getElementById("emp_tel_num").value=getCookie1('emp_tel_num');
  document.getElementById("emp_email").value=getCookie1('emp_email');
 
}
 function getCookie1(c_name)
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
