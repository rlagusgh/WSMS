package com.Controller.Schedule;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.WeekDao.WeekDaoImpl;
import com.vo.domain.USER;

public class getScheduleController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		WeekDaoImpl weekdao = (WeekDaoImpl)model.get("weekdao");
		
		HttpSession session = (HttpSession)model.get("session");
		USER login_user = (USER)session.getAttribute("login_user");
		
		model.put("mon_list", weekdao.getAllWeekSchedule(1,login_user.getID()));
		model.put("tues_list", weekdao.getAllWeekSchedule(2, login_user.getID()));
		model.put("wen_list", weekdao.getAllWeekSchedule(3, login_user.getID()));
		model.put("thur_list", weekdao.getAllWeekSchedule(4, login_user.getID()));
		model.put("fri_list", weekdao.getAllWeekSchedule(5, login_user.getID()));
		model.put("sat_list", weekdao.getAllWeekSchedule(6, login_user.getID()));
		model.put("sun_list", weekdao.getAllWeekSchedule(7, login_user.getID()));
		
		return "main.jsp";
	}
	
}
