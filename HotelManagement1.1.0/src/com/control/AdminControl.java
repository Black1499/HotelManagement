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
 * 管理员操作控制类
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
        //1、查看员工--查看个人员工，查看所有的员工，2、添加员工，3、修改员工--重置密码，员工其他信息修改，4、删除员工，
        String url = request.getServletPath();
        switch(url) {
        case "/EmpAll"://查看所有员工
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));

			if(empBo.getPage(page, limit)) {
				int count=empAll_dao.getEmpAll().size();//所有的房间数
	        	response.getWriter().println(DataClass.ok(count,empAll_dao.getPage(page, limit)).toJson());
	        	System.out.println(DataClass.ok(count,empAll_dao.getPage(page, limit)).toJson());
			};
        	break;
        case "/EmpAdd": //添加员工
        	String empAccountNum = request.getParameter("empAccountNum");//员工账号
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
        		response.getWriter().write("{\"msg\":\"添加成功\"}");
        		System.out.println("添加成功");
        	}else{
        		response.getWriter().write("{\"msg\":\"添加失败\"}");
        		System.out.println("添加失败");
        	}
        	break;
        case "/EmpPwdUpdate": //重置员工密码
        	int empIdPwd = Integer.parseInt(request.getParameter("empId"));
        	if(empBo.updateEmpPwd(empIdPwd)>0) {
        		response.getWriter().write("{\"msg\":\"重置成功！用户重置密码为123456\"}");
        		System.out.println("重置成功");
        		System.out.println("提示用户重置密码为123456");
        	}else {
        		response.getWriter().write("{\"msg\":\"重置失败\"}");
        		System.out.println("重置失败");
        	}
        	break;
        case "/updateEmp": //修改员工多数信息
        	String empAccountNumUp = request.getParameter("empAccountNum");
        	String empNameUp = request.getParameter("empName");
        	String empImgUp = request.getParameter("empImg");//此处是写与构造函数，并不修
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
        		response.getWriter().write("{\"msg\":\"修改成功\"}");
        		System.out.println("修改成功");
        	}else {
        		response.getWriter().write("{\"msg\":\"修改失败\"}");
        		System.out.println("修改失败");
        	}
        	break;
        case "/Empdel": //删除员工
        	int empId = Integer.parseInt(request.getParameter("empId"));
        	if(empBo.delEmp(empId)>0) {
        		response.getWriter().write("{\"msg\":\"删除成功\"}");
        		System.out.println("删除成功");
        	}else {
        		System.out.println("删除失败");
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
