<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import='java.util.*,com.vo.*,com.dao.*,com.bo.*'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间入住</title>
<style type="text/css">
body{
	margin:0;
	background-color:;
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
	left:5px;
	border:0;
	padding:0;
	font-size:16px;
	top:10px;
	float:left;
	background-color:#fff;
	
	/*background-color:#ffff33;*/
}
#left .text_radio{
	width:75px;
	height:35px;
	left:5px;
	border:0;
	padding:0;
	font-size:16px;
	top:16px;
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
    text-align: left; /*设置文本水平居中*/
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
	top:10px;
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
#right .table1,.table2{
	text-align:center;
	font-size: 12px;
	border:#95b8e7 1px solid;
	width:340px;
	height:30px;
	margin:0 auto;
}
#right .table1{
	height:30px;
}
#right .table2{
	font-weight:bold; 
	height:430px;
	margin:0 auto;
	overflow:auto;
}
#right table {
	border-collapse:collapse;
}
#right table th{
	width:90px; 
	height:28px; 
	line-height:28px;
	background:#ACB3BF;
	border-bottom:#777E8C 2px solid;
}
#right table td{
	width:90px;
	height:31px;
	line-height:28px;
	padding:0px; 
	border-bottom:#ACB3BF 2px solid;
}
#right table tr:{
	background:#f4f4f4;
}
#right table tr:hover{
	background:#DDDEE3;
}
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
	margin:0 40px;
	background-color:#fdadad;
}
</style>
</head>
<body>
		<%int state=Integer.parseInt(request.getParameter("state")); %>
<div id="body">
	<div id="left">
		<form method="" action="">
		<div id="" class="left_input">
			<div class="text">姓&nbsp;&nbsp;名:</div>
			<input type="text" name="customerName" class="text_input" value="" placeholder="入住者的姓名" maxlength=5/>
<!-- 			<div class="customerVIP"><img src="img\vip.png"/></div>	是否VIP,0不是，1是 -->
		</div>
		<div id="" class="left_input">
			<div class="text">证件号码:</div>
			<input type="text" name="customerIdNum" class="text_input" value="" placeholder="证件号码" maxlength=18/>
		</div>	<!-- 证件号码 -->
		<div id="" class="left_input">
			<div class="text">联系电话:</div>
			<input type="text" name="customerPhone" class="text_input" value="" placeholder="客户的联系电话" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
		</div>	<!-- 客户电话 -->
		<div id="" class="left_input">
			<div class="text">房间类型:</div>
			<select class="text_input" name="typeName">
			  <option value ="单人房">单人房</option>
			  <option value ="双人房">双人房</option>
			  <option value ="豪华房">豪华房</option>
			  <option value ="商务套间">商务间</option>
			</select>
		</div>	<!-- 房间类型 -->
		<div id="" class="left_input">
			<div class="text">房&nbsp;间&nbsp;号:</div>
			<input type='text' name="roomNum" class="text_input" id="roomNum" value="" maxlength=4/>
		</div>	<!-- 房间号 -->
		<div id="" class="left_input">
			<div class="text">钟点房:</div>
			<div class="text_radio"><input type="radio" name="checkHour" value="1" />是</div>
			<div class="text_radio"><input type="radio" name="checkHour" value="0" checked/>否</div>
		</div>	<!-- 是否钟点房 -->
		<div id="" class="left_input">
			<div class="text">入住时间:</div>
			<input type="text" name="checkTime" id="nowTime" class="text_input" value="" placeholder="入住的时间" readonly/>
		</div>	<!-- 入住时间 -->
		<div id="" class="left_input">
			<div class="text">备&nbsp;&nbsp;注：</div>
			<textarea name="checkRemark" class="text_input" rows="4" cols="16" value="" placeholder="此处为备注！" style="margin: 0px; height: 120px; width: 230px; resize: none;"></textarea>
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
		<table id="table2">
			<tbody>
			</tbody>
		</table>
		</div>
	</div>
	<div id="foot">
		<%if(state==0){%>		
			<input type="button" value="入住"/>
			<input type="button" value="" style="visibility:hidden;"/>
			<input type="button" value="关闭" style="background-color:#fff"/>
		<%}else if(state==1){%>
			<input type="button" value="修改"/>
			<input type="button" value="结账" style="background-color:#fff"/>
			<input type="button" value="关闭" style="background-color:#fff"/>
		<%} %>
	</div>
</div>
</body>
<script type="text/javascript" src="js/layUI/layui.all.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js" ></script>
<script>
	var roomCheck={
		nowTime:function(){//获得当前日期
			var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
		    var nowTime = date.getHours() + seperator2 + date.getMinutes();
		    
		   	$("#nowTime").val(currentdate+" "+nowTime);
		},
		blind:function(){//表格渲染
			$.post("ReserveTool?action=getCheckIn",{},function(data){
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
		judge:function(){//数据验证
			var num=$("input[name=customerIdNum]").val();
			var reg=/^[\d]{17}[\dXx]{1}$/;//正则表达式
			//411227199701054031
			if(reg.test(num)){
				var areaCode=num.substring(0,6);
				var year=num.substring(6,10);
				var month=num.substring(10,12);
				var data=num.substring(12,14);
				//判断出生日期是否输入正确
				if(month=="01" || month=="03" || month=="05" || month=="07" || month=="08" || month=="10" || month=="31"){
					if(data>31){
						alert("身份证号出生日期输入错误");
						return false;
					}
				}else if(month="02"){
					if((year%400==0 && year%100==0) || (year%100!=0 && year%4==0)){
						if(data>29){
							alert("身份证号出生日期输入错误");
							return false;
						}
					}else{
						if(data>28){
							alert("身份证号出生日期输入错误");
							return false;
						}
					}
				}else if(moth="04" || month=="06" || month=="09" || month=="11"){
					if(data>30){
						alert("身份证号出生日期输入错误");
						return false;
					}
						
				}
				//验证身份证号是否输入正确
				var weightingFactor=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];//加权因子
				var checkCode=[1,0,'X',9,8,7,6,5,4,3,2];
				var sum=0;
				for(var i=0;i<17;i++){
					sum+=weightingFactor[i]*parseInt(num.substring(i,i+1));
				}
				var last=sum%11;
				if(num.substring(17,18)!=checkCode[last]){
					alert("该身份证号不存在！");
					return false;
				}
			}else{
				alert("身份证号输入错误");
				return false;
			}
			return true;
		},
		check:function(){//房间入住，包括空房间入住，以及预定的房间房间
			roomCheck.blind();
			roomCheck.nowTime();
			$("input[name=customerIdNum]").blur(function(){
				roomCheck.judge();
			});
			$("input[value=入住]").click(function(){
				$.post("CheckTool?action=addCheck",$("form").serialize(),function(data){
					if(data.msg=="入住成功！"){
						$("input[name=customerName]").val("");
						$("input[name=customerPhone]").val("");
						$("input[name=customerIdNum]").val("");
						$("input[name=roomNum]").val("");
					}
					alert(data.msg);
					if(confirm("是否继续入住？")==false){
						window.location.href="roomInfo.jsp";
					}
					//重新刷新可入住房间,延迟执行可入住房间加载，因为这是两个ajax,异步请求，不受先后顺序的影响
					setTimeout(function(){
						roomCheck.blind();
					},1000);
				});
			});
		},
		display:function(){//显示入住信息
			<%
		  	RoomCheckDao check_dao=new RoomCheckDao();
			RoomCheckBo check_bo=new RoomCheckBo();
			RoomDao room_dao=new RoomDao();
			RoomBo room_bo=new RoomBo();
			CustomerInfoDao cif_dao=new CustomerInfoDao();
			CustomerInfoBo cif_bo=new CustomerInfoBo();
			
			String roomNum=request.getParameter("roomNum");
			RoomCheck model=null;
			Room room=null;
			CustomerInfo cif=null;
			
			if(check_bo.findByNum(roomNum) && room_bo.findByNum(roomNum)){
				model=check_dao.findByNum(roomNum);
				room=room_dao.findByNum(roomNum);
				if(cif_bo.findByNamePhone(model.getCustomerName(), model.getCustomerPhone()))
					cif=cif_dao.findByNamePhone(model.getCustomerName(), model.getCustomerPhone());
				%>
				$("select[name=typeName]").val("<%=room.getTypeName() %>");
				$("input[name=roomNum]").val("<%=roomNum%>");
				$("input[name=customerName]").val("<%=model.getCustomerName()%>");
				$("input[name=customerPhone]").val("<%=model.getCustomerPhone()%>");
				$("input[name=customerIdNum]").val("<%=model.getCustomerIdNum()%>");
				$("#nowTime").val("<%=model.getCheckTime().substring(0,16) %>");
				$("input[name=reserveRemark]").val("<%=model.getCheckRemark()==null?"":model.getCheckRemark()%>");
				<%
			}
		%>
		},
		update:function(){//已经入住的房间修改
			<%
				if(check_bo.findByNum(roomNum))
					model=check_dao.findByNum(roomNum);
				//当传入的值为state1时为修改界面
			if(state==1){
			%>
			$("input[value='修改']").click(function(){
				if($("input[name=roomNum]").val()!=<%=roomNum %>){
					roomCheck.nowTime();
					if(confirm("是否清理原房间")){
						$.post("CheckTool?action=updCheck&initNum="+<%=roomNum%>+"&checkId="+<%=model.getCheckId() %>,$("form").serialize(),function(data){
							alert(data.msg);
						});
					}else{
						$.post("CheckTool?action=updCheck&initsNum="+<%=roomNum%>+"&checkId="+<%=model.getCheckId() %>,$("form").serialize(),function(data){ 
		 					alert(data.msg);
		 				});
					}
				}else{
					//房间号不变，直接修改信息
	 				$.post("CheckTool?action=updCheck&checkId="+<%=model.getCheckId() %>,$("form").serialize(),function(data){ 
	 					alert(data.msg);
	 				});
				}
			});
			<%}%>
		},
		getCustomer:function(){
			$("input[name=customerName]").blur(function(){
				$.post("CheckTool?action=getCut",{name:$("input[name=customerName]").val()},function(data){
					$("input[name=customerPhone]").val(data.customerPhone);
					$("input[name=customerIdNum]").val(data.customerIdNum);
				})
			});
		},
		cancel:function(){//回到主界面
			$("input[value='关闭']").click(function(){
				window.location.href="roomInfo.jsp";
			});
		},
		jumpOut:function(){//跳转到结账界面
			$("input[value='结账']").click(function(){
				window.location.href="checkOut.jsp?roomNum="+$("input[name=roomNum]").val();
			});
		},
		init:function(){//初始化
			roomCheck.blind();
			roomCheck.getTypeNum();
			roomCheck.check();
			roomCheck.display();
			roomCheck.cancel();
			roomCheck.update();
			roomCheck.jumpOut();
			roomCheck.getCustomer();
		}
	};
	roomCheck.init();
</script>
</html>