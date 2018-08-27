<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vo.EmployeeInfoVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台页面</title>
<link rel="stylesheet" href="js/layUI/css/layui.css">
<style type="text/css">
body{
	margin:0px;
	padding:0px;
}
.main{
	width:1350px;
	height:640px;
	border:1px solid black;
	margin:auto;
}
.main div{
	position:relative;
	float:left;
}
.left{
	width:220px;
	height:640px;
	background-color:;
}
.logo{
	width:220px;
	height:50px;
	background-color:#393D49;
	background-image:url(img/logo1.png);
	background-repeat:no-repeat;
	background-position:50% 50%;
	background-size:220px 50px;
}
.quick{
	width:220px;
	height:590px;
	background-color:#393D49;
}
.right{
	width:1120px;
	height:640px;
	left:10px;
	background-color:;
}
.nav{
	width:1130px;
	height:50px;
	line-height:50px;
	left:-10px;
	background-color:#393D49;
}
.nav *{
	position:relative;
	float:left;
}
.iframe,iframe{
	width:1100px;
	height:580px;
	top:5px;
	left:5px;
	background-color:;
}
iframe{
	height:572px;
}
.myTree{
	width:220px;	
}
h3{
	text-align:center;
	color:white;
	left:300px;
}
.aEmps{
	color:white;
	left:600px;
}
span{
	left:740px;
}
span a{
	color:red;
}


#form * {
	position: relative;
	float: left;
}
#form {
	width: 450px;
	height: 200px;
	background-color:;
	display: none;
}
.item {
	width: 380px;
	height: 40px;
	border:;
	background-color:;
	margin: 10px 40px 10px 40px;
	font-size: 18px;
	line-height: 40px;
}
.item input{
	top:8px;
}
.item span{
	left: 5px;
	font-size: 15px;
	color: orangered;
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
			<div class="logo"></div>
			<div class="quick">
				<ul class="layui-nav layui-nav-tree myTree" lay-filter="test">
				<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
				
				  <li class="layui-nav-item layui-nav-itemed">
				    <a href="javascript:;">默认展开</a>
				    <dl class="layui-nav-child">
				     <li class="layui-nav-item"><a href="index_back.jsp" target="myFrame" >后台首页</a></li>
				      <dd><a href="pageData.jsp" target="myFrame">数据报表</a></dd><!--跳转方式  -->
				      <dd><a href="roomType.html"  target="myFrame" >房间类型一览</a></dd>
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;">房间管理</a>
				    <dl class="layui-nav-child">
				     <li class="layui-nav-item"><a href="roomList.html" target="myFrame" >房间一览</a></li>
				      <dd><a id="roomOut">房间导出</a></dd><!--跳转方式  -->
				      <dd><a id="roomIn">房间导入</a></dd>
				    </dl>
				  </li>
				  				  
				  <li class="layui-nav-item">
				    <a href="javascript:;">员工管理</a>
				    <dl class="layui-nav-child">
				     <li class="layui-nav-item"><a href="adminEmp.jsp" target="myFrame" >员工一览</a></li>
				      <dd><a id="empOut">员工导出</a><!--跳转方式  -->
				      <dd><a id="empIn">员工导入</a></dd>
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;">客户管理</a>
				    <dl class="layui-nav-child">
				     <li class="layui-nav-item"><a href="customerInfo.html" target="myFrame" >客户信息</a></li>
				      <dd><a id="ctoOut">信息导出</a><!--跳转方式  -->
				    </dl>
				  </li>
				  <li class="layui-nav-item"><a href="backupData.html" target="myFrame">数据备份</a></li>
				  <li class="layui-nav-item"><a href="index_back.jsp" target="myFrame" >统计数据</a></li>
				  
				  
				  <li class="layui-nav-item">
				    <a>我的操作</a>
				    <dl class="layui-nav-child">
				      <dd><a class="upPwd" href="#">修改密码</a></dd>
				      <dd><a href="ExctControl">退出登录</a></dd>
				    </dl>
				</li>
				</ul>
			</div>
		</div>
		<div class="right">
			<div class="nav">
				<h3>欢迎使用六月酒店管理系统</h3>
				<h4><a class="aEmps" href="empById.jsp" target="myFrame">尊敬的管理员,你好!</a></h4>
				<span><a href="ExctControl" >注销登录</a></span>
			</div>
			<div class="iframe">
				<iframe name="myFrame" src="index_back.jsp"></iframe>
			</div>
		</div>
	</div>
	
	
	
	<form id="form">
	<div class="item">
		<label>当前密码 &nbsp; ： </label><input type="password" id="pwd1" name="empPwd" placeholder=" 请输入当前密码 " maxlength='20'/>
	</div>
	
	<div class="item">
		<label>新的密码 &nbsp; ： </label><input type="password" id="pwd2" name="empPwdNew" placeholder=" 请输入新的密码 " maxlength='20'/>
	</div>
	<div class="item">
		<label>重复密码 &nbsp; ： </label><input type="password" id="pwd3" name="empPwdNew2" placeholder=" 请重复新的密码 " maxlength='20'/>
	</div>
</form>
</body>
<script type="text/javascript" src="js/layUI/layui.all.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script>
	layui.use('element', function(){
	  var element = layui.element; 
	  //当点击导航父级菜单和二级菜单时触发，回调函数返回所点击的菜单DOM对象：
	  element.on('nav(filter)', function(elem){
		  console.log(elem); //得到当前点击的DOM对象
		});
	});
	
	var backMan={
		roomOut:function(){
			$("#roomOut").click(function(){
				if(confirm("是否确定导出房间信息表")){
					window.location.href="ExcelRoomServlet?action=outRoom";
				}
			});
		},
		roomIn:function(){
			//把数据导入按钮变成上传
			layui.use('upload', function(){
			  var upload = layui.upload;
			  //执行实例
			  var uploadInst = upload.render({
			    elem: '#roomIn' //绑定元素
			    ,accept:'file'
			    ,url: 'ExcelRoomServlet?action=inRoom' //上传接口
			    ,done: function(res){
			    	//如果上传失败
                    if(res.code > 0){
                      return layer.msg('上传失败');
                    }else{
                    	 layer.msg(res.msg);
                    	 location.reload();
                    }
			    }
			    ,error: function(){
			      //请求异常回调
			    }
			  });
			});
		},
		empOutIn:function(){
			$("#empOut").click(function(){
				if(confirm("是否确定导出员工信息表")){
					window.location.href="ExcelEmplyoeeServlet?action=outEmp";
				}
			});
			//把数据导入按钮变成上传
			layui.use('upload', function(){
			  var upload = layui.upload;
			  //执行实例
			  var uploadInst = upload.render({
			    elem: '#empIn' //绑定元素
			    ,accept:'file'
			    ,url: 'ExcelEmplyoeeServlet?action=inEmp' //上传接口
			    ,done: function(res){
			    	//如果上传失败
                    if(res.code > 0){
                      return layer.msg('上传失败');
                    }else{
                    	 layer.msg(res.msg);
                         location.reload();
                    }
			    }
			    ,error: function(){
			    }
			  });
			});
		},
		ctoOut:function(){
			$("#ctoOut").click(function(){
				if(confirm("是否导出客户信息？")){
					window.location.href="ExcelCustomerServlet";
				}
			});
		},
		empty:function(){
			$("input[name=empPwd]").val("");
			$("input[name=empPwdNew]").val("");
   			$("input[name=empPwdNew2]").val("");
		},
		upPwd:function(){
			layer.open({
				  type: 1, 
				  title:'修改密码',
				  content: $("form"),
				  area: ['450px', '300px'],
				  btn:['确定','关闭'],
				  yes: function(index, layero){
					 backMan.checkPwd();
					layer.close(index);
				  },
				  btn2: function(index, layero){
					  $("form").css("display","none");
					  backMan.empty();
					  layer.close(index);
				  },
				  cancel: function(){ 
					  $("form").css("display","none");
					  backMan.empty();
					  layer.close();
				  }
			});
				
		},
		checkPwd:function() {
			var pwd1 = document.getElementById("pwd1").value;
			var pwd2 = document.getElementById("pwd2").value;
			var pwd3 = document.getElementById("pwd3").value;
			if(!pwd1){
				alert("请输入原密码验证");
				return false;
			}
			if(!pwd2){
				alert("请输入密码");
				return false;
			}
			if(!pwd3){
				alert("请输入密码");
				return false;
			}
			if(pwd2!=pwd3){
				alert("两次输入的密码不正确！");
				return false;
			}
			$.get("UpdatePwd",{"pwd1":pwd1,"pwd3":pwd3},function(data){
				alert(data.msg);
				if(data.msg=="密码修改成功"){
					window.location.href="login.jsp"; 
				}
				$("form").css("display","none");
				backMan.empty();
			});

		},
		init:function(){
			backMan.roomOut();
			backMan.roomIn();
			backMan.empOutIn();
			backMan.ctoOut();
			$(".upPwd").click(function(){
				backMan.upPwd();
			});
		}
	}
	backMan.init();
</script>
</html>