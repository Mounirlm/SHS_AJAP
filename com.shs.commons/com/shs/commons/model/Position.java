package com.shs.commons.model;

public class Position {
	
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Integer fk_room;
	private Integer fk_sensor;
	private Integer fk_floor;
	
	public Position() {
		
	}
	
	public Position(Integer x, Integer y, Integer width, Integer height, Integer fk_room, Integer fk_sensor,
			Integer fk_floor) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fk_room = fk_room;
		this.fk_sensor = fk_sensor;
		this.fk_floor = fk_floor;
	}

	
	public Position(Integer x, Integer y, Integer width ,Integer height) {
		super();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getFk_room() {
		return fk_room;
	}

	public void setFk_room(Integer fk_room) {
		this.fk_room = fk_room;
	}

	public Integer getFk_sensor() {
		return fk_sensor;
	}

	public void setFk_sensor(Integer fk_sensor) {
		this.fk_sensor = fk_sensor;
	}

	public Integer getFk_floor() {
		return fk_floor;
	}

	public void setFk_floor(Integer fk_floor) {
		this.fk_floor = fk_floor;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", fk_room=" + fk_room
				+ ", fk_sensor=" + fk_sensor + ", fk_floor=" + fk_floor + "]";
	}
	

}
