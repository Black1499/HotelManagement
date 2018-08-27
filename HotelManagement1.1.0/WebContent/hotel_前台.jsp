<%@page import="com.vo.EmployeeInfoVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="js/layUI/css/layui.css">
<link rel=stylesheet type=text/css href="css/zzsc.css">
<title>前台界面</title>
<style type="text/css">
	body{
		margin:0px;
		padding:0px;
	}
	.main{
		width:1350px;
		height:625px;
		border:;
		margin:auto;
	}
	.left{
		position:relative;
		float:left;
		width:248px;
		height:625px;
		background-color:;
	}
	.right{
		position:relative;
		float:left;
		width:1090px;
		height:625px;
		left:10px;
		background-color:;
	}
	.logo{
		width:248px;
		height:100px;
		background-color:#696969;
	}
	.nav{
		width:1090px;
		height:50px;
		background-color:#F5F5F5;
	}
	.logo img{
		width:248px;
		height:100px;
	}
	.quick{
		width:248px;
		height:530px;
		background-color:#F5F5F5;
	}
	iframe{
		width:1087px;
		height:577px;
		background-color:;
	}
	.nav *{
		position:relative;
		float:left;
	}
	.search{
		width:300px;
		height:50px;
		background-color:;
		margin-left:10px;
	}
	.search input{
		position:relative;
		float:left;
		width:200px;
		height:25px;
		top:12px;
	}
	.search button{
		height:30px;
		width:72px;
		top:12px;
		left:3px;
		line-height:30px;
	}
	.loginName{
		width:200px;
		height:50px;
		background-color:;
		left:590px;
		font-size:16px;
		
	}
	.loginName div{
		width:70px;
		height:24px;
		top:13px;
		background-color:white;
		line-height:24px;
		text-align:center;
	}
	.loginName div a{
 		left:12px; 
 		font-size:12px;
	}
	.loginName div+a{
		left:70px;
		color:red;
		top:13px;
	}
	
</style>
</head>
<body>
<%
	EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo");
	if(empVo == null){//为空，客户没有登陆想打开主界面
		response.sendRedirect("login.js");//重定向到登陆界面
	}
%>
	<div class="main">
		<div class="left">
			<div class="logo">
				<img src="img/log.png">
			</div>
			<div class="quick">
				<div id="firstpane" class="menu_list">
				    <p class="menu_head current">快捷操作</p>
				    <div style="display:block" class=menu_body >
				      <a href="roomInfo.jsp" target="myIframe">客房信息</a>
				      <a href="roomCheck.jsp?state=0" target="myIframe">客户入住</a>
				      <a href="roomReserve.jsp" target="myIframe">客户预定</a>
				      <a href="checkOut.jsp" target="myIframe">客户结账</a>
				    </div>
				    <p class="menu_head">我的操作</p>
				    <div style="display:none" class=menu_body >
				       <a href="empById.jsp" target="myIframe">我的信息</a>
				       <a href="ExctControl" target="" >退出登录</a>
				    </div>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="nav">
				<div class="search">
					<input type="text" id="num"/>
					<button class="layui-btn layui-btn-normal" id="btnSel">搜索</button>
				</div>
				
				<div class="loginName">
				<div style="text-align:center"><a href="empById.jsp" target="myIframe"><%=empVo.getEmpName() %></a></div>
				<a href="ExctControl">注销</a>
			</div>
			<div class="iframe">
				<iframe class="iframe" name="myIframe" src="roomInfo.jsp"></iframe>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>
<script>
	var index={
		roomFind:function(){//房间查询,根据房间的状态进行跳转或提示
			$("#btnSel").click(function(){
				$.post("JudgeRoom?action=getRoom",{roomNum:$("#num").val()},function(data){
					if(data.msg==null){
						if(data.roomAvailable==1){
							alert("该房间无法使用...");
						}else{
							if(data.roomState==0){
								//window.location.href="roomCheck.jsp?state=0";
								$("iframe").prop("src","roomCheck.jsp?state=0");
							}else if(data.roomState==1){
								//window.location.href="roomCheck.jsp?state=1&roomNum="+data.roomNum;
								$("iframe").prop("src","roomCheck.jsp?state=1&roomNum="+data.roomNum);
							}else if(data.roomState==2){
								//window.location.href="roomReserve.jsp?roomNum="+data.roomNum;
								$("iframe").prop("src","roomReserve.jsp?roomNum="+data.roomNum);
							}else if(data.roomState=3){
								alert("该房间清洁中...");
							}
						}
					}else{
						alert(data.msg);
					}
				});
			});
		},
		init:function(){
			index.roomFind();	
		}	
	}
	index.init();
</script>
</html>