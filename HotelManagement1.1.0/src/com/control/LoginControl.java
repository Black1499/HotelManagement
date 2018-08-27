package com.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.EmployeeInfoBo;
import com.vo.EmployeeInfoVo;

/**
 * ��½������
 * Servlet implementation class LoginControl
 */
@WebServlet(urlPatterns= {"/LoginControl","/ExctControl","/UpdatePwd","/UpdatePhone"})
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginControl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeInfoBo empBo = new EmployeeInfoBo();
		//���ñ����ʽ
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String url=request.getServletPath();
        switch(url) {
        	case "/LoginControl":	//�û���½
        		//��ȡ�û������������˺�
    			String empAccountNum = request.getParameter("userUid");
    			String empPassword = request.getParameter("userPassword");
    			//System.out.println(empAccountNum+empPassword);
    			EmployeeInfoVo empVo = empBo.getEmpById(empAccountNum, empPassword);
    			//���Ϊ�յ�½ʧ��
    			if(empVo == null) {  //��½ʧ��
    				//System.out.println("��½ʧ�ܣ��ض���ʧ��ҳ��");
    				//request.setAttribute("msg", "�û������������");//дһ��������ʾ
    				//out.write("{\"msg\":\"�û������������\"}");
    				request.setAttribute("msg", "asd");
    				request.getRequestDispatcher("login.jsp").forward(request, response);
    			}else {   //��½�ɹ�
    				request.getSession().setAttribute("empVo",empVo);//д��session
    				//EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //��ȡsessionֵ
    				//�ж��Ƿ�Ϊ����
    				if(empVo.getEmpAdmin() == 1) {
    					//System.out.println("���ǳ�������Ա��ת������������Ա����");
    					request.getRequestDispatcher("hotel_��̨.jsp").forward(request,response);
    				}else {
    					request.getRequestDispatcher("hotel_ǰ̨.jsp").forward(request,response);
    				}
    			}
        	break;
        	//�û�ע��
        	case "/ExctControl":
        		HttpSession session=request.getSession(false); 
            	if(session==null){  //�ж�session�Ƿ�Ϊ��
            		response.sendRedirect("login.jsp");//�ض��򵽵�½ҳ��
                    return;  
                } 
                //�����Ϊ�գ����session���� ���ض�����ҳ
                session.removeAttribute("empVo");  
            	response.sendRedirect("login.jsp");
        	break;
        	//�޸�����
        	case"/UpdatePwd":
        		EmployeeInfoVo empSessionVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //��ȡsessionֵ
        		 String pwd1 = request.getParameter("pwd1");
        		 String pwd3 = request.getParameter("pwd3");
        		 //�ж�ԭ���������Ƿ���ȷ
        		 if(empBo.MD5pwd(pwd1).equals(empSessionVo.getEmpPassword())) {
        			 if(empBo.updataEmpPwd(pwd3, empSessionVo.getEmpId())>0) {
        				 //response.getWriter().print("{\"msg\":\"�����޸ĳɹ�\"}");
        				 out.write("{\"msg\":\"�����޸ĳɹ�\"}");
        				 empSessionVo.setEmpPassword(empBo.MD5pwd(pwd1));
        				 request.getSession().setAttribute("empVo",empSessionVo);//����д��session
        			 }else {
        				 System.out.println("�޸�ʧ��");
        				 out.print("{\"msg\":\"�����޸�ʧ��\"}");
        			 }
        		 }else {
        			 System.out.println("������֤����ȷ");
        			 out.print("{\"msg\":\"������֤ʧ�ܣ�����������\"}");
        		 }
        	break;
        	case "/UpdatePhone":  //Ա���޸ĵ绰����
        		EmployeeInfoVo empPhoneVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //��ȡsessionֵ
        		String phonePwd = request.getParameter("phonePwd");
        		if(empPhoneVo.getEmpPassword().equals(empBo.MD5pwd(phonePwd))) {
        			String phone = request.getParameter("phonetext");
        			if(empBo.updateEmpPhone(empPhoneVo.getEmpId(),phone)>0) {
        				out.print("�޸ĵ绰�ɹ�");
        				empPhoneVo.setEmpPhone(phone);
        				request.getSession().setAttribute("empVo",empPhoneVo);//����д��session
        			}else {
        				System.out.println("�޸�ʧ��");
        				out.print("{\"msg\":\"�޸�ʧ�ܣ����Ժ�����\"}");
        			}
        		}else {
        			out.print("{\"msg\":\"���벻��ȷ������������\"}");
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
