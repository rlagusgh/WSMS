package com.Controller.UserController;

import java.util.Map;

import com.Controller.Controller;
import com.dao.Userdao.UserDaoImpl;
import com.vo.domain.USER;

public class UserAddController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		UserDaoImpl userdao = (UserDaoImpl)model.get("userdao");
		USER user = (USER)model.get("user");
		
		if(user == null){
			return "/USER/UserAddForm.jsp";
		}else{
			userdao.addUser(user);
			model.put("user", userdao.getUser(user.getID()));
			return "/USER/UserAddConfirm.jsp";
		}
	}
}
