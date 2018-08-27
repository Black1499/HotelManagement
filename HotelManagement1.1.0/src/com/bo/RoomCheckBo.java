package com.bo;

import com.dao.RoomCheckDao;

public class RoomCheckBo {
	private RoomCheckDao checkdao=new RoomCheckDao();
	public boolean selectAll() {
		if(checkdao.selectAll()!=null)
			return true;
		else
			return false;
	}
	public boolean findByNum(String roomNum) {
		if(checkdao.findByNum(roomNum)!=null)
			return true;
		else
			return false;
	}
	public boolean findByTime(String checkTime) {
		if(checkdao.findByTime(checkTime)!=null)
			return true;
		else
			return false;
	}
	public boolean insertRoomCheck(String roomNum,int empId,String customerName,String customerPhone,String customerIdNum,String checkTime,int checkHour,String checkRemark) {
		if(checkdao.insertRoomCheck(roomNum, empId, customerName, customerPhone, customerIdNum, checkTime, checkHour, checkRemark)!=0)
			return true;
		else
			return false;
	}
	public boolean updateRoomCheck(String roomNum,int empId,String customerName,String customerPhone,String customerIdNum,String checkTime,int checkHour,String checkRemark,int checkId) {
		if(checkdao.updateRoomCheck(roomNum, empId,customerName, customerPhone, customerIdNum, checkTime, checkHour, checkRemark,checkId)!=0)
			return true;
		else 
			return false;
	}
}
