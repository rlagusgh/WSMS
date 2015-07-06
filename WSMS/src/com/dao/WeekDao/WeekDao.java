package com.dao.WeekDao;

import java.util.List;

import com.vo.domain.WEEK;

public interface WeekDao {
	List<WEEK> getAllWeekSchedule(int day,String id);
	WEEK getWeekSchedule(int day,int start_time,String user_id);
	void createScheduleTable(String id);
	void addWeekSchedule(WEEK week,String id);
	void updateSchedule(WEEK week,String id,int now_start_time,int now_day);
	void deleteSchedule(String id,int start_time,int day);
	boolean bCheck_schedule(String id,int start_time,int end_time,int day);
	boolean bCheck_schedule_start(String id,int start_time ,int check_time, int day);
	boolean bCheck_schedule_end(String id,int end_time ,int check_time, int day);
}
