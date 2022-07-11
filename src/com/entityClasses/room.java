package com.entityClasses;

public class room {
	private int roomNumber;
	private int capacity;
	private int hasPresenterTools;
	public room() {
		super();
		// TODO Auto-generated constructor stub
	}
	public room(int roomNumber, int capacity, int hasPresenterTools) {
		super();
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.hasPresenterTools = hasPresenterTools;
	}
	@Override
	public String toString() {
		return "room [roomNumber=" + roomNumber + ", capacity=" + capacity + ", hasPresenterTools=" + hasPresenterTools
				+ "]";
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getHasPresenterTools() {
		return hasPresenterTools;
	}
	public void setHasPresenterTools(int hasPresenterTools) {
		this.hasPresenterTools = hasPresenterTools;
	}
}
