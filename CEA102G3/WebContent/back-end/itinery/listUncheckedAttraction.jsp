<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>

<%
	AttractionService attraSvc1 = new AttractionService();
	List<AttractionVO> list = attraSvc1.getUnchecked();
	pageContext.setAttribute("list",list);
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

<title>listUncheckedAttraction.jsp</title>

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
<%-- <script src="<%=request.getContextPath()%>/resources/js/chungyuDataTable.js"></script> --%>


<style>
</style>

</head>

<body>
	

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">???????????????:</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>????????????</th>
								<th>??????</th>
								<th>????????????</th>
								<th>??????</th>
								<th>??????</th>
								<th>????????????</th>
								<th>??????</th>
								<th>???????????????</th>
								<th>??????</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>????????????</th>
								<th>??????</th>
								<th>????????????</th>
								<th>??????</th>
								<th>??????</th>
								<th>????????????</th>
								<th>??????</th>
								<th>???????????????</th>
								<th>??????</th>
							</tr>
						</tfoot>
						<tbody>
							<c:if test="${list.size()==0 }">
								<p style="color:red">?????????????????????</p>
							</c:if>
							<c:forEach var="attraVO" items="${list}">

								<tr>
									<td>${attraVO.attraNo}</td>
									<td>${attraVO.sort}</td>
									<td>${attraVO.attraName}</td>
									<td>${attraVO.descr}</td>
									<td>${attraVO.location}</td>
									<td>
									<c:if test="${attraVO.isOnShelf==0}">?????????</c:if>
									<c:if test="${attraVO.isOnShelf==1}">??????</c:if>
									<c:if test="${attraVO.isOnShelf==2}">??????</c:if>
									</td>
									<td>
									<c:if test="${attraVO.attraPic1!=null&&attraVO.attraPic1!=''}">
									<img src="${attraVO.attraPic1}" style="width:300px;height:200px;">
									</c:if>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/itinery/attraction.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="???????????????" class="btn btn-primary"> <input
												type="hidden" name="attraNo" value="${attraVO.attraNo}">
											<input type="hidden" name="action" value="getOne_For_Submit">
										</FORM>
									</td>
									<td>
										<FORM METHOD="get"
											ACTION="<%=request.getContextPath()%>/itinery/attraction.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="??????" class="btn btn-primary" onclick="return(confirm('?????????????????????'))"> <input
												type="hidden" name="attraNo" value="${attraVO.attraNo}">
											<input type="hidden" name="action" value="notAvailable">
											<input type="hidden" name="finishURL" value="<%=request.getServletPath() %>">
										</FORM>
									</td>

								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</body>

</html>