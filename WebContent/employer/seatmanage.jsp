<%@page import="java.util.ArrayList"%>
<%@page import="bean.studio"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../initJS/init.js"></script>
<script>
	function editseat(obj){
		var tr=$(obj).parent().parent().parent();
		var id=tr.children("#Studio_id").text();
        window.location.href="/Web_TTMS/SeatManageServlet?mothed=searchByStudioId&id="+id;
	}
</script>
</head>

<body onload="init()">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">最接地气票务管理系统</div>

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="demo.html" id="emp_name">
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
							<th>演出厅名称</th>
							<th>演出厅编号</th>
							<th>可用座位数量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							int allPageCount = (int) session.getAttribute("allPageCount");
							int currentPage = (int) session.getAttribute("currentPage");
							List<studio> studios = (List<studio>) session.getAttribute("studios");
							for (studio s : studios) {
						%>
						<tr>
							<th><%=s.getStudio_name()%></th>
							<th id="Studio_id"><%=s.getStudio_id()%></th>
							<th><%=s.getStudio_row_count() * s.getStudio_col_count()%></th>
							<th>
								<div class="layui-btn-group">
									<button class="layui-btn layui-btn-normal layui-btn-radius "
										onclick="editseat(this)" id="toeditseat">管理座位</button>
								</div>
							</th>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>
				<div class="text" style="text-align: center;">
					<button type="button" class="btn btn-primary"
						onclick="/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=1">首页</button>
					<button type="button" class="btn btn-primary"
						onclick="/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=<%=currentPage + 1 > allPageCount ? allPageCount + 1 : currentPage%>">上一页</button>
					<button type="button" class="btn btn-outline-dark">
						当前第<%=currentPage%>页
					</button>
					<button type="button" class="btn btn-outline-dark">
						一共<%=allPageCount%>页
					</button>
					<button type="button" class="btn btn-primary"
						onclick="/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=<%=currentPage - 1 < 1 ? 1 : currentPage - 1%>">下一页</button>
					<button type="button" class="btn btn-primary"
						onclick="/Web_TTMS/SeatManageServlet?mothed=searchByPage&currentPage=<%=allPageCount%>">尾页</button>
				</div>



			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			1028298035@qq.com 欢迎反馈
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>