<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="com.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
	VipConsumptionVo convip = (VipConsumptionVo)request.getSession().getAttribute("cusVip"); //获取session值

%>
<meta charset="UTF-8">
<title>六月酒店，客户端</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="js/suimobile/css/sm.min.css" />
<link rel="stylesheet" href="js/suimobile/css/sm-extend.min.css" />
<style>
	.swiper-slide img{
		width:600px;
		height:200px;
	}
</style>
</head>
<body>
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page page-current">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                <a class="icon icon-me pull-left open-panel"></a>
                <h1 class="title">六月酒店</h1>
            </header>

            <!-- 工具栏 -->
            <nav class="bar bar-tab">
                <a class="tab-item active " href="#">
                    <span class="icon icon-home"></span>
                    <span class="tab-label ">首页</span>
                </a>
                <a class="tab-item " href="suiMessage.jsp">
                    <span class="icon icon-star"></span>
                    <span class="tab-label">我的消息</span>
                </a>
                <a class="tab-item" href="suiReserve.jsp">
                    <span class="icon icon-edit"></span>
                    <span class="tab-label">快速预定</span>
                </a>
            </nav>

            <!-- 这里是页面内容区 -->
            <div class="content" id="condiv">
              <div class="swiper-container" data-space-between='10'>
			    <div class="swiper-wrapper">
			      <div class="swiper-slide"><img src="img/timg(1).jpg" alt=""></div>
			      <div class="swiper-slide"><img src="img/timg(2).jpg" alt=""></div>
			      <div class="swiper-slide"><img src="img/timg(3).jpg" alt=""></div>
			      <div class="swiper-slide"><img src="img/timg(4).jpg" alt=""></div>
			      <div class="swiper-slide"><img src="img/timg(5).jpg" alt=""></div>
			    </div>
			    <div class="swiper-pagination"></div>
			  </div>
               
            </div>
        </div>

	<!-- 左侧工具栏 -->
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
    <!-- Left Panel with Reveal effect -->
    <div class="panel panel-left panel-reveal">
        <div class="content-block">
            <p>快捷功能</p>
            <p></p>
            <!-- Click on link with "close-panel" class will close panel -->
            <p><a href="suimobileLogin.html" class="close-panel">注册/登陆</a>&nbsp;&nbsp;&nbsp;
				<%
					if(cusVo != null){
					%>	<%=cusVo.getCustomerName() %> 已登录
				<%	
					}else{
				%>
					未登录
				<% 		
					}
				%>

			</p>
            <p><a href="suimobileById.jsp" class="close-panel">个人设置</a></p>
            <p><a href="suimobilevipBlance.jsp" class="close-panel">我的余额</a>&nbsp;&nbsp;&nbsp;
				<%
					if(convip != null){
				%>	<%=convip.getVipBlance() %>
				<%	
					}else{
				%>
					0
				<% 		
					}
				%>
			</p>
            <p><a href="#" class="close-panel">我的积分</a>&nbsp;&nbsp;&nbsp;
				<%
					if(convip != null){
				%>	<%=convip.getVipIntegral() %>
				<%	
					}else{
				%>
					0
				<% 		
					}
				%>
			</p>
            <p><a href="Cancellation" class="close-panel">注销登陆</a></p>
        </div>
    </div>


<script type='text/javascript' src='js/suimobile/js/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/suimobile/js/sm-extend.min.js' charset='utf-8'></script>

    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type="text/javascript">
    	var card={
    		blind:function(){
    			$.post("RoomTypeTool?action=getAll",{},function(d){
    				var html="";
    				html+="<div class='card'>";
    				html+="<div class='card-content'>";
    				html+="<div class='list-block media-list'>";
    				html+="<ul>";
    				for(var i=0;i<d.length;i++){
    					html+="<li class='item-content'>";
    					html+="<div class='item-media'>";
    					html+="<img src='img/"+d[i].typeTimg+"' width='44'>";
    					html+="</div>";
    					html+="<div class='item-inner'>";
    					html+="<div class='item-title-row'>";
    					html+="<div class='item-title'>"+d[i].typeName+"</div>";
    					html+="</div>";
    					html+="<div class='item-subtitle'>￥"+d[i].typePrice+"/天</div>";
    					html+="</div>";
    					html+="</li>";
    				}
    				
    				html+="</ul>";
    				html+="</div>";
    				html+="</div>";
    				html+="</div> ";
    				
    				$("#condiv").append(html);
    			});
    		},
    		init:function(){
    			card.blind();
    		}
    	}
    	card.init();
    </script>
</body>
</html>