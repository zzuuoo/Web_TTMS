<%@ page pageEncoding="UTF-8" isErrorPage="false" errorPage="error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String flag=(String)session.getAttribute("login");
if(flag==null || !flag.equals("ok"))
{
    request.getSession().setAttribute("desc", "请从入口登陆。");
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <style type="text/css">
	    body {font-size:24pt}
	    input {font-size:24pt}
    </style>
  </head>
<body>
欢迎，${emp_name} ! <br>
a目录：<br>
<a href="employee/a.html">employee/a.html</a><br>
b目录：<br>
<a href="employer/b.html">employer/b.html</a><br>
</body>
</html>