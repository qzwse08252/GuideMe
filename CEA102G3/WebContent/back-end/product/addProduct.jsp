<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料新增- addProductPage</title>
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
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
	width: 200px;
	white-space: nowrap;
}
</style>
</head>
<body bgcolor='	LightBlue'>

	<div class="container-fluid" style="padding: 0;">
		<h3>商品資料新增</h3>
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
	<div class = "container-fluid" >
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/product/product.do" name="form1"
		enctype="multipart/form-data">
		<table class="table">

			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="productName"
					value="${ (productVO==null)? " " : productVO.productName}" /></td>
			</tr>
			<tr>
				<td>定價:</td>
				<td><input type="TEXT" name="listPrice" size="45"
					value="${ (productVO==null)? " " : productVO.listPrice}" /></td>
			</tr>
			<tr>
				<td>商品敘述:</td>
				<td><textarea name="descr" row="3"
						style="margin: 0px; width: 500px; height: 250px;">
		${(productVO==null)?"":productVO.descr}</textarea></td>

			</tr>

		</table>
		<div class="container" style="float: left">
			
			<div class="input-group mb-3">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="choosePicture" name="productPic1">
					<label class="custom-file-label" for="choosePicture"
						aria-describedby="choosePicture">選擇檔案</label>
				</div>
				<div class="input-group-append">
					<span class="input-group-text" id="choosePicture1">上傳</span>
				</div>
			</div>
			<div>
				圖片預覽:
				<div id="preview"></div>
			</div>
		</div>
		<br> <br> <input type="hidden" name="action" value="insert">
		<input type="submit" class="btn btn-primary" value="送出新增" style="position:absolute">



	</FORM>

</body>
<script type="text/javascript">
        function init() {
            //先抓DOM元素
            let choosePicture = document.getElementById('choosePicture');
            let fileName = document.getElementById('fileName');
            let preview = document.getElementById('preview');
            let del = document.getElementById('del');
            let pnumber = 0;
          

            //對choosePicture註冊change事件
            choosePicture.addEventListener('change', function(e) {
                //取得檔案物件
                let files = e.target.files;
                console.log(files);
                //判斷file物件是否存在
                if (files) {

                    //取出files物件的第i個
                    for (let i = 0; i < files.length; i++) {
                        let file = files[i];
                        if (file.type.indexOf('image') > -1) {

                            // new a FileReader
                            let reader = new FileReader();
                            console.log(reader);
                            // 在FileReader物件上註冊load事件 - 載入檔案的意思
                            //這時候還沒有跟FileReader物件說要載入哪個檔案
                            reader.addEventListener('load', function(e) {
                                // 取得結果 提示：從e.target.result取得讀取到結果
                                let result = e.target.result;
                                console.log(result) //確認讀取到結果

                                //創建一個div圖片空間
                                let pictureSpace = document.createElement('div');
                                pictureSpace.innerHTML = `
                                                            <div>
                                                                <img src=${'$'}{result} style="height:200px;width:300px;">
                                                            </div>`;
                                pnumber++;
                                pictureSpace.style.float = 'left';
                                console.log(pictureSpace);
                                preview.append(pictureSpace);



                            });
                            //一定要readAsDataURL才是真正載入檔案
                            reader.readAsDataURL(file);
                        } else {
                            window.alert('請上傳圖片檔唷!')
                        }
                    }
                }
            });
        };
        window.onload = init;
    </script>
</html>