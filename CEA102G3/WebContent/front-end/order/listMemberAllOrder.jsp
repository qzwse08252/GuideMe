<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	session = request.getSession();
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	OrderService orderSvc = new OrderService();
	List<OrderVO> list_origin = orderSvc.getMemberOrders(memberVO.getMemberNo());
	Set<OrderVO> list = new LinkedHashSet<OrderVO>();
	for(OrderVO aOrderVO : list_origin){
		list.add(aOrderVO);
	}
	System.out.println(list);
	pageContext.setAttribute("list", list);
	
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>listMemberAllOrder.jsp</title>

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
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/nav-bar.css">
<link
	href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript -->
<script
 	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> 

<!-- Custom scripts for all pages-->

<script
	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
<!-- Page level plugins -->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script> 
<script
 	src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap4.min.js"></script> 

<!-- Page level custom scripts -->
<script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/chungyuDataTable.js"></script> --%>


<style>
tbody>tr {
	max-height: 300px;
	overflow-y: auto;
}

td {
	max-height: 300px;
	overflow: hidden;
}

.card{
	margin-top:70px;
}
</style>

</head>

<body>
	<c:import url="/front-end/myNavBar.jsp"/>

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="row" style="margin-left: 0px;">
				<div class="col-3 card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">所有訂單:</h6>
				</div>
			</div>
			<c:if test="${list.size()==0 }">
				<p style="color: red">尚無訂單</p>
			</c:if>
			<%@ include file="page1.file"%>
			<c:forEach var="orderVO" items="${list }" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="dataTableAllAttraction" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th width=100>商品名稱</th>
								<th width=100>定價</th>
								<th width=100>數量</th>
								<th width=100></th>
							</tr>
						</thead>
						

						<tbody>
							
							<c:forEach var="orderItemVO" items="${orderVO.orderItems}" >
								<tr
									style="background-color:#C1E4F9">
									<td>${orderItemVO.productVO.productName}</td>
									<td>${orderItemVO.productVO.listPrice}</td>
									<td>${orderItemVO.productCount }</td>
									<td>
										<div>
										<input type="number" class="form-control" min="1" max="5" style="width:50%;display:inline-block">
										<button type="button" class="btn btn-primary rate" 
											productNo="${orderItemVO.productVO.productNo }">評價</button>
										</div>
									</td>
								</tr>
							</c:forEach>
								<tr>
									<td colspan="3">總計:${orderVO.sum }</td>
									<td>日期:${orderVO.orderDate }</td>
								</tr>
						</tbody>
					</table>
					
				</div>
			</div>
			</c:forEach>
			<%@ include file="page2.file"%>
		</div>

	</div>
	<!-- /.container-fluid -->

</body>
<script>
	$(document).ready(function(){
		$(".addInShoppingCart").click(function(){
			
			$.ajax({
				url: "<%= request.getContextPath()%>/shoppingCart/shoppingCart.do",
				type: "POST",
				data: {
					action:"ADD",
					productNo: $(this).attr('productno'),
					productCount: $(this).parent().prev().find("input").val(),
				},
				success: function(data){
					alert(data);
				},
				error: function(){
					alert("failed");
				},
			});
		});
		
		$(".rate").click(function(){
			var thisBtn = $(this);
			$.ajax({
				url: "<%= request.getContextPath()%>/product/product.do",
				type: "post",
				data: {
					action: "updateRate",
					productNo: $(this).attr("productno"),
					rate: $(this).prev().val(),
				},
				success: function(data){
					swal("水喔",data,"success");
					thisBtn.prop("disabled",true);
					thisBtn.prev().val("");
				},
				error: function(){
					alert("failed");
				},
				
			});
			
		})
		
		
	});
	
</script>
</html>
















