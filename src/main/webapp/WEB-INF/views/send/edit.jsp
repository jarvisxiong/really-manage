<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/ckfinder/ckfinder.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
	<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>
	
	<script type="text/javascript">
		window.onload = function() {
			var editor = CKEDITOR.replace('blogContent');
			CKFinder.setupCKEditor(editor,'/ckeditor/');
		}
	</script>
</head>
<body>
	<jsp:include page="../top.jsp" />
	
	<div class="container main-container">
		<h5 class="page-header page-target">文章信息管理 - 编辑文章</h5>
		<form action="<%=request.getContextPath()%>/manage/news/doEdit" role="form" method="post">
			<!-- 隐藏字段 -->
			<input type="hidden" id="id" name="id" value="${news.id}" />
			<input type="hidden" id="commentId" name="commentId" value="${news.commentId}" />
			<input type="hidden" id="url" name="url" value="${news.url}" />
            <div class="form-group">
              <label for="comment">文章评论：</label>
              <textarea cols="30" id="comment" class="form-control" name="comment" rows="3" style="width: 100%;" placeholder="请输入文章评论">${news.comment}</textarea>
            </div>
			<div class="form-group">
				<label for="title">文章标题：</label>
			    <input type="text" name="title" value="${news.title}" class="form-control" style="width: 100%;" placeholder="请输入文章标题" />
			</div>
            <div class="form-group">
              <label for="subTitle">文章简介：</label>
              <textarea cols="30" id="subTitle" class="form-control" name="subTitle" rows="3" style="width: 100%;" placeholder="请输入文章简介">${news.subTitle}</textarea>
            </div>
            <div class="form-group">
              <label for="domain">文章来源：</label>
                <input type="text" name="domain" value="${news.domain}" class="form-control" style="width: 100%;" placeholder="请选择文章图片" />
            </div>
			<div class="form-group">
				<label for="imgLink">文章图片：</label>
			    <input type="text" name="imgLink" value="${news.imgLink}" class="form-control" style="width: 100%;" placeholder="请选择文章图片" />
			</div>
            <div class="form-group">
              <label for="imgLink"></label>
              <img src="${news.imgLink}" />
            </div>
            
            <!-- 
			<div class="form-group">
				<label for="blogContent">文章内容：</label>
			    <textarea id="blogContent" name="blogContent">${blogInfo.blogContent}</textarea>
			</div>
             -->
			<div class="form-group">
				<label for="blogState">文章状态：</label><br>
			    
			    <label class="radio-inline">
				  <input type="radio" name="state" id="state" value="0" 
			    	<c:if test="${'0' eq news.state}">checked="checked"</c:if>
                    <c:if test="${empty news.state}">checked="checked"</c:if>>
			    	已经完成，我要发布
				</label>
				&nbsp;&nbsp;
				<label class="radio-inline">
				  <input type="radio" name="state" id="state" value="1" 
			    	<c:if test="${'1' eq news.state}">checked="checked"</c:if>>
			    	还未完成，存到草稿箱
				</label>
			</div>
			<input type="submit" class="btn btn-primary btn-sm" value="保存" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

