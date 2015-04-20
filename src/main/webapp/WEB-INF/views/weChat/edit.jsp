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
		<h5 class="page-header page-target">微信公众号管理 - 新增微信公众号</h5>
		<form action="<%=request.getContextPath()%>/manage/weChat/show" role="form" method="post">
			<!-- 隐藏字段 -->
			<div class="form-group">
				<label for="typeName">搜狗微信OpenId ( 友情链接: <a href="http://weixin.sogou.com/" target="_blank">搜狗微信搜索</a> )：</label>
			    <input type="text" id="openId" name="openId" class="form-control" style="width: 238px;" placeholder="请输入搜狗微信OpenId" /><br>
                <c:if test="${not empty warning}">
                <label class="text-danger">错误提示：${warning}</label>
                </c:if>
			</div>
            
			<input type="submit" class="btn btn-primary btn-sm" value="获取" />
			<input type="reset" class="btn btn-primary btn-sm" value="重置" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

