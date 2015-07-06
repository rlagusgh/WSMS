package com.Controller.UserController;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;

public class LogoutController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		
		return "redirect:/WSMS/index.jsp";
	}
}
