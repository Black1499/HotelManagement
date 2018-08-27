package com.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.CheckOutDao;
import com.util.DBUtil;
import com.vo.CheckOut;

public class CheckOutBo {
	CheckOutDao cod=new CheckOutDao();
	public boolean insertOut(String roomNum) {
		int num=0;
		num=cod.insertOut(roomNum);
		if(num>0)
			return true;
		else
			return false;
	}
	public boolean findByNum(String roomNum) {
		if(cod.findByNum(roomNum)!=null)
			return true;
		else
			return false;
	}
	public boolean updCheckOut(String roomNum,String outTime,float outRecivable,String remark,int outId) {
		int num=0;
		num=cod.updCheckOut(roomNum, outTime, outRecivable,remark, outId);
		if(num>0)
			return true;
		else
			return false;
	}
	public boolean updCheckOut(int empId,String outTime,float outDiscount,float outRecivable,float outActual,String outRemark,String roomNum) {
		int num=0;
		num=cod.updCheckOut(empId, outTime, outDiscount, outRecivable, outActual, outRemark, roomNum);
		if(num>0)
			return true;
		else
			return false;
	}
}
