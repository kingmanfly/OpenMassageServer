<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>后台管理系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<style type="text/css">
			#footer{ font-weight: 400; text-align: center; padding: 20px;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12 column">
					<img class="img-responsive center-block" src="http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518358756757&di=c24319e6ee39c763a4274d17f4d74388&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F80%2F81%2F45bOOOPIC81_1024.jpg" />
					<form role="form" action="check_login" method="get">
						<div class="form-group">
							 <label for="username">UserName</label><input type="text" class="form-control" name="userName" />
						</div>
						<div class="form-group">
							 <label for="password">Password</label><input type="password" class="form-control" name="password" />
						</div>
						
						<div class="checkbox">
							 <label><input type="checkbox" />记住用户名</label>
						</div> 
						<center>
							<button type="submit" class="btn btn-default btn-success">登录</button>
						</center>
					</form>
				</div>
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
	</body>
</html>