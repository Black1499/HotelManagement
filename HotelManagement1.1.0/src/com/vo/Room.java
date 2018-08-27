package com.vo;

public class Room {
	String roomNum;
	String typeName;
	int typePrice;
	int typeDeposit;
	String roomPhone;
	int roomAvailable;
	int roomState;
	String roomRemark;
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
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

	public String getRoomPhone() {
		return roomPhone;
	}

	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}

	public int getRoomAvailable() {
		return roomAvailable;
	}

	public void setRoomAvailable(int roomAvailable) {
		this.roomAvailable = roomAvailable;
	}

	public int getRoomState() {
		return roomState;
	}

	public void setRoomState(int roomState) {
		this.roomState = roomState;
	}

	public String getRoomRemark() {
		return roomRemark;
	}

	public void setRoomRemark(String roomRemark) {
		this.roomRemark = roomRemark;
	}

	public Room() {
		super();
	}

	public Room(String roomNum, String typeName, int typePrice, int typeDeposit, 
			String roomPhone, int roomAvailable,int roomState, String roomRemark) {
		super();
		this.roomNum = roomNum;
		this.typeName = typeName;
		this.typePrice = typePrice;
		this.typeDeposit = typeDeposit;
		this.roomPhone = roomPhone;
		this.roomAvailable = roomAvailable;
		this.roomState = roomState;
		this.roomRemark = roomRemark;
	}
}
