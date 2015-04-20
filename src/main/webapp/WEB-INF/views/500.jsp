<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>500系统异常 - 真象说后台管理系统</title>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet">
</head>
<style type="text/css">
		.container {
			margin: 150px auto auto auto;
		}
		.panel-body {
			padding: 15px 60px 40px 60px;
		}
		.container {
			max-width: 553px;
		}
		.panel-body .btn:not(.btn-block) { width:120px;margin-bottom:10px; }
	</style>
<body>
	<div class="container">
	    <div class="row">
	        <div>
	            <div class="panel panel-danger">
	                <div class="panel-heading">
	                    <h3 class="panel-title">
	                        <span class="glyphicon glyphicon-user"></span> 真象说后台管理系统</h3>
	                </div>
	                
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-xs-6 col-md-4">
	                        	<img alt="" src="<%=request.getContextPath()%>/assets/images/sorry.jpg">
	                        </div>
	                        <div class="col-xs-6 col-md-8">
	                        	<br>
	                        	<h3>500系统异常：</h3>
	                        	<h3>对不起，服务器存在异常</h3>
	                        	<h3>请联系系统管理员</h3>
	                        </div>
	                    </div>
	                    <a href="<%=request.getContextPath()%>/admins/login" class="btn btn-danger btn-block" role="button">登录</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>
