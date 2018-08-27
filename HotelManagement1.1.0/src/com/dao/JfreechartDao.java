package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.util.DBUtil;

public class JfreechartDao {
	/**
	 * ���ĳ��ʱ���Ӫ�ս��
	 * @param bgein ��ʼʱ��
	 * @param end ����ʱ��
	 * 
	 * @return Ӫ�ս��
	 */
	public int getTurnover(String begin,String end) {
		String sql="select sum(outActual) from checkOut where outTime<? and outTime>?";
		Object[] o= {end,begin};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		int num=0;
		try {
			while(rs.next()) {
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * ���ĳ��ʱ��θ������ͷ����Ӫ�ս��
	 * @param begin ��ʼʱ��
	 * @param end	����ʱ��
	 * @return ÿ�ַ����Ӫ�ս��
	 */
	public HashMap<String,Float> getTurnoverType(String begin,String end) {
		String sql="select t.typeName,sum(c.outActual) from checkOut c " + 
				"inner join room r on r.roomNum=c.roomNum " + 
				"inner join roomType t on t.typeId=r.typeId " + 
				"where c.outTime<? and c.outTime>? " + 
				"group by t.typeName";
		Object[] o= {end,begin};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		HashMap<String,Float> map=new HashMap<String,Float>();
		try {
			while(rs.next()) {
				map.put(rs.getString(1), rs.getFloat(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * ���ĳ��ʱ����ڸ������͵ķ�����ס����
	 * @param begin ��ʼʱ��
	 * @param end ����ʱ��
	 * @return �������ͷ������ס����
	 */
	public HashMap<String,Integer> getCheckCount(String begin,String end){
		String sql="select t.typeName,count(t.typeName) from checkOut c " + 
				"inner join room r on r.roomNum=c.roomNum " + 
				"inner join roomType t on t.typeId=r.typeId " + 
				"where c.outTime<? and c.outTime>? " + 
				"group by t.typeName";
		Object[] o= {end,begin};
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		try {
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * ��ø������͵ķ�������
	 * */
	public HashMap<String,Integer> getTypeCount(){
		String sql="select t.typeName,count(t.typeName) from room r " + 
				"inner join roomType t on r.typeId=t.typeId " + 
				"group by t.typeName";
		ResultSet rs=DBUtil.executeQuery(sql);
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		try {
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * ����ס��������
	 * */
	public int getCheck() {
		String sql="select count(*) from room where roomState=0";
		ResultSet rs=DBUtil.executeQuery(sql);
		int num=0;
		try {
			while(rs.next()) {
				 num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * ��������
	 * */
	public int getRoomCount() {
		String sql="select count(*) from room";
		ResultSet rs=DBUtil.executeQuery(sql);
		int num=0;
		try {
			while(rs.next()) {
				 num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	
	/**
	 * ����ס������
	 * */
	public int getIn() {
		String sql="select count(*) from room where roomState=1";
		ResultSet rs=DBUtil.executeQuery(sql);
		int num=0;
		try {
			while(rs.next()) {
				 num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
}
