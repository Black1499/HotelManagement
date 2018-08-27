package com.bo;

import java.util.List;

import com.dao.CustomerInfoDao;
import com.vo.CustomerInfo;
import com.vo.CustomerInfoVo;
import com.vo.VipConsumptionVo;

/**
 * 客户信息表业务逻辑层
 * @author 杨万生
 *
 */
public class CustomerInfoBo {
	CustomerInfoDao cuDao = new CustomerInfoDao();
	/**
	 * 查询出所有客户信息
	 * @return list集合
	 */
	public List<CustomerInfoVo> getCtiAll(){
		return cuDao.getCtiAll();
	}
	
	
	/**
	 * 办理vip 参数缺一不可
	 * @param customerName 客户姓名
	 * @param customerIdNum 客户证件号
	 * @param customerPhone 客户电话
	 * @return
	 */
	public int setCitVip(String customerName,String customerIdNum,String customerPhone) {
		return cuDao.setCitVip(customerName, customerIdNum, customerPhone);
	}
	/**
	 * 查询指定编号VIP
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
	 * 用户登陆
	 * @param cusId
	 * @param cusPwd
	 * @return
	 */
	public CustomerInfo cusLogin(String cusId,String cusPwd) {
		return cuDao.cusLongin(cusId, cusPwd);
	}
	
	/**
	 * 客户的余额信息
	 * @param cusId 用户编号
	 * @return
	 */
	public VipConsumptionVo getVipInfo(int cusId) {
		return cuDao.getVipInfo(cusId);
	}
	/**
	 * 用户注注册
	 * @param name 用户名字
	 * @param password 用户密码
	 * @param idNum 用户证件号
	 * @param phone 用户电话
	 * @return 大于1注册成功
	 */
	public int cusRegister(String name , String password,String idNum ,String phone) {
		return cuDao.cusRegister(name, password, idNum, phone);
	}
	
}
