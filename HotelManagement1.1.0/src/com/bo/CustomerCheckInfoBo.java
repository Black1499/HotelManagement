package com.bo;

import com.dao.CustomerCheckInfoDao;

public class CustomerCheckInfoBo {
	CustomerCheckInfoDao cci_dao=new CustomerCheckInfoDao();
	public boolean findByNum(String roomNum) {
		if(cci_dao.findByNum(roomNum)!=null)
			return true;
		else 
			return false;
	}
}
