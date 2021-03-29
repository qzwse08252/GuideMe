<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itine.model.*"%>

<%
  ItineVO itineVO = (ItineVO) request.getAttribute("itineVO"); 
//ItineServlet.java (Concroller) �s�Jreq��itineVO���� (�]�A�������X��itineVO, �]�]�A��J��ƿ��~�ɪ�itineVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��{��ƭק� - update_itine_input.jsp</title>

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
		 <h3>��{��ƭק� - update_itine_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="itine.do" name="form1">
<table>
	<tr>
		<td>��{�W��:<font color=red><b>*</b></font></td>
		<td><input type="text" name="itineName" value="<%=itineVO.getItineName()%>"></td>
	</tr>
	<tr>
		<td>��{���A:</td>
		<td>
			<select name="itineStatus">
					<option value="0" ${(itineVO.itineStatus==0)?'selected':''}>������</option>
					<option value="1" ${(itineVO.itineStatus==1)?'selected':''}>�w����</option>
					<option value="2" ${(itineVO.itineStatus==2)?'selected':''}>�w�R��</option>
					<option value="3" ${(itineVO.itineStatus==3)?'selected':''}>�w�X�C</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>��{�d���O:</td>
		<td><input type="textarea" name="itineBoard" size="45"	value="${itineVO.itineBoard }" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itineNo"  value="<%=itineVO.getItineNo()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
<input type="submit" value="�e�X�ק�"></FORM>

<br>�e�X�ק諸�ӷ��������|:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%=request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%> (���d�ҥثe�u�Ω�:istAllEmp.jsp))</b>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


</html>