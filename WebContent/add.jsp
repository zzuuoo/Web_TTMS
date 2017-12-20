<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/newadd.css" rel="stylesheet">
<title>增加员工信息</title>
<style>
.table th, .table td {
	text-align: center;
	height: 30px;
}
</style>

</head>
<body>

	<div style="height:50px;line-height:50px;font-size:25px;vertical-align: middle;text-align: center" class="bg-primary">XXX影院管理系统</div>

	<div class="row" style="padding:10px 10px">

		<!-- 左导航栏 -->
		<%@include file="nav.jsp"%>

		<!-- 增加员工信息 -->
		<div class="col-md-10" style="padding-top:10px">
			<form class="form-horizontal" role="form" action="EmployeeServlet?method=add" method="post">
				<div class="form-group">
					<label for="emp_no" class="col-sm-2 control-label">用户编号</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="emp_no" name="emp_no" 
							placeholder="请输入大小写字母和数字,长度6-20位" pattern="[a-zA-Z0-9]{6,20}" 
							required="required" oninvalid="setCustomValidity('请输入大小写字母和数字,长度6-20位!')" 
							oninput="setCustomValidity('')" >
					</div>
				</div>
				<div class="form-group">
					<label for="emp_name" class="col-sm-2 control-label">用户姓名</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="emp_name" name="emp_name"
							placeholder="请输入真实姓名,10字以内" pattern="[\u4e00-\u9fa5]{2,10}"
							required="required" oninvalid="setCustomValidity('请输入真实姓名,10字以内!')"
							oninput="setCustomValidity('')" >
					</div>
				</div>
				<div class="form-group">
					<label for="emp_tel_num" class="col-sm-2 control-label">电话号码</label>
					<div class="col-sm-6">
						<input type="tel" class="form-control" id="emp_tel_num" name="emp_tel_num"
							placeholder="请输入手机号码" pattern="1[3-8][0-9]{9}" 
							required="required" oninvalid="setCustomValidity('请输入正确手机号码!')"
							oninput="setCustomValidity('')">
					</div>
				</div>
				<div class="form-group">
					<label for="emp_addr" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="emp_addr" name="emp_addr"
							placeholder="请输入地址" pattern=".{2,30}" 
							required="required" oninvalid="setCustomValidity('请输入地址!')"
							oninput="setCustomValidity('')">
					</div>
				</div>
				<div class="form-group">
					<label for="emp_email" class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-6">
						<input type="email" class="form-control" id="emp_email" name="emp_email" 
							placeholder="请输入Email" required="required"  
							pattern="([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+((\.[a-z]{2,3}){1,2})"
							oninvalid="setCustomValidity('请输入正确格式Email!')"
							oninput="setCustomValidity('')">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label" style="color:red;font-weight: bold;">${result}</div>
					<div class="col-sm-6">
						<!-- <input type="hidden" name="method" value="add"> -->
						<button type="submit" class="btn btn-primary">提  交</button>						
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>