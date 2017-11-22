//不加验证直接登录
function login() {
//    console.log(1)、
//    window.location.href = './house.html';

}
//验证登录
$("#userLogin").click(function(){
    $.ajax({
        url: "LoginCheck",
        type: "POST",
        dataType: 'json',
        data: {
            username: $("#mobile").val(),
            password: $("#emp_pass").val()
    },
        success: function(data){    //    alert后台返回的参数
//            alert(typeof data);
//            alert(data);
            
            if(data.flag=='yes'){
//            	window.location.href = './house.html';
            	alert(document.cookie);
            	if(data.type==1){
            		//管理员文件夹
            		window.location.href = './employer/house.html';
            	}else{
            		//普通用户文件夹
            		window.location.href = './employee/house.html';
            		
            	}
            	
            }
            else{
            	window.location.href = './error.jsp';
            }
            
    },
        error: function(obj){
        	console.log(obj);
            alert('访问失败');
    }
});
});	