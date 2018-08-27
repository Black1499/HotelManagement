<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户结账</title>
<style>
* {
	position: relative;
	font-family: "微软雅黑";
}

.div_body {
	width: 1070px;
	height: 550px;
	margin: auto;
	background-color: #e8eff6;
}

.div_top {
	width: 700px;
	height: 130px;
	margin: 0px auto;
	left: 20px;
	padding: 10px;
}

.div_body_Logo {
	width: 640px;
	height: 130px;
	/*background-color:	#FFDAB9;*/
	float: left;
	left: 10px;
}

.div_Logo {
	width: 300px;
	height: 130px;
	background-image: url("img/bigLogo.png");
	float: left;
}

.div_span_body {
	width: 300px;
	height: 120px;
	float: left;
	padding: 5px;
	top:20px;
}

.div_span1 {
	width: 200px;
	
	height: 30px;
	margin: auto;
	text-align: center;
	top: 10px;
	font-size: 25px;
	line-height: 30px;
}

.div_span2 {
	width: 250px;
	height: 45px;
	margin: auto;
	text-align: center;
	top: 25px;
	font-size: 35px;
	line-height: 45px;
}
.title_body1{
	width:900px;
	height:30px;
	border:1px solid gray;
	margin:auto;
	top:10px;
	
}
.h4{
	left:320px;
}
.div_body1{
	width:700px;
	height:120px;
	margin: auto;
	top:10px;	
}
.div_body2_1{
	width:330px;
	height:130px;
	top:-20px;
	float:left;
	text-align:center;
}
.div_body2_2{
	width:330px;
	height:130px;
	top:-20px;
	float:right;
	
	text-align:center;
}
.div_body2_3{
	height:100px;
	border:1px solid gray;
	float:left;
	left:20px;
	top:-15px;
}
.div_name1{
	top:5px;
}
.div_name2{
	top:10px;
}
.div_name3{
	top:15px;
}
.div_name4{
	top:10px;
}
.div_name5{
	top:18px;
	float: left;
	
}

.div_sex1{
	top:5px;
}
.div_sex2{
	top:10px;
}
.div_sex3{
	top:15px;
}
.div_sex4{
	top:20px;
}
div span{
	float: left;
}
div input{
	width:245px;
	float:left;
}
.div_h4{
	width:100px;
	height:30px;
	left:152px;
	line-height:15px;
	top:10px;
	font-size:18px;
}
.div_h5{
	width:100px;
	height:30px;
	top:6px;
	
	font-size:18px;
}
.div_body2{
	width:700px;
	height:100px;
	
	margin:auto;
	top:20px;
	
	float:left;
	text-align:center;
}
.div_text1{
	width:230px;
	height:30px;
	border:1px soild red;
	top:20px;
	float:left;
	
}
.input_text1{
	width:130px;
}

.div_text2{
	width:230px;
	height:30px;
	border:1px soild red;
	top:20px;
	float:left;
	left:20px;
}
.input_text2{
	width:130px;
}

.div_text4{
	width:230px;
	height:30px;
	border:1px soild red;
	top:20px;
	float:left;
	
}
.input_text4{
	width:130px;
	
}

.div_text6{
	width:230px;
	height:30px;
	border:1px soild red;
	top:28px;
	float:left;
	
}
.input_text6{
	width:130px;
	
}
.div_text3{
	width:230px;
	height:30px;
	border:1px soild red;
	top:28px;
	float:left;
	
}
.input_text3{
	width:130px;
}
.button1{
	width:120px;
	height:30px;
	top:28px;
	float:right;
	right:40px;
}
.input_radio1{
	width:26px;
	height:15px;
}
.input_radio2{
	width:26px;
	height:15px;
}

.input_text5{
	left:14px;
}
.div_radio1{
	width: 15px;
	height: 15px;
}
.div_name4 span{
	position:relative;
	float:left;
	top:20px;
	left:-180px;
}
.div_name4 input{
	position:relative;
	float:left;
	top:0px;
	left:80px;
}
#remark{
	width:240px;
	height:50px;
}
</style>
</head>
<body>
	<div class="div_body">
		<div class="div_top">
			<div class="div_body_Logo">
				<div class="div_Logo"></div>
				<div class="div_span_body">
					<div class="div_span1">GUEST BILL</div>
					<div class="div_span2">宾 客 账 单</div>
				</div>
			</div>
		</div>
		<div class="div_h4">
			<h4>客户信息:</h4>
		</div>
		<div class="div_body1">
			<div class="div_body2_1">
			<form>
				<div>
					<span>房间编号：</span>
					<input type="text" name="roomNum">
				</div>
				
				<div class="div_name1">
					<span>客户姓名：</span>
					<input type="text" name="customerName" disabled>
				</div>
				
				<div class="div_name2">
					<span>联系方式：</span>
					<input type="text"  name="customerPhone" disabled>
				</div>
				
				<div class="div_name3">
					<span>身份证号：</span>
					<input type="text" name="customerIdNum" disabled>
				</div>
				
				
			</div>
			<div class="div_body2_3"></div>
			<div class="div_body2_2">
				<div>
					<span>离开时间：</span>
					<input type="text" name="outTime" id="nowTime" disabled>
				</div>
				
				<div class="div_sex1">
					<span class="span1">是否钟点：</span>
					<input type="radio" name="checkHour" value="0" class="input_radio1"/><span>否</span>
					<input type="radio" name="checkHour" value="1" class="input_radio2"/><span>是</span>
				</div>
				
				<div class="div_name4">
					<span >入住时间：</span>
					<input type="text" name="checkTime" disabled>
				</div>
				
				<div class="div_name5">
					<span class="span2">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
					<textarea id="remark"></textarea>
				</div>
			</div>
			<div class="div_h5">
				<h4>结账明细：</h4>
			</div>
			<div class="div_body2">
				<div class="div_text1">
					<span>实收金额：</span>
					<input type="text" class="input_text1" name="recive">
				</div>
				<div class="div_text4">
					<span>应收金额：</span>
					<input type="text" class="input_text4" name="recivable">
				</div>
				<div class="div_text2">
					<span>折扣：</span>
					<input type="text" class="input_text2" name="discount">
				</div>
				<div class="div_text3">
					<span>折后金额：</span>
					<input type="text" class="input_text3" name="actual">
				</div>
				 
				<div class="div_text6">
					<span>找零金额：</span>
					<input type="text" class="input_text6" name="surplus">
				</div>
				<input type="button" class="button1" value="提交"/>
				</form>
			</div>
		</div> 
	</div>
</body>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script>
	<%
		String roomNum=request.getParameter("roomNum");
 	%> 
	var checkOut={
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
		dataByNum:function(){//文本框数据加载
			checkOut.nowTime();
			$("input[name=roomNum]").val("<%=roomNum%>");
			$.get("CheckOutTool?action=getData",{roomNum:<%=roomNum%>},function(data){
				$("input[name=customerName]").val(data.customerName);
				$("input[name=customerPhone]").val(data.customerPhone);
				$("input[name=customerIdNum]").val(data.customerIdNum);
				$("input[name=checkTime]").val(data.checkTime.substring(0,16));
				if(data.checkHout==1)
					$("input[value=1]").prop("checked","checked");
				else
					$("input[value=0]").prop("checked","checked");
				
				$("#remark").val(data.remark);
				$("input[name=recivable]").val(data.recivable);
			});
			$("input[name=recive]").focus();
		},
		blurNum:function(){
			checkOut.nowTime();
			$("input[name=roomNum]").blur(function(){
				$.get("CheckOutTool?action=getData",{roomNum:$("input[name=roomNum]").val()},function(data){
					$("input[name=customerName]").val(data.customerName);
					$("input[name=customerPhone]").val(data.customerPhone);
					$("input[name=customerIdNum]").val(data.customerIdNum);
					$("input[name=checkTime]").val(data.checkTime.substring(0,16));
					if(data.checkHout==1)
						$("input[value=1]").prop("checked","checked");
					else
						$("input[value=0]").prop("checked","checked");
					
					$("#remark").val(data.remark);
					$("input[name=recivable]").val(data.recivable);
				});
				$("input[name=recive]").focus();
			});
		},
		getActual:function(){//计算实收金额
			$("input[name=discount]").blur(function(){
				var discount=$("input[name=discount]").val();
				if($("input[name=discount]").val()==""){
					var price=$("input[name=recivable]").val();
					$("input[name=actual]").val(price);
				}else{
					var price=discount*$("input[name=recivable]").val()*0.1;
					$("input[name=actual]").val(price);
				}
				var money=$("input[name=recive]").val()-$("input[name=actual]").val();
				$("input[name=surplus]").val(money);
			});
		},
		outSub:function(){//提交，结账，添加内容
			$("input[value=提交]").click(function(){
				if($("input[name=discount]").val()==""){
					$("input[name=discount]").val("10");
				}
				$.post("CheckOutTool?action=updOut",$("form").serialize(),function(data){
					alert(data.msg);
					if(confirm("是否留在该页面")==false){
						window.location.href="roomInfo.jsp";
					}
				});
			});
		},
		init:function(){
			<%if(roomNum==null){%>
				checkOut.blurNum();
			<%}else{%>
				checkOut.dataByNum();
			<%}%>
			checkOut.getActual();
			checkOut.outSub();
		
		}
	}
	checkOut.init();
</script>
</html>