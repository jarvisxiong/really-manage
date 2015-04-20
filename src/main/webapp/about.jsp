<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="icon" />
<meta charset="utf-8">
<title>关于真象</title>
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
  rel="stylesheet">
</head>
<style type="text/css">
.page-target {
  font-weight: bold;
  color: #FF5722;
  text-align: center;
}

.tab-bottom-line {
  margin: 0 auto 0;
  border-top: 1px solid #E0E0E0;
}

.table {
  margin-bottom: 0px;
}

body {
  background-color: #F5F5F5;
  font-size: 12px;
}

.main-container {
  background-color: #FFFFFF;
  margin: 0px auto;
  padding: 0px 10px 10px 10px;
  border-radius: 0px;
  font-size: 12px;
  color: #8E8E8E;
}
.setting-about {
  margin: 50px auto;
  text-align: center;
}
</style>
<body>
  <div class="container main-container">
    <h5 class="page-header page-target">关于我们</h5>
    <div class="setting-about">
      <img src="<%=request.getContextPath()%>/assets/images/logo.png" width="80px" height="80px">
      <h5>真象说</h5>
    </div>
  </div>
</body>
</html>

