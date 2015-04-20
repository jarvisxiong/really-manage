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
		<h5 class="page-header page-target">文章管理 - 新增</h5>
		<form action="<%=request.getContextPath()%>/manage/news/praeShow" role="form" method="post">
			<div class="form-group">
				<label for="url">文章URL地址：</label>
			    <input type="text" name="url" class="form-control" style="width: 100%;" placeholder="请输入文章URL地址" />
			</div>
			<input type="submit" class="btn btn-primary btn-sm" value="确定" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

