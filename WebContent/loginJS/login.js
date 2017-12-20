//不加验证直接登录
function login() {
//    console.log(1)
//    window.location.href = './house.html';
}
//验证登录
$("#userLogin").click(function(){

    if(checkEmpNo()&&checkEmpPass()){
             $.ajax({
        url: "LoginCheck",
        type: "POST",
        dataType:'json',
        data: {
            username: $("#mobile").val(),
            password: $("#emp_pass").val()
    },
        success: function(data){    //    alert后台返回的参数
            // alert(data.flag+data.type);
            console.log("登录成功");
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
    }
   
});	

function checkEmpNo()
{
    var reg=/^[a-zA-Z0-9]{6,20}$/;
    var emp_no=document.getElementById("mobile");
    if(!reg.test(emp_no.value))
    {
        alert("账号请输入大小写字母和数字,长度6-20位!");
        emp_no.focus();
        return false;
    }
    else
    {
        return true;
    }
}

function checkEmpPass()
{
    var reg=/^[a-zA-Z0-9]{6,20}$/;
    var emp_pass=document.getElementById("emp_pass");
    if(!reg.test(emp_pass.value))
    {
        alert("密码请输入大小写字母和数字,长度6-20位!");
        emp_pass.focus();
        return false;
    }
    else
    {
        return true;
    }
}