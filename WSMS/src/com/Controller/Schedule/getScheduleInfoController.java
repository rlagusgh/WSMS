package com.Controller.Schedule;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.WeekDao.WeekDaoImpl;
import com.vo.domain.USER;

public class getScheduleInfoController implements Controller{
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		WeekDaoImpl weekdao = (WeekDaoImpl)model.get("weekdao");
		
		HttpSession session = (HttpSession)model.get("session");
		USER login_user = (USER)session.getAttribute("login_user");
		String day = (String)model.get("day");
		String start_time = (String)model.get("start_time");
		
		model.put("week", weekdao.getWeekSchedule(Integer.parseInt(day), Integer.parseInt(start_time), login_user.getID()));
		
		return "/SCHEDULE/schduleInfo.jsp";
	}
}
