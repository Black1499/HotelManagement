<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vo.EmployeeInfoVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六月酒店</title>
<link type="text/css" rel="stylesheet" href="css/menu.css"> <!--模块-->
<style type="text/css">
body{
	margin: 0px;
	background-color:#DDDEE3;
	background-image: url("img/bgimage.jpg");
	/*background-repeat:no-repeat;*/
	background-size:100% 100%;-moz-background-size:100% 100%;
	}
*{
	position:relative;
}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
a:hover{
 text-decoration:underline;  /*鼠标放上去有下划线*/
}
#body{
	width:1366px;
	height:838px;
	
	margin:0 auto;
}
#left{
	width:252px;
	height:738px;
	float:left;
	
	position:relation;
	background-color:#fff;
	/*opacity:0.4;*/
	
	
	background:transparent;
	color:#fff;
	background-color:rgba(28,49,78,0.35);
}
.menus{
	height:632px;
	overflow:auto;
	
}
.logo{
	color:#fff;
	opacity: 0.7;
	
}
.logo img{
	width:250px;
	height:100px;
}
#right{
	width:1000px;
	height:660px;
	float:right;
	
	top:39px;
	left:-50px;
	background-color:#fff;
	/*opacity:0.7;*/
	background:transparent;
	color:#fff;
	background-color:rgba(28,49,78,0.35);
}
#loginName{
	width:70px;
	height:30px;
	top: -40px;
    left: 60px;
	float:left;
	padding:0 5px;
}
#loginName{
	width:140px;
	height:30px;
	
	top: 40px;
    left: 60px;
	float:left;
	padding:0 5px;
}
#loginName a{
	font-size:20px;
	color:#333;
	font-weight:bold;
}

</style>
<script type="text/javascript">
var itemHeight = 35;
var dividerHeight = 1;

function openMenu(obj) {
    menuTitleId = obj.id;
    menuId = "menu" + menuTitleId.substring(10);
    indicatorId = "indicator" + menuTitleId.substring(10);

    menu = document.getElementById(menuId);
    indicator = document.getElementById(indicatorId);
    height = menu.style.height;

    if (height == "0px" || height == "") {
        childAmount = menu.getElementsByTagName('div').length;
        dividerAmount = menu.getElementsByTagName('li').length;
        height = childAmount * itemHeight + dividerAmount * dividerHeight;
        menu.style.height = height + "px";
        indicator.style.transform = "rotate(90deg)";
    } else {
        menu.style.height = "0px";
        indicator.style.transform = "rotate(0deg)";
    }
}
</script>
</head>
<body>
<div id="body">
	<div id="left">
	<div class="logo"><img alt="" src="img/logo_六月酒店(2).png"></div>
		<div class="menus">
		    <div id="menu_title1" class="menu_title" onclick="openMenu(this)">
		      	  快捷操作
		        <div class="indicator" id="indicator1">✈</div>
		    </div>
		    <div class="menu" id="menu1">
		        <div class="item">
		            <a href="roomReserve.jsp" target="MenuGo">VIP预定</a>
		        </div>
		      
		        <div class="item">
		            <a href="vipCutAdd.jsp" target="MenuGo">VIP办理</a>
		        </div>
		      
		        <div class="item">
		            <a href="roomCheck.jsp?state=0" target="MenuGo">客户入住</a>
		        </div>
		    </div>
		
		  
		
		    <div id="menu_title2" class="menu_title" onclick="openMenu(this)">
		        	客房管理
		        <div class="indicator" id="indicator2">▶</div>
		    </div>
		    <div class="menu" id="menu2">
		        <div class="item">
		            <a href="roomInfo.jsp" target="MenuGo">客房一览</a>
		        </div>
		      
		        <div class="item">
		            <a href="cutRadio.jsp" target="MenuGo" >客户信息</a>
		        </div>
		 
		        <div class="item">
		            <a href="checkOut.jsp" target="MenuGo">客房结账</a>
		        </div>
		    </div>
		
		   
		
		    <div id="menu_title3" class="menu_title" onclick="openMenu(this)">
		        VIP操作
		        <div class="indicator" id="indicator3">▶</div>
		    </div>
		    <div class="menu" id="menu3">
		        <div class="item">
		            <a href="vipMoneyAdd.jsp" target="MenuGo">VIP充值</a>
		        </div>
		        
		        <div class="item">
		            <a href="vipCutAdd.jsp" target="MenuGo" >VIP办理</a>
		        </div>
		     
		        <div class="item">
		            <a href="vipCustomerInfo.jsp" target="MenuGo">VIP充值记录</a>
		        </div>
		        
		        <div class="item">
		            <a href="#">VIP积分消费</a>
		        </div>
		    </div>
		    
		    
		    <div id="menu_title4" class="menu_title" onclick="openMenu(this)">
		       	我的操作
		        <div class="indicator" id="indicator4">▶</div>
		    </div>
		    <div class="menu" id="menu4">
		       	<div class="item">
		            <a href="empById.jsp" target="MenuGo">我的信息</a>
		        </div>
		        
		        <div class="item">
		            <a href="ExctControl" target="" >退出登录</a>
		        </div>
		     
		    </div>
 
		</div>
	</div>
		<%
			EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo");
			if(empVo == null){//为空，客户没有登陆想打开主界面
				response.sendRedirect("login.js");//重定向到登陆界面
			}
		%>
		<div id="loginName"><a href="empById.jsp" target="MenuGo"><%=empVo.getEmpName() %></a>|<a href="ExctControl">注销</a></div>
		
	<iframe id="right" name="MenuGo" src="roomInfo.jsp"></iframe>
</body>
</html>