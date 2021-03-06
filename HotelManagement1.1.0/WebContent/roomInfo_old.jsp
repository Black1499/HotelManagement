<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>六月酒店管理系统</title>
<link rel="stylesheet" href="js/layUI/css/layui.css">
<style type='text/css'>
*{
	position:relative;
	float:left;
}
.div_body1{
	width:1340px;
	height:700px;
	background-color:#FFFFF0;
	margin:auto;
}
.div_body2{
	width:990px;
	height:700px;
	background-color:#F5F5F5;
	margin:auto;
	/*border:1px solid gray;*/
	
}
.div_body{
	height:100px;
	/*border:1px solid gray;*/
	margin:auto;
	padding:10px;
}
.div_span1{
	width:80px;
	height:30px;
	left:15px;
	top:15px;
	font-family:"微软雅黑";
}
.div_input_body{
	width:672px;
	height:24px;
	top:47px;
	left:-35px;
}
.div_input1{
	width:80px;
	font-family:"微软雅黑";
}
.div_input2{
	width:110px;
	float:left;
	
	margin-left:8px;
	font-family:"微软雅黑";
}
.div_input3{
	width:100px;
	float:left;
	
	margin-left:8px;
	font-family:"微软雅黑";
}
.div_input4{
	width:100px;
	float:left;
	
	margin-left:8px;
	font-family:"微软雅黑";
}
.div_input5{
	width:100px;
	float:left;
	
	margin-left:8px;
	font-family:"微软雅黑";
}
.div_input6{
	width:100px;
	float:left;
	border:none;
	
	margin-left:8px;
	font-family:"微软雅黑";
}
.div_select_body{
	width:400px;
	height:28px;
	border:none;
	top:55px;
	left:500px;
	font-family:"微软雅黑";
}
.input_select{
	width:220px;
	height:20px;
	font-size:16px;
	font-family:"微软雅黑";
}
.select_button{
	text-align:center;
	background-color:#B22222;
	color:white;
	height:27px;
	width:60px;
	top:-1px;
	font-family:"微软雅黑";
}

*{
	position:relative;
}
#main {
	width:990px;
	height:720px;
	background-color:#DDDEE3;
	margin: auto;
}
.top{
	width:990px;
	height:120px;
	float:left;
	background-color:#333333;
	/*border:1px solid gray;*/
}
.buttom{
	width:990px;
	float:left;
	background-color:#DDDEE3;
	/*border:1px solid gray;*/
}
.img{
	width:83px;
	height:88Px;
	border-radius:10px 0 10px 0;
	float:left;
	cursor:pointer;
	margin:12px 3px 3px 12px;	
}
.blue{
	background-color:#00A5FF;
}
.red{
	background-color:#FF5555;
}
.gray{
	background-color:#A9A9A9;
}
.yellow{
	background-color:#E1E100;
}
.green{
	background-color:#00FFCC;
}
.image1{
	widht:50px;
	height:50px;
    margin: auto;
    left: 10px;
    right:10px
}
.image{
    top:5px;
	widht:45px;
	height:45px;
	margin: 0;
}
.roomNum{
	width:50px;
	height:18px;
	left:25px;
    top:-4px;
    bottom:0px;
	/*background-color:red;*/
}
.roomType{
	width:50px;
	height:18Px;
	left:20px;
    top:-4px;
	/*background-color:red;*/
}

.roomNum span,.roomType span{
	font-family:"微软雅黑";
	font-size:12px;
}
.rNum .rType{
	vertical-align: middle;/*垂直居中*/
	font-size:0.5em;
}
.spanFour{
	font-size:27px;
	left:14px;
	top:14px;
}
.img:hover {
	box-shadow:0px 0px 3px #000;
	margin:7px 3px 3px 7px;	
	width:88px;
	height:93Px;
}
.img:hover .image{
    top:3px;
    left:-1px;
	widht:50px;
	height:50px;
}
.img:hover span{
	top:3px;
	left:2px;
	font-weight:bold
}
span{
	font-size:16px;
}
input[type=radio]{
	position:relative;
	top:5px;
	left:-5px;
}
.box1{
	width:10px;
	height:10px;
	background-color:#FF5555;
	left:5px;
	top:6px;
}
.box2{
	width:10px;
	height:10px;
	background-color:#A9A9A9;
	left:5px;
	top:6px;
}
.box3{
	width:10px;
	height:10px;
	background-color:#00A5FF;
	left:5px;
	top:6px;
}
.box4{
	width:10px;
	height:10px;
	background-color:#E1E100;
	left:5px;
	top:6px;
}
.box5{
	width:10px;
	height:10px;
	background-color:#00FFCC;
	left:5px;
	top:6px;
}
</style>
</head>
<body>
	<div id="main">
		<div class="top">
			<div class="div1_body1">
			<div class="div_body2">
				<div class="div_body3">
					<div class="div_span1">
						<span>客房状态：</span>
					</div>
						<div class="div_input_body">
							<div class="div_input1">
								<input type="radio" name="state" value="5" checked/>
								<span>全部</span>
							</div>
							<div class="div_input2">
								<input type="radio" name="state" value="4"/>
								<span>不可使用</span>
								<div class="box1"></div>
							</div>
							<div class="div_input3">
								<input type="radio" name="state" value="0"/>
								<span>可入住</span><div class="box2"></div>
							</div>
							<div class="div_input4">
								<input type="radio" name="state" value="1"/>
								<span>已入住</span><div class="box3"></div>
							</div>
							<div class="div_input5">
								<input type="radio" name="state" value="2"/>
								<span>被预定</span><div class="box4"></div>
							</div>
							<div class="div_input6">
								<input type="radio" name="state" value="3"/>
								<span>被清洁</span><div class="box5"></div>
							</div>
						</div>
						<div class="div_select_body">
							<span>房间搜索：</span>
								<input type="text" name="roomNum" id="num" class="input_select" maxlength=4/>
								<input type="button" value="搜索" class="select_button" id="btnSel"/>
						</div>
					</div>
				</div>	
				</div>
		</div>
		<div class="buttom">
		</div>
	</div>
</body>
	<script type="text/javascript" src="js/layUI/layui.all.js" ></script>
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js" ></script>
	<script>
		var room={
			showShade:function(){//使用layUI的遮罩层
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.open({
					  type:3,
					  time:500,
					  shade:[0.3,"#393D49"]
					});
				});     
			},
			blindRoom:function(){//渲染界面，显示出所有的房间
				$(".buttom").empty();
				$.ajax({
					type:"post",
					url:"JudgeRoom?action=getAllRoom",
					beforeSend: function() { //发送请求前
						room.showShade();
                    },
					success:function(data){
						$.each(data, function(i,obj) {
							var html="";
							if(obj.roomAvailable==1){
								html+="<div class='img red layui-anim layui-anim-scale' name='divImg'>";
							}else if(obj.roomAvailable==0){
								if(obj.roomState==0){
									html+="<div class='img gray layui-anim layui-anim-scale' name='divImg'>";
								}else if(obj.roomState==1){
									html+="<div class='img blue layui-anim layui-anim-scale' name='divImg' roomNum='"+obj.roomNum+"'>";
								}
								else if(obj.roomState==2){
									html+="<div class='img yellow layui-anim layui-anim-scale' name='divImg' roomNum='"+obj.roomNum+"'>";
								}
								else if(obj.roomState==3){
									html+="<div class='img green layui-anim layui-anim-scale' name='divImg'>";
								}
							}
							html+="<div class='image1'><img src='img/房间白.png' class='image'></div>";
							html+="<div class='roomNum'>";
							html+="<span class='rNum'>"+obj.roomNum+"</span></div>";
							html+="<div class='roomType'>";
							html+="<span class='rType'>"+obj.typeName+"</span></div>";
							html+="</div>";
							$(".buttom").append(html);
						});
					}
				});
			},
			roomPart:function(num){//按照房间状态条件，来渲染界面
				$(".buttom").html("");//清空
				$.ajax({
					type:"post",
					url:"JudgeRoom?action=getPartRoom",
					data:{state:num},
					beforeSend: function() { //发送请求前
						room.showShade();
                    },
					success:function(data){
						$.each(data, function(i,obj) {
							var html="";
							if(num==4){
								html+="<div class='img red layui-anim layui-anim-scale' name='divImg'>";
							}else if(num==0){
								html+="<div class='img gray layui-anim layui-anim-scale' name='divImg'>";
							}else if(num==1){
								html+="<div class='img blue layui-anim layui-anim-scale' name='divImg' roomNums='"+obj.roomNum+"'>";
							}
							else if(num==2){
								html+="<div class='img yellow layui-anim layui-anim-scale' name='divImg' roomNum='"+obj.roomNum+"'>";
							}
							else if(num==3){
								html+="<div class='img green layui-anim layui-anim-scale' name='divImg'>";
							}
							html+="<div class='image1'><img src='img/房间白.png' class='image'></div>";
							html+="<div class='roomNum'>";
							html+="<span class='rNum'>"+obj.roomNum+"</span></div>";
							html+="<div class='roomType'>";
							html+="<span class='rType'>"+obj.typeName+"</span></div>";
							html+="</div>";
							$(".buttom").append(html);
						});
					}
				});
			},
			roomChange:function(){//根据radio值生成房间列表
				$("input[name='state']").change(function(){
					if(this.value==5){
						room.blindRoom();
					}else{
						room.roomPart(this.value);
					}
				});
			},
			roomClick:function(){//点击房间图标时实现的功能
				$(document).on("click",".yellow",function(){//显示预定的房间信息
					window.location.href="roomReserve.jsp?roomNum="+$(this).attr("roomNum");
				});
				$(document).on("click",".gray",function(){
					window.location.href="roomCheck.jsp?state=0";
				});
				$(document).on("click",".blue",function(){
					window.location.href="roomCheck.jsp?state=1&roomNum="+$(this).attr("roomNum");
				});
				$(document).on("click",".green",function(){
					alert("该房间正在清洁！");
				});
				$(document).on("click",".red",function(){
					alert("该房间无法使用！");
				});
			},
			roomFind:function(){//房间查询,根据房间的状态进行跳转或提示
				$("#btnSel").click(function(){
					$.post("JudgeRoom?action=getRoom",{roomNum:$("#num").val()},function(data){
						if(data.msg==null){
							if(data.roomAvailable==1){
								alert("该房间无法使用...");
							}else{
								if(data.roomState==0){
									window.location.href="roomCheck.jsp?state=0";
								}else if(data.roomState==1){
									window.location.href="roomCheck.jsp?state=1&roomNum="+data.roomNum;
								}else if(data.roomState==2){
									window.location.href="roomReserve.jsp?roomNum="+data.roomNum;
								}else if(data.roomState=3){
									alert("该房间清洁中...");
								}
							}
						}else{
							alert(data.msg);
						}
					});
				});
			},
			init:function(){
				room.blindRoom();//加载所有的房间
				room.roomChange();
				room.roomClick();
				room.roomFind();
			}
		};
		room.init();
	</script>
</html>