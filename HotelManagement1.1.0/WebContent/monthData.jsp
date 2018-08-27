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
<%@page import="com.dao.JfreechartDao"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="org.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>
</head>
<body>
	<%	
  		JfreechartDao jd=new JfreechartDao();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //实例柱形图，并添加数据
  		for(int i=1;i<=12;i++){
  			if(i<10){
  				dataset.addValue(jd.getTurnover("2018-0"+i+"-01","2018-0"+(i+1)+"-01"), i+"月",i+"月");
  			}else if(i>10 && i<12){
  				dataset.addValue(jd.getTurnover("2018-"+i+"-01","2018-"+(i+1)+"-01"), i+"月",i+"月");
  			}else{
  				dataset.addValue(jd.getTurnover("2018-"+i+"-01","2018-"+i+"-31"), i+"月",i+"月");
  			}
  		}
  		 CategoryDataset data= dataset;  
         JFreeChart chart = ChartFactory.createBarChart3D(  
                             "2018年每月营收额", // 图表标题  
                             "月份", // x轴的显示标签  
                             "营收额/月", // y轴的显示标签  
                             data, // 数据集  
                             PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                             true,           // 是否显示图例(对于简单的柱状图必须是false)  
                             false,          // 是否生成工具  
                             false           // 是否生成URL链接  
                             );  
         //从这里开始  
         CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
         CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
         chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
         chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体 
         String fileName = ServletUtilities.saveChartAsPNG(chart,650,500,session); 
         //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
         String url = request.getContextPath() + "/DisplayChart?filename=" + fileName;
         //根据文件名去临时目录下寻找该图片，这里的/DisplayChart路径要与配置文件里用户自定义的<url-pattern>一致
         System.out.println(url);
  %>

	<img src="<%= url %>" width="650" height="500">
</body>
</html>