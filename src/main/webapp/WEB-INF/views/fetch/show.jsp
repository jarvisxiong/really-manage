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
		<h5 class="page-header page-target">众号管理 - 抓取文章</h5>
		<form action="<%=request.getContextPath()%>/manage/weChat/fetchSimpleStart" role="form" method="post">
			<!-- 隐藏字段 -->
			<div class="form-group">
			    <input type="text" id="encryDataLink" name="encryDataLink" class="form-control" style="width: 538px;" placeholder="请输入搜狗微信抓取链接" /><br>
                <input type="text" id="openid" name="openid" class="form-control" style="width: 538px;" placeholder="请输入搜狗微信抓取链接" /><br>
                <c:if test="${not empty warning}">
                <label class="text-danger">错误提示：${warning}</label>
                </c:if>
			</div>
            
			<input type="submit" class="btn btn-primary btn-sm" value="抓取" />
			<input type="reset" class="btn btn-primary btn-sm" value="重置" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

