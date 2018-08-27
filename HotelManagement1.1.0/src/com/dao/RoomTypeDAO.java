package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.util.DBUtil;
import com.util.JDBCUtils;
import com.vo.RoomType;
public class RoomTypeDAO {
	
	public List<RoomType> getPager(int page, int limit) {
        // ��ʼ����
        int start = (page - 1) * limit + 1;
        // ��������
        int end = page * limit;

        String sql = "select * from(SELECT [typeId] ,[typeName],[typePrice] ,[typeDeposit],[typeTimg],[typeRemark],ROW_NUMBER() over(order by typeId) row FROM [RoomType]) t where t.row>=? and t.row<=?";
        //ִ�в�ѯ����List<Product>
        return JDBCUtils.queryForList(sql, RoomType.class, start,end);
    }
    
    /**
     * ����ܼ�¼��
     * @return
    */
    public int getCount() {
        String sql="select COUNT(*) count from RoomType";
        Map<String,Object> map=JDBCUtils.queryForMap(sql);
        return (int)map.get("count");
    }
    //ɾ��
    public int delete(int typeId) {//ɾ��
		String sql="delete from RoomType where typeId=?";
		Object[] in = {typeId};
		return JDBCUtils.update(sql, in);
	}
    //���
    public int insert(RoomType model) {//���
		String sql = "insert into RoomType(typeName,typePrice,typeDeposit,typeTimg,typeRemark) values(?,?,?,?,?)";
		Object[] in = {model.getTypeName(),model.getTypePrice(),model.getTypeDeposit(),model.getTypeTimg(),model.getTypeRemark()};
		
		int count = JDBCUtils.update(sql, in);		
		return count;
	}
  //�޸�
  	public int updatePwd(int typeId,String typeName,int typePrice,int typeDeposit,String typeTimg,String typeRemark) {//�޸�
  		String sql="update RoomType set typeName=?,typePrice=?,typeDeposit=?,typeTimg=?,typeRemark=? where typeId=?";
  		Object[] in = {typeName,typePrice,typeDeposit,typeTimg,typeRemark,typeId};
  		return JDBCUtils.update(sql, in);
  	}
    //JUnit
    public static void main(String[] args) {
        RoomTypeDAO dao=new RoomTypeDAO();
        
        System.out.println(dao.getCount());
        
        for (RoomType p : dao.getPager(2, 10)) {
            System.out.println(p);
        }
    }
    
    /**
     * ��ѯ�������� ��־��
     * @return
     */
	public ArrayList<RoomType> getAll(){
		String sql="select * from roomType";
		ArrayList<RoomType> list=new ArrayList<RoomType>();
		RoomType rt=null;
		ResultSet rs=DBUtil.executeQuery(sql);
		try {
			while(rs.next()) {
				rt=new RoomType();
				rt.setTypeId(rs.getInt(1));
				rt.setTypeName(rs.getString(2));
				rt.setTypePrice(rs.getInt(3));
				rt.setTypeDeposit(rs.getInt(4));
				rt.setTypeTimg(rs.getString(5));
				rt.setTypeRemark(rs.getString(6));
				list.add(rt);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public RoomType getAll(String typeName){
		String sql="select * from roomType where typeName=?";
		Object[] o= {typeName};
		RoomType rt=null;
		ResultSet rs=DBUtil.excuteQuery(sql, o);
		try {
			while(rs.next()) {
				rt=new RoomType();
				rt.setTypeId(rs.getInt(1));
				rt.setTypeName(rs.getString(2));
				rt.setTypePrice(rs.getInt(3));
				rt.setTypeDeposit(rs.getInt(4));
				rt.setTypeTimg(rs.getString(5));
				rt.setTypeRemark(rs.getString(6));
			}
			return rt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
}
