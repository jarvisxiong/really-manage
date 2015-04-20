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
<body>
  <jsp:include page="../top.jsp" />

  <div class="container main-container">
    <h5 class="page-header page-target">文章信息管理 - 真象评论</h5>
    <form action="<%=request.getContextPath()%>/manage/news/doComment"
      role="form" method="post">
      <input type="hidden" id="newsId" name="newsId" value="${newsId}" />
      <input type="hidden" id="id" name="id" value="${commentId}" />
      <div class="form-group">
        <label for="truthDegree">真象评价：</label><br>
        <label class="radio-inline"><input type="radio" name="truthDegree" id="truthDegree" value="1" checked="checked">假的</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="truthDegree" id="truthDegree" value="2">可疑</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="truthDegree" id="truthDegree" value="3">无所谓</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="truthDegree" id="truthDegree" value="4">可能</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="truthDegree" id="truthDegree" value="5">真的</label>&nbsp;&nbsp;
      </div>
      <div class="form-group">
        <label for="content">真象评论：</label>
        <textarea cols="30" id="content" class="form-control"
          name="content" rows="3" style="width: 500px;"
          placeholder="请输入真象评论"></textarea>
      </div>
      <div class="form-group">
        <label for="relationship">身份标签：</label><br>
        <label class="radio-inline"><input type="radio" name="relationship" id="relationship" value="1">知情人</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="relationship" id="relationship" value="2">当事人</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="relationship" id="relationship" value="3">同事</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="relationship" id="relationship" value="4">同行</label>&nbsp;&nbsp;
        <label class="radio-inline"><input type="radio" name="relationship" id="relationship" value="5">瞎猜</label>&nbsp;&nbsp;
      </div>
      <input type="submit" class="btn btn-primary btn-sm" value="确定" />
    </form>
  </div>

  <jsp:include page="../bottom.jsp" />
</body>
</html>

