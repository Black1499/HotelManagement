package com.vo;

/**
 * 房间类型
 * @author 殷家日
 *
 */
public class RoomType {
	private int typeId;//主键
	private String typeTimg;
	private String typeName;//类型名称
	private int typePrice;//价格
	private int typeDeposit;//押金
	private String typeRemark;//备注
	
	public String getTypeTimg() {
		return typeTimg;
	}
	public void setTypeTimg(String typeTimg) {
		this.typeTimg = typeTimg;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getTypePrice() {
		return typePrice;
	}
	public void setTypePrice(int typePrice) {
		this.typePrice = typePrice;
	}
	public int getTypeDeposit() {
		return typeDeposit;
	}
	public void setTypeDeposit(int typeDeposit) {
		this.typeDeposit = typeDeposit;
	}
	public String getTypeRemark() {
		return typeRemark;
	}
	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}
	
	
	public RoomType() {}
	
	public RoomType( String typeName, int typePrice, int typeDeposit, String typeTimg,String typeRemark) {
		super();
		this.typeName = typeName;
		this.typePrice = typePrice;
		this.typeDeposit = typeDeposit;
		this.typeTimg=typeTimg;
		this.typeRemark = typeRemark;
	}
	public RoomType(int typeId, String typeName, int typePrice, int typeDeposit, String typeTimg,String typeRemark) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typePrice = typePrice;
		this.typeDeposit = typeDeposit;
		this.typeTimg=typeTimg;
		this.typeRemark = typeRemark;
	}
}
