<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.exper_order.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ExperOrderVO expordVO = (ExperOrderVO) request.getAttribute("expordVO");
%>

<html>
<head>
<title>體驗梯次資料 - listOneExperOrder.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='lightskyblue'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>體驗梯次資料 - listOneExperOrder.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-sell-end/exper_order/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>體驗梯次編號</th>
			<th>體驗編號</th>
			<th>報名起始日</th>
			<th>報名結束日</th>
			<th>體驗開始時間</th>
			<th>體驗結束時間</th>
			<th>人數上限</th>
			<th>最低開團人數</th>
			<th>價格</th>
			<th>狀態</th>
			<th>已報名人數</th>



		</tr>
		<tr>
		
			<td><%=expordVO.getExper_order_no()%></td>
			<td><%=expordVO.getExper_no()%></td>
			<td><%=expordVO.getApply_start()%></td>
			<td><%=expordVO.getApply_end()%></td>
			<td><%=expordVO.getExper_order_start()%></td>
			<td><%=expordVO.getExper_order_end()%></td>
			<td><%=expordVO.getExper_max_limit()%></td>
			<td><%=expordVO.getExper_min_limit()%></td>
			<td><%=expordVO.getExper_now_price()%></td>
			<td><%=expordVO.getExper_apply_sum()%></td>

			


		</tr>
	</table>

</body>
</html>