<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的余额</title><!-- suimobilevipBlance.jsp -->
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="js/suimobile/css/sm.min.css" />
<link rel="stylesheet" href="js/suimobile/css/sm-extend.min.css" />
<script type='text/javascript' src='js/suimobile/js/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm-extend.min.js' charset='utf-8'></script>
</head>
<body>
<%
	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
	VipConsumptionVo convip = (VipConsumptionVo)request.getSession().getAttribute("cusVip"); //获取session值
%>
<header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back" href="suimobileIndex.jsp">
      <span class="icon icon-left"></span>
     	 返回
    </a>
  <h1 class='title'>我的余额</h1>
</header>
<div class="content">
  <div class="list-block">
    <ul>
      <!-- Text inputs -->
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">余额</div>
            <div class="item-input">
              <input type="text" placeholder="" disabled id="vipBlance" >
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">充值方式：</div>
            <div class="item-input">
              <input type="text" id='picker'/>
            </div>
          </div>
        </div>
      </li>
          
    </ul>
   </div>
   <div class="row">
      <div class="col-50"> <a href="#" class="button button-success " id="Putforward">提现</a></div>
      <div class="col-50"> <a href="#" class="button button-danger" id="Recharge">充值</a></div>
    </div>
</div>
<script>
//窗口加载事件
$(document).ready(function(){
	var vipbla = "请登录";
	<%
		if(convip != null){
	%>
		vipbla = <%=convip.getVipBlance() %>
	<% 
		}
	%>
     $("#vipBlance").val(vipbla);
});
$("#picker").picker({
	  toolbarTemplate: '<header class="bar bar-nav">\
	  <button class="button button-link pull-left"></button>\
	  <button class="button button-link pull-right close-picker">确定</button>\
	  <h1 class="title">支付方式</h1>\
	  </header>',
	  cols: [
	    {
	      textAlign: 'center',
	      values: ['支付宝', '微信', '银联']
	    }
	  ]
});



//提现按钮
$(document).on('click','#Putforward', function () {
	var vipBlance = $("#vipBlance").val();
    $.prompt('请输入提现金额，最多可提现'+vipBlance, function (value) {
    	if(value > vipBlance){
    		 $.alert('你所提现的金额大于余额，请重新输入');
    	}else{
    		$.toast("操作成功，余额2小时内到你的账户");
    	}
    });
});
//充值按钮
$(document).on('click','#Recharge', function () {
	var vipBlance = $("#vipBlance").val();
    $.prompt('请输入充值金额', function (value) {
    	 var blance = value;//充值金额
    	 $.alert("<input type='password' id='blanPwd' />","请输入支付密码", function () {
    		 var blanPwd = $("#blanPwd").val();
    		 if(!blanPwd){
           	  $.alert("请输入支付密码！");
             }else{
           	  $.post("setVipAdd",{"blance":blance},function(data){
           		  	$.toast("充值成功！");
               			location.reload();
                   		<%
                   		//重新获取session值
                   		VipConsumptionVo convip2 = (VipConsumptionVo)request.getSession().getAttribute("cusVip");
                   		if(convip2 != null){
                   		%>
                   			$("#vipBlance").val(<%=convip2.getVipBlance()%>);
                   		<%
                   		}else{
                   		%>
                   			$("#vipBlance").val("网络出现了点问题，请刷新");
                   		<%
                   		}
                   		%>
           		});
             } 
         });
    });
});  

</script>
	
</body>
</html>