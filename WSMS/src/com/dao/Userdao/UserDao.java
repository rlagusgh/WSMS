package com.dao.Userdao;

import java.util.List;

import com.vo.domain.USER;

public interface UserDao {
	USER getUser(String id);
	List<USER> getAllUsers();
	void addUser(USER user);
	void deleteAllUsers();
	int getCountAllUsers();
	void UserUpdate(USER user);
	int bCheck_id(String id);
}
