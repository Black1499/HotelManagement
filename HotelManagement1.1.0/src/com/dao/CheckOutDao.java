package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBUtil;
import com.vo.CheckOut;

public class CheckOutDao {
	/**
	 * 向结账表内插入信息
	 * 
	 * */
	public int insertOut(String roomNum) {
		int num=0;
		String sql="insert into checkOut(roomNum) values(?)";
		num=DBUtil.excuteUpdate(sql, new Object[] {roomNum});
		return num;
				
	}
	
	/**
	 * 查找结账id
	 * */
	
	public CheckOut findByNum(String roomNum) {
		String sql="select top(1) * from checkOut where roomNum=? order by outTime desc";
		Object[] o= {roomNum};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}
	/**
	 * 结账表修改，当如入住房间改变时
	 * */
	public int updCheckOut(String roomNum,String outTime,float outRecivable,String remark,int outId) {
		String sql="update checkOut set roomNum=?,outTime=?,outRecivalble=?,outRemark=? where outId=?";
		Object[] o= {roomNum,outTime,outRecivable,remark,outId};
		int num=0;
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	
	/**
	 * 客户结账
	 * */
	public int updCheckOut(int empId,String outTime,float outDiscount,float outRecivable,float outActual,String outRemark, String roomNum ) {
		String sql="update checkOut set empId=?,outTime=?,outDiscount=?,outRecivalble=?,outActual=?,outRemark+=? where roomNum=?";
		Object[] o= {empId,outTime,outDiscount,outRecivable,outActual,outRemark,roomNum};
		int num=0;
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
}
