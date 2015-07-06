package com.Controller.Schedule;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.WeekDao.WeekDaoImpl;
import com.vo.domain.USER;
import com.vo.domain.WEEK;

public class AddScheduleController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		WeekDaoImpl weekdao = (WeekDaoImpl)model.get("weekdao");
		
		WEEK week = (WEEK)model.get("week");
		
		if(week!=null){
			HttpSession session = (HttpSession)model.get("session");
			USER user = (USER)session.getAttribute("login_user");
			
			if(weekdao.bCheck_schedule(user.getID(), week.getStart_time(), week.getEnd_time(), week.getDay())){
				model.put("overlap", "true");
				return "addScheduleForm.jsp";
			}else{
				weekdao.addWeekSchedule(week, user.getID());
				return "redirect:../schedule.do";
			}
		}else{
			return "redirect:addScheduleForm.jsp";
		}
	}
	
}
