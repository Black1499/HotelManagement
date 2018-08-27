package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBUtil;
import com.vo.RoomCheck;

public class RoomCheckDao {
	/*
	 * 查询所有的入住信息
	 */
	public ArrayList<RoomCheck> selectAll(){
		String sql="select * from roomCheck";
		ArrayList<RoomCheck> list=new ArrayList<RoomCheck>();
		RoomCheck roomCheck=null;
		ResultSet rs=DBUtil.executeQuery(sql);
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
	/*
	 * 根据房间Id查询入住信息
	 */
	public RoomCheck findByNum(String roomNum){
		String sql="select top(1) * from roomCheck  where roomNum=? order by checkTime desc";
		RoomCheck roomCheck=null;
		Object[] o= {roomNum};
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomCheck;
	}
	public RoomCheck findByTime(String checkTime){
		String sql="select  * from roomCheck  where checkTime=?";
		RoomCheck roomCheck=null;
		Object[] o= {checkTime};
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomCheck;
	}
	/*
	 * 添加入住信息
	 */
	public int insertRoomCheck(String roomNum,int empId,String customerName,String customerPhone,String customerIdNum,String checkTime,int checkHour,String checkRemark) {
		String sql="insert into roomCheck values(?,?,?,?,?,?,?,?)";
		Object[] o= {roomNum,empId,customerName,customerPhone,customerIdNum,checkTime,checkHour,checkRemark};
		int num=0;
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	/*
	 * 修改入住信息
	 */
	public int updateRoomCheck(String roomNum,int empId,String customerName,String customerPhone,String customerIdNum,String checkTime,int checkHour,String checkRemark,int checkId) {
		String sql="update roomCheck set roomNum=?,empId=?,customerName=?,customerPhone=?,customerIdNum=?,checkTime=?,checkHour=?,checkRemark=? where checkId=?";
		Object[] o= {roomNum,empId,customerName,customerPhone,customerIdNum,checkTime,checkHour,checkRemark,checkId};
		int num=0;
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
}
