package com.vo;

public class CustomerCheckInfo {
	String roomNum;
	int empId;
	String customerName;
	String customerPhone;
	String customerIdNum;
	String checkTime;
	String outTime;
	int checkHour;
	float recivable;
	float dicount;
	float actual;
	String remark;
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public int getCheckHour() {
		return checkHour;
	}
	public void setCheckHour(int checkHour) {
		this.checkHour = checkHour;
	}
	public float getRecivable() {
		return recivable;
	}
	public void setRecivable(float recivable) {
		this.recivable = recivable;
	}
	public float getDicount() {
		return dicount;
	}
	public void setDicount(float dicount) {
		this.dicount = dicount;
	}
	public float getActual() {
		return actual;
	}
	public void setActual(float actual) {
		this.actual = actual;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public CustomerCheckInfo(String roomNum, int empId, String customerName, String customerPhone, String customerIdNum,
			String checkTime, String outTime, int checkHour, float recivable, float dicount, float actual,
			String remark) {
		super();
		this.roomNum = roomNum;
		this.empId = empId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerIdNum = customerIdNum;
		this.checkTime = checkTime;
		this.outTime = outTime;
		this.checkHour = checkHour;
		this.recivable = recivable;
		this.dicount = dicount;
		this.actual = actual;
		this.remark = remark;
	}
	public CustomerCheckInfo() {
		super();
	}
	
}
