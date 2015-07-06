package com.dao.WeekDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.vo.domain.WEEK;

public class WeekDaoImpl implements WeekDao{
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<WEEK> getAllWeekSchedule(int day,String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		WEEK week = null;
		List<WEEK> weeks = new ArrayList<WEEK>(); 
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("select * from WEEK_SCHEDULE where day = ? AND user_id=?");
			ps.setInt(1, day);
			ps.setString(2, id);
			
			rs = ps.executeQuery();
			while(rs.next()){
				week = new WEEK();
				week.setTitle(rs.getString("title"));
				week.setContents(rs.getString("contents"));
				week.setDay(rs.getInt("day"));
				week.setStart_time(rs.getInt("start_time"));
				week.setEnd_time(rs.getInt("end_time"));
				week.setAlram(rs.getInt("alram"));
				week.setColor(rs.getString("color"));
				week.setClass_room(rs.getString("CLASS_ROOM"));
				weeks.add(week);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return weeks;
	}//getAllWeekSchedule(int day)
	
	public void createScheduleTable(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("create table "+id+"_SCHEDULE_TABLE ( TITLE VARCHAR(45),"
					+ "CONTENTS VARCHAR(45),DAY INT,START_TIME INT,END_TIME INT,ALRAM INT,CLASS_ROOM VARCHAR(45),COLOR VARCHAR(45) )");
			
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//createScheduleTable

	@Override
	public void addWeekSchedule(WEEK week,String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("insert into WEEK_SCHEDULE(USER_ID,TITLE,CONTENTS,DAY,START_TIME,END_TIME,ALRAM,CLASS_ROOM,COLOR) "
					+ "values(?,?,?,?,?,?,1,?,?)");
			ps.setString(1, id);
			ps.setString(2, week.getTitle());
			ps.setString(3, week.getContents());
			ps.setInt(4, week.getDay());
			ps.setInt(5, week.getStart_time());
			ps.setInt(6, week.getEnd_time());
			ps.setString(7, week.getClass_room());
			ps.setString(8, week.getColor());
			
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//addWeekSchedule

	@Override
	public WEEK getWeekSchedule(int day, int start_time,String user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		WEEK week = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("select * from WEEK_SCHEDULE where day=? AND start_time=? AND user_id=?");
			ps.setInt(1, day);
			ps.setInt(2, start_time);
			ps.setString(3, user_id);
			
			rs = ps.executeQuery();
			
			rs.next();
			week = new WEEK()
				.setTitle(rs.getString("title"))
				.setContents(rs.getString("contents"))
				.setDay(rs.getInt("day"))
				.setStart_time(rs.getInt("start_time"))
				.setEnd_time(rs.getInt("end_time"))
				.setClass_room(rs.getString("class_room"))
				.setColor(rs.getString("color"))
				.setAlram(rs.getInt("alram"));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return week;
	}//getWeekSchedule

	@Override
	public void updateSchedule(WEEK week, String id,int now_start_time,int now_day) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("update WEEK_SCHEDULE set title=?,contents=?,day=?,start_time=?,end_time=?,class_room=?,color=?,alram=? where user_id=? AND day=? AND start_time=?");
			ps.setString(1, week.getTitle());
			ps.setString(2, week.getContents());
			ps.setInt(3, week.getDay());
			ps.setInt(4, week.getStart_time());
			ps.setInt(5, week.getEnd_time());
			ps.setString(6, week.getClass_room());
			ps.setString(7, week.getColor());
			ps.setInt(8, 1);
			ps.setString(9, id);
			ps.setInt(10, now_day);
			ps.setInt(11, now_start_time);
			
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//updateSchedule

	@Override
	public void deleteSchedule(String id, int start_time, int day) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("delete WEEK_SCHEDULE where USER_ID=? AND start_time=? AND day=?");
			ps.setString(1, id);
			ps.setInt(2, start_time);
			ps.setInt(3, day);
			
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//deleteSchedule

	@Override
	public boolean bCheck_schedule(String id, int start_time, int end_time,
			int day) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("select * from WEEK_SCHEDULE where day=? AND USER_ID=? AND "
					+ "(START_TIME BETWEEN ? AND ? OR END_TIME BETWEEN ? AND ?)");
			ps.setInt(1, day);
			ps.setString(2, id);
			ps.setInt(3, start_time);
			ps.setInt(4, end_time);
			ps.setInt(5, start_time);
			ps.setInt(6, end_time);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return false;
	}

	@Override
	public boolean bCheck_schedule_start(String id,int start_time ,int check_time, int day){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("select * from WEEK_SCHEDULE where day=? AND USER_ID=? AND "
					+ "end_time between ? and ?");
			ps.setInt(1, day);
			ps.setString(2, id);
			ps.setInt(3, check_time);
			ps.setInt(4, start_time);

			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}//bCheck_schedule_start
		
		return false;
	}

	@Override
	public boolean bCheck_schedule_end(String id, int end_time, int check_time,
			int day) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("select * from WEEK_SCHEDULE where day=? AND USER_ID=? AND "
					+ "start_time between ? and ?");
			ps.setInt(1, day);
			ps.setString(2, id);
			ps.setInt(3, end_time);
			ps.setInt(4, check_time);

			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return false;
	}//bCheck_schedule_end
}
