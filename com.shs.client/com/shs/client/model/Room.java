package com.shs.client.model;

public class Room {
	private Integer id;
	private String type_room;
	private Integer floor;
	
	public Room(Integer id, String type_room, Integer floor) {
		super();
		this.id = id;
		this.type_room = type_room;
		this.floor = floor;
	}
	
	public Room() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_room() {
		return type_room;
	}
	public void setType_room(String type_room) {
		this.type_room = type_room;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", type_room=" + type_room + ", floor=" + floor + "]";
	}
	
}