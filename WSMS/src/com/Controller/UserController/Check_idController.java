package com.Controller.UserController;

import java.util.Map;

import com.Controller.Controller;
import com.dao.Userdao.UserDaoImpl;

public class Check_idController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		UserDaoImpl userdao = (UserDaoImpl)model.get("userdao");
		
		String id = (String)model.get("id");
		int bcheck_id = userdao.bCheck_id(id);
		
		model.put("bCheck_id", bcheck_id);
		model.put("id", id);
		
		return "/USER/Check_id.jsp";
	}
}
