<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>真象日报 - 基金行业最新资讯</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
<meta name="apple-mobile-web-app-status-bar-style" content="yes" />

<link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/assets/css/carouselrows.css" type="text/css" rel="stylesheet" />
<style type="text/css">
  body{
    padding-top: 20px;
  }
  .row {
    margin-right: -5px;
    margin-left: -15px;
  }
  
  .col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, 
  .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, 
  .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, 
  .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, 
  .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, 
  .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, 
  .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, 
  .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
    padding-left: 0px;
    padding-right: 0px;
  }
</style>
</head>

<body>
  <div class="container" id="news-container">
    <c:forEach items="${news}" var="obj" varStatus="s">
      <div class="row carousel-row" id="row${s.index+1}">
        <div class="col-xs-12 col-xs-offset-0 slide-row">
          <div id="carousel-1" class="carousel slide slide-carousel" data-ride="carousel">
            <div class="carousel-inner">
              <div class="item active">
                <a href="${obj.url}" target="_blank"><img src="${root}/imgbase/${obj.imgLocPath}"></a>
              </div>
            </div>
          </div>
          <div class="slide-content">
            <span style="color: #000000; font-size: 14px;"><a href="${obj.url}" target="_blank"><strong>${obj.title}</strong></a></span>
            <p style="color: #837E7E; margin-top: 5px;">${hfn:subString(obj.subTitle, 35)}</p>
          </div>
          <div class="slide-footer">
            <span class="pull-left">
              <span>来源：${obj.domain}</span>
            </span>
            <span class="pull-right">
              <i class="fa fa-fw fa-eye"></i> <fmt:formatNumber value="${obj.truthDegree}" pattern="#" type="number"/>
            </span>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</body>

<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>

</html>
