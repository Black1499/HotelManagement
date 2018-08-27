<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="com.vo.CustomerInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人设置</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="js/suimobile/css/sm.min.css" />
<link rel="stylesheet" href="js/suimobile/css/sm-extend.min.css" />
<script type='text/javascript' src='js/suimobile/js/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm-extend.min.js' charset='utf-8'></script>
</head>
<body>

<header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back" href="suimobileIndex.jsp">
      <span class="icon icon-left"></span>
     	 返回
    </a>
  <h1 class='title'>个人设置</h1>
</header>
<div class="content">
  <div class="list-block">
    <ul>
      <li class="item-content">
        <div class="item-media"><i class="icon icon-f7"></i></div>
        <div class="item-inner">
          <div class="item-title">用户编号</div>
          <div class="item-after"><%=cusVo.getCustomerId() %></div>
        </div>
      </li>
      <li class="item-content">
        <div class="item-media"><i class="icon icon-f7"></i></div>
        <div class="item-inner">
          <div class="item-title">我的名字</div>
          <div class="item-after myname" id="myname"><%=cusVo.getCustomerName() %></div>
        </div>
      </li>
      <li class="item-content">
        <div class="item-media"><i class="icon icon-f7"></i></div>
        <div class="item-inner">
          <div class="item-title">手机号码</div>
          <div class="item-after upphone"><%=cusVo.getCustomerPhone() %></div>
        </div>
      </li>
      <li class="item-content">
        <div class="item-media"><i class="icon icon-f7"></i></div>
        <div class="item-inner">
          <div class="item-title">性别</div>
          <div class="item-after">女</div>
        </div>
      </li>
      <li class="item-content">
        <div class="item-media"><i class="icon icon-f7"></i></div>
        <div class="item-inner">
          <div class="item-title">修改密码</div>
          <div class="item-after uppwds">...</div>
        </div>
      </li>
    </ul>
  </div>
 </div>
 <script type="text/javascript">
 //修改电话的弹窗
 $(document).on('click','.upphone', function () {
     $.prompt('请输入新的手机号码', function (value) {
         $.alert('你的新手机号码为"' + value + '"');
     });
 });
 //修改密码的弹窗
 $(document).on('click','.uppwds', function () {
     $.prompt('请输入原密码', function (value) {
         //value为当前框输入的值
         if(value != "1234"){
        	 $.alert("密码输入错误");
         }else{
        	 $.prompt('请输入新密码', function (value) {
        		 $.alert('修改成功！你的新密码为"' + value + '"');
        	 });
         }
     });
 });
 $(document).on('click','.myname',function(){
	 
	$.prompt("请输入的你名字",function(value){
		$("#myname").val(value);
		$.alert("修改成功。")
	}) ;
 });
 </script>
</body>
</html>