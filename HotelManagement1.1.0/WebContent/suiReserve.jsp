<%@page import="com.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="js/suimobile/css/sm.min.css" />
<link rel="stylesheet" href="js/suimobile/css/sm-extend.min.css" />
</head>
<body>
<%
	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
	VipConsumptionVo convip = (VipConsumptionVo)request.getSession().getAttribute("cusVip"); //获取session值

%>
<header class="bar bar-nav">
  <h1 class='title'>房间预定</h1>
</header>
<div class="content">
	
  <div class="list-block">
    <ul>
      <!-- Text inputs -->
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">编号</div>
            <div class="item-input">
              <input type="text" placeholder="Number" id="number" value=<%=cusVo.getCustomerId() %>>
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">姓名</div>
            <div class="item-input">
              <input type="text" placeholder="Name" id="name" value=<%=cusVo.getCustomerName() %>>
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-email"></i></div>
          <div class="item-inner">
            <div class="item-title label">联系电话</div>
            <div class="item-input">
              <input type="text" placeholder="Phone" id="phone" value=<%=cusVo.getCustomerPhone() %>>
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-password"></i></div>
          <div class="item-inner">
            <div class="item-title label">身份证号</div>
            <div class="item-input">
              <input type="text" placeholder="IdNum" class="" id="idNum" value=<%=cusVo.getCustomerIdNum() %>>
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-gender"></i></div>
          <div class="item-inner">
            <div class="item-title label">房间类型</div>
            <div class="item-input">
            <input type="text" id='typeSelect'/>
<!--               <select id="typeSelect">	 -->
<!--               </select> -->
            </div>
          </div>
        </div>
      </li>
       <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-gender"></i></div>
          <div class="item-inner">
            <div class="item-title label">房间编号</div>
            <div class="item-input">
             <input type="text" id='roomSelect'/>
<!--               <select id="roomSelect"> -->
<!--               </select> -->
            </div>
          </div>
        </div>
      </li>
      <!-- Date -->
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-calendar"></i></div>
          <div class="item-inner">
            <div class="item-title label">预定日期</div>
            <div class="item-input">
              <input type="date" placeholder="Date" value="" id="date">
            </div>
          </div>
        </div>
      </li>
      <li class="align-top">
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-comment"></i></div>
          <div class="item-inner">
            <div class="item-title label">备注</div>
            <div class="item-input">
              <textarea id="remark"></textarea>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
 <div class="content-block">
    <div class="row">
      <div class="col-100"><a href="#" class="button button-big button-fill button-success" id="sub">提交</a></div>
    </div>
  </div>
</div>
</body>
<script type='text/javascript' src='js/suimobile/js/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm-extend.min.js' charset='utf-8'></script>
<script>
	var suiReserve={
		getType:function(){
			var arr=[];
			$.post("suiReserve?action=getType",{},function(d){
				//var html="";
				for(var i=0;i<d.length;i++){
					//html+="<option value='"+d[i].typeId+"'>"+d[i].typeName+"</option>";
					arr[i]=d[i].typeName;
				}
				//$("#typeSelect").append(html);
			});
			$("#typeSelect").picker({
				  toolbarTemplate: '<header class="bar bar-nav">\
				  <button class="button button-link pull-left"></button>\
				  <button class="button button-link pull-right close-picker">确定</button>\
				  <h1 class="title">房间类型</h1>\
				  </header>',
				  cols: [
				    {
				      textAlign: 'center',
				      values: arr
				    }
				  ]
				});
		},
		getRoom:function(){
			
			
			
			
// 			$("#typeSelect").blur(function(){
// 				$("#roomSelect").val("");
 				var arr=[];
				$.post("suiReserve?action=getRoom",{typeName:"豪华房"},function(d){
					for(var i=0;i<d.length;i++){
						arr[i]=d[i].roomNum;
					}
				});
				$("#roomSelect").picker({
					  toolbarTemplate: '<header class="bar bar-nav">\
					  <button class="button button-link pull-left"></button>\
					  <button class="button button-link pull-right close-picker">确定</button>\
					  <h1 class="title">房间编号</h1>\
					  </header>',
					  cols: [
					    {
					      textAlign: 'center',
					      values: arr
					    }
					  ]
				});
// 			});
		},
		reserve:function(){
			$("#sub").click(function(){
				$.post("ReserveTool?action=addReserve",{roomNum:$("#roomSelect").val(),reserveTime:$("#date").val(),
					customerName:$("#name").val(),customerPhone:$("#phone").val(),reserveRemark:$("#remark").val()},function(d){
						 $.alert(d.msg, function () {
							 window.location.href="suiMessage.jsp";
					     });
				});
			});
		},
		init:function(){
			suiReserve.getType();
			suiReserve.getRoom();
			suiReserve.reserve();
			
		}		
	}
	suiReserve.init();
</script>
</html>