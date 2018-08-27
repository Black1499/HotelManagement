package com.bo;

import com.dao.RoomTypeDAO;

public class RoomTypeBo {
	private RoomTypeDAO rtd=new RoomTypeDAO();
	public boolean getAll() {
		if(rtd.getAll()!=null) {
			return true;
		}else {
			return false;
		}
	}
}
