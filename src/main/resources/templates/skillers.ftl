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
						<li>
							<a href="/admin/userlist"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a>
						</li>
						<li class="active">
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
			<div class="row">
				<div class="col-md-12 column">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>姓名</th>
								<th>年龄</th>
								<th>描述</th>
								<th>工作城市</th>
								<th>审核状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<#list skillers as skiller>
								<tr>
									<td>${skiller.id}</td>
									<td>${skiller.nickname}</td>
									<td>${skiller.age}</td>
									<td>${skiller.description}</td>
									<td>${skiller.location}</td>
									<td>
										<#switch skiller.status>
											<#case 0>
											待审核
											<#break>
											<#case 1>
											已通过
											<#break>
											<#case 2>
											已拒绝
											<#break>
										</#switch>
									</td>
									<td><a href="/admin/skillerdetail?id=${skiller.id}">查看</a></td>
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
							<li><a href="/admin/skillerlist?pageNo=${currentPage - 1}&pageSize=${pageSize}">上一页</a></li>
						</#if>
						
						<#list 1..skillersCount as index>
							<#if currentPage == index>
								<li class="disabled"><a href="#">${index}</a></li>
							<#else>
								<li><a href="/admin/skillerlist?pageNo=${index}&pageSize=${pageSize}">${index}</a></li>
							</#if>
						</#list>
						<#if currentPage gte skillersCount>
							<li class="disabled"><a href="#">下一页</a></li>
						<#else>
							<li><a href="/admin/skillerlist?pageNo=${currentPage + 1}&pageSize=${pageSize}">下一页</a></li>
						</#if>
					</ul>
				</div>
			</div>
		</div>
		
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>