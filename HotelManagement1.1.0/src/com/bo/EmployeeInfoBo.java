package com.bo;

import java.util.ArrayList;
import java.util.List;

import com.dao.EmployeeInfoDao;
import com.vo.EmployeeInfoVo;

/**
 * Ա����ҵ���߼���
 * @author ������
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
	 * ��ѯԱ��������Ա����½
	 * @param empAccountNum  Ա���˺�
	 * @param empPassword Ա������
	 * @return Ա��ʵ����
	 */
	
	public EmployeeInfoVo getEmpById(String empAccountNum,String empPassword) {
		EmployeeInfoVo emp = empDao.getEmpById(empAccountNum, empPassword);
		return emp;
	}
	/**
	 * Ա����������
	 * @param empAccountNum
	 * @return ����1�ɹ�
	 */
	public int updateEmpPwd(int empId) {
		return empDao.updateEmpPwd(empId);
	}
	
	public String MD5pwd(String pwd) {
		return empDao.MD5pwd(pwd);
	}
	
	
	/**
	 * Ա����������
	 * @param empAccountNum
	 * @return ����1�ɹ�
	 */
	public int updateEmpPwd(int empId,String empPwd) {
		return empDao.updateEmpPwd(empId,empPwd);
	}
	
	/**
	 * Ա���޸�����
	 * @param empPassword ������
	 * @param empId Ա�����
	 * @return ����1�ɹ�
	 */
	public int updataEmpPwd(String empPassword,int empId) {
		return empDao.updataEmpPwd(empPassword, empId);
	}
	
	/**
	 * Ա���޸ĵ绰����
	 * @param empId
	 * @return
	 */
	public int updateEmpPhone(int empId,String phone) {
		return empDao.updateEmpPhone(empId,phone);
	}
	
	//��������Ա�Ĳ���
	/**
	 * �鿴����Ա��
	 * @return Ա����list����
	 */
	public List<EmployeeInfoVo> getEmpAll(){
		return empDao.getEmpAll();
	}
	
	/**
	 * ���Ա��
	 * @param empVo Ա��ʵ����
	 * @return ����1�ɹ�
	 */
	public int addEmp(EmployeeInfoVo emp) {
		return empDao.addEmp(emp);
	}
	
	/**
	 * ɾ��Ա��
	 * @param empId Ա��ID���
	 * @return ����1�ɹ�
	 */
	public int delEmp(int empId) {
		return empDao.delEmp(empId);
	}
	
	/**
	 * �޸�Ա����Ϣ
	 * @param empId Ա��ID���
	 * @param empVo Ա��ʵ����
	 * @return
	 */
	public int updateEmp(int empId,EmployeeInfoVo empVo ) {
		return  empDao.updateEmp(empId, empVo);
	}
}
