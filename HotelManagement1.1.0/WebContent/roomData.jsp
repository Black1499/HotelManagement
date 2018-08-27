<%@page import="java.text.DecimalFormat"%>
<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.*,com.vo.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dao.JfreechartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset,org.jfree.chart.ChartFactory
,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间入住率</title>
</head>
<body>
<%
	JfreechartDao jd=new JfreechartDao();
    DefaultPieDataset dpd = new DefaultPieDataset();//实例饼状图
    //当前各个类型房间的入住数
    HashMap<String,Integer> map=jd.getCheckCount("2018-01-01", "2018-12-31");
    //各个房间的数量
     HashMap<String,Integer> typeCount=jd.getTypeCount();
    RoomTypeDAO rtd=new RoomTypeDAO();
	ArrayList<RoomType> list=rtd.getAll();
    //数据添加
   for(int i=0;i<list.size();i++){
	   String name=list.get(i).getTypeName();
	   dpd.setValue(name,map.get(name));
   }


    JFreeChart chart = ChartFactory.createPieChart("2018年房间入住百分比",dpd, true, false, false);
    PiePlot pp = (PiePlot)chart.getPlot();
    pp.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",new DecimalFormat("0.0"),new DecimalFormat("0.0%")));

    String fileName = ServletUtilities.saveChartAsPNG(chart,650,500,session); 
    //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
    
    String url = request.getContextPath() + "/DisplayChart?filename=" + fileName;
    //根据文件名去临时目录下寻找该图片，这里的/DisplayChart路径要与配置文件里用户自定义的<url-pattern>一致
%>
<img src="<%= url %>" width="650" height="500">
</body>
</html>