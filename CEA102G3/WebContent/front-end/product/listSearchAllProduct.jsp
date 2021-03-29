<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>
<%@ page import="com.product.model.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String productName = request.getParameter("productName");
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getProductOnShelfByName(productName);
	if(productName==null){
		list = (List<ProductVO>)session.getAttribute("list");
	}
	session.setAttribute("list", list);
	
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

<title>listSearchAllProduct.jsp</title>

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
					<h6 class="m-0 font-weight-bold text-primary">所有商品:</h6>
				</div>


			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="dataTableAllAttraction" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th width="150">商品名稱</th>
								<th>定價</th>
								<th>描述</th>
								<th width="30">評分</th>
								<th>商品圖片</th>
								<th width="100">數量</th>
								<th></th>
							</tr>
						</thead>
						

						<tbody>
							<%-- 							<c:forEach var="attraVO" items="${list}"> --%>
							<%@ include file="page1.file"%>
							<c:if test="${list.size()==0 }">
								<p style="color: red">查無資料</p>
							</c:if>
							<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr
									style="background-color:${param.productNo==productVO.productNo?'#C1E4F9':''}">
									<td>${productVO.productName}</td>
									<td>${productVO.listPrice}</td>
									<td>${productVO.descr}</td>
									<td><i class="fas fa-star"></i>${productVO.totalRate!=null?String.format("%.2f",productVO.totalRate/productVO.totalRateCount):''}</td>
									
									<td>
											<img src="<%=request.getContextPath()%>/product/controllerImage.do?picCol=product_pic1&PKNumber=${productVO.productNo}" 
												style="width: 300px; height: 200px;">
									</td>
									<td >
										<input type="number" name="productCount" min="1" value="1" class="form-control">
									</td>
									<td>
										<button class="btn btn-primary addInShoppingCart" productNo="${productVO.productNo }">加入購物車</button>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
					
				</div>
			</div>
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
					swal("ok!","加入成功","success");
					var b = setTimeout(function(){
						location.reload()
					},1500);
				},
				error: function(){
					alert("failed");
				},
			});
		});
		
		
	});
	
</script>
</html>
















