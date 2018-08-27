package com.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.CustomerInfoBo;
import com.bo.VipConsumptionBo;
import com.util.JsonUtil;
import com.vo.CustomerInfo;
import com.vo.DataClass;
import com.vo.EmployeeInfoVo;
import com.vo.VipConsumptionVo;

/**
 * Servlet implementation class VipControl
 */
@WebServlet(urlPatterns= {"/VipConsump","/VipConsumpById","/VipConsumpAdd","/vipMoneyAdd","/Rechargerecord"})
public class VipControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VipControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VipConsumptionBo vipBo = new VipConsumptionBo();
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String url=request.getServletPath();//获取请求的路径
        switch(url) {
        case "/VipConsump"://查询所有的充值记录
        	int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
        	int count = vipBo.getVipall().size();//所有数据的总和，集合大小
        	String result =DataClass.ok(count,vipBo.getPageVIP(page, limit)).toJson();
        	response.getWriter().print(result);
        	break;
        case "/VipConsumpById"://按名字查询所有的充值记录
        	String name = request.getParameter("name");
        	String vipRs = JsonUtil.toJson(vipBo.getVipById(name));
        	response.getWriter().print(vipRs);
        	break;
        case "/VipConsumpAdd" : //办理VIP
        	String cutName = request.getParameter("cutName");
        	String cutIdNum = request.getParameter("cutIdNum");
        	String cutPhone = request.getParameter("cutPhone");
        	CustomerInfoBo cusBo = new CustomerInfoBo();
        	if(cusBo.setCitVip(cutName, cutIdNum, cutPhone)>0) {
        		out.write("{\"msg\":\"办理成功\"}");
        	}else {
        		System.out.println("办理失败，待修改");
        		out.write("{\"msg\":\"办理失败，请重新查询后再办理\"}");
        	}
        	
        	break;
        case "/vipMoneyAdd": //VIP充值
        	String cutNameMoney = request.getParameter("cutName");
        	int cutId = Integer.parseInt(request.getParameter("cutId"));
        	float  vipRecord = Integer.parseInt(request.getParameter("vipRecord"));
        	int empId = Integer.parseInt(request.getParameter("empId"));
        	if(vipBo.setVipAdd(cutNameMoney, cutId, vipRecord,empId)>0) {
        		System.out.println("充值成功");  //弹窗返回，充值成功
        		out.write("{\"msg\":\"充值成功\"}");
        	}else {
        		System.out.println("充值失败"); //弹窗返回，充值失败
        		out.write("{\"msg\":\"失败成功\"}");
        	}
        	break;
        case "/Rechargerecord"://用户充值记录
        	//查询用户是否登陆
        	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //获取session值
        	if(cusVo != null) {
        		String vipRechar = JsonUtil.toJson(vipBo.getVipByIds(cusVo.getCustomerId()));
        		System.out.println(vipRechar);
            	response.getWriter().print(vipRechar);
        	}else {
        		//不作任何操作
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
