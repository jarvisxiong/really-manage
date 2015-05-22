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
    <h5 class="page-header page-target">公众号管理 - 更新</h5>
    <form action="<%=request.getContextPath()%>/manage/weChat/doUpdate"
      role="form" method="post" class="form-horizontal">
      <div class="row">
        <div class="col-sm-2" align="right">
          <img src="${wechat.logoLink}" class="img-circle" />
        </div>
        <div class="col-sm-7">
          <input type="hidden" id="publicNO" name="publicNO" value="${wechat.publicNO}" />
          <h3>${wechat.publicName}</h3><br>
          <div class="form-group">
            <label class="col-sm-2 control-label">微信号：</label>
            <div class="col-sm-10"><p class="form-control-static">${wechat.publicNO}</p></div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">功能介绍：</label>
            <div class="col-sm-10"><p class="form-control-static">${wechat.memo}</p></div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">微信认证：</label>
            <div class="col-sm-10"><p class="form-control-static">${wechat.weChatCret}</p></div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">搜狗加密串：</label>
            <div class="col-sm-10">
              <input type="text" id="encryData" name="encryData" value="${wechat.encryData}" 
                      class="form-control" style="width: 530px;" placeholder="请输入搜狗微信加密串" />
             </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10"><input type="submit" class="btn btn-primary btn-sm" value="保存" /></div>
          </div>
        </div>
        <div class="col-sm-3" align="left">
          <b>二维码扫描关注</b><br>
          <img src="${wechat.cdnLink}" class="img-polaroid" width="100" height="100" />
        </div>
      </div>
      <p>&nbsp;</p>
    </form>
  </div>

  <jsp:include page="../bottom.jsp" />
</body>
</html>

