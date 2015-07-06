package com.service.UserService;

import org.junit.Test;

public class UserServiceTest {

	@Test
	public void UpgradeTest(){
		UserServiceImpl service = new UserServiceImpl();
		
		service.UserUpgradeLevel("Mook");
	}
}
