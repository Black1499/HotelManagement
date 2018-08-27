package com.vo;

import com.util.JsonUtil;

public class DataClass {
	/**相应编码*/
	private int code;
	/**相应消息*/
	private String msg;
	/**数据总量*/
	private int count;
	/**响应数据*/
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DataClass(int code, String msg, int count, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	public DataClass() {
		super();
	}
	public static DataClass ok() {
		return ok(0,null);
	}
	public static DataClass ok(int count, Object data){
        return new DataClass(0, "操作成功!", count, data);
    }
	public static DataClass ok(String msg) {
		return new DataClass(0,msg, 0,null);
	}
	
	public static DataClass error() {
		return ok(0,null);
	}
	public static DataClass error(int count, Object data){
        return new DataClass(0, "操作失败!", count, data);
    }
	public static DataClass error(String msg) {
		return new DataClass(0,msg, 0,null);
	}
	
	public String toJson() {
		return JsonUtil.toJson(this);
	}
}
