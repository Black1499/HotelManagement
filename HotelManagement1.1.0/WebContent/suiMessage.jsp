<%@page import="com.vo.VipConsumptionVo"%>
<%@page import="com.vo.CustomerInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的消息</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="js/suimobile/css/sm.min.css" />
<link rel="stylesheet" href="js/suimobile/css/sm-extend.min.css" />
</head>
<body>
<div class="content">
		<!-- 工具栏 -->
        <nav class="bar bar-tab">
            <a class="tab-item " href="suimobileIndex.jsp">
                <span class="icon icon-home"></span>
                <span class="tab-label ">首页</span>
            </a>
            <a class="tab-item active " href="#">
                <span class="icon icon-star"></span>
                <span class="tab-label">我的消息</span>
            </a>
            <a class="tab-item" href="suiReserve.jsp">
                 <span class="icon icon-edit"></span>
                 <span class="tab-label">快速预定</span>
             </a>
        </nav>
	
	  <div class="buttons-tab">
	    <a href="#tab1" class="tab-link active button">预定</a>
	    <a href="#tab2" class="tab-link button">入住</a>
	    <a href="#tab3" class="tab-link button">结账</a>
	    <a href="#tab4" class="tab-link button">充值</a>
	  </div>
	  <div class="content-block">
	    <div class="tabs">
	      <div id="tab1" class="tab active">
	        <div class="content-block" id="reserve">
	          
	        </div>
	      </div>
	      <div id="tab2" class="tab">
	        <div class="content-block" id="check">
	          
	        </div>
	      </div>
	      <div id="tab3" class="tab">
	        <div class="content-block" id="out">
	          
	        </div>
	      </div>
	      
	      <div id="tab4" class="tab">
	        <div class="content-block" id="vip">
	          
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</body>
<script type='text/javascript' src='js/suimobile/js/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm-extend.min.js' charset='utf-8'></script>
<script>
<%
	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
	VipConsumptionVo convip = (VipConsumptionVo)request.getSession().getAttribute("cusVip"); //获取session值
	String name=cusVo.getCustomerName();
	String phone=cusVo.getCustomerPhone();
%>
	var sMessage={
		reserve:function(){
			$.post("SuiMessageServlet",{action:'getReserve',name:'<%=name%>',phone:'<%=phone%>'},function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					var reserveTime=data[i].reserveTime.substring(0,16);
					html+="<div class='card'>";
					html+="<div class='card-content'>";
					html+="<div class='card-content-inner'>尊敬的"+data[i].customerName+"客户，您已成功预订了"+data[i].roomNum+"房间，请在"+reserveTime+"之前前来入住。</div>";
					html+="</div>";
					html+="</div>";
				}
				$("#reserve").append(html);
			});
		},
		check:function(){
			$.post("SuiMessageServlet",{action:'getCheck',name:'<%=name%>',phone:'<%=phone%>'},function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					var checkTime=data[i].checkTime.substring(0,16);
					html+="<div class='card'>";
					html+="<div class='card-content'>";
					html+="<div class='card-content-inner'>尊敬的"+data[i].customerName+"客户，您在"+checkTime+"成功入住了"+data[i].roomNum+"房，希望你能好好享受这休息时光。</div>";
					html+="</div>";
					html+="</div>";
				}
				$("#check").append(html);
			});
		},
		unique:function(arr){
		  var hash=[];
		  for (var i = 0; i < arr.length; i++) {
		     if(hash.indexOf(arr[i])==-1){
		      hash.push(arr[i]);
		     }
		  }
		  return hash;
		},
		out:function(){
			$.post("SuiMessageServlet",{action:'getOut',name:'<%=name%>',phone:'<%=phone%>'},function(data){
				var html="";
				var array=[];
				for(var i=0;i<data.length;i++){
					array[i]=data[i].outTime.substring(0,16);
				}
				array=sMessage.unique(array);
				
				for(var i=0;i<array.length;i++){
					var outTime=data[i].outTime.substring(0,16);
					html+="<div class='card'>";
					html+="<div class='card-content'>";
					html+="<div class='card-content-inner'>尊敬的"+"<%=name%>"+"客户，您在"+outTime+"进行结账，房间编号为"+data[i].roomNum+"，共消费了"+data[i].outActual+"元。欢迎下次光临。</div>";
					html+="</div>";
					html+="</div>";
				}
				$("#out").append(html);
			});
		},
		vip:function(){
			$.post("Rechargerecord",{},function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					var inTime=data[i].inTime.substring(0,16);
					var money=data[i].vipRecord;
					var remark=data[i].customerIdNum;
					html+="<div class='card'>";
					html+="<div class='card-content'>";
					html+="<div class='card-content-inner'>尊敬的"+"<%=name%>"+"客户，您在"+inTime+",通过："+remark+"，成功充值了"+money+"元。</div>";
					html+="</div>";
					html+="</div>";
				}
				$("#vip").append(html);
			});
		},
		init:function(){
			sMessage.reserve();
			sMessage.check();
			sMessage.out();
			sMessage.vip();
		}
	}
	sMessage.init();
</script>
</body>
</html>