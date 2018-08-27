<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>vip办理</title>
<style type="text/css">
	.div1{
		margin: auto;
		border: 1px solid gray;
		width:450px;
		height:250px;
		left:100px;
		top:100px;
		text-align: center;
		position: relative;
}
.div2{
	margin-top:20px;
}
.div3{
	margin-top:20px;
}
.div4{
	margin-top:20px;
}
.btnSubmit{
	margin-top:20px;
	float:right;
	position:relative;
	right:30px;
	width:120px;
	height:35px;
	font-size:18px;
	
}
div input{
	width:280px;
	height:30px;
	font-size:20px;
}
div span{
	font-size:20px;
	
}
</style>
</head>
<body>
	<div class="div1">
		<from>
			<div class="div2">
				<span id="span1">客户姓名：</span>
				<input type="text" id="cutName"  />
			</div>
			<div class="div3">
				<span id="span2">证件号码：</span>
				<input type="text" id="cutIdNum"  />
			</div>
			  <div class="div3">
				<span id="span2" >客户电话：</span>
				<input type="text" id="cutPhone"  />
			</div>
			<input type="button" id="btnAddVipCut" value="确认办理" class="btnSubmit"/>
		</from>
	</div>
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		$("#btnAddVipCut").click(function(){
			 var cutName = $("#cutName").val();
			 var cutIdNum = $("#cutIdNum").val();
			 var cutPhone = $("#cutPhone").val();
			 if(!cutName){
				 alert("请输入客户姓名");
				 return false;
			 }if(!cutIdNum){
				 alert("请输入客户证件号");
				 return false;
			 }if(!cutPhone){
				 alert("请输入客户联系电话");
				 return false;
			 }
			$.get("VipConsumpAdd",{"cutName":cutName,"cutIdNum":cutIdNum,"cutPhone":cutPhone},function(data){
				alert(data.msg);
			});
		});
	</script>
</body>
</html>