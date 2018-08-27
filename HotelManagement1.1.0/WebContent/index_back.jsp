<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="java.util.*"%>
<%@page import="com.vo.*"%>
<%@page import="com.dao.*"%>
<%@page import="org.jfree.chart.renderer.category.BarRenderer"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@page import="java.awt.Font"%>
<%@page import="org.jfree.chart.axis.ValueAxis"%>
<%@page import="org.jfree.chart.axis.CategoryAxis"%>
<%@page import="org.jfree.chart.plot.CategoryPlot"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.data.category.*"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
.main{
	width:1060px;
	height:540px;
	background-color:white;
}
.main>div{
	width:528px;
	height:268px;
	border:1px solid gray;
	position:relative;
	float:left;
}
#divOne>div{
	width:190px;
	height:90px;
	border:;
	float:left;
	margin:20px 35px 20px 35px;
}
#divOne div img{
	position:relative;
	float:left;
	width:70px;
	height:70px;
	top:17px;
}
.title{
	position:relative;
	float:left;
	left:15px;
}
.data{
	position:relative;
	float:left;
	width:100px;
	height:25px;
	left:12px;
	text-align:center;
	line-height:25px;
	background-color:;
}
</style>
</head>
<body>
	<%
		JfreechartDao jd=new JfreechartDao();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date nextday=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nextday);
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		nextday=calendar.getTime(); //这个时间就是日期往后推一天的结果 ,第二天
		//System.out.println(df.format(new Date()) );
		//System.out.println(df.format(nextday) );
		int moneyToday =jd.getTurnover(df.format(new Date()), df.format(nextday));
		
		int roomCount=jd.getCheck();
		Calendar cale = Calendar.getInstance();
		String firstday, lastday;
		// 获取前月的第一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		firstday = df.format(cale.getTime());
		// 获取前月的最后一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		lastday = df.format(cale.getTime());
		//System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
		
		int moneyMonth=jd.getTurnover(firstday, lastday);
		//数据转换，保留两位小数
		double part=jd.getIn();
		double all=jd.getRoomCount();
		double per=part/all*100;
		DecimalFormat dfs = new DecimalFormat("#.00");
		String str = dfs.format(per);
		
	  //实例饼状图
	  DefaultPieDataset dpd = new DefaultPieDataset();
	  HashMap<String,Integer> map=jd.getCheckCount(df.format(new Date()), df.format(nextday));//当日房间入住百分比
	  HashMap<String,Integer> typeCount=jd.getTypeCount();
	  RoomTypeDAO rtd=new RoomTypeDAO();
      ArrayList<RoomType> list=rtd.getAll();
	    //数据添加
	   for(int i=0;i<list.size();i++){
		   String name=list.get(i).getTypeName();
		   if(map.get(name)==null)
			   dpd.setValue(name,0);
		   else
			   dpd.setValue(name,map.get(name));
		   //System.out.println(name+"+"+map.get(name));
	   }


	  JFreeChart chart1= ChartFactory.createPieChart("本日房间入住百分比",dpd, true, false, false);
	  PiePlot pp = (PiePlot)chart1.getPlot();
	  pp.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",new DecimalFormat("0.0"),new DecimalFormat("0.0%")));

	  String fileName1 = ServletUtilities.saveChartAsPNG(chart1,528,268,session); 
	  //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp） 
	  String url1 = request.getContextPath() + "/DisplayChart?filename=" + fileName1;
	  
	 //实例柱形图，并添加数据
	 DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	 HashMap<String,Float> maptype=jd.getTurnoverType(firstday,lastday);//
	 for(int i=0;i<list.size();i++){
		 String name=list.get(i).getTypeName();
		 dataset1.addValue(maptype.get(name), name,name );
	 }
	 CategoryDataset data= dataset1;  
     JFreeChart chart2 = ChartFactory.createBarChart3D(  
                         "本月各类房间营收额", // 图表标题  
                         "房间类型", // x轴的显示标签  
                         "营收额/月", // y轴的显示标签  
                         data, // 数据集  
                         PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                         true,           // 是否显示图例(对于简单的柱状图必须是false)  
                         false,          // 是否生成工具  
                         false           // 是否生成URL链接  
                         );  
     //从这里开始  
     CategoryPlot plot=chart2.getCategoryPlot();//获取图表区域对象  
     CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
     domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
     domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
     ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
     rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15)); 
     
     chart2.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
     chart2.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体 
     String fileName2 = ServletUtilities.saveChartAsPNG(chart2,528,268,session); 
     //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
     String url2 = request.getContextPath() + "/DisplayChart?filename=" + fileName2;
     //根据文件名去临时目录下寻找该图片，这里的/DisplayChart路径要与配置文件里用户自定义的<url-pattern>一致
     
     //获得各类房间的数量
     HashMap<String,Integer> tpc=jd.getTypeCount();
     DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
     for(int i=0;i<list.size();i++){
		 String name=list.get(i).getTypeName();
		 if(map.get(name)==null)
			 dataset2.addValue(0, name,name );
		 else{
			 double value=(double)map.get(name)/(double)tpc.get(name)*100;
			 dataset2.addValue(value, name,name );
			 System.out.println(map.get(name)+"  "+tpc.get(name));
		 }
	 }
     CategoryDataset data1= dataset2;  
     JFreeChart chart3 = ChartFactory.createBarChart3D(  
                         "本日各类房间入住百分比", // 图表标题  
                         "月份", // x轴的显示标签  
                         "百分比", // y轴的显示标签  
                         data1, // 数据集  
                         PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                         true,           // 是否显示图例(对于简单的柱状图必须是false)  
                         false,          // 是否生成工具  
                         false           // 是否生成URL链接  
                         );  
     CategoryPlot plot1=chart3.getCategoryPlot();//获取图表区域对象  
     CategoryAxis domainAxis1=plot1.getDomainAxis();         //水平底部列表  
     domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
     domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
     ValueAxis rangeAxis1=plot.getRangeAxis();//获取柱状  
     rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15)); 
     
     chart3.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
     chart3.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体 
     String fileName3 = ServletUtilities.saveChartAsPNG(chart3,528,268,session); 
     //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
     String url3 = request.getContextPath() + "/DisplayChart?filename=" + fileName3;
	%>
	<div class="main">
		<div id="divOne">
			<div>
				<img src="img/yens6.png">
				<div class="title"><h3>今日营收额</h3></div>
				<div class="data"><%=moneyToday %>元</div>
			</div>
			<div>
				<img src="img/bed.png">
				<div class="title"><h3>剩余房间数</h3></div>
				<div class="data"><%=roomCount %>个</div>
			</div>
			<div>
				<img src="img/yens7.png">
				<div class="title"><h3>本月营收额</h3></div>
				<div class="data"><%=moneyMonth %>元</div>
			</div>
			<div>
				<img src="img/percent.png">
				<div class="title"><h3>房间入住率</h3></div>
				<div class="data"><%=str %>%</div>
			</div>
		</div>
		<div><img src="<%=url1 %>"/></div>
		<div><img src="<%=url2 %>"/></div>
		<div><img src="<%=url3 %>"/></div>
	</div>
</body>
</html>