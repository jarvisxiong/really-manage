<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>咖啡日报后台管理系统 - V1.0 - 登录</title>
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.css" type="text/css"></link>
<style type="text/css">
body {
  background-color: #fdfcfa;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: Arial, 微软雅黑, 宋体, Geneva, Tahoma, sans-serif;
}

.panel-body {
  padding: 15px 0px 0px 0px;
}

.panel-default {
  opacity: 0.9;
  margin-top: 10px;
}

.form-group {
  padding: 0px 15px 0px 45px;
}

.copy-div {
  text-align: center;
  padding: auto;
  margin: 20px auto;
}

.container {
  max-width: 370px;
  padding: 160px 60px 40px 60px;
}

.form-signin {
  max-width: 200px;
  padding: 25px 60px 40px 60px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid #e5e5e5;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  -webkit-box-shadow: 0 1px 25px rgba(0, 0, 0, .05);
  -moz-box-shadow: 0 1px 25px rgba(0, 0, 0, .05);
  box-shadow: 0 1px 25px rgba(0, 0, 0, .05);
}
  
.form-signin .form-signin-heading,.form-signin .checkbox {
  margin-bottom: 10px;
}

.form-signin .checkbox {
  font-weight: normal;
}

.form-signin .form-control {
  position: relative;
  font-size: 14px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.form-signin .form-control:focus {
  z-index: 2;
}

.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <div>
        <!-- 
        <div align="left">
          <img src="<%=request.getContextPath()%>/assets/images/logo.png" width="28px" height="28px" />
          <b style="color: #FF5722;">真象是什么不重要，重要的是探寻真相的过程</b>
        </div>
         -->
        <div class="panel panel-default">
          <div class="panel-heading">
            <span class="glyphicon glyphicon-lock"></span> 咖啡日报后台管理 - 登录
          </div>
          <div class="panel-body">
            <form action="<%=request.getContextPath()%>/admins/doLogin" class="form-horizontal" role="form"
              method="post">
              <c:if test="${not empty warning}">
                <div style="margin: 0px 30px 0px 30px" class="input-group">
                  <div class="alert alert-warning" style="width: 218px; margin-bottom: 10px;">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${warning}
                  </div>
                </div>

              </c:if>
              <div style="margin: 0px 30px 10px 30px" class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input id="adminsLogin"
                  type="text" class="form-control" name="adminsLogin" placeholder="请输入账号" />
              </div>
              <div style="margin: 10px 30px 10px 30px" class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> <input id="adminsPwd"
                  type="password" class="form-control" name="adminsPwd" placeholder="请输入密码" />
              </div>
              <div style="width: 278px;" class="form-group">
                <button type="submit" class="btn btn-primary btn-sm btn-block">登录</button>
              </div>
            </form>
          </div>
          <div class="panel-footer" style="text-align: right;">咖啡日报 版权所有</div>
        </div>
      </div>
    </div>
  </div>

  <script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/docs.min.js"></script>
</body>
</html>
