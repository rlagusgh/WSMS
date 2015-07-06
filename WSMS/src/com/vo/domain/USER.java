package com.vo.domain;

public class USER {
	String ID;
	String NAME;
	String PASSWORD;
	String EMAIL;
	LEVEL level;
	String JOIN_DATE;
	String MOD_DATE;
	
	public USER() {
		super();
	}

	public USER(String iD, String nAME, String pASSWORD, String eMAIL, LEVEL level) {
		ID = iD;
		NAME = nAME;
		PASSWORD = pASSWORD;
		EMAIL = eMAIL;
		this.level = level;
	}

	public void nextLevel(){
		this.level = level.nextLevel();
	}
	
	public void prevLevel(){
		this.level = LEVEL.ValueOf(level.intValue()+1);
	}
	
	public String getID() {
		return ID;
	}

	public USER setID(String iD) {
		ID = iD;
		return this;
	}

	public String getNAME() {
		return NAME;
	}

	public USER setNAME(String nAME) {
		NAME = nAME;
		return this;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public USER setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
		return this;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public USER setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
		return this;
	}

	public LEVEL getLevel() {
		return level;
	}

	public USER setLevel(LEVEL level) {
		this.level = level;
		return this;
	}

	public String getJOIN_DATE() {
		return JOIN_DATE;
	}

	public USER setJOIN_DATE(String jOIN_DATE) {
		JOIN_DATE = jOIN_DATE;
		return this;
	}

	public String getMOD_DATE() {
		return MOD_DATE;
	}

	public USER setMOD_DATE(String mOD_DATE) {
		MOD_DATE = mOD_DATE;
		return this;
	}
}
