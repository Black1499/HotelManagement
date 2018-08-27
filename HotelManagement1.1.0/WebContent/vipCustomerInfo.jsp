<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vip充值记录表</title>
<style type="text/css">
body{
	margin:0;
	background-color:#333;
	background-image: url("img/bgimage.jpg");
	background-repeat:no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	margin:0 auto;}
*{
	position:relative;
	font-family:"微软雅黑";
}
#divSearch{
	width: 350px;
	height: 40px;
	margin: 20px auto;
	top: 20px;
	/*left: 90px;*/
}
#divSearch input,span{
	width: 200px;
	height: 26px;
	line-height: 26px;
	font-size: 18px;
}
#divSearch button{
	width: 50px;
	height: 30px;
	background: #95B8E7;
	border-radius:20px 0px 20px 0px;
}
#divSearch button:hover{
	background: #FFDEAD;
}
#body{
	width:860px;
	height:660px;
	background-color:#FFF;
	margin:0 auto;
	opacity:0.7;
}
#tables{
	width: 780px;
    height: 550px;
    left: 50px;
    /*top: 30px;*/
	float:left;
	background-color:#ddd;
}
</style>
 <link rel="stylesheet" href="js/layUI/css/layui.css" media="all">
</head>
<body>
<div id="body">
	<div id="divSearch">
		<span>查询:&nbsp;&nbsp;&nbsp;</span>
		<input type="text" id="textSearch" placeholder="请输入查询客户的姓名"/>
		<button type="button" id="buttonSearch">查询</button>
	</div>
	<div id="tables">
		<table id="demo" lay-filter="table"></table>
	</div>
</div>
<script type="text/javascript" src="js/layUI/layui.all.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;
	  table.render({
	    elem: '#demo'
	    ,height: 500
	    ,url: 'VipConsump' //数据接口
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'customerId', title:'编号', width:80}
	      ,{field: 'customerName', title: '客户名', width:80}
	      ,{field: 'customerVIP', title: '是否VIP', width:80,
	    	  templet: function(d){
          		if(d.customerVIP == 0){
          			return "否";
          		}else{
          			return "是";
          		}
          	} 
	      } 
	      ,{field: 'vipRecord', title: '充值金额', width: 80}
	      ,{field: 'inTime', title: '充值时间', width: 150, sort: true}
	      ,{field: 'vipBlance', title: '余额', width: 80, sort: true}
	      ,{field: 'vipIntegral', title: '积分', width: 80}
	      ,{field: 'empName', title: '操作员', width: 135, sort: true}
	    ]]
	  });
	});
	$("#buttonSearch").click(function(){
		alert("尚未找到");
	});
	
	
	
	
</script>
</body>
</html>