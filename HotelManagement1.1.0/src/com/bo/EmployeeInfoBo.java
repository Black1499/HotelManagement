package com.bo;

import java.util.ArrayList;
import java.util.List;

import com.dao.EmployeeInfoDao;
import com.vo.EmployeeInfoVo;

/**
 * 员工类业务逻辑层
 * @author 杨万生
 *
 */
public class EmployeeInfoBo {
	EmployeeInfoDao empDao = new EmployeeInfoDao();
	

	public boolean getPage(int page,int limit) {
		ArrayList<EmployeeInfoVo> list=empDao.getPage(page, limit);
		if(list!=null)
			return true;
		else
			return false;
	}

	
	
	/**
	 * 查询员工，用于员工登陆
	 * @param empAccountNum  员工账号
	 * @param empPassword 员工密码
	 * @return 员工实体类
	 */
	
	public EmployeeInfoVo getEmpById(String empAccountNum,String empPassword) {
		EmployeeInfoVo emp = empDao.getEmpById(empAccountNum, empPassword);
		return emp;
	}
	/**
	 * 员工重置密码
	 * @param empAccountNum
	 * @return 大于1成功
	 */
	public int updateEmpPwd(int empId) {
		return empDao.updateEmpPwd(empId);
	}
	
	public String MD5pwd(String pwd) {
		return empDao.MD5pwd(pwd);
	}
	
	
	/**
	 * 员工重置密码
	 * @param empAccountNum
	 * @return 大于1成功
	 */
	public int updateEmpPwd(int empId,String empPwd) {
		return empDao.updateEmpPwd(empId,empPwd);
	}
	
	/**
	 * 员工修改密码
	 * @param empPassword 新密码
	 * @param empId 员工编号
	 * @return 大于1成功
	 */
	public int updataEmpPwd(String empPassword,int empId) {
		return empDao.updataEmpPwd(empPassword, empId);
	}
	
	/**
	 * 员工修改电话号码
	 * @param empId
	 * @return
	 */
	public int updateEmpPhone(int empId,String phone) {
		return empDao.updateEmpPhone(empId,phone);
	}
	
	//超级管理员的操作
	/**
	 * 查看所有员工
	 * @return 员工类list集合
	 */
	public List<EmployeeInfoVo> getEmpAll(){
		return empDao.getEmpAll();
	}
	
	/**
	 * 添加员工
	 * @param empVo 员工实体类
	 * @return 大于1成功
	 */
	public int addEmp(EmployeeInfoVo emp) {
		return empDao.addEmp(emp);
	}
	
	/**
	 * 删除员工
	 * @param empId 员工ID编号
	 * @return 大于1成功
	 */
	public int delEmp(int empId) {
		return empDao.delEmp(empId);
	}
	
	/**
	 * 修改员工信息
	 * @param empId 员工ID编号
	 * @param empVo 员工实体类
	 * @return
	 */
	public int updateEmp(int empId,EmployeeInfoVo empVo ) {
		return  empDao.updateEmp(empId, empVo);
	}
}
