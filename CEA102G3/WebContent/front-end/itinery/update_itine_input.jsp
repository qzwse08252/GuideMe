<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itine.model.*"%>

<%
  ItineVO itineVO = (ItineVO) request.getAttribute("itineVO"); 
//ItineServlet.java (Concroller) 存入req的itineVO物件 (包括幫忙取出的itineVO, 也包括輸入資料錯誤時的itineVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程資料修改 - update_itine_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>行程資料修改 - update_itine_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="itine.do" name="form1">
<table>
	<tr>
		<td>行程名稱:<font color=red><b>*</b></font></td>
		<td><input type="text" name="itineName" value="<%=itineVO.getItineName()%>"></td>
	</tr>
	<tr>
		<td>行程狀態:</td>
		<td>
			<select name="itineStatus">
					<option value="0" ${(itineVO.itineStatus==0)?'selected':''}>未完成</option>
					<option value="1" ${(itineVO.itineStatus==1)?'selected':''}>已完成</option>
					<option value="2" ${(itineVO.itineStatus==2)?'selected':''}>已刪除</option>
					<option value="3" ${(itineVO.itineStatus==3)?'selected':''}>已出遊</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>行程留言板:</td>
		<td><input type="textarea" name="itineBoard" size="45"	value="${itineVO.itineBoard }" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itineNo"  value="<%=itineVO.getItineNo()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出修改"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%=request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


</html>