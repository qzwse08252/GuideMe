<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
 ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java(Concroller), 存入req的prodcutVO物件
%>

<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>

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
<body bgcolor='DarkGray'>


<table id="table-1">
	<tr><td>
		 <h3>商品資料 - ListOneProduct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/Product/ProductSelect_Page.jsp">回首頁</a></h4>
		 <h4><a href="<%=request.getContextPath()%>/back-end/Product/listAllProduct.jsp">所有商品</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>定價</th>
		<th>商品描述</th>
		<th>總評價筆數</th>
		<th>總評價星數</th>
		<th>產品狀態</th>
		<th>商品圖片</th>
		<th>商品圖片</th>
		<th>商品圖片</th>
		
		
	</tr>
	<tr>
		<td>${productVO.productNo}</td>
		<td>${productVO.productName}</td>
		<td>${productVO.listPrice}</td>
		<td>${productVO.descr}</td>
		<td>${productVO.totalRateCount}</td>
		<td>${productVO.totalRate}</td>
        <th>${(productVO.productStatus=='true')?"下架":"上架"}</th>
		<td><img src="<%=request.getContextPath() %>
			/product/controllerImage.do${productVO.picSrc1}" 
			width =25px></td>
		<td><img src="<%=request.getContextPath() %>
			/product/controllerImage.do${productVO.picSrc2}" 
			width =25px></td>
		<td><img src="<%=request.getContextPath() %>
			/product/controllerImage.do${productVO.picSrc3}" 
			width =25px></td>
		<td>
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" ">
		         <input type="submit" value="修改商品狀態">
			     <input type="hidden" name="productNo"  value="${productVO.productNo}">
			     <input type="hidden" name="productStatus"  value=${(productVO.productStatus)=='true'? 0 : 1}>
			     <input type="hidden" name="action"	value="getOne_For_Status"></FORM>
		</td>	
		
	</tr>
</table>

</body>
</html>