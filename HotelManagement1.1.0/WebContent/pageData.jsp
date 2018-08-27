<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="js/layUI/css/layui.css" media="all">
<style>
	body{
		width:1000px;
		height:600px;
	}
</style>
<title>数据统计</title>
</head>
<body>
	<div class="layui-tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">每月营收额</li>
	    <li>季度营收额</li>
	    <li>房间入住率</li>
	  </ul>
	  <div class="layui-tab-content">
	    <div class="layui-tab-item layui-show"><jsp:include page="monthData.jsp" /> </div>
	    <div class="layui-tab-item"><jsp:include page="quarterData.jsp" /></div>
	    <div class="layui-tab-item"><jsp:include page="roomData.jsp" /></div>
	  </div>
	</div>
</body>
<script type="text/javascript" src="js/layUI/layui.all.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script>
	//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
	  var element = layui.element;
	  
	  //…
	});
</script>
</html>