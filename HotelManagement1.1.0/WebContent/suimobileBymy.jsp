<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>关于我们</title>
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
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page page-current">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
                <a class="icon icon-me pull-left open-panel"></a>
                <h1 class="title">房间预定</h1>
            </header>

            <!-- 工具栏 -->
            <nav class="bar bar-tab">
                <a class="tab-item  " href="suimobileIndex.jsp">
                    <span class="icon icon-home"></span>
                    <span class="tab-label ">首页</span>
                </a>
                <a class="tab-item " href="suimobile预定.html">
                    <span class="icon icon-star"></span>
                    <span class="tab-label">预定房间</span>
                </a>
                <a class="tab-item active" href="#">
                    <span class="icon icon-settings"></span>
                    <span class="tab-label">关于我们</span>
                </a>
            </nav>

            <!-- 这里是页面内容区 -->
            <div class="content">
                 关于我们的介绍内容
               
            </div>
        </div>

	<!-- 左侧工具栏 -->
    <!-- popup, panel 等放在这里 -->
    <div class="panel-overlay"></div>
    <!-- Left Panel with Reveal effect -->
    <div class="panel panel-left panel-reveal">
        <div class="content-block">
            <p>这是一个侧栏</p>
            <p></p>
            <!-- Click on link with "close-panel" class will close panel -->
            <p><a href="suimobileLogin.jsp" class="close-panel">注册/登陆</a></p>
            <p><a href="suimobile个人设置.html" class="close-panel">个人设置</a></p>
            <p><a href="#" class="close-panel">我的余额</a></p>
            <p><a href="#" class="close-panel">我的积分</a></p>
            <p><a href="#" class="close-panel">注销登陆</a></p>
        </div>
    </div>


    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>



</body>
</html>