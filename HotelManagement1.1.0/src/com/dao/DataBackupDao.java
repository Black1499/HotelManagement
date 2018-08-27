package com.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.util.DBUtil;
import com.util.JDBCUtils;
import com.vo.DataBackup;

public class DataBackupDao {
	public List<DataBackup> getAll(){
		String sql="select * from dataBackup";
		List<DataBackup> list=JDBCUtils.queryForList(sql, DataBackup.class, null);
		return list;
	}
	public int add(String name,String  size,String date) {
		String sql="insert into dataBackup values(?,?,?)";
		Object[] o= {name,size,date};
		int num=0;
		num=DBUtil.excuteUpdate(sql, o);
		return num;
	}
	
	public int delete(int id) {
		String sql="delete from dataBackup where dataId=?";
		Object[] o= {id};
		int num=0;
		num=DBUtil.excuteUpdate(sql,o);
		return num;
	}
	/**
	 * 备份数据库
	 * @param path 路径
	 * @return 是否成功
	 */
	public String backUp(String path) {
		String id=UUID.randomUUID().toString().substring(0, 13);
		String paths=path+id+".bak";
		boolean b=false;
		try {
			 String sql="backup database hotelDB to disk=?";
			 Connection con = DBUtil.getConn();  
			 PreparedStatement ps;
			 ps = con.prepareStatement(sql);
			 ps.setString(1, paths);
			 b=ps.execute();
			// System.out.println(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(b==false) {
			  return paths;
		  }else {
			  return "";
		  }
	}
	
	
	public boolean restoreData(String path) {
		String sql="alter database hotelDB set online with rollback immediate";
		PreparedStatement ps=null;
		try {
			Connection conn=DBUtil.getConn();
			ps = conn.prepareStatement("USE master");//注意使用master下的存储过程。
			ps.execute();
			ps=conn.prepareStatement(sql);
			CallableStatement cs=conn.prepareCall("{call dataRestore(hotelDB,?)}");
			cs.setString(1, path);
			cs.execute();
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
//	public static void main(String[] args) {
//		DataBackupDao a=new DataBackupDao();
//		String path="D:\\DataBaseBackUp\\5026af6a-ccac-41e6-b2a3-97a9f91eb479.bak";
//		File f=new File(path);
//		File f1=new File("D:\\DataBaseBackUp\\"+"hotelDB"+".bak");
//		f.renameTo(f1);
//		System.out.println(f.getName());
//		System.out.println(a.restoreData(path));
//	}
}
