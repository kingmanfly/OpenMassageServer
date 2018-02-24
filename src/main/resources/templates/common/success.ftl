<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>成功提示</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body>
	<style type="text/css">
	
	</style>
	
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="alert alert-dismissable alert-danger">
					 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
					<h4>
						成功!
					</h4> <strong>${msg}</strong><a href="${url}" class="alert-link">3s后自动跳转</a>
				</div>
			</div>
		</div>
	</div>
	</body>
	<script language="javascript" type="text/javascript">
		setTimeout('location.href="${url}"', 3000);
	</script>
</html>