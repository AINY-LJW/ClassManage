<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校园教室管理系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet"
	type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<!-- <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script> -->

<script>
	$(function() {

		$(".i-text").focus(function() {
			$(this).addClass('h-light');
		});

		$(".i-text").focusout(function() {
			$(this).removeClass('h-light');
		});

		$("#username").focus(function() {
			var username = $(this).val();
			if (username == '输入账号') {
				$(this).val('');
			}
		});

		$("#username").focusout(function() {
			var username = $(this).val();
			if (username == '') {
				$(this).val('输入账号');
			}
		});

		$("#password").focus(function() {
			var username = $(this).val();
			if (username == '输入密码') {
				$(this).val('');
			}
		});

		

		/*  $(".registerform").Validform({
		 tiptype:function(msg,o,cssctl){
		 var objtip=$(".error-box");
		 cssctl(objtip,o.type);
		 objtip.text(msg);
		 },
		 ajaxPost:true
		 }); 
		 */
	});
</script>


</head>

<body>


	<div class="header">
		<h1 class="headerLogo">
			<a title="后台管理系统"><img alt="logo"
				src="images/uugai.com_1552554117272.png"></a>
		</h1>
		<div class="headerNav">
			<a href="#">企业官网</a> <a href="#">关于我们</a> <a href="#">开发团队</a> <a
				href="#">意见反馈</a> <a href="#">帮助</a>
		</div>
	</div>

	<div class="banner">

		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout: fixed;">
				<div class="error-box"></div>

				<form class="registerform">
					<div class="fm-item">
						<label for="logonId" class="form-label">LFH系统登陆：</label> <input
							type="text" maxlength="100" id="username" class="i-text"
							datatype="s6-18" placeholder="请输入用户名！">
							<div class="ui-form-explain"></div>
					</div>

					<div class="fm-item">
						<label for="logonId" class="form-label">登陆密码：</label> <input
							type="password" value="" maxlength="100" id="password"
							class="i-text" datatype="*6-16" placeholder="请输入密码！">
							<div class="ui-form-explain"></div>
					</div>


					<div class="fm-item">
						<label for="logonId" class="form-label"></label> <input
							type="button" value="" tabindex="4" id="send-btn"
							class="btn-login" onclick="submitTo()" />
						<div class="ui-form-explain"></div>
					</div>

				</form>

			</div>

		</div>

		<div class="bd">
			<ul>
				<li
					style="background: url(themes/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"></li>
				<li
					style="background: url(themes/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"></a></li>
			</ul>
		</div>

		<div class="hd">
			<ul></ul>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(".banner").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			effect : "fold",
			autoPlay : true,
			autoPage : true,
			trigger : "click"
		});
		function submitTo() {
			 $.ajax({
				type : "POST",
				url : "login",
				data : {
					name : $("#username").val(),
			 		pwd:$("#password").val()
				},
				//返回数据的格式
				datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
				//在请求之前调用的函数
				beforeSend : function() {
					//$("#msg").html("logining");
				},
				success : function(data) {

					if(data=="ok"){
						alert("登陆成功");
						window.location.href="/";
					}else{
						alert("账号密码错误");
					}
					
				},
				//调用执行后调用的函数
				complete : function(XMLHttpRequest, textStatus) {

				},
				//调用出错执行的函数
				error : function() {
					alert("删除失败");
				}
			}); 
		}
	</script>


	<div class="banner-shadow"></div>

	<div class="footer">
		<p>Copyright &copy; 2014.Company name All rights reserved. LFH</p>
	</div>

</body>
</html>
