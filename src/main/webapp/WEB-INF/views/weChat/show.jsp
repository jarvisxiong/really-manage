<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
    <h5 class="page-header page-target">微信公众号管理 - 新增微信公众号</h5>
    <form action="<%=request.getContextPath()%>/manage/weChat/doEdit"
      role="form" method="post">
      <div class="row">
        <div class="col-sm-2" align="right">
          <img src="${wechat.logoLink}" class="img-circle" />
        </div>
        <div class="col-sm-7">
          <h3>${wechat.publicName}</h3>
          <h5><b>微信号：</b> ${wechat.publicNO}</h5>
          <h5><b>功能介绍：</b> ${wechat.memo}</h5>
          <h5><b>微信认证：</b> ${wechat.weChatCret}</h5>
        </div>
        <div class="col-sm-3" align="left">
          <b>二维码扫描关注</b><br>
          <img src="${wechat.cdnLink}" class="img-polaroid" width="100" height="100" />
        </div>
      </div>
      <p>&nbsp;</p>
      <input type="submit" class="btn btn-primary btn-sm" value="保存" />
    </form>
  </div>

  <jsp:include page="../bottom.jsp" />
</body>
</html>

