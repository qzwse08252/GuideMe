<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
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

<title>listAllProduct.jsp</title>

<!-- Custom fonts for this template-->
<%-- <link href="<%=request.getContextPath()%>/resources/css/all.min.css" --%>
<!-- 	rel="stylesheet" type="text/css"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" -->
<!-- 	rel="stylesheet"> -->

<!-- Custom styles for this template-->
<!-- <link -->
<%-- 	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" --%>
<!-- 	rel="stylesheet"> -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css"> --%>
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/resources/css/nav-bar.css"> --%>
<!-- <link -->
<%-- 	href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap4.min.css" --%>
<!-- 	rel="stylesheet"> -->

<!-- Bootstrap core JavaScript-->
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script> --%>
<!-- Core plugin JavaScript -->
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> --%>

<!-- Custom scripts for all pages-->

<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script> --%>
<!-- Page level plugins -->
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap4.min.js"></script> --%>

<!-- Page level custom scripts -->
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


	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="row" style="margin-left: 0px;">
				<div class="col-3 card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">所有商品:</h6>
				</div>
<!-- 				<div class="col-3"></div> -->
<!-- 				<div class=" col-6 input-group mb-3 form-row"> -->
<%-- 					<form action="<%=request.getContextPath()%>/product/product.do" --%>
<!-- 						method="get"> -->
<!-- 						<div class="form-row"> -->
<!-- 							<div class="form-group col-7"> -->
<!-- 								<input type="text" class="form-control " placeholder="輸入景點名稱" -->
<!-- 									name="attraName" size="50"> -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-3"> -->
<!-- 								<select class="custom-select" name="sort"> -->
<!-- 									<option value="景點">景點</option> -->
<!-- 									<option value="餐廳">餐廳</option> -->
<!-- 									<option value="住宿">住宿</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-md-2"> -->
<!-- 								<input type="submit" class="btn btn-primary" value="搜尋"> -->
<!-- 							</div> -->
<!-- 							<input type="hidden" name="action" value="searchForAttra"> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->

			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="dataTableAllAttraction" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>定價</th>
								<th>描述</th>
								<th>總評分</th>
								<th>總評價數</th>
								<th>在架狀態</th>
								<th>商品圖片</th>
								<th>修改</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>定價</th>
								<th>描述</th>
								<th>總評分</th>
								<th>總評價數</th>
								<th>在架狀態</th>
								<th>商品圖片</th>
								<th>修改</th>
							</tr>
						</tfoot>

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
									<td>${productVO.productNo}</td>
									<td>${productVO.productName}</td>
									<td>${productVO.listPrice}</td>
									<td>${productVO.descr}</td>
									<td>${productVO.totalRate}</td>
									<td>${productVO.totalRateCount}</td>
									<td><c:if test="${productVO.productStatus==false}">架下</c:if> <c:if
											test="${productVO.productStatus==true}">架上</c:if> </td>
									<td>
											<img src="<%=request.getContextPath()%>/product/controllerImage.do?picCol=product_pic1&PKNumber=${productVO.productNo}" 
												style="width: 300px; height: 200px;">
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/product/product.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改" class="btn btn-primary">
											<input type="hidden" name="productNo"
												value="${productVO.productNo}"> <input type="hidden"
												name="action" value="getOne_For_Update"> <input
												type="hidden" name="whichPage" value="${param.whichPage }">
											<input type="hidden" name="finishURL"
												value="/back-end/itinery/listAllProduct_full.jsp">
										</FORM>
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

</html>
















