package com.bo;

import java.util.List;

import com.dao.VipConsumptionDao;
import com.vo.VipConsumptionVo;

/**
 * vip��ֵ��¼��ҵ���߼���
 * @author ������
 *
 */
public class VipConsumptionBo {
	VipConsumptionDao vipDao = new VipConsumptionDao();
	/**
	 * ��ȡ���еĳ�ֵ��¼��Ϣ
	 * @return
	 */
	public List<VipConsumptionVo> getVipall(){
		return vipDao.getVipall();
	}
	/**
	 * ��ҳ��ѯ
	 * @param page
	 * @param eng
	 * @return
	 */
	public List<VipConsumptionVo> getPageVIP(int page,int eng){
		return vipDao.getPageVIP(page, eng);
	}
	/**
	 * ����ģ����ѯ�û���ֵ��¼
	 * @param name �ͻ�����
	 * @return list����
	 */
	public List<VipConsumptionVo> getVipById(String name){
		return vipDao.getVipById(name);
	}
	
	/**
	 * vip�ͻ���ֵ
	 * @param name �ͻ�����
	 * @param customrId �ͻ�Id���
	 * @param vipRecord ��ֵ���
 	 * @param empId ��ǰ��½��Ա�����
	 * @return
	 */
	public int setVipAdd(String name,int customrId,float vipRecord, int empId) {
		return vipDao.setVipAdd(name, customrId, vipRecord, empId);
	}
	
	/**
	 * �ͻ���������
	 * @param customerId �ͻ����
	 * @param vipConsumption �����˵Ļ���
	 * @return
	 */
	public int setVipIntegral(int customerId,int vipConsumption) {
		return vipDao.setVipIntegral(customerId, vipConsumption);
	}
	
	/**
	 * �ͻ�������ֵ 
	 * @param customrId �û����
	 * @param vipRecord ���
	 * @return ����0��ֵ�ɹ�
	 */
	public int setVipAdd(int customrId,float vipRecord) {
		return vipDao.setVipAdd(customrId,vipRecord);
	}
	/**
	 * �û�ע�ᣬ��ʼ������
	 * @param customrId �û����
	 * @return
	 */
	public int setVipAdd(int customrId) {
		return vipDao.setVipAdd(customrId);
	}
	
	
	/**
	 * �û���ֵ��¼
	 * @param consumId �û����
	 * @return VIP��ֵ����
	 */
	public List<VipConsumptionVo> getVipByIds(int consumId){
		return vipDao.getVipById(consumId);
	}
}
