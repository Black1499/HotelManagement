<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vo.EmployeeInfoVo" %>
<%@ page import="com.util.MD5" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<% 
		EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo");
		if(empVo == null){//为空，客户没有登陆想打开主界面
			response.sendRedirect("login.js");//重定向到登陆界面
		}
%>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script>
<style type="text/css">
body{
	margin:0;
	background:transparent;
	margin:0 auto;}
*{
	position:relative;
	font-family:"微软雅黑";
}
#body{
	width:1000px;
	height:570px;
	
	background-color:#FFF;
	margin:0 auto;
}
#left{
	width:298px;
	height:500px;
	left:50px;
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
	
	/*background-color:#ffff33;*/
}
#left .text_checkBox{
	width:70px;
	height:30px;
	left:5px;
	border:0;
	padding:0;
	font-size:16px;
	top:15px;
	float:left;
}
.left_input{
	width:250px;
	height:35px;
	margin: 0 auto 10px auto;
}
.left_textarea{
	width:250px;
	height:110px;
	top:-5px;
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
#left #phone{
	width:40px;
	height:30px;
	top:-22px;
	line-height: 35px;
	font-size:12px;
	float:right;
	color:#fdadad;
	cursor:pointer;
}
#right{
	width:505px;
	height:500px;
	top:30px;
	float: right;
	
	/*background-color:#ddd;*/
}
#right .right_revise{
	width:360px;
	height:490px;
	
	text-align:center;
	font-size: 12px;
	margin:0 auto;
	overflow:auto;
	border:#95b8e7 1px solid;
}
#rightPwd{
	width:360px;
	height:300px;
	margin: 50px auto;
}
.right_input{
	width:260px;
	height:35px;
	left:10px;
	margin: 0 auto 10px auto;
}
.right_value{
	width:260px;
	margin: 0 auto 10px auto;
}
#right .text{
	width:71px;
	height:35px;
	float:left;
	line-height: 50px; /*设置line-height与父级元素的height相等*/
    text-align: left; /*设置文本水平居中*/
	font-size:15px;
}
#right .text_input{
	width:180px;
	height:30px;
	left:5px;
	border:0;
	padding:0;
	z-index:2;
	font-size:16px;
	top:10px;
	float:left;
	
	background: whitesmoke;
}
#right .text_value{
	width:160px;
	height:20px;
	top:-5px;
	left:85px;
	z-index:1;
	border:0;
	padding:0;
	font-size:12px;
	float:left;
	color:red;
}
#right .ok{
	width:80px;
	height:30px;
	top:100px;
	background-color:#fdadad;
}
#foot{
	position:absolute;
	width:605px;
	height:50px;
	top:600px;
	left:220px;
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
<script type="text/javascript">

window.onload=function() {
	var rightNone = document.getElementById("right").style.display="none";//隐藏
	if(rightNone != null){
		 document.getElementById("left").style.left="280px";//向左移动230px
	}else{
		 document.getElementById("left").style.left="50px";//向左移动230px
	}
	
	//document.getElementById("sexFemale").checked=true;
	//document.getElementById("yes").checked=true;
};

function phone() {
	var rightNone = document.getElementById("right").style.display="none";//隐藏
	if(rightNone="none" != null){
		document.getElementById("right").style.display="block";//显示
		document.getElementById("left").style.left="50px";//回到原来位置
	}
	var html="<form  name='frm'  >";
		html+="<div class='right_input'> ";
		html+="<div class='text'>当前号码<span style='color:red'>*</span>:</div>";
		html+="<input type='text' id='phonePwd' name='empPhoneOld' class='text_input' value='' placeholder='请输入当前密码' maxlength='11'/></div>";
		html+="<div class='right_input'>";
		html+="<input type='text' class='text_value' value='不能为空' readonly/>";
		html+="</div> <div class='right_input'>";
		html+="<div class='text'>新 号 码<span style='color:red'>*</span>:</div>";
		html+="<input type='text' id='phonetext' name='empPhoneNew1' class='text_input' value='' placeholder='请输入新手机号吗' maxlength='11'/>";
		html += "</div><input type='button' id='okPhone' onclick='updatePhone()'  class='ok' value='确定'/>";
		html+="</form>";
	document.getElementById("rightPwd").innerHTML=html;
	
};

function revise() {
	var rightNone = document.getElementById("right").style.display="none";//隐藏
	if(rightNone="none" != null){
		var html="<form name='frm'  >";
		 html+="	<div class='right_input'>";
		 html+="			<div class='text'>当前密码<span style='color:red'>*</span>:</div>";
		 html+="			<input type='password' id='pwd1' name='empPasswordOld' class='text_input' value='' placeholder='请输入当前密码' maxlength='20'/>";
		 html+="		</div>";
		 html+="		<div class='right_input'>";
		 html+="			<input type='text' class='text_value' value='不能为空' readonly/>";
		 html+="		</div>";
		 html+="		<div class='right_input'>";
		 html+="			<div class='text'>新 密 码<span style='color:red'>*</span>:</div>";
		 html+="			<input type='password' id='pwd2' name='empPasswordNew1' class='text_input' value='' placeholder='请输入新密码' maxlength='20'/>";
		 html+="		</div>";
		 html+="		<div class='right_input'>";
		 html+="			<div class='text'>重复密码<span style='color:red'>*</span>:</div>";
		 html+="			<input type='password' id='pwd3' name='empPasswordNew2' class='text_input' value='' placeholder='请重复新密码' maxlength='20'/>";
		 html+="		</div>";
		 html+="		<input type='button' id='okPwd' onclick='checkPwd()' class='ok' value='确定'/>";
		 html+="</form>";
		document.getElementById("rightPwd").innerHTML=html;
		document.getElementById("right").style.display="block";//显示
		document.getElementById("left").style.left="50px";//回到原来位置
	}	
};
function cancel() {
	var rightNone = document.getElementById("right").style.display="block";//显示	 
	if(rightNone="none" != null){
		document.getElementById("right").style.display="none";//隐藏     
		document.getElementById("left").style.left="280px";//向左移动230px
	}
};
function $(id){
	return document.getElementById(id);
}
</script>
</head>
<body>
	
	<div id="body">
		<div id="left">
			<div id="empAccountNum" class="left_input">
				<div class="text">员工账号:</div>
				<input type="text" name="empAccountNum" class="text_input" value=<%=empVo.getEmpAccountNum() %> disabled/>
			</div>	<!-- 证件号码 -->
			<div id="empName" class="left_input">	<!-- 客户姓名 -->
				<div class="text">姓&nbsp;&nbsp;名:</div>
				<input type="text" name="empName" class="text_input" value=<%=empVo.getEmpName() %> disabled/>
			</div>
			<div id="empSex" class="left_input">
				<div class="text">性&nbsp;&nbsp;别:</div>
					<div class='text_checkBox'><input type='radio' id='sexMale' value='男' disabled >男</div>
					<div class='text_checkBox'><input type='radio' id='sexFemale' value='女' disabled >女</div>
			</div>	<!-- 房间号 -->
			<div id="empIdNum" class="left_input">
				<div class="text">身份证号:</div>
				<input type="text" name="empIdNum" class="text_input disabled" value=<%=empVo.getEmpIdNum() %> disabled/>
			</div>	<!-- 房间号 -->
			<div id="empAdmin" class="left_input">
				<div class="text">超管:</div>
				<div class='text_checkBox'><input type='CheckBox' id='yes' value='1' disabled>是</div>
				<div class='text_checkBox'><input type='CheckBox' id='no' value='0' disabled>否</div>
			</div>
			<div id="empPhone" class="left_input">
				<div class="text">联系电话:</div><!-- 员工联系电话 -->
				<input type="text" name="empPhone" class="text_input" value=<%=empVo.getEmpPhone() %> disabled maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
				<div id="phone" onclick="phone()">修改</div>
				</div>	
			<div id="empAddress" class="left_textarea">
				<div class="text">住&nbsp;&nbsp;址:</div>
				<textarea name="empRemark" class="text_input" rows="4" cols="20" disabled style="margin: 0px; height: 75px; width: 230px; resize: none;" placeholder=<%=empVo.getEmpAddress() %>></textarea>
			</div>	<!-- 入住时间 -->
			<div id="empRemark" class="left_textarea">
				<div class="text">备&nbsp;&nbsp;注：</div>
				<textarea name="empRemark" class="text_input" rows="4" cols="20" disabled  style="margin: 0px; height: 80px; width: 230px; resize: none;" placeholder=<%=empVo.getEmpRemark() %>  ></textarea>
			</div>	<!-- 备注 -->
		</div>
		<div id="right" style="display:none;">
			<div class="right_revise" id="right_revise">
			<div id="rightPwd">
				
			</div>
			</div>
		</div>
		<div id="foot">
			<input type="button" id="revise" class="revise" value="修改密码" onclick="revise()"/>
			<input type="button" id="cancel" class="cancel" value="取消" onclick="cancel()" style="background-color:#fff"/>
		</div>
	</div>
	<script type="text/javascript">
	//设置单选按钮，复选框的选中
		var sexMale = document.getElementById("sexMale");
		var sexFemale = document.getElementById("sexFemale");
		if("<%=empVo.getEmpSex()%>" == "男"){
			sexMale.checked = true;
		}else{
			sexFemale.checked = true;
		}
		var yes = document.getElementById("yes");
		var no = document.getElementById("no");
		if("<%=empVo.getEmpAdmin()%>" == 1 ){
			yes.checked = true;
			no.checked = false;
		}else{
			yes.checked = false;
			no.checked = true;
		}
	//Ajax请求
	//修改密码
	</script>
	
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		function checkPwd() {
			//alert("sdfsdd");
			var pwd1 = document.getElementById("pwd1").value;
			var pwd2 = document.getElementById("pwd2").value;
			var pwd3 = document.getElementById("pwd3").value;
			if(!pwd1){
				alert("请输入原密码验证");
				return false;
			}
			if(!pwd2){
				alert("请输入密码");
				return false;
			}
			if(!pwd3){
				alert("请输入密码");
				return false;
			}
			if(pwd2!=pwd3){
				alert("两次输入的密码不正确！");
				return false;
			}
			//alert($("#pwd").val());
			$.get("UpdatePwd",{"pwd1":pwd1,"pwd3":pwd3},function(data){
				alert(data.msg);
			});
		};
		
		
		function updatePhone() {
			var phonePwd = document.getElementById("phonePwd").value;
			var phonetext = document.getElementById("phonetext").value;
			if(!phonePwd){
				alert("请输入密码验证");
				return false;
			}
			if(!phonetext){
				alert("请输入新的电话号码");
				return false;
			}
			$.get("UpdatePhone",{"phonePwd":phonePwd,"phonetext":phonetext},function(data){
				alert(data.msg);
			});
		};
		
	</script>
</body>
</html>