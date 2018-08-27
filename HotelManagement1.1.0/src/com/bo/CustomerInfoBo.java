package com.bo;

import java.util.List;

import com.dao.CustomerInfoDao;
import com.vo.CustomerInfo;
import com.vo.CustomerInfoVo;
import com.vo.VipConsumptionVo;

/**
 * �ͻ���Ϣ��ҵ���߼���
 * @author ������
 *
 */
public class CustomerInfoBo {
	CustomerInfoDao cuDao = new CustomerInfoDao();
	/**
	 * ��ѯ�����пͻ���Ϣ
	 * @return list����
	 */
	public List<CustomerInfoVo> getCtiAll(){
		return cuDao.getCtiAll();
	}
	
	
	/**
	 * ����vip ����ȱһ����
	 * @param customerName �ͻ�����
	 * @param customerIdNum �ͻ�֤����
	 * @param customerPhone �ͻ��绰
	 * @return
	 */
	public int setCitVip(String customerName,String customerIdNum,String customerPhone) {
		return cuDao.setCitVip(customerName, customerIdNum, customerPhone);
	}
	/**
	 * ��ѯָ�����VIP
	 * */
	public boolean judgeVIP(int customerId){
		if(cuDao.judgeVIP(customerId)!=null)
			return true;
		else
			return false;
	}
	
	public boolean findByNamePhone(String name,String phone){
		if(cuDao.findByNamePhone(name,phone)!=null)
			return true;
		else
			return false;
	}
	
	public boolean findById(int customerId){
		if(cuDao.findById(customerId)!=null)
			return true;
		else
			return false;
	}
	
	public boolean findByName(String customerName){
		if(cuDao.findByName(customerName)!=null)
			return true;
		else
			return false;
	}
	
	/**
	 * �û���½
	 * @param cusId
	 * @param cusPwd
	 * @return
	 */
	public CustomerInfo cusLogin(String cusId,String cusPwd) {
		return cuDao.cusLongin(cusId, cusPwd);
	}
	
	/**
	 * �ͻ��������Ϣ
	 * @param cusId �û����
	 * @return
	 */
	public VipConsumptionVo getVipInfo(int cusId) {
		return cuDao.getVipInfo(cusId);
	}
	/**
	 * �û�עע��
	 * @param name �û�����
	 * @param password �û�����
	 * @param idNum �û�֤����
	 * @param phone �û��绰
	 * @return ����1ע��ɹ�
	 */
	public int cusRegister(String name , String password,String idNum ,String phone) {
		return cuDao.cusRegister(name, password, idNum, phone);
	}
	
}
