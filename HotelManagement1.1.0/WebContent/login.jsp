<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六月酒店登陆</title>
<style>
	.div1{
		width:1340;
		margin:auto;
		height:620px;
		position:relative;
	}
	body{
	   background-position: top center;
	   background-repeat: no-repeat;
       background-color: #ebebeb;
		color: #fff;
		margin: 0;
		padding: 0;
	}
	div {
		display: block;
	}
	a{
		margin: 0;
		padding: 0;
		text-decoration: none;
		color: -webkit-link;
		cursor: pointer;
	}
	.wrap-large {
		width: 1190px;
		margin: 0 auto;
		padding: 0;
	}
	.back-home {
		line-height: 60px;
		overflow: hidden;
		text-align: left;
		margin: 0;
		padding: 0;
	}
	.back-home .link-btn{
		margin: 0;
	    padding: 0 0 0 14px;
	    font-size: 16px;
	    color: #fff;
	    position: relative;
	}
	.hd-box{
		margin: 0;
	    padding: 0;
		text-align: center;
	}
	.hd-box .siting-logo{
		margin: 0;
		padding: 0;
		display: inline-block;
	    width: 350px;
	    height: 114px;
	    background-image: url(img/logo1.png);
	}
	.page-cont{
		width: 400px;
	    margin: 30px auto 0;
	    padding: 30px 40px;
	    background: url(img/transparent.png);
	    overflow: hidden;
	    color: #b4b4b4;
	}
	.login-btn-tabs {
	    margin: auto;
	    padding: 0;
	    font-size: 18px;
	    width: 150px;
	}
	.login-btn-tabs .active {
	    font-weight: 400;
	    color: #fff;
	    cursor: default;
	    text-decoration: none;
	}
	.login-btn-tabs a {
	    display: inline-block;
	    margin: 0 20px;
	    padding: 0;
	    color: #b4b4b4;
	}
	.verify-info {/*显示错误信息*/
	    height: 20px;
	    margin-top: 10px;
	    line-height: 20px;
	    display:none;
	    padding-left: 80px;
	    color: #ff5756;
	    background: url(img/wrong.png) no-repeat 65px center;
	    position: relative;
	    z-index: 99;
	}
	.page-cont .control-group {/*form表单*/
	    margin: 15px 130px 0 130px;
	    padding: 0;
	}
	.control-group .controls {
	    margin: 0;
	    padding: 0;
	    position: relative;
	}
	.control-group .controls label {
	    display: block;
	    margin: 0;
	    padding: 0;
	    position: relative;
	}
	.control-group input[type="text"], .control-group input[type="password"] {
	    line-height: 18px;
	    width: 300px;
	    height: 18px;
	    margin: 0;
	    padding: 10px;
	    border: 1px solid #ddd;
	}
	.verify-group .controls label input[type="text"] {
	    width: 156px;
	}
	.verify-group .controls .verify-image {
	    width: 130px;
	    height: 40px;
	    vertical-align: -16px;
	    
	    display: block;
	    float: right;
	    margin: 0;
	    padding: 0;
	}
	.button-group input[type="button"],.button-group input[type="submit"] {
	    display: block;
	    width: 100%;
	    margin: 0;
	    padding: 0;
	    font: normal 18px/36px "Microsoft Yahei";
	    background-color: #f60;
	    border: none;
	    color: #fff;
	    cursor: pointer;
	    border-radius: 3px;
	}
	.footer-wrap{
		margin: 0; 
		margin-top: 15px; 
		position:relative;
		top:90px;
		left:40px;
		width: 500px;
	}
	.inputs{
		left:-80px;
	}
	</style>
</head>
	<body style="background-image: url(img/london.jpg);background-size:1340px 620px;">
		<div class="div1">
			<div class="wrap-large">
				<div class="back-home"><!--返回首页-->
					<a href="http://www.baidu.com" class="link-btn">﹤  返回首页</a>
				</div>
				<div class="hd-box"><!--logo -->
					<h1 class="siting-logo"></h1>
				</div>
				<div class="page-cont"><!--登录框 内容-->
					<div class="my-form-box">
						<div class="login-btn-tabs">
							<a class="active">员工登录</a>
						</div>
						<div class="verify-info" id="shows"  data-eleid="userName">登录失败,请重新尝试</div>
						<form action="LoginControl" method="post"  name="frm" onsubmit="return checkForm();" >
							<div class="control-group">
								<div class="controls">
									<label class="inputs" for="userName">
										<input name="userUid" id="uid"  type="text" value="" placeholder="员工账号">
									</label>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<label class="inputs" for="pwd">
										<input name="userPassword" id="password"  type="password" value="" placeholder="密码">
									</label>
								</div>
							</div>
						   
							<div class="control-group button-group">
								<input type="submit" name="useraction" id="usersubmit"   value="登录" title="登录" class="login-btn">
							</div>
						</form>
					</div>
				</div>
			</div>
			<div id="footer" class="footer-wrap" >
				<div class="m-footer-link-list" style="border:0; background-color: transparent">
					<a href="http://content.tujia.com/tujiajianjie.htm" target="_blank" class="forst" rel="nofollow">关于我们</a>|
					<a href="http://content.tujia.com/youkebangzhu.htm" target="_blank" rel="nofollow">我是房客</a>|
					<a href="https://www.tujia.com/sitemap.htm" target="_blank">网站地图</a>
				</div>
			</div>
		</div>
		<script>
		function checkForm(){
			var ftext = document.getElementById("uid").value;
			var fpwd = document.getElementById("password").value;
			if(!ftext){
				alert("请输入用户名");
				return false;
			}
			if(!fpwd){
				alert("请输入密码");
				return false;
			}
			return true;
		};
		//窗口加载完成事件
		window.onload=function(data){ 
			<%if(request.getAttribute("msg")!=null)
				{
				%>
				alert("登陆失败，请重新输入账号和密码");	
				 //document.getElementById("shows").show(2000);
				<%
				
				}
			%>
		} 
		
	</script>	
</body>
</html>