package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.MD5;
import com.vo.EmployeeInfoVo;

/**
 * 员工表连接数据类
 * @author 杨万生
 *
 */
public class EmployeeInfoDao {
	
	/**
	 * 员工登陆
	 * @param empAccountNum 员工账号
	 * @param empPassword 员工密码
	 * @return 员工实体类
	 */
	public EmployeeInfoVo getEmpById(String empAccountNum,String empPassword){
		String sql = "select * from employeeInfo where empAccountNum=? and empPassword =? ";
		String pwd = MD5.getMD5(empPassword);//加密员工登陆的密码，验证数据库
		Object[] o = {empAccountNum,pwd};
		ResultSet rs = DBUtil.excuteQuery(sql, o);
		EmployeeInfoVo empVo = null;
		try {
			while(rs.next()) {
			empVo = new EmployeeInfoVo(rs.getInt("empId"),
										rs.getString("empAccountNum"),
										rs.getString("empPassword"),
										rs.getString("empName"),
										rs.getString("empSex"),
										rs.getString("empIdNum"),
										rs.getString("empPhone"),
										rs.getString("empAddress"),
										rs.getInt("empAdmin"),
										rs.getString("empRemark"),
										rs.getString("empImg"));
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return empVo;
	}
	/**
	 * 分页查询
	 * */
	public ArrayList<EmployeeInfoVo> getPage(int page,int limit) {
		int start=(page-1)*limit+1;
		int end=page*limit;
		String sql="select * from(select *,ROW_NUMBER() over(order by empId) rows from employeeInfo ) o " + 
				"where o.rows>=? and o.rows<=?"; 	
		Object[] o= {start,end};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		ArrayList<EmployeeInfoVo> list=new ArrayList<EmployeeInfoVo>();
		EmployeeInfoVo emp=null;
		try {
			while(rs.next()) {
				emp=new EmployeeInfoVo();
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpAccountNum(rs.getString("empAccountNum"));
				emp.setEmpPassword(rs.getString("empPassword"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpIdNum(rs.getString("empIdNum"));
				emp.setEmpPhone(rs.getString("empPhone"));
				emp.setEmpAddress(rs.getString("empAddress"));
				emp.setEmpAdmin(rs.getInt("empAdmin"));
				emp.setEmpRemark(rs.getString("empRemark"));
				emp.setEmpImg(rs.getString("empImg"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 员工密码重置
	 * @param empId 员工编号
	 * @return
	 */
	public int updateEmpPwd(int empId) {
		String pwd = MD5.getMD5("123456");
		String sql = "update employeeInfo set empPassword =? where empId=?";
		Object[] o = {pwd,empId};
		System.out.println(pwd);
		return DBUtil.excuteUpdate(sql, o);
	}
	
	/**
	 * 员工修改密码
	 * @param empPassword 新密码
	 * @param empId 员工编号
	 * @return
	 */
	public int updataEmpPwd(String empPassword,int empId) {
		String sql = "update employeeInfo set empPassword =? where empId=?";
		String pwd = MD5.getMD5(empPassword);//加密密码，写入数据库
		Object[] o = {pwd,empId};
		return DBUtil.excuteUpdate(sql, o);
	}
	

	/**
	 * Admin密码重置
	 * @param empId 员工编号
	 * @return
	 */
	public int updateEmpPwd(int empId,String empPwd) {
		String pwd = MD5.getMD5(empPwd);
		String sql = "update employeeInfo set empPassword =? where empId=?";
		Object[] o = {pwd,empId};
		return DBUtil.excuteUpdate(sql, o);
	}
	
	
	/**
	 * 员工修改电话号码
	 * @param empId 员工编号
	 * @return
	 */
	public int updateEmpPhone(int empId,String phone) {
		String sql = "update employeeInfo set empPhone=? where empId=?";
		Object[] obj = {phone,empId};
		return DBUtil.excuteUpdate(sql, obj);
	}
	/**
	 * 查询所有员工
	 * @return 员工实体类集合
	 */
	public List<EmployeeInfoVo> getEmpAll(){
		List<EmployeeInfoVo> list = new ArrayList(); 
		String sql = "select * from employeeInfo ";
		ResultSet rs = DBUtil.executeQuery(sql);
		EmployeeInfoVo empVo = null;
		try {
			while(rs.next()) {
			empVo = new EmployeeInfoVo(rs.getInt("empId"),
										rs.getString("empAccountNum"),
										rs.getString("empPassword"),
										rs.getString("empName"),
										rs.getString("empSex"),
										rs.getString("empIdNum"),
										rs.getString("empPhone"),
										rs.getString("empAddress"),
										rs.getInt("empAdmin"),
										rs.getString("empRemark"),
										rs.getString("empImg"));
			System.out.println(rs.getString("empImg"));
			list.add(empVo);
			}
			DBUtil.closeConn();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 加密密码
	 * @param pwd
	 * @return
	 */
	public String MD5pwd(String pwd) {
		return MD5.getMD5(pwd);
	}
	
	
	/**
	 * 添加员工
	 * @return 返回大于1成功
	 */
	public int addEmp(EmployeeInfoVo empVo) {
		String sql = "insert into employeeInfo ";
		sql +=" (empAccountNum,empPassword,empName,empSex,empIdNum,empPhone,empAddress,empRemark,empImg)";
		sql +=" values(?,?,?,?,?,?,?,?,?)";
		String pwd = MD5.getMD5("123456");
		Object[] obj = {empVo.getEmpAccountNum(),pwd,empVo.getEmpName(),empVo.getEmpSex(),
						empVo.getEmpIdNum(),empVo.getEmpPhone(),empVo.getEmpAddress(),
						empVo.getEmpRemark(),empVo.getEmpImg()};
		if(DBUtil.excuteUpdate(sql, obj)>0) {
			return 1;
		}else {
			return 0;
		}
	}
	/**
	 * 删除员工
	 * @param empId 员工编码
	 * @return 大于0执行成功
	 */
	public int delEmp(int empId) {
		String sql = "delete from employeeInfo where empId =?";
		Object[] obj = {empId};
		if(DBUtil.excuteUpdate(sql, obj)>0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 修改员工信息
	 * @param empId 员工ID编号
	 * @param empVo 员工实体类
	 * @return
	 */
	public int updateEmp(int empId,EmployeeInfoVo empVo ) {
		String sql = "update employeeInfo set empAccountNum=?,empName=?,";
		sql +=" empIdNum=?,empPhone=?,empAddress=?,empRemark=?,";
		sql +=" empSex=? where empId=?";
		Object[] obj = {
				empVo.getEmpAccountNum(),empVo.getEmpName(),empVo.getEmpIdNum(),
				empVo.getEmpPhone(),empVo.getEmpAddress(),
				empVo.getEmpRemark(),empVo.getEmpSex(),
				empId
		};
		System.out.println(empVo.getEmpSex());
		return DBUtil.excuteUpdate(sql, obj);
	}
	
	
	
	
	
	
	
	
	
	
	public int addEmps(Object[] o) {
		String sql="insert into employeeInfo values(?,?,?,?,?,?,?,?,?,?)";
		if(DBUtil.excuteUpdate(sql, o)>0) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
