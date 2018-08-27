package com.bo;

import java.util.ArrayList;

import com.dao.RoomDao;
import com.util.DBUtil;
import com.vo.Room;

public class RoomBo {
	private RoomDao roomdao=new RoomDao();
	public boolean selectAll() {
		ArrayList<Room> list=roomdao.selectAll();
		if(list!=null)
			return true;
		else
			return false;
	}
	public boolean getPage(int page,int limit) {
		ArrayList<Room> list=roomdao.getPage(page, limit);
		if(list!=null)
			return true;
		else
			return false;
	}
	public boolean findByAvailable(int available) {
		ArrayList<Room> list=roomdao.findByAvailable(available);
		if(list!=null)
			return true;
		else
			return false;
	}
	public boolean findByState(int state) {
		ArrayList<Room> list=roomdao.findByState(state);
		if(list!=null)
			return true;
		else
			return false;
	}
	public boolean findByNum(String roomNum) {
		Room room=roomdao.findByNum(roomNum);
		if(room!=null)
			return true;
		else
			return false;
	}
	public boolean findByType(int typeId) {
		ArrayList<Room> list=roomdao.findByType(typeId);
		if(list!=null)
			return true;
		else
			return false;
	}
	public boolean insertRoom(String roomNum,int typeId,String roomPhone,int roomAvailable,int roomState,String roomRemark) {
		int num=roomdao.insertRoom(roomNum, typeId, roomPhone, roomAvailable, roomState, roomRemark);
		if(num!=0)
			return true;
		else
			return false;
	}
	public boolean deleteRoom(String roomNum) {
		int num=roomdao.deleteRoom(roomNum);
		if(num!=0)
			return true;
		else
			return false;
	}
	public boolean updRoom(String roomNum,int typeId,String roomPhone,int roomAvailable,String roomRemark) {
		int num=roomdao.updRoom(roomNum, typeId, roomPhone, roomAvailable, roomRemark);
		if(num!=0)
			return true;
		else
			return false;
	}
	public boolean updateRoomAvailable(String roomNum,int roomAvailable) {
		int num=roomdao.updateRoomAvailable(roomNum, roomAvailable);
		if(num!=0)
			return true;
		else
			return false;
	}
	public boolean updateRoomState(String roomNum,int roomState) {
		int num=roomdao.updateRoomState(roomNum, roomState);
		if(num!=0)
			return true;
		else
			return false;
	}
}
