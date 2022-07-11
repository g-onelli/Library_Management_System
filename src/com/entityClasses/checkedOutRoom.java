package com.entityClasses;

public class checkedOutRoom {
	private int patrons_id;
	private int rooms_roomnumber;
	private String dueDate;
	public checkedOutRoom() {
		super();
	}
	public checkedOutRoom(int patrons_id, int rooms_roomnumber, String dueDate) {
		super();
		this.patrons_id = patrons_id;
		this.rooms_roomnumber = rooms_roomnumber;
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {
		return "checkedOutRoom [patrons_id=" + patrons_id + ", rooms_roomnumber=" + rooms_roomnumber + ", dueDate="
				+ dueDate + "]";
	}
	public int getPatrons_id() {
		return patrons_id;
	}
	public void setPatrons_id(int patrons_id) {
		this.patrons_id = patrons_id;
	}
	public int getRooms_roomnumber() {
		return rooms_roomnumber;
	}
	public void setRooms_roomnumber(int rooms_roomnumber) {
		this.rooms_roomnumber = rooms_roomnumber;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
}
