package com.vo.domain;

public class WEEK {
	String user_id;
	String title;
	String contents;
	int day;
	int start_time;
	int end_time;
	int alram;
	String class_room;
	String color;
	
	public WEEK() {
		super();
	}

	public WEEK(String user_id, String title, String contents, int day,
			int start_time, int end_time, int alram, String class_room,
			String color) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.contents = contents;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.alram = alram;
		this.class_room = class_room;
		this.color = color;
	}

	public String getUser_id() {
		return user_id;
	}

	public WEEK setUser_id(String user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public WEEK setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContents() {
		return contents;
	}

	public WEEK setContents(String contents) {
		this.contents = contents;
		return this;
	}

	public int getDay() {
		return day;
	}

	public WEEK setDay(int day) {
		this.day = day;
		return this;
	}

	public int getStart_time() {
		return start_time;
	}

	public WEEK setStart_time(int start_time) {
		this.start_time = start_time;
		return this;
	}

	public int getEnd_time() {
		return end_time;
	}

	public WEEK setEnd_time(int end_time) {
		this.end_time = end_time;
		return this;
	}

	public int getAlram() {
		return alram;
	}

	public WEEK setAlram(int alram) {
		this.alram = alram;
		return this;
	}

	public String getClass_room() {
		return class_room;
	}

	public WEEK setClass_room(String class_room) {
		this.class_room = class_room;
		return this;
	}

	public String getColor() {
		return color;
	}

	public WEEK setColor(String color) {
		this.color = color;
		return this;
	}
}
