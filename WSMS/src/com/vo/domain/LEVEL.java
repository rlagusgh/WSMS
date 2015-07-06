package com.vo.domain;

public enum LEVEL {
	ADMIN(null,0),USER(ADMIN,1);
	
	private final LEVEL nextLEVEL;
	private final int intValue;
	
	LEVEL(LEVEL nextLEVEL,int intValue){
		this.nextLEVEL = nextLEVEL;
		this.intValue = intValue;
	}
	
	public int intValue(){
		return this.intValue;
	}
	
	public LEVEL nextLevel(){
		return this.nextLEVEL;
	}
	
	public static LEVEL ValueOf(String level){ 
		switch(level){
		case "ROLE_USER":
			return USER;
		case "ROLE_AUTH":
			return ADMIN;
		default:
			throw new AssertionError("Unknown value"+level);
		}
	}
	
	public static LEVEL ValueOf(int level){ 
		switch(level){
		case 1:
			return USER;
		case 0:
			return ADMIN;
		default:
			throw new AssertionError("Unknown value"+level);
		}
	}
}
