/*

*/

package com.service.UserService;

import com.dao.Userdao.UserDaoImpl;
import com.vo.domain.*;

public class UserServiceImpl implements UserService{
	UserDaoImpl userdao;
	
	public void setUserdao(UserDaoImpl userdao) {
		this.userdao = userdao;
	}
	
	@Override
	public void UserUpgradeLevel(String id) {
		USER user = userdao.getUser(id);
		if(canUpgradeLevel(user))
			upgradeLevel(user);
	}

	@Override
	public void UserDowngradeLevel(String id) {
		USER user = userdao.getUser(id);
		if(canDowngradeLevel(user))
			downgradeLevel(user);
	}

	private boolean canUpgradeLevel(USER user) {
		switch(user.getLevel()){
		case USER:
			return true;
		case ADMIN:
			return false;
		default:
			return false;
		}
	}

	private boolean canDowngradeLevel(USER user) {
		switch(user.getLevel()){
		case USER:
			return false;
		case ADMIN:
			return true;
		default:
			return false;
		}
	}
	
	private void upgradeLevel(USER user) {
		user.nextLevel();
		userdao.UserUpdate(user);
	}
	
	private void downgradeLevel(USER user) {
		user.prevLevel();
		userdao.UserUpdate(user);
	}
}
