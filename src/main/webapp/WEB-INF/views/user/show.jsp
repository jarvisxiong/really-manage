<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
	<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>
</head>
<body>
	<jsp:include page="../top.jsp" />
	
	<div class="container main-container">
		<h5 class="page-header page-target">用户管理 - 查看详细 - <a href="#">返回</a></h5>
		<form action="" role="form" method="post">
			<div class="form-group">
				<label for="logiName">用户名：</label>
			    <input type="text" id="logiName" name="logiName" value="${user.logiName}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="sex">性别：</label>
			    <input type="text" id="sex" name="sex" value="${user.sex}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="mobile">手机号：</label>
			    <input type="text" id="mobile" name="mobile" value="${user.mobile}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="email">邮箱：</label>
			    <input type="text" id="email" name="email" value="${user.email}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="address">地址：</label>
			    <input type="text" id="address" name="address" value="${user.address}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="code">邮编：</label>
			    <input type="text" id="code" name="code" value="${user.code}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="qq">QQ：</label>
			    <input type="text" id="qq" name="qq" value="${user.qq}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="privateWeb">个人网站：</label>
			    <input type="text" id="privateWeb" name="privateWeb" value="${user.privateWeb}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="privateMsg">个人说明：</label>
				<textarea cols="30" id="privateMsg" class="form-control" name="privateMsg" rows="3" style="width: 585px;">${message.privateMsg}</textarea>
			</div>
			
			<div class="form-group">
				<label for="createTime">注册时间：</label>
			    <input type="text" id="createTime" name="createTime" value="${user.createTime}" class="form-control" style="width: 585px;" />
			</div>
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

