<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
