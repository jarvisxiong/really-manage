<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>  
</head>
<script type="text/javascript">
function queryReset() {
  document.getElementById("queryStartDate").value = null;
  document.getElementById("queryEndDate").value = null;
}
</script>
<body>
  <jsp:include page="../top.jsp" />

  <div class="container main-container">
    <h5 class="page-header page-target">文章基础管理</h5>
    <form class="form-inline" role="form" action="<%=request.getContextPath()%>/manage/news/list" method="post">
      <input type="hidden" id="query" name="query" value="query" />
      <strong>开始日期：</strong>
      <input type="text" id="queryStartDate" name="queryStartDate" class="form-control Wdate" type="text" 
    	onFocus="WdatePicker({dateFmt:'yyyyMMdd'})"
      	value="${queryStartDate}" style="width: 150px;" placeholder="选择开始日期">
      &nbsp;&nbsp;&nbsp;&nbsp;<strong>结束日期：</strong>
      <input type="text" id="queryEndDate" name="queryEndDate" class="form-control Wdate" type="text" 
    	onFocus="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'queryStartDate\')}',maxDate:'20201001'})"
        value="${queryEndDate}" style="width: 150px;" placeholder="选择结束日期">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <button type="submit" class="btn btn-primary btn-sm">查询</button>
      <button type="button" onclick="queryReset();" class="btn btn-primary btn-sm">重置</button>
      <a href="<%=request.getContextPath()%>/manage/news/praeUrl">
      <button type="button" class="btn btn-warning btn-sm">新增</button>
      </a>
      <span class="text-danger"></span>
      <a href="#"><!--  -->
      <button type="button" class="btn btn-warning btn-sm" onclick="selected();">入库</button>
      </a>
    </form>
    <hr />
    <div class="table-responsive">
      <table class="table table-hover">
        <thead>
          <tr>
            <th style="text-align: center;">
              <!-- 
              <input type="checkbox" id="SelectAll" onclick="selectAll();" />
               -->
            </th>
            <th style="text-align: center;">标题</th>
            <th style="text-align: center;">来源</th>
            <!-- 
            <th style="text-align: center;">真象指数</th>
            <th style="text-align: center;">评论数</th>
             -->
            <th style="text-align: center;">抓取时间</th>
            <!-- 
            <th style="text-align: center;">发表人</th>
            <th style="text-align: center;">状态</th>
             -->
            <th style="text-align: center;">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:set var="totalRead" value="0" />
          <c:choose>
            <c:when test="${not empty pager.news}">
              <c:forEach items="${pager.news}" var="obj" varStatus="s">
                <tr>
                  <td style="text-align: center;">
                    <input type="checkbox" id="subcheck" value="${obj.id}" onclick="setSelectAll();" />
                  </td>
                  <td style="text-align: left;">
                    <a href="${obj.url}" target="_blank">
                    <img src="${obj.imgLink}" height="30px" width="30px" />&nbsp;
                    ${obj.title} ... 
                    </a>
                  </td>
                  <td style="text-align: left;">${obj.domain}<br>${obj.weChatPublicNO}</td>
                  <!-- 
                  <td style="text-align: right;"><fmt:formatNumber value="${obj.truthDegree}" pattern="#" type="number"/>%</td>
                  <td style="text-align: right;">${obj.opinionTimes}</td>
                   -->
                  <td style="text-align: center;">${obj.createTime}</td>
                  <!-- 
                  <td style="text-align: left;">${obj.creator}</td>
                  
                  <td style="text-align: center;">
                    <c:if test="${'0' eq obj.state}">
                      <span class="text-success">已发布</span>
                    </c:if> 
                    <c:if test="${'1' eq obj.state}">
                      <a href="<%=request.getContextPath()%>/manage/news/send?newsId=${obj.id}">
                        <button type="button" class="btn btn-warning btn-sm">发布</button>
                      </a>
                    </c:if>
                  </td>
                   -->
                  <td style="text-align: center;">
                    <!-- 
                    <a href="<%=request.getContextPath()%>/manage/news/comment?newsId=${obj.id}&commentId=${obj.commentId}">
                      <button type="button" class="btn btn-warning btn-sm">评论</button></a>
                    <a href="<%=request.getContextPath()%>/manage/news/edit?newsId=${obj.id}">
                      <button type="button" class="btn btn-warning btn-sm">修改</button>
                    </a>
                     -->
                    <button type="button" class="btn btn-danger btn-sm"
                      onclick="if(confirm('您确定执行删除么?')) document.location = '<%=request.getContextPath()%>/manage/news/delete?newsId=${obj.id}';">删除</button>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert"
                  aria-hidden="true">&times;</button>
                <strong>提示：</strong>系统没有查询到相关信息
              </div>
            </c:otherwise>
          </c:choose>

          <jsp:include page="../msg.jsp" />
        </tbody>
      </table>
      <div class="tab-bottom-line"></div>

      <ul class="pager">
        <li
          <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
          <a
          <c:if test="${pager.currentPage > 1}">href="<%=request.getContextPath()%>/manage/news/list?currentPage=1"</c:if>>首页</a>
        </li>

        <li
          <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
          <a
          <c:if test="${pager.currentPage > 1}">href="<%=request.getContextPath()%>/manage/news/list?currentPage=${pager.currentPage-1}"</c:if>>上一页</a>
        </li>

        <li
          <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
          <a
          <c:if test="${pager.currentPage < pager.lastPage}">href="<%=request.getContextPath()%>/manage/news/list?currentPage=${pager.currentPage+1}"</c:if>>下一页</a>
        </li>

        <li
          <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
          <a
          <c:if test="${pager.currentPage < pager.lastPage}">href="<%=request.getContextPath()%>/manage/news/list?currentPage=${pager.lastPage}"</c:if>>尾页</a>
        </li>

        <li class="">&nbsp;</li>
        <li class=""><strong>${pager.currentPage}/${pager.lastPage}</strong></li>
        <li class="">&nbsp;</li>
        <li class="disabled">共<strong>${pager.totalCount}</strong>条
        </li>
        <li class="">&nbsp;</li>
        <li class="">每页显示<strong>${pageSize}</strong>条
        </li>
      </ul>
    </div>
  </div>

  <jsp:include page="../bottom.jsp" />
</body>
<script type="text/javascript">
function selected() {
	// 获取选中的CheckBox
	var newsIds = $('input[id="subcheck"]:checked').map(function() {
		return this.value;
	}).get().join();
  	
	// 判断是否已经选择文章
	if (newsIds == '') {
  		alert("请您选择需要入库的文章");
		return;
	}
	
	window.location.href = "<%=request.getContextPath()%>/manage/news/intoDBatch?newsIds="+newsIds;
}
</script>
</html>
