package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBUtil;
import com.vo.RoomReserve;

public class RoomReserveDao {
	public ArrayList<RoomReserve> selectAll(){
		ArrayList<RoomReserve> list=new ArrayList<RoomReserve>();
		RoomReserve roomReserve=null;
		String sql="select * from roomReserve";
		ResultSet rs=DBUtil.executeQuery(sql);
		try {
			while(rs.next()) {
				roomReserve=new RoomReserve();
				roomReserve.setReserveId(rs.getInt(1));
				roomReserve.setRoomNum(rs.getString(2));
				roomReserve.setEmpId(rs.getInt(3));
				roomReserve.setReserveTime(rs.getString(4));
				roomReserve.setCustomerName((rs.getString(5)));
				roomReserve.setCustomerPhone(rs.getString(6));
				roomReserve.setReserveRemark(rs.getString(7));
				list.add(roomReserve);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public RoomReserve findByNum(String roomNum){
		RoomReserve roomReserve=null;
		String sql="select * from roomReserve where roomNum=?";
		Object[] o= {roomNum};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		try {
			while(rs.next()) {
				roomReserve=new RoomReserve();
				roomReserve.setReserveId(rs.getInt(1));
				roomReserve.setRoomNum(rs.getString(2));
				roomReserve.setEmpId(rs.getInt(3));
				roomReserve.setReserveTime(rs.getString(4));
				roomReserve.setCustomerName((rs.getString(5)));
				roomReserve.setCustomerPhone(rs.getString(6));
				roomReserve.setReserveRemark(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomReserve;
	}
	public int insertRoomReserve(String roomNum,int empId,String reserveTime,String customerName,String customerPhone,String reserveReamark) {
		int num=0;
		String sql="insert into roomReserve values(?,?,?,?,?,?)";
		Object[] o= {roomNum,empId,reserveTime,customerName,customerPhone,reserveReamark};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	public int updateRoomReserve(String roomNum,String reserveTime,String customerName,String customerPhone,String reserveRemark) {
		int num=0;
		String sql="update roomReserve set reserveTime=?,customerName=?,customerPhone=?,reserveRemark=? where roomNum=?";
		Object[] o= {reserveTime,customerName,customerPhone,reserveRemark,roomNum};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
}
