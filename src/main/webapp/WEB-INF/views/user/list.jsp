<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>
</head>
<body>
	<jsp:include page="../top.jsp" />
	
	<div class="container main-container">
		<h5 class="page-header page-target">用户管理</h5>
		
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<th style="text-align:center;">用户名</th>
						<th style="text-align:center;">真象等级</th>
						<th style="text-align:center;">注册邮箱</th>
						<th style="text-align:center;">注册时间</th>
						<th style="text-align:center;">最后登录时间</th>
						<th style="text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty users}">
					<c:forEach items="${users}" var="user" varStatus="s">
					<tr>
						<td style="text-align:center;">${s.index + 1}</td>
						<td style="text-align:center;">&nbsp;&nbsp;${user.username}</td>
						<td style="text-align:center;">${user.truthDegreeScore}</td>
						<td style="text-align:center;">&nbsp;&nbsp;${user.email}</td>
						<td style="text-align:center;">${user.registerTime}</td>
						<td style="text-align:center;">${user.lastLoginTime}</td>
						<td style="text-align:center;">
							<button type="button" class="btn btn-primary btn-sm">查看</button>
							<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
							<button type="button" class="btn btn-danger btn-sm">禁用</button>
						</td>
					</tr>
					
					</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>提示：</strong>系统没有查询到用户信息
					</div>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="tab-bottom-line"></div>
		</div>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>
