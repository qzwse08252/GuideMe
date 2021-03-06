<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.experience.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ExperienceService experSvc = new ExperienceService();
	List<ExperienceVO> list = experSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="extypeSvc" scope="page" class="com.exper_type.model.ExperTypeService" />


<html>
<head>
<title>所有達人體驗資料 - listAllExperience.jsp.jsp</title>

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
	width: 800px;
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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有達人體驗資料 - listAllExperience.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-sell-end/experience/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>體驗編號</th>
			<th>發表體驗會員編號</th>
			<th>審核者編號</th>
			<th>體驗名稱</th>
			<th>體驗價格</th>
			<th>體驗敘述</th>
			<th>體驗狀態</th>
			<th>體驗種類</th>
			<th>修改</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="experVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${experVO.exper_no}</td>
				<td>${experVO.host_no}</td>
				<td>${experVO.checker_no}</td>
				<td>${experVO.name}</td>
				<td>${experVO.price}</td>
				<td>${experVO.exper_descr}</td>
				<c:choose>
					<c:when test="${experVO.exper_status == '0'}">
						<td>審核中</td>
					</c:when>
					<c:when test="${experVO.exper_status == '1'}">
						<td>已審核</td>
					</c:when>
					<c:when test="${experVO.exper_status == '2'}">
						<td>下架</td>
					</c:when>
					<c:otherwise>
						<td>正常</td>
					</c:otherwise>
				</c:choose>
				<td>${extypeSvc.getOneExperType(experVO.exper_type_no).exper_type_name}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Experience/Experience.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="exper_no" value="${experVO.exper_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>