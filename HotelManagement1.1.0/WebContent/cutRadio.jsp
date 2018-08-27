<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息查看</title>
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
		font-size: 16px;
	}
	#body{
		width:860px;
		height:660px;
		
		background-color:#FFF;
		margin:0 auto;
		opacity:0.7;
	}
	#table{
		width: 730px;
	    height: 600px;
	    left: 50px;
	    top: 20px;
		float:left;
		
		background-color:#ddd;
	}
	#table .table1,.table2{text-align:center; font-size: 12px;border:#95b8e7 1px solid;width:730px}
	#table .table1{height:40px;}
	#table .table2{font-weight:bold; height:530px;overflow-x:hidden;}
	#table table {border-collapse:collapse;}
	#table table th{width:130px; height:38px; line-height:38px; background:#DDDEE3; border-bottom:#777E8C 2px solid;font-size: 14px;}
	#table table .idNum1{width:190px;}
	#table table td{width:138px; height:41px; line-height:38px; padding:0px; border-bottom:#ACB3BF 2px solid;}
	#table table tr:{background:#f4f4f4;}
	#table table tr:hover{background:#DDDFFF;}
</style>
</head>
<body>
	<div id="body">
	<div id="table">
		<div id="hereunder_table">
			<span>
				<input type="radio" class="cus" name="cus" id="cusAll" checked />全部
				<input type="radio" class="cus" name="cus" id="cusOrdinary" />普通
				<input type="radio" class="cus" name="cus" id="cusVIP" />VIP
			</span>
			<span>
				<input type="text" id="cusText" placeholder="查询" style="margin: 10px 0 10px 336px;" />
				<button type="button" id="btnChick">查询</button>
			</span>
		<div class="table1">
		<table>
			<thead>
				<tr>
					<th>客户名字</th>
					<th>客户电话</th>
					<th class="idNum1">客户身份证号码</th>
					<th>是否VIP</th>
					<th>入住次数</th>
					<th>备注</th>
				</tr>
			</thead>
		</table>
		</div>
		<div class="table2">
			<table id="userTable">
				<tbody>
				
				</tbody>
			</table>
		</div>
	</div>
	</div>

<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script>
<script>
	var cus = {
		//查看所有的客户
		list:function(list){
			$.ajax({
		        type: "get", //请求类型
		        url: "CitAllList", //路径
		        success: function(data) { //请求成功后的事件
					$("#userTable tr").remove();  //移除表格中除第一行以外的数据
		            $.each(data, function(i, obj) {   
		                var tr = $("<tr/>");
		                $("<td/>").html(obj.customerName).appendTo(tr);
		                $("<td/>").html(obj.customerPhone).appendTo(tr);
		                $("<td/>").html(obj.customerIdNum).appendTo(tr);
		                //是否VIP
		                if(obj.customerVIP == 0){
		                	 $("<td/>").html("<input type='checkbox' name='vip'>").appendTo(tr);
		                }else{
		                	 $("<td/>").html("<input type='checkbox' name='vip' checked>").appendTo(tr);
		                }
		                $("<td/>").html(obj.customerCount).appendTo(tr);
		                $("<td/>").html(obj.customerRemark).appendTo(tr);
		                $("#userTable").append(tr);
		            });
		        },
		        complete: function() { //请求完成时的事件，不论成功或失败
		            //$("#loading").hide(500);
		        },
		        error: function(xhr, textStatus, errorThrown) { //错误时
		            alert(xhr + textStatus + errorThrown);
		        }
		   });
		},
		//查看所有的普通用户
		cusOrdinary:function(){
			$.ajax({
		        type: "get", //请求类型
		        url: "citByTomer", //路径
		        success: function(data) { //请求成功后的事件
					$("#userTable tr").remove();  //移除表格中除第一行以外的数据
		            $.each(data, function(i, obj) {   
		                var tr = $("<tr/>");
		                $("<td/>").html(obj.customerName).appendTo(tr);
		                $("<td/>").html(obj.customerPhone).appendTo(tr);
		                $("<td/>").html(obj.customerIdNum).appendTo(tr);
		                //是否VIP
		                if(obj.customerVIP == 0){
		                	 $("<td/>").html("<input type='checkbox' name='vip'>").appendTo(tr);
		                }else{
		                	 $("<td/>").html("<input type='checkbox' name='vip' checked>").appendTo(tr);
		                }
		                $("<td/>").html(obj.customerCount).appendTo(tr);
		                $("<td/>").html(obj.customerRemark).appendTo(tr);
		                $("#userTable").append(tr);
		            });
		        },
		        complete: function() { //请求完成时的事件，不论成功或失败
		            //$("#loading").hide(500);
		        },
		        error: function(xhr, textStatus, errorThrown) { //错误时
		            alert(xhr + textStatus + errorThrown);
		        }
		   });
		},
		//查看所有的VIP客户
		cusVIP:function(){
			$.ajax({
		        type: "get", //请求类型
		        url: "CitByVIP", //路径
		        success: function(data) { //请求成功后的事件
					$("#userTable tr").remove();  //移除表格中除第一行以外的数据
		            $.each(data, function(i, obj) {   
		                var tr = $("<tr/>");
		                $("<td/>").html(obj.customerName).appendTo(tr);
		                $("<td/>").html(obj.customerPhone).appendTo(tr);
		                $("<td/>").html(obj.customerIdNum).appendTo(tr);
		                //是否VIP
		                if(obj.customerVIP == 0){
		                	 $("<td/>").html("<input type='checkbox' name='vip'>").appendTo(tr);
		                }else{
		                	 $("<td/>").html("<input type='checkbox' name='vip' checked>").appendTo(tr);
		                }
		                $("<td/>").html(obj.customerCount).appendTo(tr);
		                $("<td/>").html(obj.customerRemark).appendTo(tr);
		                $("#userTable").append(tr);
		            });
		        },
		        complete: function() { //请求完成时的事件，不论成功或失败
		            //$("#loading").hide(500);
		        },
		        error: function(xhr, textStatus, errorThrown) { //错误时
		            alert(xhr + textStatus + errorThrown);
		        }
		   });
		}
	};
	cus.list();
	
	$("#cusAll").click(function(){
		cus.list();
	});
	$("#cusOrdinary").click(function(){
		cus.cusOrdinary();
	});
	$("#cusVIP").click(function(){
		cus.cusVIP();
	});
	$("#btnChick").click(function(){
		if(!$("#cusText").val()){
			return;
		}
		$.ajax({
	        type: "get", //请求类型
	        url: "CitById", //路径
	        data:{"cutName":$("#cusText").val()},  //发送给后台的数据
	        success: function(data) { //请求成功后的事件
				$("#userTable tr").remove();  //移除表格中除第一行以外的数据
	            $.each(data, function(i, obj) {   
	                var tr = $("<tr/>");
	                $("<td/>").html(obj.customerName).appendTo(tr);
	                $("<td/>").html(obj.customerPhone).appendTo(tr);
	                $("<td/>").html(obj.customerIdNum).appendTo(tr);
	                //是否VIP
	                if(obj.customerVIP == 0){
	                	 $("<td/>").html("<input type='checkbox' name='vip'>").appendTo(tr);
	                }else{
	                	 $("<td/>").html("<input type='checkbox' name='vip' checked>").appendTo(tr);
	                }
	                $("<td/>").html(obj.customerCount).appendTo(tr);
	                $("<td/>").html(obj.customerRemark).appendTo(tr);
	                $("#userTable").append(tr);
	            });
	        },
	        complete: function() { //请求完成时的事件，不论成功或失败
	            //$("#loading").hide(500);
	        },
	        error: function(xhr, textStatus, errorThrown) { //错误时
	            alert(xhr + textStatus + errorThrown);
	        }
	   });
	});
</script>	
</body>
</html>