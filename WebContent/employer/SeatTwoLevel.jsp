<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.studio"%>

<%@page import="java.util.*"%>
<%@page import="bean.seat"%>
<!-- <!DOCTYPE html> -->
					<%
						int studioId = (int) session.getAttribute("studioId");
						List<seat> seatlist = (List<seat>) session.getAttribute("Seatlist");
						int row = (int) session.getAttribute("row");
						int col = (int) session.getAttribute("col");
					%>
<html>
<head>
<!--<meta charset="utf-8">-->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>座位管理</title>
<link rel="stylesheet" href="../css/layui.css">
<link rel="stylesheet" href="../layui/layuicss/layui.css">
<link rel="stylesheet" type="text/css"
	href="../seatcss/jquery.seat-charts.css">
<link rel="stylesheet" type="text/css" href="../seatcss/style.css">
<link rel="stylesheet" href="../layui/layuicss/layui.css" media="all">
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<style type="text/css">
.icon-seat-s {
	height: 50px;
	width: 50px;
	background-image: url("KY.png");
	background-size: 50px,50px;
	margin-left: 10px;
	float: left;
	line-height: 70px;
	margin-top: 10px;
}

.icon-seat-k {
	height: 50px;
	width: 50px;
	background-image: url("KQ.png");
	background-size: 50px,50px;
	margin-left: 10px;
	float: left;
	line-height: 70px;
	margin-top: 10px;
}

.icon-delete-little {
	height: 50px;
	width: 50px;
	background-image: url("SH.png");
	background-size: 50px,50px;
	margin-left: 10px;
	float: left;
	line-height: 70px;
	margin-top: 10px;
}
.zuowei{
	margin-top: 500px;
	width:<%=60*col%>px;
	height:<%=60*row%>px;
	margin: 0 auto;
}

</style>
 <script src="../initJS/init.js"></script>
</head>

<body onload="init()">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">最接地气票务管理系统</div>

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
				<a href="demo.html" id="emp_name">
						<img src="../images/鹿鹿.png" class="layui-nav-img"> 用户名
				</a>
					</li>
				<li class="layui-nav-item"><a href="../index.html">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="main.html">首页</a></li>
					<li class="layui-nav-item"><a class=""
						href="studiomanage.html">演出厅管理</a></li>
					<li class="layui-nav-item"><a
						href="/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=1">座位管理</a>

					</li>
					<li class="layui-nav-item"><a href="usermanage.html">用户管理</a>

					</li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">

				<table class="layui-table">
					<thead>
						<tr>
							<th>演出厅编号:<%=studioId %></th>
							<th>点击座位修改状态</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
				</table>

				<!--  class="allseat" id="seatid" -->
				<div class="zuowei">

					<table>
						<%
							if (seatlist != null) {
								for (int i = 0; i < col; i++) {
						%>
						<tr>
							<!-- class="clearfix ol" -->
							<%
								for (int j = 0; j < row; j++) {
							%>
							<td>
								<%
									if (seatlist.get(i * row + j).getSeat_status() == 1) {
								%>
								<div id="<%=seatlist.get(i * row + j).getSeat_id()%>"
									class="icon-seat-s" onclick="seatstatus(this);"></div> 
								<%
 									} else if (seatlist.get(i * row + j).getSeat_status() == 0) {
 								%>
								<div id="<%=seatlist.get(i * row + j).getSeat_id()%>"
									class="icon-seat-k" onclick="seatstatus(this);"></div>
								<%
 									} else if (seatlist.get(i * row + j).getSeat_status() == -1) {
 								%>
								<div id="<%=seatlist.get(i * row + j).getSeat_id()%>" 
								class="icon-delete-little" onclick="seatstatus(this);"></div> 
								<%
 								}
 								%>
							</td>
							<%
								}
							%>
						</tr>
						<%
							}
							}
						%>
					</table>
				</div>

				<div class="col-md-10">
						<div class="icon-seat-s"></div><div style="float:left;line-height: 70px;">可用</div>
						<div class="icon-delete-little"></div><div style="float:left;line-height: 70px;">损坏</div>
						<div class="icon-seat-k"></div><div style="float:left;line-height: 70px;">空缺</div>
				</div><br/>
			<!--<input type="button" name="" value="保存" onclick="status()">-->
			 <button class="btn btn-large btn-primary" type="button" onclick="status()">保存</button>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			1028298035@qq.com 欢迎反馈
		</div>
	</div>
	<!--<script src="../layui/layui.js"></script>-->
	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
		function seatstatus(obj) {
			switch (obj.className) { //改变当前焦点控件的图片
			case "icon-seat-s":
				obj.className = "icon-seat-k";
				break;
			case "icon-seat-k":
				obj.className = "icon-delete-little";
				break;
			case "icon-delete-little":
				obj.className = "icon-seat-s";
				break;
			}
		}

		function status() {
			var seat_used = document.getElementsByClassName("icon-seat-s");
			var seat_empty = document.getElementsByClassName("icon-seat-k");
			var seat_demage = document.getElementsByClassName("icon-delete-little");
			var idString = "";
			var statuString = "";
			var i;
			var seatid;
			var status;
			for (i = 0; i < seat_used.length - 1; i++) { //1
				var iElement = seat_used[i];
				seatid = iElement.getAttribute("id");
				status = 1;
				idString = idString + seatid + ",";
				statuString = statuString + status + ",";
			}
			for (i = 0; i < seat_empty.length - 1; i++) { //0
				var iElement = seat_empty[i];
				seatid = iElement.getAttribute("id");
				status = 0;
				idString = idString + seatid + ",";
				statuString = statuString + status + ",";
			}
			for (i = 0; i < seat_demage.length - 1; i++) { //-1
				var iElement = seat_demage[i];
				seatid = iElement.getAttribute("id");
				status = -1;
				idString = idString + seatid + ",";
				statuString = statuString + status + ",";
			}
			//console.log(obj);
					$.ajax({
						type : "post",
						url : "/Web_TTMS/SeatManageServlet?mothed=updata",
						data : {
							ids : idString,
							status : statuString,
							studio_id :<%=studioId%>
						},
						success : function(data, status) {
							alert(data);
							window.location.href = "/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=1";
						},
						error : function(data, status) {
							alert("修改失败");
						}
					})
			}
	</script>
</body>
</html>