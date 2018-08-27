<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.vo.*,com.dao.*,com.bo.*,com.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间预定</title>
<link rel="stylesheet" href="js/layUI/css/layui.css">
<style type="text/css">
body{
	margin:0;
	margin:0 auto;
}
*{
	position:relative;
	font-family:"微软雅黑";
}
#body{
	width:1000px;
	height:570px;
	background-color:#FFF;
	margin:0 auto;
	opacity:0.7;
}
#left{
	width:298px;
	height:500px;
	left:100px;
	top:20px;
	float:left;
	
	/*background-color:#ddd;*/
}
#left .text_input{
	width:180px;
	height:30px;
	left:15px;
	border:0;
	padding:0;
	font-size:16px;
	top:10px;
	float:left;
	
	/*background-color:#ffff33;*/
}
.left_input{
	width:250px;
	height:35px;
	margin: 0 auto 10px auto;
	
}
#left .text{
	width:70px;
	height:35px;
	float:left;
	line-height: 50px; /*设置line-height与父级元素的height相等*/
    text-align: left; /*设置文本水平居左*/
	font-size:15px;
}
.customerVIP{
	width:40px;
	left:210px;
	top:5px;
	position:absolute;
	float:left;
}
.customerVIP img{

	width:40px;
	height:40px;
}
#middle{
	width:2px;
	height:500px;
	top:20px;
	left:170px;
	float:left;
	
	background-color:#333;
} 
#right{
	width:505px;
	height:500px;
	top:30px;
	float: right;
	
	/*background-color:#ddd;*/
}
#right .table1,.table2{text-align:center; font-size: 12px;border:#95b8e7 1px solid;width:340px;height:30px;margin:0 auto;}
#right .table1{height:30px;}
#right .table2{font-weight:bold; height:430px;margin:0 auto;overflow:auto;}
#right table {border-collapse:collapse;}
#right table th{width:90px; height:28px; line-height:28px; background:#ACB3BF; border-bottom:#777E8C 2px solid;}
#right table td{width:90px; height:31px; line-height:28px; padding:0px; border-bottom:#ACB3BF 2px solid;}
#right table tr:{background:#f4f4f4;}
#right table tr:hover{background:#DDDEE3;}
#foot{
	width:700px;
	height:50px;
	top:30px;
	left:170px;
	float: left;
	
	/*background-color:#ddd;*/
}
#foot input{
	width:120px;
	height:35px;
	margin:0 50px;
	background-color:#fdadad;
}
</style>
</head>
<body>
<div id="body">
	<div id="left">
	<form action="addReserve" method="post">
	<div id="" class="left_input">	<!-- 客户姓名 -->
			<div class="text">VIP编号:</div>
			<input type="text" id="vip" class="text_input"  placeholder="VIP编号" maxlength=6/>
			<div class="customerVIP"><img alt="" src="img/vip.png"/></div>	<!-- 是否VIP,0不是，1是 -->
		</div>
		<div id="" class="left_input">	<!-- 客户姓名 -->
			<div class="text">姓&nbsp;&nbsp;&nbsp;名:</div>
			<input type="text" name="customerName" class="text_input"  placeholder="预定者的姓名" maxlength=5/>
		</div>
		<div id="" class="left_input">
			<div class="text">联系电话:</div>
			<input type="text" name="customerPhone" class="text_input" placeholder="客户的联系电话" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
		</div>	<!-- 客户电话 -->
		<div id="" class="left_input">
			<div class="text">房间类型:</div>
			<select class="text_input" name="typeName">
			  <option value ="单人房">单人房</option>
			  <option value ="双人房">双人房</option>
			  <option value ="豪华房">豪华房</option>
			  <option value ="商务套间">商务套间</option>
			</select>
		</div>	<!-- 房间类型 -->
		<div id="" class="left_input">
			<div class="text">房&nbsp;间&nbsp;号:</div>
			<input type="text" name="roomNum" class="text_input" placeholder="房间编号" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
		</div>	<!-- 房间号 -->
		<div id="" class="left_input">
			<div class="text">预定时间:</div>
			<input type="text" name="reserveTime" class="text_input" id="date" placeholder="预定时的时间"/>
		</div>	<!-- 预定时间 -->
		<div id="" class="left_input">
			<div class="text">备&nbsp;&nbsp;注：</div>
			<textarea name="reserveRemark" class="text_input" rows="4" cols="20" value="" placeholder="此处为备注！" style="margin: 0px; height: 140px; width: 230px; resize: none;"></textarea>
		</div>	<!-- 备注 -->
		</form>
	</div>
	
	<div id="middle"></div>
	<div id="right">
		<div class="table1">
		<table>
			<thead>
				<tr>
					<th>房间号</th>
					<th>房间类型</th>
					<th>价格(元)/天</th>
					<th>押金(元)</th>
				</tr>
			</thead>
		</table>
		</div>
		
		<div class="table2">
		<table>
			<tbody>
			</tbody>
		</table>
		</div>
	</div>
	<div id="foot">
		<input type="button" class="ok" value="确定" />
		<input type="button" class="check" value="入住" style="visibility:hidden;"/>
		<input type="button" class="cancel" value="关闭" style="background-color:#fff;"/>
	</div>
</div>
</body>
<script type="text/javascript" src="js/layUI/layui.all.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js" ></script>
<script>
	var roomReserve={
		useUI:function(){//ui组键加载,日期框
			layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  //执行一个laydate实例
			  laydate.render({
			    elem: '#date' //指定元素
			  });
			});
		},
		blind:function(){
			$.get("ReserveTool?action=getCheckIn",{},function(data){
				$("tbody").empty();
				$.each(data,function(index,obj){//加载可入住的房间
					var tr=$("<tr class='tr'/>").data("obj",obj);
					$("<td/>").html(obj.roomNum).appendTo(tr);
					$("<td/>").html(obj.typeName).appendTo(tr);
					$("<td/>").html(obj.typePrice).appendTo(tr);
					$("<td/>").html(obj.typeDeposit).appendTo(tr);
					$("tbody").append(tr);
				});
			});
		},
		getTypeNum:function(){//选中房间，获得房间编号，以及房间类型
			$("tbody").on("click",".tr",function(){
				var obj=$(this).data("obj");
				$("input[name=roomNum]").val(obj.roomNum);
				$("select[name=typeName]").val(obj.typeName);
			}) 
		},
		findVIP:function(){//该方法适用于查找VIP,当焦点离开时查找
			$("#vip").blur(function(){
				$.post("ReserveTool?action=getCustomer",{customerId:$("#vip").val()},function(data){
					$("input[name=customerName]").val(data.customerName);
					$("input[name=customerPhone]").val(data.customerPhone);
					if(data.msg)//当该客户不是VIP时弹出
						alert(data.msg);
				});
			});
		},
		operateReserve:function(){//房间预定和退订，以及入住
			if($(".ok").val()=="确定"){
				$(".ok").click(function(){
					if(confirm("是否预定？")){
						$.post("ReserveTool?action=addReserve",$("form").serialize(),function(data){
							if(data.msg){
								alert(data.msg);
								$("#vip").val("");
								$("input[name=customerName]").val("");
								$("input[name=customerPhone]").val("");
								$("input[name=reserveTime]").val("");
								$("input[name=reserveRemark]").val("");
							}
						});
					}
					//重新刷新可入住房间,延迟执行可入住房间加载，因为这是两个ajax,异步请求，不受先后顺序的影响
					setTimeout(function(){
						roomReserve.blind();
					},1000);
				});
			}else if($(".ok").val()=="退订"){
				$(".ok").click(function(){
					if(confirm("是否退订？")){
						$.get("ReserveTool?action=delReserve",{roomNum:$("input[name=roomNum]").val()},function(data){
							if(data.msg){
								alert(data.msg);
								$("#vip").val("");
								$("input[name=customerName]").val("");
								$("input[name=customerPhone]").val("");
								$("input[name=reserveTime]").val("");
								$("input[name=reserveRemark]").val("");
								window.location.href='roomInfo.html';
							}
						});
					}
				});
			}
			//预定的房间入住
			$("input[value='入住']").click(function(){
				$.post("CheckTool?action=vipCheck",{vipId:$("#vip").val(),roomNum:$("input[name=roomNum]").val(),remark:$("input[name=reserveRemark]").val()},function(data){
					alert(data.msg);
					window.location.href='roomInfo.html';
				});
			});	
			
		},
		cancel:function(){//页面跳转
			$("input[value='关闭']").click(function(){
				window.location.href='roomInfo.html';
			});
		},
		display:function(){//显示预定房间的信息，以及退订功能
			<%
			  	RoomReserveDao reserve_dao=new RoomReserveDao();
				RoomReserveBo reserve_bo=new RoomReserveBo();
				RoomDao room_dao=new RoomDao();
				RoomBo room_bo=new RoomBo();
				CustomerInfoDao cif_dao=new CustomerInfoDao();
				CustomerInfoBo cif_bo=new CustomerInfoBo();
				
				String roomNum=request.getParameter("roomNum");
				
				RoomReserve model=null;
				Room room=null;
				CustomerInfo cif=null;
				
				if(reserve_bo.findByNum(roomNum) && room_bo.findByNum(roomNum)){
					model=reserve_dao.findByNum(roomNum);
					room=room_dao.findByNum(roomNum);
					if(cif_bo.findByNamePhone(model.getCustomerName(), model.getCustomerPhone()))
						cif=cif_dao.findByNamePhone(model.getCustomerName(), model.getCustomerPhone());
					%>
					$("#vip").val("<%=cif.getCustomerId()%>");
					$("select[name=typeName]").val("<%=room.getTypeName() %>");
					$("input[name=roomNum]").val("<%=roomNum%>");
					$("input[name=customerName]").val("<%=model.getCustomerName()%>");
					$("input[name=customerPhone]").val("<%=model.getCustomerPhone()%>");
					$("#date").val("<%=model.getReserveTime().substring(0,10)%>");
					$("input[name=reserveRemark]").val("<%=model.getReserveRemark()==null?"":model.getReserveRemark()%>");
					<%
				}
			%>
			if(<%=roomNum!=null%>){
				$(".ok").val("退订");
				$(".check").css("visibility","visible");
			}
		},
		init:function(){
			roomReserve.useUI();
			roomReserve.blind();
			roomReserve.getTypeNum();
			roomReserve.findVIP();
			roomReserve.display();
			roomReserve.operateReserve();
			roomReserve.cancel();
		}
	};
	roomReserve.init();
</script>
</html>