//不加验证直接登录
function login() {
//    console.log(1)
//    window.location.href = './house.html';
}
//验证登录
$("#userLogin").click(function(){
    $.ajax({
        url: "LoginCheck",
        type: "POST",
        dataType:'json',
        data: {
            username: $("#mobile").val(),
            password: $("#emp_pass").val()
    },
        success: function(data){    //    alert后台返回的参数
            alert(data.flag+data.type);
            if(data.type=='1'){
            	window.location.href = './employer/main.html';
            }else{
            	window.location.href = './employee/main.html';
            }
            
            
    },
        error: function(){
            alert('访问失败');
    }
});
});	