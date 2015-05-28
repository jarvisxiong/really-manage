<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png"
  type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/favicon.png"
  type="image/x-icon" rel="icon" />
</head>
<body>
  <jsp:include page="../top.jsp" />

  <div class="container main-container">
    <h5 class="page-header page-target">微信公众号管理</h5>
    <form class="form-inline" role="form">
      <a href="<%=request.getContextPath()%>/manage/weChat/edit">
        <button type="button" class="btn btn-primary btn-sm">新增</button>
      </a>
      <!-- 
      <a href="<%=request.getContextPath()%>/manage/weChat/fetchShow">
        <button type="button" class="btn btn-primary btn-sm">手动抓取</button>
      </a>
       -->
    </form>
    <hr />
    <div class="table-responsive">
      <table class="table table-hover">
        <thead>
          <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: left;">微信公众号名称</th>
            <th style="text-align: left;">微信公众号</th>
            <th style="text-align: right;">文章数量</th>
            <th style="text-align: right;">发布数量</th>
            <th style="text-align: center;">抓取时间</th>
            <th style="text-align: center;">状态</th>
            <!-- 
            <th style="text-align: center;">说明</th>
             -->
            <th style="text-align: center;">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty weChats}">
              <c:set var="bbcount" value="0" />
              <c:set var="ddcount" value="0" />
              <c:forEach items="${weChats}" var="weChat" varStatus="s">
                <tr>
                  <td style="text-align: center;">${s.index + 1}</td>
                  <td style="text-align: left;">
                    <img alt="" src="${weChat.logoLink}" class="img-circle" width="25px" height="25px">
                    &nbsp;${weChat.publicName}
                  </td>
                  <td style="text-align: left;">${weChat.publicNO}</td>
                  <td style="text-align: right;">0</td>
                  <td style="text-align: right;">0</td>
                  <td style="text-align: center;">${weChat.fetchTime}</td>
                  <td style="text-align: center;">
                    <c:if test="${not empty weChat.encryData}">
                      <span class="text-success">数据正常</span>
                    </c:if>
                    <c:if test="${empty weChat.encryData}">
                      <span class="text-danger">加密串不存在</span>
                    </c:if>
                  </td>
                  <!-- 
                  <td style="text-align: center;"><span class="text-success">信息正确&nbsp;&nbsp;正常抓取</span></td>
                   -->
                  <td style="text-align: center;">
                  <a href="http://weixin.sogou.com/gzh?openid=${weChat.openId}" target="_blank">
                      <button type="button" class="btn btn-primary btn-sm">查看搜狗</button>
                  </a>
                  <a href="<%=request.getContextPath()%>/manage/weChat/show?openId=${weChat.openId}">
                      <button type="button" class="btn btn-primary btn-sm">查看</button>
                  </a>
                  <a href="<%=request.getContextPath()%>/manage/weChat/update?openId=${weChat.openId}">
                      <button type="button" class="btn btn-primary btn-sm">修改</button>
                  </a>
                  <button type="button" class="btn btn-primary btn-sm"
                      onclick="if(confirm('您确定执行删除么?')) document.location = '<%=request.getContextPath()%>/manage/weChat/delete?openId=${weChat.openId}';">删除</button>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert"
                  aria-hidden="true">&times;</button>
                <strong>提示：</strong>系统没有查询到微信公众号信息
              </div>
            </c:otherwise>
          </c:choose>

          <jsp:include page="../msg.jsp" />

        </tbody>
      </table>
      <div class="tab-bottom-line"></div>

    </div>
  </div>

  <jsp:include page="../bottom.jsp" />
</body>
</html>
