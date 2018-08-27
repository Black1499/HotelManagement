package com.vo;

/**
 * 客户信息表实体类
 * @author 杨万生
 *
 */
public class CustomerInfoVo {
	private int customerId;
	private String customerName;
	private String customerPhone;
	private String customerIdNum;
	private int customerVIP;
	private int customerCount;
	private String customerRemark;
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
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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
	public int getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	@Override
	public String toString() {
		return "CustomerInfoVo [customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerIdNum=" + customerIdNum + ", customerVIP=" + customerVIP
				+ ", customerCount=" + customerCount + ", customerRemark=" + customerRemark + "]";
	}
	public CustomerInfoVo(int customerId, String customerName, String customerPhone, String customerIdNum,
			int customerVIP, int customerCount, String customerRemark) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerIdNum = customerIdNum;
		this.customerVIP = customerVIP;
		this.customerCount = customerCount;
		this.customerRemark = customerRemark;
	}
	public CustomerInfoVo() {}
}
