<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="js/layUI/css/layui.css" media="all">
<style type="text/css">
#form * {
	position: relative;
	float: left;
}

#form {
	width: 450px;
	height: 500px;
	background-color:;
	display: none;
}

.item {
	width: 380px;
	height: 40px;
	border:;
	background-color:;
	margin: 10px 40px 10px 40px;
	font-size: 18px;
	line-height: 40px;
}
.itemtext{
	height: 70px;
}
.itempuc{
	height: 130px;
}
input[type=text] {
	width: 220px;
	height: 20px;
	top: 8px;
}

select {
	width: 160px;
	height: 28px;
	top: 6px;
}

input[type=radio] {
	top: 9px;
	width: 20px;
	height: 20px;
	margin-left: 40px;
}

#remark {
	width: 220px;
	height: 80px;
	top: 8px;
}
.layui-btn-container{
	margin-top:20px;
	margin-left:20px;
	width:400px;
}
.msg{
	width:500px;
	height:40px;
	color:red;
	position:relative;
	float:left;
	left:400px;
	top:-45px;
	font-size:24px;
	line-height:40px;
}
.layui-upload{
	width: 450px;
	height: auto;
	position:relative;
}
.layui-upload-list{
	margin: 10px 20px 0px 105px;
	position:relative;
}
.layui-btn{
	top: 40px;
    left: 25px;
}
</style>
<title>员工管理</title>
</head>
<body>
	<div class="layui-btn-container">
	  <button class="layui-btn layui-btn-radius layui-btn-normal" id="Add">添加员工</button>
	  <button class="layui-btn layui-btn-radius layui-btn-normal" id="resetPassword">重置密码</button>
	  <button class="layui-btn layui-btn-danger layui-btn-radius" id="btnDel">批量删除</button> 
	</div>
	<div class="msg"></div>
	<table id="demo" lay-filter="table"></table>
	<form id="form">
		<div class="layui-upload">
			<div class="layui-upload-list" id="divimg">
				<img class="layui-upload-img" id="imgUpload" width="100px" height="120px">
			</div>
			<span class="img" name="empImg" style="display: none;"></span>
			<button type="button" class="layui-btn" id="btnUpload" >上传图片</button>
		</div>
		<input class="empImg" name="empImg" value="" style="display: none;" />
		
		
		<div class="item">
			<label>账 &nbsp; &nbsp; 号 &nbsp; ： </label> <input type="text" name="empAccountNum" placeholder=" 账 号 " />
		</div>
		<div class="item">
			<label>姓 &nbsp; &nbsp; 名 &nbsp; ： </label><input type="text" name="empName" placeholder=" 姓 名 "/>
		</div>
		<div class="item">
			<label>性 &nbsp; &nbsp; 别 &nbsp; ：</label> 
			<input type="radio" name="empSex" id="sexMale" value="男"  ><label>男</label> 
			<input type="radio" name="empSex" id="sexFemale" value="女"  ><label>女</label>
		</div>
		<div class="item">
			<div class="text">身份证号 ：</div>
			<input type="text" name="empIdNum" id="empIdNum" class="text_input" value="" placeholder="身份证号" />
		</div>
		<div class="item">
			<div class="text">是否超管 ：</div>
			<input type="radio" name="empAdmin" value="1" ><label>是</label>
			<input type="radio" name="empAdmin" value="0" ><label>否</label>
		</div>
		<div class="item">
			<div class="text">联系电话 ：</div>
			<input type="text" name="empPhone" class="text_input" id="empPhone" placeholder="联系电话" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
		</div>
		<div class="item itemtext">
			<div class="text">住 &nbsp; &nbsp; 址 &nbsp; ： </div>
			<textarea name="empAddress" rows="4" cols="20" value="" placeholder=" 住 址 信 息 " style="margin: 0px; height: 75px; width: 230px; resize: none;"></textarea>
		</div>
		<div class="item itemtext">
			<div class="text">备 &nbsp; &nbsp; 注 &nbsp; ： </div>
			<textarea  name="empRemark" rows="4" cols="20" value="" placeholder=" 备 注 信 息 " style="margin: 0px; height: 75px; width: 230px; resize: none;"></textarea>
		</div>
	</form>
<input type="text" id="empId">
<style>
	#empId{
		display:none;
	}
</style>
<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/layUI/layui.all.js"></script>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	var admin = {
		table:null,
		useTable:function(){//使用数据表格
			layui.use('table', function() {
				admin.table = layui.table;
			});
		},
		blindTable:function(){//数据表格渲染
			admin.table.render({
			    elem: '#demo',
			    width: 940,	
			    url: 'EmpAll' ,//数据接口
			    page: true ,//开启分页
			    cols: [[ //表头
			    	{type:'checkbox'},
				    {field: 'empAccountNum', title: '员工账号',sort:true},
				    {field: 'empName', title: '姓名'},
				    {field: 'empSex', title: '性别'},
				    {field: 'empPhone', title: '手机号码'},
				    {field: 'empAdmin', title: '是否超管',
				    	templet: function(d){
				    		if(d.empAdmin==0)
				    			return 'NO';
				    		else if(d.empAdmin==1)
				    			return 'YES';
				    	}
				   },
				   {field:'empImg',title:'照片',
						templet:function(d){
							return '<img src=\"img/'+d.empImg+'\" width=20 height=28/>';
						}   
				   },
				   {title:'员工操作', width:150, align:'center', toolbar: '#barDemo'} 
			    ]]
			});
		},
		empty:function(){//清空数据
			$("input[name=empAccountNum]").val("");
			$("input[name=empName]").val("");
   			$("input[name=empIdNum]").val("");
   			$("input[name=empPhone]").val("");
   			$("input[name=empAddress]").val("");
   			$("input[name=empRemark]").val("");
   			$("#imgUpload").attr("src","");
		},
		operateTable:function(){//对数据表格的操作
			admin.table.on('tool(table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			  var data = obj.data; //获得当前行数据
			  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  var tr = obj.tr; //获得当前行 tr 的DOM对象
			 
			  if(layEvent === 'del'){ //删除
			    layer.confirm('是否删除这条数据？', function(index){
			      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			      admin.blindTable();
			      layer.close(index);
			      $.post("Empdel",{empId:data.empId},function(data){
			    	alert(data.msg);
			      });
			    });
			  } else if(layEvent === 'edit'){ //编辑
			  	admin.empty();
				  $("input[name=empAccountNum]").val(data.empAccountNum);
				  $("input[name=empName]").val(data.empName);
				  $("input[name=empPhone]").val(data.empPhone);
	   			  $("input[name=empIdNum]").val(data.empIdNum);
	   			  if(data.empSex=="男"){
	   			 	 $("input[value='男']").attr("checked","true");
	   			 	 $("input[value='男']").prop("checked","true");
	   			  }
	   			  else{
	   				 $("input[value='女']").attr("checked","true");
	   				 $("input[value='女']").prop("checked","true");
	   			  }
	   			  if(data.empAdmin==0){
	   			 	 $("input[value=0]").attr("checked","true");
	   			 	 $("input[value=0]").prop("checked","true");
	   			  }
	   			else{
	   				$("input[value=1]").attr("checked","true");
	   				$("input[value=1]").prop("checked","true");
	   			}
	   			$("textarea[name=empAddress]").val(data.empAddress);
	   			$("textarea[name=empRemark]").val(data.empRemark);
	   			
	   			var img = $(".empImg").val();
	   			$("#imgUpload").attr("src","img/"+data.empImg);
				  layer.open({
					  type: 1, 
					  title:'客房编辑',
					  content: $("form"),
					  area: ['450px', '500px'],
					  btn:['确定','关闭'],
					  yes: function(index, layero){
					   	$.post('updateEmp?empId='+data.empId,$("form").serialize(),function(data){
					   		alert(data.msg);
				   			admin.empty();
				   			admin.table.reload('demo', {//表格数据重载
							  url: 'EmpAll'
							});
				   			$("form").css("display","none");
				   			layer.close(index);
					   	});
					  },
					  btn2: function(index, layero){
						  $("form").css("display","none");
						  admin.empty();
						  layer.close(index);
					  },
					  cancel: function(){ 
						  $("form").css("display","none");
						  admin.empty();
						  layer.close();
					  }
				});	
			  }
			});
		},
		roomExcel:function(){
			layui.use('upload', function(){
				var upload = layui.upload;
			   
				//执行实例
				var uploadInst = upload.render({
					elem: '#btnUpload',
					url: 'UploadFile',
					before: function(obj) {
						//预读本地文件示例，不支持ie8
						obj.preview(function(index, file, result) {
							$('#imgUpload').attr('src', result);
						});
					},
					done: function(res) {
						//如果上传失败
						if(res.code > 0) {
							return layer.msg('上传失败');
						}
						layer.msg(res.msg);
						$(".img").html(res.data.name);
						$(".empImg").val(res.data.name);
					},
					error: function() {
						var demoText = $('#demoText');
						demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
						demoText.find('.demo-reload').on('click', function() {
							uploadInst.upload();
						});
					}
				});
			});
			
		},
		empNum:[],
		stateChange:function(){
			admin.table.on('checkbox(table)',function(obj){
				if(obj.checked){//如果勾选添加至数组
					admin.empNum=admin.empNum.concat(obj.data.empId);
				}else{//如果不勾选从数组里面删除
 					for(var i=0;i<admin.empNum.length;i++){
 						if(admin.empNum[i]==obj.data.empNum){
 							admin.empNum.splice(i,1);
 							console.log(i);
 						}
					}
				}
				
			});
			$("#resetPassword").click(function(){
				if(confirm("是否重置选中的账号？")){
					var count=0;
					$.each(admin.empNum,function(i,o){
						$.post("EmpPwdUpdate",{empId:o},function(data){
							if(data.msg){
								count++;
							}
						});
					});
					alert("操作成功");
					
					admin.blindTable();
					admin.empNum=[];
				}
			});
			$("#btnDel").click(function(){
				if(confirm("是否删除选中账号？")){
					var count=0;
					$.each(admin.empNum,function(i,o){
						$.post("Empdel",{empId:o},function(data){
							if(data.msg){
								count++;
							}
						});
					});
					alert("删除成功");
					
					admin.blindTable();
					admin.empNum=[];
				}
			})
		},
		Add:function(){
			$("#Add").click(function(){
				layer.open({
					  type: 1, 
					  title:'员工添加',
					  content: $("form"), //这里content是一个普通的String
					  area: ['450px', '500px'],
					  btn:['确定','关闭'],
					  yes: function(index, layero){
					   	$.post('EmpAdd',$("form").serialize(),function(data){
					   		alert(data.msg);
					   		if(confirm("是否继续添加？")){
					   			admin.empty();
					   		}else{
							   	admin.blindTable();
					   			$("form").css("display","none");
					   			admin.empty();
					   			layer.close(index);
					   		}
					   	});
					  },
					  btn2: function(index, layero){
						  $("form").css("display","none");
						  admin.empty();
						  layer.close(index);
					  },
					  cancel: function(){ 
						  $("form").css("display","none");
						  admin.empty();
						  layer.close();
					  }
				});
			});
		},
		init:function(){
			admin.useTable();
			admin.blindTable();
			admin.operateTable();
			admin.roomExcel();
			admin.stateChange();
			admin.Add();
		}
	}
	admin.init();
</script>
</body>
</html>