package com.dao.Userdao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vo.domain.LEVEL;
import com.vo.domain.USER;

public class UserDaoTest {
	List<USER> users;
	UserDaoImpl dao = new UserDaoImpl();
	
	@Before
	public void setUp(){
		users = Arrays.asList(
					new USER("haha9006","����ȣ","1234","kimnx9006@naver.com",LEVEL.USER),
					new USER("blackmyy","������","1234","blackmyy@naver.com",LEVEL.USER),
					new USER("jochi","��ġ��","1234","jochi@naver.com",LEVEL.USER),
					new USER("yangS","�缺��","1234","yangS@naver.com",LEVEL.USER),
					new USER("mook","���繬","1234","mook@naver.com",LEVEL.USER)
				);
	}
	
	@Test
	public void addTest(){
		Init();
		dao.addUser(users.get(0));
	}
	
	@Test
	public void AddAndGetUser(){
		Init();
		
		for(USER user : users)
			dao.addUser(user);
		
		for(USER user : users)
			checkUser(user);
		
	}
	
	@Test
	public void GetUserTest(){
		Init();
		
		dao.addUser(users.get(0));
		assertThat(dao.getUser(users.get(0).getID()).getID(),is(users.get(0).getID()));
	}
	
	@Test
	public void GetUserFailTest(){
		Init();
		
		String id = dao.getUser("s").getID();
		
		if(id == null)
			System.out.println("����");
		
	}
	
	public void Init(){
		dao.deleteAllUsers();
		assertThat(dao.getCountAllUsers(),is(0));
	}
	
	public void checkUser(USER user){
		USER getUser = dao.getUser(user.getID());
		assertThat(getUser.getID(),is(user.getID()));
		assertThat(getUser.getNAME(),is(user.getNAME()));
		assertThat(getUser.getPASSWORD(),is(user.getPASSWORD()));
		assertThat(getUser.getEMAIL(),is(user.getEMAIL()));
		assertThat(getUser.getLevel(),is(user.getLevel()));
	}
}
