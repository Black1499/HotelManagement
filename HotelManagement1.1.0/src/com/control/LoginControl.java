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
 * 登陆控制器
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
		//设置编码格式
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String url=request.getServletPath();
        switch(url) {
        	case "/LoginControl":	//用户登陆
        		//提取用户输入的密码和账号
    			String empAccountNum = request.getParameter("userUid");
    			String empPassword = request.getParameter("userPassword");
    			//System.out.println(empAccountNum+empPassword);
    			EmployeeInfoVo empVo = empBo.getEmpById(empAccountNum, empPassword);
    			//如果为空登陆失败
    			if(empVo == null) {  //登陆失败
    				//System.out.println("登陆失败，重定向到失败页面");
    				//request.setAttribute("msg", "用户名或密码错误");//写一个弹窗提示
    				//out.write("{\"msg\":\"用户名或密码错误\"}");
    				request.setAttribute("msg", "asd");
    				request.getRequestDispatcher("login.jsp").forward(request, response);
    			}else {   //登陆成功
    				request.getSession().setAttribute("empVo",empVo);//写入session
    				//EmployeeInfoVo empVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //获取session值
    				//判断是否为超管
    				if(empVo.getEmpAdmin() == 1) {
    					//System.out.println("我是超级管理员，转发到超级管理员界面");
    					request.getRequestDispatcher("hotel_后台.jsp").forward(request,response);
    				}else {
    					request.getRequestDispatcher("hotel_前台.jsp").forward(request,response);
    				}
    			}
        	break;
        	//用户注销
        	case "/ExctControl":
        		HttpSession session=request.getSession(false); 
            	if(session==null){  //判断session是否为空
            		response.sendRedirect("login.jsp");//重定向到登陆页面
                    return;  
                } 
                //如果不为空，清除session内容 再重定向网页
                session.removeAttribute("empVo");  
            	response.sendRedirect("login.jsp");
        	break;
        	//修改密码
        	case"/UpdatePwd":
        		EmployeeInfoVo empSessionVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //获取session值
        		 String pwd1 = request.getParameter("pwd1");
        		 String pwd3 = request.getParameter("pwd3");
        		 //判断原密码输入是否正确
        		 if(empBo.MD5pwd(pwd1).equals(empSessionVo.getEmpPassword())) {
        			 if(empBo.updataEmpPwd(pwd3, empSessionVo.getEmpId())>0) {
        				 //response.getWriter().print("{\"msg\":\"密码修改成功\"}");
        				 out.write("{\"msg\":\"密码修改成功\"}");
        				 empSessionVo.setEmpPassword(empBo.MD5pwd(pwd1));
        				 request.getSession().setAttribute("empVo",empSessionVo);//重新写入session
        			 }else {
        				 System.out.println("修改失败");
        				 out.print("{\"msg\":\"密码修改失败\"}");
        			 }
        		 }else {
        			 System.out.println("密码验证不正确");
        			 out.print("{\"msg\":\"密码验证失败，请重新输入\"}");
        		 }
        	break;
        	case "/UpdatePhone":  //员工修改电话号码
        		EmployeeInfoVo empPhoneVo = (EmployeeInfoVo)request.getSession().getAttribute("empVo"); //获取session值
        		String phonePwd = request.getParameter("phonePwd");
        		if(empPhoneVo.getEmpPassword().equals(empBo.MD5pwd(phonePwd))) {
        			String phone = request.getParameter("phonetext");
        			if(empBo.updateEmpPhone(empPhoneVo.getEmpId(),phone)>0) {
        				out.print("修改电话成功");
        				empPhoneVo.setEmpPhone(phone);
        				request.getSession().setAttribute("empVo",empPhoneVo);//重新写入session
        			}else {
        				System.out.println("修改失败");
        				out.print("{\"msg\":\"修改失败，请稍后重试\"}");
        			}
        		}else {
        			out.print("{\"msg\":\"密码不正确，请重新输入\"}");
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
