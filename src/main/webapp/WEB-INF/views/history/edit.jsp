<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
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
		};
	</script>
</head>
<body>
	<jsp:include page="../top.jsp" />
	
	<div class="container main-container">
		<h5 class="page-header page-target">素材管理 - 编辑素材</h5>
		<form action="${root}/manage/news/history/doEdit" role="form" method="post">
			<div class="row">
				<div class="col-xs-7">
					<!-- 隐藏字段 -->
					<input type="hidden" id="id" name="id" value="${news.id}" />
					<input type="hidden" id="commentId" name="commentId" value="${news.commentId}" />
					<input type="hidden" id="url" name="url" value="${news.url}" />
		            <!-- 
		            <div class="form-group">
		              <label for="comment">文章评论：</label>
		              <textarea cols="30" id="comment" class="form-control" name="comment" rows="3" style="width: 100%;" placeholder="请输入文章评论">${news.comment}</textarea>
		            </div>
		             -->
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
						<label for="imgLink">输入图片链接重新获取：</label>
					    <input type="text" id="refetchImgLink" name="refetchImgLink" class="form-control" style="width: 100%;" placeholder="输入图片链接重新获取" />
					    <!-- 在不修改图片的情况下默认 -->
					    <input type="hidden" id="imgLocPath" name="imgLocPath" value="${news.imgLocPath}" />
					</div>
					<div class="form-group">
						<input type="button" id="refetchImgBut" name="refetchImgBut" class="btn btn-primary btn-sm" value="重新获取" disabled="disabled" />
					</div>
		            <!-- 
					<div class="form-group">
						<label for="blogContent">文章内容：</label>
					    <textarea id="blogContent" name="blogContent">${blogInfo.blogContent}</textarea>
					</div>
		             -->
					<input type="hidden" name="state" id="state" value="${news.state}">
					<hr>
					<input type="submit" class="btn btn-primary btn-sm" value="保存" />
				</div>
				
				<div class="col-xs-5">
					<div class="form-group">
		              <label for="title">文章图片预览：</label>
		            </div>
					<div class="form-group">
		              <img id="indexImg" name="indexImg" src="${root}/imgbase/${news.imgLocPath}" class="img-thumbnail" />
		            </div>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../bottom.jsp" />
	
	<script type="text/javascript">
		$('#refetchImgLink').on('input',function(e){
			var link = $.trim($('#refetchImgLink').val());
			if (link == '') {
				$('#refetchImgBut').attr("disabled", true);
			} else {
				$('#refetchImgBut').attr("disabled", false);
			}
		});
		
		$("#refetchImgBut").click( function() {
			var link = $.trim($('#refetchImgLink').val());
			var resLink = $('#imgLocPath').val();
			$.ajax({
    		  	url : '${root}/manage/news/refetchImage', // 跳转到 action    
    		  	data : {link : link, resLink: resLink},
    		  	type : 'post',
    		  	beforeSend:function(){
    		  		$("#indexImg").attr("src", "${root}/assets/images/loading.gif");
    		  	},
    		  	cache : false,
    		  	dataType : 'json',
    		  	success : function(data) {
    		  		$('#imgLocPath').val(data.imgRefetchPath);
    		  		$("#indexImg").attr("src", "${root}/imgbase/" + data.imgRefetchPath);
    		  	},
    		  	error : function() {
    		  		$("#indexImg").attr("src", "${root}/assets/images/111.png");
    		  		alert("获取异常，请检查你输入的图片链接！友情提示：如果是直接在微信网页中复制图片网址链接的话，则不可用。");
    		  	}
		  	});
		});
	</script>
</body>
</html>
