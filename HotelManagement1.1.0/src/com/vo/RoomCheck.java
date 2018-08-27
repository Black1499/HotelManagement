package com.vo;

public class RoomCheck {
	int checkId;
	String roomNum;
	int empId;
	String customerName;
	String customerPhone;
	String customerIdNum;
	String checkTime;
	byte checkHour;
	String checkRemark;
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
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
	public byte getCheckHour() {
		return checkHour;
	}
	public void setCheckHour(byte checkHour) {
		this.checkHour = checkHour;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	public RoomCheck(int checkId, String roomNum, int empId, String customerName, String customerPhone,
			String customerIdNum, String checkTime, byte checkHour, String checkRemark) {
		super();
		this.checkId = checkId;
		this.roomNum = roomNum;
		this.empId = empId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerIdNum = customerIdNum;
		this.checkTime = checkTime;
		this.checkHour = checkHour;
		this.checkRemark = checkRemark;
	}
	public RoomCheck() {
		super();
	}
	
}
