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
		<h5 class="page-header page-target">贴子管理 - 贴子信息管理 - 编辑贴子</h5>
		<form action="<%=request.getContextPath()%>/manage/blogInfo/doEdit" role="form" method="post">
			<!-- 隐藏字段 -->
			<input type="hidden" id="blogId" name="blogId" value="${blogInfo.blogId}" />
			<div class="form-group">
				<label for="blogTitle">贴子标题：</label>
			    <input type="text" name="blogTitle" value="${blogInfo.blogTitle}" class="form-control" style="width: 585px;" placeholder="请输入贴子标题" />
			</div>
			
			<div class="form-group">
				<label for="picLink">贴子图片：</label>
			    <input type="text" name="picLink" value="${blogInfo.picLink}" class="form-control" style="width: 585px;" placeholder="请选择贴子图片" />
			</div>
			
			<div class="form-group">
				<label for="blogType">贴子类型：</label><br>
				<c:forEach items="${types}" var="type" varStatus="s">
				<label class="radio-inline">
					<input type="radio" name="blogType" id="blogType${type.typeId}" value="${type.typeId}"
						<c:if test="${empty blogInfo.blogType && '1' eq type.typeId}">checked="checked"</c:if>
			    		<c:if test="${type.typeId eq blogInfo.blogType}">checked="checked"</c:if> />${type.typeName}
				</label>
				</c:forEach>
			</div>
			
			<div class="form-group">
				<label for="resource">贴子简介：</label>
				<textarea cols="30" id="subContent" class="form-control" name="subContent" rows="3" style="width: 585px;" placeholder="请输入贴子简介">${blogInfo.subContent}</textarea>
			</div>
			<div class="form-group">
				<label for="blogContent">贴子内容：</label>
			    <textarea id="blogContent" name="blogContent">${blogInfo.blogContent}</textarea>
			</div>
			<div class="form-group">
				<label for="blogState">博客状态：</label><br>
			    
			    <label class="radio-inline">
				  <input type="radio" name="blogState" id="blogState1" value="0" 
			    	<c:if test="${empty blogInfo.blogState}">checked="checked"</c:if>
			    	<c:if test="${'0' eq blogInfo.blogState}">checked="checked"</c:if>>
			    	已经完成，我要发布
				</label>
				&nbsp;&nbsp;
				<label class="radio-inline">
				  <input type="radio" name="blogState" id="blogState2" value="1" 
			    	<c:if test="${'1' eq blogInfo.blogState}">checked="checked"</c:if>>
			    	还未完成，存到草稿箱
				</label>
			</div>
			<input type="submit" class="btn btn-primary btn-sm" value="保存" />
			<input type="button" class="btn btn-primary btn-sm" value="重置" />
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
</body>
</html>

