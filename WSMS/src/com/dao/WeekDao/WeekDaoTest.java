package com.dao.WeekDao;

import org.junit.Test;

public class WeekDaoTest {
	
	@Test
	public void getTest(){
		WeekDaoImpl weekdao = new WeekDaoImpl();
		weekdao.createScheduleTable("haha9006");
	}
}
