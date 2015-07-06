package com.Controller.UserController;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.Userdao.UserDaoImpl;
import com.vo.domain.USER;

public class LoginCheckController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		UserDaoImpl userdao = (UserDaoImpl)model.get("userdao");
		System.out.println("login");
		String id = (String)model.get("id");
		String password = (String)model.get("password");

		USER check_user = userdao.getUser(id);
		
		if(check_user==null){
			return "/LOGIN/login_fail.jsp";
		}else{
			if(check_user.getPASSWORD().equals(password)){
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("login_user", check_user);
				session.setMaxInactiveInterval(60*60);
												
				return "redirect:schedule.do";
			}else{
				return "/LOGIN/login_fail.jsp";
			}
		}
	}
}
