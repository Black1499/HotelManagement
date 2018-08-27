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
 * �ͻ�����������
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
		//�����ʽ
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //ʵ�����ͻ��߼���������
        CustomerInfoBo cutBo = new CustomerInfoBo();
        VipConsumptionBo vipBo = new VipConsumptionBo();
        String url = request.getServletPath();
        switch(url) {
        case "/cusLogin"://�û���½
        	String cusId = request.getParameter("cusId");
        	String cusPwd = request.getParameter("cusPwd");
        	CustomerInfo cusUser = cutBo.cusLogin(cusId, cusPwd);
        	if(cusUser!=null) {
				request.getSession().setAttribute("cusUser",cusUser);//д��session
				CustomerInfo cusVo = (CustomerInfo)request.getSession().getAttribute("cusUser"); //��ȡsessionֵ
				if(cusVo!= null) {
					VipConsumptionVo cusvip = cutBo.getVipInfo(cusVo.getCustomerId());
					request.getSession().setAttribute("cusVip",cusvip);//д��session
				}
				request.getRequestDispatcher("suimobileIndex.jsp").forward(request,response);
				//response.sendRedirect("suimobileIndex.html");
				
        	}else {
        		request.getRequestDispatcher("suimobileLogin.html").forward(request,response);
        	}
        	break;
        case "/Cancellation":
        	HttpSession session=request.getSession(false); 
        	if(session==null){  //�ж�session�Ƿ�Ϊ��
        		response.sendRedirect("suimobileIndex.jsp");//�ض��򵽵�½ҳ��
                return;  
            } 
            //�����Ϊ�գ����session���� ���ض�����ҳ
            session.removeAttribute("cusVip");  
            session.removeAttribute("cusUser");
        	response.sendRedirect("suimobileLogin.html");//�ص���½����
        	break;
        case "/setVipAdd"://�û���ֵ
        	int blace = Integer.parseInt(request.getParameter("blance"));
        	int a = 0 ;
        	//ͨ��session��ȡ��ǰ�û��ı��
        	CustomerInfo cusVos = (CustomerInfo)request.getSession().getAttribute("cusUser"); //��ȡsessionֵ
        	a = vipBo.setVipAdd(cusVos.getCustomerId(),blace);
        	if(a>0) {
        		//��ֵ�ɹ�������д��session
				VipConsumptionVo cusvips = cutBo.getVipInfo(cusVos.getCustomerId());
				request.getSession().setAttribute("cusVip",cusvips);//д��session
        		System.out.println("��ֵ�ɹ�");
        	}else {
        		System.out.println("��ֵʧ��");
        	}
        	break;
        case "/cusRegister"://�û�ע��
        	String cusName = request.getParameter("cusName");
        	String password = request.getParameter("Password");
        	String idNum = request.getParameter("idNum");
        	String phone = request.getParameter("phone");
        	//System.out.println(cusName+","+password+","+idNum+","+phone);
        	//ע��ɹ����Զ���½��ִ��һ�γ�ֵ
        	int c = 0;
        	c = cutBo.cusRegister(cusName, password, idNum, phone);
        	if(c>0) {
        		System.out.println("ע��ɹ�");
        		//��ʼ��½
        		CustomerInfo cusUsers = cutBo.cusLogin(phone, password);
            	if(cusUsers!=null) {//��½�ɹ�
    				request.getSession().setAttribute("cusUser",cusUsers);//д��session
    				vipBo.setVipAdd(cusUsers.getCustomerId());//ִ���û��˺ų�ʼ��
    				if(cusUsers!= null) {
    					//��ȡ�˻������Ϣ
    					VipConsumptionVo cusvip = cutBo.getVipInfo(cusUsers.getCustomerId());
    					request.getSession().setAttribute("cusVip",cusvip);//д��session
    				}
    				request.getRequestDispatcher("suimobileIndex.jsp").forward(request,response);//ת������ҳ
            	}else {
            		request.getRequestDispatcher("suimobileLogin.html").forward(request,response);//ת������½����
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
