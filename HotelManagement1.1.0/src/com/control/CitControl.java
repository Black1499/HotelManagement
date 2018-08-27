package com.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.CustomerInfoBo;
import com.bo.VipConsumptionBo;
import com.util.JsonUtil;
import com.vo.CustomerInfo;
import com.vo.VipConsumptionVo;

/**
 * 客户操作控制器
 * Servlet implementation class CitControl
 */
@WebServlet(urlPatterns= {"/cusLogin","/Cancellation",
				"/setVipAdd","/cusRegister"})
public class CitControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //实例化客户逻辑操作对象
        CustomerInfoBo cutBo = new CustomerInfoBo();
        VipConsumptionBo vipBo = new VipConsumptionBo();
        String url = request.getServletPath();
        switch(url) {
        case "/cusLogin"://用户登陆
        	String cusId = request.getParameter("cusId");
        	String cusPwd = request.getParameter("cusPwd");
        	CustomerInfo cusUser = cutBo.cusLogin(cusId, cusPwd);
        	if(cusUser!=null) {
				request.getSession().setAttribute("cusUser",cusUser);//写入session
				CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
				if(cusVo!= null) {
					VipConsumptionVo cusvip = cutBo.getVipInfo(cusVo.getCustomerId());
					request.getSession().setAttribute("cusVip",cusvip);//写入session
				}
				request.getRequestDispatcher("suimobileIndex.jsp").forward(request,response);
				//response.sendRedirect("suimobileIndex.html");
				
        	}else {
        		request.getRequestDispatcher("suimobileLogin.html").forward(request,response);
        	}
        	break;
        case "/Cancellation":
        	HttpSession session=request.getSession(false); 
        	if(session==null){  //判断session是否为空
        		response.sendRedirect("suimobileIndex.jsp");//重定向到登陆页面
                return;  
            } 
            //如果不为空，清除session内容 再重定向网页
            session.removeAttribute("cusVip");  
            session.removeAttribute("cusUser");
        	response.sendRedirect("suimobileLogin.html");//回到登陆界面
        	break;
        case "/setVipAdd"://用户充值
        	int blace = Integer.parseInt(request.getParameter("blance"));
        	int a = 0 ;
        	//通过session获取当前用户的编号
        	CustomerInfo cusVos = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
        	a = vipBo.setVipAdd(cusVos.getCustomerId(),blace);
        	if(a>0) {
        		//充值成功，重新写入session
				VipConsumptionVo cusvips = cutBo.getVipInfo(cusVos.getCustomerId());
				request.getSession().setAttribute("cusVip",cusvips);//写入session
        		System.out.println("充值成功");
        	}else {
        		System.out.println("充值失败");
        	}
        	break;
        case "/cusRegister"://用户注册
        	String cusName = request.getParameter("cusName");
        	String password = request.getParameter("Password");
        	String idNum = request.getParameter("idNum");
        	String phone = request.getParameter("phone");
        	//System.out.println(cusName+","+password+","+idNum+","+phone);
        	//注册成功后，自动登陆，执行一次充值
        	int c = 0;
        	c = cutBo.cusRegister(cusName, password, idNum, phone);
        	if(c>0) {
        		System.out.println("注册成功");
        		//开始登陆
        		CustomerInfo cusUsers = cutBo.cusLogin(phone, password);
            	if(cusUsers!=null) {//登陆成功
    				request.getSession().setAttribute("cusUser",cusUsers);//写入session
    				vipBo.setVipAdd(cusUsers.getCustomerId());//执行用户账号初始化
    				if(cusUsers!= null) {
    					//获取账户余额信息
    					VipConsumptionVo cusvip = cutBo.getVipInfo(cusUsers.getCustomerId());
    					request.getSession().setAttribute("cusVip",cusvip);//写入session
    				}
    				request.getRequestDispatcher("suimobileIndex.jsp").forward(request,response);//转发到首页
            	}else {
            		request.getRequestDispatcher("suimobileLogin.html").forward(request,response);//转发到登陆界面
            	}
        	}
        	break;
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
