<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/logo.png"
  type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/logo.png"
  type="image/x-icon" rel="icon" />
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css"
  rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/css/dashboard.css"
  rel="stylesheet">
</head>
<style type="text/css">
.home-container {
  margin: 200px auto auto auto;
}

.panel-body {
  padding: 25px 60px 40px 60px;
}

.home-container {
  max-width: 353px;
}

.panel-body .btn:not (.btn-block ) {
  width: 120px;
  margin-bottom: 10px;
}
</style>
<body>
  <jsp:include page="top.jsp" />
  
  <div class="container main-container">
    <h5 class="page-header page-target">管理平台 - 首页</h5>
    <div class="welcome-panel">
      <div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">系统管理员，您好：</h3>
          </div>
          <div class="panel-body" style="text-align: center;">
            <h4 class="text-primary">
              <strong>欢迎登录真象说后台管理系统</strong>
              <img src="<%=request.getContextPath()%>/assets/images/welcome.gif" height="266" width="266">
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="bottom.jsp" />
</body>
</html>
