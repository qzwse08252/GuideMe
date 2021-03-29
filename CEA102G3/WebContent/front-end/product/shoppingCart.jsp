<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.* , com.order_item.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<%
	Vector<OrderItemVO> buylist = (Vector<OrderItemVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist",buylist);
%>
<body>

	<table>
		<tr>
			<th>商品名</th>
			<th>價格</th>
			<th>數量</th>
			<th></th>
		</tr>
		<c:forEach var = "orderItemVO" items = "${buylist }">
		<tr>
			<td>${orderItemVO.productVO.productName }</td>
			<td>${orderItemVO.productVO.listPrice }</td>
			<td align="center">${orderItemVO.productCount }</td>
			<td>
				<input type="hidden" name="action" value="DELETE">
              	<input type="hidden" name="del" value="${orderItemVO.productVO.productNo }">
				<button class="btn btn-primary deleteBtn">刪除</button>
			</td>
		</tr>
		</c:forEach>
	</table>

<script>
	$(document).ready(function(){
		$(".deleteBtn").click(function(){
			$.ajax({
				url: "<%=request.getContextPath() %>/shoppingCart/shoppingCart.do",
				type: "get",
				data: {
					action: "DELETE",
					del: $(this).prev().val(),
				},
				success: function(data){
					swal("ok!","刪除成功","success");
					var b = setTimeout(function(){
						location.reload()
					},1500);
				},
				error: function(){
					alert("failed");
				},
			})
		});
		
		
	});
	
	
</script>
</body>
</html>