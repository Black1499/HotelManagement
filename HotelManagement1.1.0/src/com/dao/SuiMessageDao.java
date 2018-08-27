package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBUtil;
import com.vo.CheckOut;
import com.vo.RoomCheck;
import com.vo.RoomReserve;

public class SuiMessageDao {
	public ArrayList<RoomReserve> findReserve(String name,String phone){
		ArrayList<RoomReserve> list=new ArrayList<RoomReserve>();
		RoomReserve roomReserve=null;
		String sql="select * from roomReserve where customerName=? and customerPhone=?";
		Object[] o= {name,phone};
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
				list.add(roomReserve);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<RoomCheck> findCheck(String name,String phone){
		String sql="select * from roomCheck where customerName=? and customerPhone=?";
		ArrayList<RoomCheck> list=new ArrayList<RoomCheck>();
		RoomCheck roomCheck=null;
		Object[] o= {name,phone};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		try {
			while(rs.next()) {
				roomCheck=new RoomCheck();
				roomCheck.setCheckId(rs.getInt(1));
				roomCheck.setRoomNum(rs.getString(2));
				roomCheck.setEmpId(rs.getInt(3));
				roomCheck.setCustomerName(rs.getString(4));
				roomCheck.setCustomerPhone(rs.getString(5));
				roomCheck.setCustomerIdNum(rs.getString(6));
				roomCheck.setCheckTime(rs.getString(7));
				roomCheck.setCheckHour(rs.getByte(8));
				roomCheck.setCheckRemark(rs.getString(9));
				list.add(roomCheck);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<CheckOut> findOut(String name,String phone) {
		String sql="select * from checkOut where roomNum in (select roomNum from roomCheck where customerName=? and customerPhone=?)";
		Object[] o= {name,phone};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		ArrayList<CheckOut> list=new ArrayList<CheckOut>();
		CheckOut co=null;
		try {
			while(rs.next()) {
				co=new CheckOut();
				co.setOutId(rs.getInt(1));
				co.setRoomNum(rs.getString(2));
				co.setEmpId(rs.getInt(3));
				co.setOutTime(rs.getString(4));
				co.setOutDiscount(rs.getFloat(5));
				co.setOutRecivable(rs.getFloat(6));
				co.setOutActual(rs.getFloat(7));
				co.setOutRemark(rs.getString(8));
				list.add(co);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
