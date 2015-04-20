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
		<h5 class="page-header page-target">系统管理 - 管理员管理 - 编辑数据</h5>
		<form action="<%=request.getContextPath()%>/admins/doEdit" role="form" method="post">
			<c:if test="${not empty warning}">
			<div class="alert alert-warning" style="width: 585px;">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>${warning}
			</div>
			</c:if>
			<div class="form-group">
				<label for="adminsLogin">登录名：</label>
			    <input type="text" id="adminsLogin" name="adminsLogin" value="${adminz.adminsLogin}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="adminsPwd">密码：</label>
			    <input type="password" id="adminsPwd" name="adminsPwd" value="${adminz.adminsPwd}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="adminsPwd2">重复密码：</label>
			    <input type="password" id="adminsPwd2" name="adminsPwd2" value="${adminz.adminsPwd2}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="adminsName">姓名：</label>
			    <input type="text" id="adminsName" name="adminsName" value="${adminz.adminsName}" class="form-control" style="width: 585px;" />
			</div>
			
			<div class="form-group">
				<label for="memo">说明：</label>
				<textarea cols="30" id="memo" class="form-control" name="memo" rows="3" style="width: 585px;">${adminz.memo}</textarea>
			</div>
			
			<input type="submit" class="btn btn-primary btn-sm" value="保存" />
			<input type="reset" class="btn btn-primary btn-sm" value="重置" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

