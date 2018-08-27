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
        String url=request.getServletPath();//��ȡ�����·��
        switch(url) {
        case "/VipConsump"://��ѯ���еĳ�ֵ��¼
        	int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
        	int count = vipBo.getVipall().size();//�������ݵ��ܺͣ����ϴ�С
        	String result =DataClass.ok(count,vipBo.getPageVIP(page, limit)).toJson();
        	response.getWriter().print(result);
        	break;
        case "/VipConsumpById"://�����ֲ�ѯ���еĳ�ֵ��¼
        	String name = request.getParameter("name");
        	String vipRs = JsonUtil.toJson(vipBo.getVipById(name));
        	response.getWriter().print(vipRs);
        	break;
        case "/VipConsumpAdd" : //����VIP
        	String cutName = request.getParameter("cutName");
        	String cutIdNum = request.getParameter("cutIdNum");
        	String cutPhone = request.getParameter("cutPhone");
        	CustomerInfoBo cusBo = new CustomerInfoBo();
        	if(cusBo.setCitVip(cutName, cutIdNum, cutPhone)>0) {
        		out.write("{\"msg\":\"����ɹ�\"}");
        	}else {
        		System.out.println("����ʧ�ܣ����޸�");
        		out.write("{\"msg\":\"����ʧ�ܣ������²�ѯ���ٰ���\"}");
        	}
        	
        	break;
        case "/vipMoneyAdd": //VIP��ֵ
        	String cutNameMoney = request.getParameter("cutName");
        	int cutId = Integer.parseInt(request.getParameter("cutId"));
        	float  vipRecord = Integer.parseInt(request.getParameter("vipRecord"));
        	int empId = Integer.parseInt(request.getParameter("empId"));
        	if(vipBo.setVipAdd(cutNameMoney, cutId, vipRecord,empId)>0) {
        		System.out.println("��ֵ�ɹ�");  //�������أ���ֵ�ɹ�
        		out.write("{\"msg\":\"��ֵ�ɹ�\"}");
        	}else {
        		System.out.println("��ֵʧ��"); //�������أ���ֵʧ��
        		out.write("{\"msg\":\"ʧ�ܳɹ�\"}");
        	}
        	break;
        case "/Rechargerecord"://�û���ֵ��¼
        	//��ѯ�û��Ƿ��½
        	CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //��ȡsessionֵ
        	if(cusVo != null) {
        		String vipRechar = JsonUtil.toJson(vipBo.getVipByIds(cusVo.getCustomerId()));
        		System.out.println(vipRechar);
            	response.getWriter().print(vipRechar);
        	}else {
        		//�����κβ���
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
