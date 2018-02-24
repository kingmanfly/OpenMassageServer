<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>后台管理系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<style type="text/css">
			.container .thead{margin-top: 134px;}
			.margin-top30{margin-top: 30px}
		</style>
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
				<!--警告框-->
				<div class="col-md-12">
					<div class="alert alert-danger alert-dismissible fade in" role="alert">
						<h4>审核要求:</h4>
						<p>拒绝联系方式无效，拒绝描述不清，拒绝黄赌毒！</p>
						<p>
							<a href="/admin/auditing-pass?id=${detail.id}" type="button" class="btn btn-danger">通过</a>
							<a href="/admin/auditing-reject?id=${detail.id}" type="button" class="btn btn-default">拒绝</a>
						</p>
					</div>
				</div>
				<!--警告框-->
				
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>姓名</th>
							<th>电话</th>
							<th>年龄</th>
							<th>性别</th>	
							<th>身高</th>
							<th>级别</th>
							<th>工作位置</th>
							<th>联系方式</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${detail.nickname}</td>
							<td>${detail.phone}</td>
							<td>${detail.age}</td>
							<td>${(detail.sex == 0)?string('女','男')}</td>  
							<td>${detail.height}</td>
							<td>
								<#switch detail.level>
									<#case 0>
										高级技师
									<#break>
									<#case 1>
										中级技师
									<#break>
									<#case 2>
										初级技师
									<#break>
									<#case 3>
										实习技师
									<#break>
								</#switch>
							</td>
							<td>${detail.location}</td>
							<td>${detail.phone}</td>
						</tr>
					</tbody>
				</table>
				<div class="col-md-12">
					<div>头像展示</div>
					<div class="col-md-4">
						<img src='${detail.pic_head_path}' class="img-responsive" alt="Responsive image">
					</div>
				</div>
				<div class="col-md-12">
					<div>个人展示</div>
					<#list detail.pic_show as item>
						<div class="col-md-4">
							<img src='${item}' class="img-responsive" alt="Responsive image">
						</div>
					</#list>
				</div>
			</div>
		</div>
		
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>