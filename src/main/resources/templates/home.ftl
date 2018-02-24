<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>后台管理系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	<body>
	<style type="text/css">
		#footer{ font-weight: 400; text-align: center; padding: 20px;}
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
						<li class="active">
							<a href="/admin/home"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;后台首页</a>
						</li>
						<li>
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
			<!--网站统计数据-->
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">网站统计数据</h3>
						</div>
						<div class="panel-body">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>统计项目</th>
										<th>今日</th>
										<th>昨日</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>注册会员</th>
										<td>200</td>
										<td>400</td>
									</tr>
									<tr>
										<th>登录会员</th>
										<td>4100</td>
										<td>5112</td>
									</tr>
									<tr>
										<th>今日发帖</th>
										<td>1540</td>
										<td>4511</td>
									</tr>
									<tr>
										<th>转载次数</th>
										<td>150</td>
										<td>110</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--网站统计数据-->
				<!--访客统计图表-->
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">访客统计</div>
						<div class="panel-body">
							<canvas id="myChart" class="col-md-12"></canvas>
						</div>
					</div>
				</div>
				<!--访客统计图表-->
			</div>
		</div>
		<!--footer-->
		<footer id="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<p>Copyright&nbsp;©&nbsp;2012-2015&nbsp;&nbsp;www.openmassage.com&nbsp;&nbsp;沪ICP备13014270号-4</p>
					</div>
				</div>
			</div>
		</footer>
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.min.js" ></script>
		<script>
			var ctx = document.getElementById("myChart").getContext('2d');
			var myChart = new Chart(ctx, {
			    type: 'line',
			    data: {
			        labels: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			        datasets: [{
			            label: '# of Vistor',
			            data: [1, 3, 8, 19, 100, 200,260],
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255,99,132,1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero:true
			                }
			            }]
			        }
			    }
			});
		</script>
	</body>
</html>