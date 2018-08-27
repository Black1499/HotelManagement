package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBUtil;
import com.vo.Room;

public class RoomDao {
	/*
	 * 查询所有房间信息
	 */
	public ArrayList<Room> selectAll() {
		String sql="select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,r.roomState,r.roomRemark "
				+ "from roomType t inner join room r on t.typeId=r.typeId"; 
			
		ResultSet rs=DBUtil.executeQuery(sql);
		ArrayList<Room> list=new ArrayList<Room>();
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 分页查询
	 * */
	public ArrayList<Room> getPage(int page,int limit) {
		int start=(page-1)*limit+1;
		int end=page*limit;
		String sql="select * from(select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,r.roomState,r.roomRemark,ROW_NUMBER() over(order by r.roomNum) rows " + 
				"from roomType t inner join room r on t.typeId=r.typeId) o " + 
				"where o.rows>=? and o.rows<=?"; 	
		Object[] o= {start,end};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		ArrayList<Room> list=new ArrayList<Room>();
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 查询房间是否可用的信息
	 */
	public ArrayList<Room> findByAvailable(int available){
		String sql="select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,"
				+ "r.roomState,r.roomRemark from roomType t "
				+ "inner join room r on t.typeId=r.typeId where r.roomAvailable=?";
		Object[] o= {available};
		ResultSet rs=DBUtil.excuteQuery(sql,o);
		ArrayList<Room> list=new ArrayList<Room>();
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 按状态信息查询房间
	 */
	public ArrayList<Room> findByState(int state){
		String sql="select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,"
				+ "r.roomState,r.roomRemark from roomType t "
				+ "inner join room r on t.typeId=r.typeId where r.roomState=?";
		Object[] o= {state};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		ArrayList<Room> list=new ArrayList<Room>();
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 根据房间号码查询房间信息
	 */
	public Room findByNum(String roomNum){
		String sql="select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,r.roomState,r.roomRemark "
				+ "from roomType t inner join room r on t.typeId=r.typeId where r.roomNum=?"; 
		Object[] o= {roomNum};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	public ArrayList<Room> findByType(int typeId){
		String sql="select t.typeName,t.typePrice,t.typeDeposit,r.roomNum,r.roomPhone,r.roomAvailable,r.roomState,r.roomRemark "
				+ "from roomType t inner join room r on t.typeId=r.typeId where t.typeId=?"; 
		Object[] o= {typeId};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		ArrayList<Room> list=new ArrayList<Room>();
		Room room=null;
		try {
			while(rs.next()) {
				room=new Room();
				room.setTypeName(rs.getString(1));
				room.setTypePrice(rs.getInt(2));
				room.setTypeDeposit(rs.getInt(3));
				room.setRoomNum(rs.getString(4));
				room.setRoomPhone(rs.getString(5));
				room.setRoomAvailable(rs.getInt(6));
				room.setRoomState(rs.getInt(7));
				room.setRoomRemark(rs.getString(8));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 添加房间
	 */
	public int insertRoom(String roomNum,int typeId,String roomPhone,int roomAvailable,int roomState,String roomRemark) {
		int num=0;
		String sql="insert into room (roomNum,typeId,roomPhone,roomAvailable,roomState,roomRemark) values(?,?,?,?,?,?)";
		Object[] o= {roomNum,typeId,roomPhone,roomAvailable,roomState,roomRemark};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	/*
	 * 删除房间
	 */
	public int deleteRoom(String roomNum) {
		int num=0;
		String sql="delete from room where roomNum=?";
		Object[] o= {roomNum};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	/*
	 * 修改房间
	 */
	public int updRoom(String roomNum,int typeId,String roomPhone,int roomAvailable,String roomRemark) {
		int num=0;
		String sql="update room set typeId=?,roomPhone=?,roomAvailable=?,roomRemark=? where roomNum=?";
		Object[] o= {typeId,roomPhone,roomAvailable,roomRemark,roomNum};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	/*
	 * 修改房间是否可用
	 */
	public int updateRoomAvailable(String roomNum,int roomAvailable) {
		int num=0;
		String sql="update room set roomAvailable=? where roomNum=?";
		Object[] o= {roomAvailable,roomNum};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	/*
	 * 修改房间的状态
	 */
	public int updateRoomState(String roomNum,int roomState) {
		int num=0;
		String sql="update room set roomState=? where roomNum=?";
		Object[] o= {roomState,roomNum};
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
}
