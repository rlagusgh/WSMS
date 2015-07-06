package com.Controller.UserController;

import java.util.Map;

import com.Controller.Controller;
import com.dao.Userdao.UserDaoImpl;
import com.service.UserService.UserServiceImpl;
import com.vo.domain.LEVEL;
import com.vo.domain.USER;

public class UserLevelgradeController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String id = (String)model.get("id");
		
		UserDaoImpl userdao = (UserDaoImpl)model.get("userdao");
		UserServiceImpl service = (UserServiceImpl)model.get("servicedao");
		
		USER user = userdao.getUser(id);

		if(user.getLevel().equals(LEVEL.USER)){
			service.UserUpgradeLevel(id);
		}else{
			service.UserDowngradeLevel(id);
		}

		model.put("users", userdao.getAllUsers());

		return "redirect:UserInfo.do";
	}
		
}
