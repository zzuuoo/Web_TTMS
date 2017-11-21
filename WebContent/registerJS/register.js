    //返回登录页面
    function back () {
//    	consloe.
//        window.location.href = 'index.html';
        }
    //验证注册
    $("#register").click(function() {
        $.ajax({
            url: "Register",
            type: "POST",
            dataType: 'json',
            data: {
                emp_no: $("#emp_no").val(),
                password: $("#emp_pass").val(),
                type: $("#type").val(),
                emp_email: $("#emp_email").val(),
                emp_tel_num: $("#emp_tel_num").val(),
                emp_name:'test',
                emp_addr:'1',
                Head_path:'1'
            },
            success: function(data){    //    alert后台的返回值
               alert(typeof data);
               console.log(data);
//               data = JSON.parse(data);
                if(data.flag=='yes'){
                	 alert("注册成功，跳转登录页面");
                	window.location.href = 'index.html';
                }else{
                	alert("注册失败,请重新注册");
                }
                
                
            },
            error: function(){
                alert('访问失败');
            }
        });
    });