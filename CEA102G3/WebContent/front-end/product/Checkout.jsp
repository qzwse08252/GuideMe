<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.order_item.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mode II 範例程式 - Checkout.jsp</title>
<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/resources/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/slick.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/slick-theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/slick.min.js"></script>
<!-- Core plugin JavaScript-->
<%--     <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> --%>

<!-- Custom scripts for all pages-->
<script
	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>

<style>
.container {
	margin-top: 100px;
}
</style>

</head>
<body>
	<c:import url="/front-end/myNavBar.jsp" />

	<div class="container">
		<div class="row">
			<font size="+3">網路商城 - 結帳 </font>
			<hr>
		</div>
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th width="200">商品名稱</th>
					<th width="100">價格</th>
					<th width="100">數量</th>
					<th width="120"></th>
				</tr>

				<%
					Vector<OrderItemVO> buylist = (Vector<OrderItemVO>) session.getAttribute("shoppingcart");
					String amount = (String) session.getAttribute("amount");
				%>
				<%
					for (int i = 0; i < buylist.size(); i++) {
						OrderItemVO orderItemVO = buylist.get(i);
						String name = orderItemVO.getProductVO().getProductName();
						int price = orderItemVO.getProductVO().getListPrice();
						int productCount = orderItemVO.getProductCount();
				%>
				<tr>
					<td width="200"><div align="center">
							<b><%=name%></b>
						</div></td>
					<td width="100"><div align="center">
							<b><%=price%></b>
						</div></td>
					<td width="100"><div align="center">
							<b><%=productCount%></b>
						</div></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><div align="center">
							<font color="red"><b>總金額：</b></font>
						</div></td>
					<td></td>
					<td><font color="red"><b>$<%=amount%></b></font></td>
					<td></td>
				</tr>
			</table>
			<p>
			<form method="post"
				action="<%=request.getContextPath()%>/order/order.do">
				<input type="hidden" name="action" value="confirmCheckOut">
				<input type="hidden" name="amount" value="<%=amount%>">
				<label for="ccn">信用卡號:</label>
				<input id="ccn" type="tel" name="creditCardNo" inputmode="numeric" pattern="[0-9\s]{13,19}" 
					autocomplete="cc-number" maxlength="19" placeholder="xxxx xxxx xxxx xxxx" required>
				<button type="submit" class="btn btn-primary">確定付款</button>
			</form>
		</div>
	</div>

</body>
</html>
