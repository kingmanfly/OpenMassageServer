<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>后台管理系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body>
	<style type="text/css">
	.container .thead{margin-top: 134px;}
	</style>
		<!--导航-->
		<nav class="navbar navbar-default">
			<div class="container">
				<!--小屏幕导航按钮和logo-->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
			 	 		<span class="icon-bar"></span>
			 	 		<span class="icon-bar"></span>
			 	 		<span class="icon-bar"></span>
			 	 	</button>
					<a href="#" class="navbar-brand">后台管理系统</a>
				</div>

				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li>
							<a href="/admin/home"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;后台首页</a>
						</li>
						<li class="active">
							<a href="/admin/userlist"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a>
						</li>
						<li>
							<a href="/admin/skillerlist"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;技师管理</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a id="dLabel" href="#home" type="button" data-toggle="dropdown" aria-haspop="true" aria-expanded="false">
								${nickname}
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" aria-labelledby="dLable">
								<li>
									<a href="/admin/logout"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;退出账户</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!--导航-->
		<div class="container">
			<div class="row>
				<div class="col-md-12 column">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>姓名</th>
								<th>手机号码</th>
								<th>是否升级为技师</th>
								<th>注册时间</th>
							</tr>
						</thead>
						<tbody>
							<#list users as user>
								<tr>
									<td>${user.sharp_id}</td>
									<td>${user.nickname}</td>
									<td>${user.phone}</td>
									<td>${(user.category == 2)?string('是','否')}</td>
									<td>${user.create_time}</td>
								</tr>	
							</#list>
							
						</tbody>
					</table>
				</div>
				<div class="col-md-12 column">
					<ul class="pagination pull-right">
						<#if currentPage lte 1>
							<li class="disabled"><a href="#">上一页</a></li>
						<#else>
							<li><a href="/admin/userlist?pageNo=${currentPage - 1}&pageSize=${pageSize}">上一页</a></li>
						</#if>
						
						<#list 1..usersCount as index>
							<#if currentPage == index>
								<li class="disabled"><a href="#">${index}</a></li>
							<#else>
								<li><a href="/admin/userlist?pageNo=${index}&pageSize=${pageSize}">${index}</a></li>
							</#if>
						</#list>
						<#if currentPage gte usersCount>
							<li class="disabled"><a href="#">下一页</a></li>
						<#else>
							<li><a href="/admin/userlist?pageNo=${currentPage + 1}&pageSize=${pageSize}">下一页</a></li>
						</#if>
					</ul>
				</div>
			</div>
		</div>
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>