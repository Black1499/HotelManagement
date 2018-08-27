package com.vo;

public class RoomReserve {
	int reserveId;
	String roomNum;
	String reserveTime;
	String customerName;
	String customerPhone;
	String reserveRemark;
	int empId;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
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
	public String getReserveRemark() {
		return reserveRemark;
	}
	public void setReserveRemark(String reserveRemark) {
		this.reserveRemark = reserveRemark;
	}
	public RoomReserve(int reserveId, String roomNum, int empId, String reserveTime, String customerName, String customerPhone,
			String reserveRemark) {
		super();
		this.reserveId = reserveId;
		this.roomNum = roomNum;
		this.empId = empId;
		this.reserveTime = reserveTime;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.reserveRemark = reserveRemark;
	}
	public RoomReserve() {
		super();
	}
	
}
