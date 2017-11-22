    //返回登录页面
    function back () {
        window.location.href = 'index.html';
        }
    //验证注册
    $("#register").click(function() {
        $.ajax({
            url: "",
            type: "POST",
            data: {
                emp_no: $("#emp_no").val(),
                password: $("#emp_pass").val(),
                type: $("#type").val(),
                emp_email: $("#emp_email").val(),
                emp_tel_num: $("#emp_tel_num").val(),
                emp_name:'',
                emp_addr:'',
                Head_path:''
            },
            success: function(data){    //    alert后台的返回值
                alert(data);
            },
            error: function(){
                alert('访问失败');
            }
        });
    });