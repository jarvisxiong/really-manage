<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>
<script src="<%=request.getContextPath()%>/assets/js/popup.js"></script>
<script type="text/javascript">
	var showSocialCount = function(blogId){
	    var pop = new Popup({ contentType:1,scrollType:'auto',isReloadOnClose:false,width:980,height:510});
		pop.setContent("contentUrl","<%=request.getContextPath()%>/manage/request/countBySocial?blogId="+blogId+"");
		pop.setContent("title", "贴子访问统计");
		pop.build();
		pop.show();
	};
</script>

</head>

<body>
	<jsp:include page="../top.jsp" />
	
	<div class="container main-container">
		<h5 class="page-header page-target">贴子管理 - 贴子信息管理</h5>
		<form class="form-inline" role="form">
			<input type="text" class="form-control" placeholder="输入贴子标题">
			<input type="text" class="form-control" placeholder="输入贴子日期">
			<input type="text" class="form-control" placeholder="输入贴子类别">
			<button type="button" class="btn btn-primary btn-sm">查询</button>
			<button type="reset" class="btn btn-primary btn-sm">重置</button>
			<a href="<%=request.getContextPath()%>/manage/blogInfo/edit">
			<button type="button" class="btn btn-warning btn-sm">新增</button>
			</a>
		</form>
		<hr />
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="text-align:center;">序号</th>
						<th style="text-align:center;">缩略图</th>
						<th style="text-align:center;">状态</th>
						<th>博客标题</th>
						<th style="text-align:center;">贴子类别</th>
						<th style="text-align:center;">操作时间</th>
						<th style="text-align:right;">阅读量</th>
						<th style="text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="totalRead" value="0" />
					<c:choose>
					<c:when test="${not empty pager.blogInfos}">
					<c:forEach items="${pager.blogInfos}" var="blogInfo" varStatus="s">
					<tr>
						<td style="text-align:center;">${s.index + 1}</td>
						<td style="text-align:center;"><img src="${blogInfo.picLink}" width="50px" height="33px" /></td>
						<td style="text-align:center;">
							<c:if test="${'0' eq blogInfo.blogState}">
							<span class="label label-success">已发布</span>
							</c:if>
							
							<c:if test="${'1' eq blogInfo.blogState}">
							<span class="label label-danger">未发布</span>
							</c:if>
						</td>
						<td>${blogInfo.blogTitle}</td>
						<td style="text-align:center;">${blogInfo.blogTypeName}</td>
						<td style="text-align:center;">${blogInfo.blogTime}</td>
						<td style="text-align: right; font-weight:bold;">
							<a href="#" onclick="showSocialCount('${blogInfo.blogId}');">
							<fmt:formatNumber value="${blogInfo.readCount}" pattern="###,###,###,##0" />
							</a>
							<c:set var="totalRead" value="${totalRead + blogInfo.readCount}" />
						</td>
						<td style="text-align:center;">
							<a href="<%=request.getContextPath()%>/manage/blogInfo/edit?blogId=${blogInfo.blogId}">
								<button type="button" class="btn btn-warning btn-sm">修改</button>
							</a>
							
							<button type="button" class="btn btn-danger btn-sm" onclick="if(confirm('您确定执行删除么?')) document.location = '<%=request.getContextPath()%>/manage/blogInfo/delete?blogId=${blogInfo.blogId}';">删除</button>
						</td>
					</tr>
					</c:forEach>
					<tr class="active">
						<td colspan="5" style="text-align: center; font-weight: bold;">本页博客总访问量</td>
						<td colspan="2" style="text-align: right; font-weight: bold;">
							<fmt:formatNumber value="${totalRead}" pattern="###,###,###,##0" />&nbsp;&nbsp;
						</td>
						<td>&nbsp;</td>
					</tr>
					</c:when>
					<c:otherwise>
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>提示：</strong>系统没有查询到博客信息
					</div>
					</c:otherwise>
					</c:choose>
					
					<jsp:include page="../msg.jsp" />
				</tbody>
			</table>
			<div class="tab-bottom-line"></div>
			
			<ul class="pager">
				<li <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
					<a <c:if test="${pager.currentPage > 1}">href="<%=request.getContextPath()%>/manage/blogInfo/list?currentPage=1"</c:if>>首页</a>
				</li>
				
				<li <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
					<a <c:if test="${pager.currentPage > 1}">href="<%=request.getContextPath()%>/manage/blogInfo/list?currentPage=${pager.currentPage-1}"</c:if>>上一页</a>
				</li>
				
				<li <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
					<a <c:if test="${pager.currentPage < pager.lastPage}">href="<%=request.getContextPath()%>/manage/blogInfo/list?currentPage=${pager.currentPage+1}"</c:if>>下一页</a>
				</li>
				
				<li <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
					<a <c:if test="${pager.currentPage < pager.lastPage}">href="<%=request.getContextPath()%>/manage/blogInfo/list?currentPage=${pager.lastPage}"</c:if>>尾页</a>
				</li>
				
				<li class="">&nbsp;</li>
				<li class=""><strong>${pager.currentPage}/${pager.lastPage}</strong></li>
				<li class="">&nbsp;</li>
				<li class="disabled">共<strong>${pager.totalCount}</strong>条</li>
				<li class="">&nbsp;</li>
				<li class="">每页显示<strong>10</strong>条</li>
			</ul>
		</div>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>
