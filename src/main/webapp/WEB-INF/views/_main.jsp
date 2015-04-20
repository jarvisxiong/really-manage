<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="shortcut icon"/>
<link href="<%=request.getContextPath()%>/assets/images/favicon.png" type="image/x-icon" rel="icon"/>

<title>后台管理 - 新成博客</title>

<!-- Bootstrap core CSS -->
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/assets/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style type="text/css">
	body {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		font-size: 13px;
		line-height: 1.42857143;
		color: #333;
	}
</style>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">新成博客管理平台</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">首页</a>
					<li><a href="#">吴新成 [系统管理员]</a></li>
					<li><a href="#">退出系统</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"><b>博客管理</b></li>
					<li class="active"><a href="<%=request.getContextPath()%>/blogInfo/list">博客管理</a></li>
					<li><a href="#">类别管理</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"><b>访问查询</b></li>
					<li><a href="#">首页访问查询</a></li>
					<li><a href="#">博客访问查询</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"><b>报表统计</b></li>
					<li><a href="#">博客日报表</a></li>
					<li><a href="#">博客月报表</a></li>
					<li><a href="#">博客季报表</a></li>
					<li><a href="#">博客年报表</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"><b>系统管理</b></li>
					<li><a href="#">操作员管理</a></li>
					<li><a href="http://www.baidu.com" target="_blank">用户管理</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h4 class="page-header">博客管理</h4>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="<%=request.getContextPath()%>/assets/js/holder/holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div>

				<h4 class="sub-header">博客列表</h4>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>博客标题</th>
								<th>博客类别</th>
								<th>操作时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>4</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>5</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>6</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>7</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>8</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>9</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
							<tr>
								<td>10</td>
								<td>马来西来客机在南中海域坠海，机上共有人员239名</td>
								<td>实时新闻</td>
								<td>2014-03-15 13:42:59</td>
								<td>
									<button type="button" class="btn btn-primary btn-sm">修改</button>
									<button type="button" class="btn btn-success btn-sm">预览</button>
									<button type="button" class="btn btn-warning btn-sm">特殊标记</button>
									<button type="button" class="btn btn-danger btn-sm">禁用</button>
								</td>
							</tr>
						</tbody>
					</table>
					<ul class="pager">
						<li><a href="#">Previous</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">...</a></li>
						<li><a href="#">30</a></li>
						<li><a href="#">Next</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/docs.min.js"></script>
</body>
</html>
