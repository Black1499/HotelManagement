package com.bo;

import java.util.List;

import com.dao.VipConsumptionDao;
import com.vo.VipConsumptionVo;

/**
 * vip充值记录表业务逻辑层
 * @author 杨万生
 *
 */
public class VipConsumptionBo {
	VipConsumptionDao vipDao = new VipConsumptionDao();
	/**
	 * 获取所有的充值记录信息
	 * @return
	 */
	public List<VipConsumptionVo> getVipall(){
		return vipDao.getVipall();
	}
	/**
	 * 分页查询
	 * @param page
	 * @param eng
	 * @return
	 */
	public List<VipConsumptionVo> getPageVIP(int page,int eng){
		return vipDao.getPageVIP(page, eng);
	}
	/**
	 * 名字模糊查询用户充值记录
	 * @param name 客户名字
	 * @return list集合
	 */
	public List<VipConsumptionVo> getVipById(String name){
		return vipDao.getVipById(name);
	}
	
	/**
	 * vip客户充值
	 * @param name 客户名字
	 * @param customrId 客户Id编号
	 * @param vipRecord 充值金额
 	 * @param empId 当前登陆的员工编号
	 * @return
	 */
	public int setVipAdd(String name,int customrId,float vipRecord, int empId) {
		return vipDao.setVipAdd(name, customrId, vipRecord, empId);
	}
	
	/**
	 * 客户积分消费
	 * @param customerId 客户编号
	 * @param vipConsumption 消费了的积分
	 * @return
	 */
	public int setVipIntegral(int customerId,int vipConsumption) {
		return vipDao.setVipIntegral(customerId, vipConsumption);
	}
	
	/**
	 * 客户自主充值 
	 * @param customrId 用户编号
	 * @param vipRecord 金额
	 * @return 大于0充值成功
	 */
	public int setVipAdd(int customrId,float vipRecord) {
		return vipDao.setVipAdd(customrId,vipRecord);
	}
	/**
	 * 用户注册，初始化数据
	 * @param customrId 用户编号
	 * @return
	 */
	public int setVipAdd(int customrId) {
		return vipDao.setVipAdd(customrId);
	}
	
	
	/**
	 * 用户充值记录
	 * @param consumId 用户编号
	 * @return VIP充值表集合
	 */
	public List<VipConsumptionVo> getVipByIds(int consumId){
		return vipDao.getVipById(consumId);
	}
}
