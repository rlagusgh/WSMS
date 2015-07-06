package com.Controller.Schedule;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.WeekDao.WeekDaoImpl;
import com.vo.domain.USER;

public class DelScheduleController implements Controller{
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		WeekDaoImpl weekdao = (WeekDaoImpl)model.get("weekdao");
		
		String day = (String)model.get("day");
		String start_time = (String)model.get("start_time");
		HttpSession session = (HttpSession)model.get("session");
		USER login_user = (USER)session.getAttribute("login_user");
				
		weekdao.deleteSchedule(login_user.getID(), Integer.parseInt(start_time), Integer.parseInt(day));
		
		return "redirect:../schedule.do";
	}
}
