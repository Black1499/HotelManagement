package com.bo;

import java.util.*;

import com.dao.SuiMessageDao;
import com.vo.*;

public class SuiMessageBo {
	SuiMessageDao smd=new SuiMessageDao();
	public boolean findReserve(String name,String phone){
		if(smd.findReserve(name, phone)!=null)
			return true;
		else
			return false;
	}
	public boolean findCheck(String name,String phone){
		if(smd.findCheck(name, phone)!=null)
			return true;
		else
			return false;
	}
	public boolean findOut(String name,String phone){
		if(smd.findOut(name, phone)!=null)
			return true;
		else
			return false;
	}
}
