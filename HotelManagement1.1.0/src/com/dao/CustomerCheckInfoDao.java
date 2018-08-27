package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBUtil;
import com.vo.CustomerCheckInfo;

public class CustomerCheckInfoDao {
	public CustomerCheckInfo findByNum(String roomNum) {
		CustomerCheckInfo cci=null;
		String sql="select c.roomNum,o.empId,c.customerName,c.customerPhone,c.customerIdNum,"
				+ "c.checkTime,c.checkHour,o.outTime,o.outDiscount,o.outRecivalble,o.outActual,o.outRemark "
				+ "from roomCheck c inner join checkOut o on c.roomNum=o.roomNum "
				+ "inner join room r on r.roomNum=c.roomNum where r.roomState=1 and o.roomNum=?";
		ResultSet rs=DBUtil.excuteQuery(sql, new Object[] {roomNum});
		try {
			while(rs.next()) {
				cci=new CustomerCheckInfo();
				cci.setRoomNum(rs.getString(1));
				cci.setEmpId(rs.getInt(2));
				cci.setCustomerName(rs.getString(3));
				cci.setCustomerPhone(rs.getString(4));
				cci.setCustomerIdNum(rs.getString(5));
				cci.setCheckTime(rs.getString(6));
				cci.setCheckHour(rs.getInt(7));
				cci.setOutTime(rs.getString(8));
				cci.setDicount(rs.getFloat(9));
				cci.setRecivable(rs.getFloat(10));
				cci.setActual(rs.getFloat(11));
				cci.setRemark(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cci;
	}
}
