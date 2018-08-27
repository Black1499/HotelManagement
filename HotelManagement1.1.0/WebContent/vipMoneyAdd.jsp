<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vo.EmployeeInfoVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VIP客户充值</title>
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
.btnVipMoneyAdd{
	margin-top:20px;
	float:right;
	position:relative;
	right:30px;
	width:120px;
	height:35px;
	font-size:18px;
	
}
</style>
</head>
<body>
	<div class="div1">
		<div class="div2">
			客户名字<input type="text" id="cutName" />
		</div>
		<div class="div3">
		
			客户编号<input type="text" id="cutId" />
		</div>
		<div class="div3">
			充值金额<input type="text" id="vipRecord" />
		</div>
	<input type="button" id="btnVipMoneyAdd" value="确认" />
	</div>
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script> 
	<script type="text/javascript">
	<%
		EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo");
		if(empVo == null){//为空，客户没有登陆想打开主界面
			response.sendRedirect("login.js");//重定向到登陆界面
		}
	%>
		
		$("#btnVipMoneyAdd").click(function(){
			var cutName = $("#cutName").val();
			 var cutId = $("#cutId").val();
			 var vipRecord = $("#vipRecord").val();
			 var empId = <%=empVo.getEmpId()%>
			 if(!cutName){
				 alert("请输入客户姓名");
				 return false;
			 }if(!cutId){
				 alert("请输入客户编号");
				 return false;
			 }if(!vipRecord){
				 alert("请输入充值金额");
				 return false;
			 }
			$.get("vipMoneyAdd",{"cutName":cutName,"cutId":cutId,"vipRecord":vipRecord,"empId":empId},function(data){
				alert(data.msg);
			})
		});
	
	</script>
</body>
</html>