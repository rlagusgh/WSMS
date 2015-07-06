package com.Controller.Schedule;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.Controller.Controller;
import com.dao.WeekDao.WeekDaoImpl;
import com.vo.domain.USER;
import com.vo.domain.WEEK;

public class modifyScheduleController implements Controller{
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		WeekDaoImpl weekdao = (WeekDaoImpl)model.get("weekdao");
		
		HttpSession session = (HttpSession)model.get("session");
		USER login_user = (USER)session.getAttribute("login_user");
		String day = (String)model.get("day");
		String start_time = (String)model.get("start_time");
		WEEK week = (WEEK)model.get("week");
		String now_day = (String)model.get("now_day");
		String now_start_time = (String)model.get("now_start_time");
		String now_end_time = (String)model.get("now_end_time");

		if(week==null){
			weekdao.getWeekSchedule(Integer.parseInt(day), Integer.parseInt(start_time), login_user.getID());
			model.put("week", weekdao.getWeekSchedule(Integer.parseInt(day), Integer.parseInt(start_time), login_user.getID()));
			
			return "updateScheduleForm.jsp";
		}else{
			if(week.getStart_time()==Integer.parseInt(now_start_time) && week.getEnd_time()==Integer.parseInt(now_end_time) && week.getDay()==Integer.parseInt(now_day)){
				weekdao.updateSchedule(week, login_user.getID(),Integer.parseInt(now_start_time),Integer.parseInt(now_day));
				
				return "redirect:../schedule.do";
			}else if(week.getStart_time()==Integer.parseInt(now_start_time) && week.getDay()==Integer.parseInt(now_day)){
				if(weekdao.bCheck_schedule_end(login_user.getID(), Integer.parseInt(now_end_time), week.getEnd_time(), week.getDay())){
					model.put("week", weekdao.getWeekSchedule(Integer.parseInt(now_day), Integer.parseInt(now_start_time), login_user.getID()));
					model.put("overlap", "true");
					
					return "updateScheduleForm.jsp";
				}else{
					weekdao.updateSchedule(week, login_user.getID(),Integer.parseInt(now_start_time),Integer.parseInt(now_day));
					
					return "redirect:../schedule.do";
				}
				
			}else if(week.getEnd_time()==Integer.parseInt(now_end_time) && week.getDay()==Integer.parseInt(now_day)){
				if(weekdao.bCheck_schedule_start(login_user.getID(), Integer.parseInt(now_start_time), week.getStart_time(), week.getDay())){
					model.put("week", weekdao.getWeekSchedule(Integer.parseInt(now_day), Integer.parseInt(now_start_time), login_user.getID()));
					model.put("overlap", "true");
					
					return "updateScheduleForm.jsp";
				}else{
					weekdao.updateSchedule(week, login_user.getID(),Integer.parseInt(now_start_time),Integer.parseInt(now_day));
					
					return "redirect:../schedule.do";
				}
				
			}else{
				if(weekdao.bCheck_schedule(login_user.getID(), week.getStart_time(), week.getEnd_time(), week.getDay())){
					//������ �����Ұ��� �̹� �������� ����
					model.put("week", weekdao.getWeekSchedule(Integer.parseInt(now_day), Integer.parseInt(now_start_time), login_user.getID()));
					model.put("overlap", "true");
					
					return "updateScheduleForm.jsp";
				}else{
					weekdao.updateSchedule(week, login_user.getID(),Integer.parseInt(now_start_time),Integer.parseInt(now_day));
					
					return "redirect:../schedule.do";
				}
			}
		}
	}
}
