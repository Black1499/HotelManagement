package com.bo;

import com.dao.DataBackupDao;

public class DataBackupBo {
	private DataBackupDao dbd=new DataBackupDao();
	public boolean add(String name,String size,String date) {
		if(dbd.add(name, size, date)!=0)
			return true;
		else
			return false;
	}
	
	public boolean delete(int id) {
		if(dbd.delete(id)!=0) {
			return true;
		}else {
			return false;
		}
	}
	public boolean getAll() {
		if(dbd.getAll()!=null)
			return true;
		else
			return false;
	}
	
	
	
	
}
