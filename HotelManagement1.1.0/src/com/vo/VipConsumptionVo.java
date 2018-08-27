package com.vo;

import java.util.Date;

/**
 * vip消费表实体类
 * @author 杨万生
 *
 */
public class VipConsumptionVo {
	private int customerId ;
	private String customerName;
	private String customerIdNum;
	private int customerVIP;
	private float vipRecord;
	private String inTime;
	private float vipBlance;
	private float vipIntegral;
	private String empName;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIdNum() {
		return customerIdNum;
	}
	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}
	public int getCustomerVIP() {
		return customerVIP;
	}
	public void setCustomerVIP(int customerVIP) {
		this.customerVIP = customerVIP;
	}
	public float getVipRecord() {
		return vipRecord;
	}
	public void setVipRecord(float vipRecord) {
		this.vipRecord = vipRecord;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public float getVipBlance() {
		return vipBlance;
	}
	public void setVipBlance(float vipBlance) {
		this.vipBlance = vipBlance;
	}
	public float getVipIntegral() {
		return vipIntegral;
	}
	public void setVipIntegral(float vipIntegral) {
		this.vipIntegral = vipIntegral;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "VipConsumptionVo [customerId=" + customerId + ", customerName=" + customerName + ", customerIdNum="
				+ customerIdNum + ", customerVIP=" + customerVIP + ", vipRecord=" + vipRecord + ", inTime=" + inTime
				+ ", vipBlance=" + vipBlance + ", vipIntegral=" + vipIntegral + ", empName=" + empName + "]";
	}
	public VipConsumptionVo(int customerId, String customerName, String customerIdNum, int customerVIP, float vipRecord,
			 float vipBlance, String inTime, float vipIntegral, String empName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerIdNum = customerIdNum;
		this.customerVIP = customerVIP;
		this.vipRecord = vipRecord;
		this.inTime = inTime;
		this.vipBlance = vipBlance;
		this.vipIntegral = vipIntegral;
		this.empName = empName;
	}
	public VipConsumptionVo() {}
}
