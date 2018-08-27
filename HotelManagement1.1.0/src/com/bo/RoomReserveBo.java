package com.bo;

import com.dao.RoomReserveDao;

public class RoomReserveBo {
	private RoomReserveDao reservedao=new RoomReserveDao();
	public boolean selectAll() {
		if(reservedao.selectAll()!=null) 
			return true;
		else
			return false;
	}
	public boolean findByNum(String roomNum) {
		if(reservedao.findByNum(roomNum)!=null)
			return true;
		else
			return false;
	}
	public boolean insertRoomReserve(String roomNum,int empId,String reserveTime,String customerName,String customerPhone,String reserveRemark) {
		if(reservedao.insertRoomReserve(roomNum, empId, reserveTime, customerName, customerPhone, reserveRemark)!=0)
			return true;
		else 
			return false;
	}
	public boolean updateRoomReserve(String roomNum,String reserveTime,String customerName,String customerPhone,String reserveRemark) {
		if(reservedao.updateRoomReserve(roomNum, reserveTime, customerName, customerPhone, reserveRemark)!=0)
			return true;
		else
			return false;
	}
}
