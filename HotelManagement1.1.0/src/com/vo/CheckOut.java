package com.vo;

public class CheckOut {
	int outId;
	String roomNum;
	int empId;
	String outTime;
	float outDiscount;
	float outRecivable;
	float outActual;
	String outRemark;
	public int getOutId() {
		return outId;
	}
	public void setOutId(int outId) {
		this.outId = outId;
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
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public float getOutDiscount() {
		return outDiscount;
	}
	public void setOutDiscount(float outDiscount) {
		this.outDiscount = outDiscount;
	}
	public float getOutRecivable() {
		return outRecivable;
	}
	public void setOutRecivable(float outRecivable) {
		this.outRecivable = outRecivable;
	}
	public float getOutActual() {
		return outActual;
	}
	public void setOutActual(float outActual) {
		this.outActual = outActual;
	}
	public String getOutRemark() {
		return outRemark;
	}
	public void setOutRemark(String outRemark) {
		this.outRemark = outRemark;
	}
	public CheckOut(int outId, String roomNum, int empId, String outTime, float outDiscount, float outRecivable,
			float outActual, String outRemark) {
		super();
		this.outId = outId;
		this.roomNum = roomNum;
		this.empId = empId;
		this.outTime = outTime;
		this.outDiscount = outDiscount;
		this.outRecivable = outRecivable;
		this.outActual = outActual;
		this.outRemark = outRemark;
	}
	public CheckOut() {
		super();
	}
	
}
