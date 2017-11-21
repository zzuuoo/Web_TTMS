<%@ page pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <style type="text/css">
	    body {font-size:30px}
	    input {font-size:30px}
	    div {color:red;text-align:center}
    </style>
  </head>
<body>
<br>
<div>出错了!</div>
<div>你的网络有问题，或者操作有问题，反正我们没问题。</div>
<br>
<div>${desc}</div>
<div><a href="index.html">回首页</a></div>
</body>
</html>