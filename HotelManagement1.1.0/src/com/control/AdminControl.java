package com.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.EmployeeInfoBo;
import com.dao.EmployeeInfoDao;
import com.util.JsonUtil;
import com.vo.DataClass;
import com.vo.EmployeeInfoVo;

/**
 * ����Ա����������
 * Servlet implementation class AdminControl
 */
@WebServlet(urlPatterns= {"/EmpAll","/EmpAdd","/EmpPwdUpdate","/Empdel","/updateEmp"})
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeInfoBo empBo = new EmployeeInfoBo();
    	EmployeeInfoDao empAll_dao = new EmployeeInfoDao();
        //1���鿴Ա��--�鿴����Ա�����鿴���е�Ա����2�����Ա����3���޸�Ա��--�������룬Ա��������Ϣ�޸ģ�4��ɾ��Ա����
        String url = request.getServletPath();
        switch(url) {
        case "/EmpAll"://�鿴����Ա��
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));

			if(empBo.getPage(page, limit)) {
				int count=empAll_dao.getEmpAll().size();//���еķ�����
	        	response.getWriter().println(DataClass.ok(count,empAll_dao.getPage(page, limit)).toJson());
	        	System.out.println(DataClass.ok(count,empAll_dao.getPage(page, limit)).toJson());
			};
        	break;
        case "/EmpAdd": //���Ա��
        	String empAccountNum = request.getParameter("empAccountNum");//Ա���˺�
        	String empName = request.getParameter("empName");
        	String empPwd = "123456";
        	String empSex = request.getParameter("empSex");
        	String empIdNum = request.getParameter("empIdNum");
        	String empPhone = request.getParameter("empPhone");
        	String empAddress = request.getParameter("empAddress");
        	String empRemark = request.getParameter("empRemark");
        	String empImg = request.getParameter("empImg");
        	
        	EmployeeInfoVo empAdd = new EmployeeInfoVo(
        			empAccountNum,empPwd,empName,empSex,empIdNum,empPhone,
        			empAddress,empRemark,empImg
        			);
        	if(empBo.addEmp(empAdd)>0) {
        		response.getWriter().write("{\"msg\":\"��ӳɹ�\"}");
        		System.out.println("��ӳɹ�");
        	}else{
        		response.getWriter().write("{\"msg\":\"���ʧ��\"}");
        		System.out.println("���ʧ��");
        	}
        	break;
        case "/EmpPwdUpdate": //����Ա������
        	int empIdPwd = Integer.parseInt(request.getParameter("empId"));
        	if(empBo.updateEmpPwd(empIdPwd)>0) {
        		response.getWriter().write("{\"msg\":\"���óɹ����û���������Ϊ123456\"}");
        		System.out.println("���óɹ�");
        		System.out.println("��ʾ�û���������Ϊ123456");
        	}else {
        		response.getWriter().write("{\"msg\":\"����ʧ��\"}");
        		System.out.println("����ʧ��");
        	}
        	break;
        case "/updateEmp": //�޸�Ա��������Ϣ
        	String empAccountNumUp = request.getParameter("empAccountNum");
        	String empNameUp = request.getParameter("empName");
        	String empImgUp = request.getParameter("empImg");//�˴���д�빹�캯����������
        	String empIdNumUp = request.getParameter("empIdNum");
        	String empPhoneUp = request.getParameter("empPhone");
        	String empAddressUp = request.getParameter("empAddress");
        	String empRemarkUp = request.getParameter("empRemark");
        	String empSexUp = request.getParameter("empSex");
        	int empIdUp = Integer.parseInt(request.getParameter("empId")); 
        	EmployeeInfoVo empUpda = new EmployeeInfoVo(
        			empAccountNumUp,empNameUp,empSexUp,empIdNumUp,
        			empPhoneUp,empAddressUp,empRemarkUp,empImgUp);
        	System.out.println(empSexUp);
        	if(empBo.updateEmp(empIdUp, empUpda)>0) {
        		response.getWriter().write("{\"msg\":\"�޸ĳɹ�\"}");
        		System.out.println("�޸ĳɹ�");
        	}else {
        		response.getWriter().write("{\"msg\":\"�޸�ʧ��\"}");
        		System.out.println("�޸�ʧ��");
        	}
        	break;
        case "/Empdel": //ɾ��Ա��
        	int empId = Integer.parseInt(request.getParameter("empId"));
        	if(empBo.delEmp(empId)>0) {
        		response.getWriter().write("{\"msg\":\"ɾ���ɹ�\"}");
        		System.out.println("ɾ���ɹ�");
        	}else {
        		System.out.println("ɾ��ʧ��");
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
