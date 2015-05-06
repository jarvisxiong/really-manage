<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="shortcut icon" />
<link href="<%=request.getContextPath()%>/assets/images/logo.png" type="image/x-icon" rel="icon" />

<title>咖啡日报后台管理- V1.0</title>

<link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/css/dashboard.css" rel="stylesheet">
</head>
<style type="text/css">
  .page-target {
    font-weight: bold;
    color: #603811;
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
  }
  .main-container {
    background-color: #FFFFFF;
    margin: 10px auto;
    padding: 5px 20px 30px 20px;
    border-radius: 3px;
  }
</style>
<body>
  <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand"><strong>咖啡日报管理平台</strong></a>
      </div>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-left">
          <li><a href="<%=request.getContextPath()%>/admins/main"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
          
          <!-- 
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> 文章管理 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="<%=request.getContextPath()%>/manage/news/list"><span class="glyphicon glyphicon-book"></span> 文章基础管理</a></li>
              <li class="divider"></li>
              <li><a href="<%=request.getContextPath()%>/manage/news/send/list"><span class="glyphicon glyphicon-book"></span> 文章发布管理</a></li>
            </ul>
          </li>
           -->
          
          <li><a href="<%=request.getContextPath()%>/manage/news/list"><span class="glyphicon glyphicon-th-list"></span> 素材管理</a></li>
          <li><a href="<%=request.getContextPath()%>/manage/news/send/list"><span class="glyphicon glyphicon-th-list"></span> 出版管理</a></li>
          <li><a href="<%=request.getContextPath()%>/manage/news/history/list"><span class="glyphicon glyphicon-th-list"></span> 历史管理</a></li>
          <li><a href="<%=request.getContextPath()%>/manage/weChat/list"><span class="glyphicon glyphicon-th-list"></span> 公众号管理</a></li>
          
          <!-- 
          <li><a href="<%=request.getContextPath()%>/manage/user/list"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
          <li><a href="<%=request.getContextPath()%>/manage/message/list"><span class="glyphicon glyphicon-comment"></span> 留言管理</a></li>
           -->
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a data-toggle="modal" data-target=".logout-modal" href=""><span class="glyphicon glyphicon-log-out"></span> 退出系统</a></li>
        </ul>
      </div>
    </div>
  </div>
  
  <div class="modal fade logout-modal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h5 class="modal-title">系统提示</h5>
        </div>
        <div class="modal-body" style="text-align: center; font-weight: bold;">您确定退出管理系统吗？</div>
        <div class="modal-footer">
          <a href="<%=request.getContextPath()%>/admins/logout">
            <button type="button" class="btn btn-primary btn-sm">确定</button>
          </a>
          <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
  </div>
  
</body>
</html>
