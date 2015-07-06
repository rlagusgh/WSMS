package com.Controller.FrontController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.Controller;
import com.Controller.Schedule.*;
import com.Controller.UserController.*;
import com.vo.domain.*;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();

		try{
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("userdao", sc.getAttribute("userdao"));
			model.put("commentdao", sc.getAttribute("commentdao"));
			model.put("weekdao", sc.getAttribute("weekdao"));
			model.put("tbldao", sc.getAttribute("tbldao"));
			model.put("servicedao", sc.getAttribute("servicedao"));
			
			model.put("session", request.getSession());
			
			Controller pageController = null;
			
			if("/main.do".equals(servletPath)){
				pageController = new LoginCheckController();
				model.put("id", request.getParameter("id"));
				model.put("password",request.getParameter("password"));
			}else if("/schedule.do".equals(servletPath)){
				pageController = new getScheduleController();
			}else if("/SCHEDULE/scheduleInfo.do".equals(servletPath)){
				pageController = new getScheduleInfoController();
				model.put("day",request.getParameter("day"));
				model.put("start_time", request.getParameter("start_time"));
			}else if("/SCHEDULE/scheduleMod.do".equals(servletPath)){
				pageController = new modifyScheduleController();
				if(request.getParameter("title")!=null){
					model.put("now_day", request.getParameter("now_day"));
					model.put("now_start_time", request.getParameter("now_start_time"));
					model.put("now_end_time", request.getParameter("now_end_time"));
					WEEK week = new WEEK()
						.setTitle(request.getParameter("title"))
						.setClass_room(request.getParameter("class_room"))
						.setContents(request.getParameter("contents"))
						.setColor(request.getParameter("color"))
						.setAlram(1)
						.setDay(Integer.parseInt(request.getParameter("day")))
						.setStart_time(Integer.parseInt(request.getParameter("start_time")))
						.setEnd_time(Integer.parseInt(request.getParameter("end_time")));
					model.put("week", week);
				}else{
					model.put("day",request.getParameter("day"));
					model.put("start_time", request.getParameter("start_time"));
				}
			}else if("/SCHEDULE/scheduleDel.do".equals(servletPath)){
				pageController = new DelScheduleController();
				model.put("day",request.getParameter("day"));
				model.put("start_time", request.getParameter("start_time"));
			}else if("/SCHEDULE/addSchedule.do".equals(servletPath)){
				pageController = new AddScheduleController(); 
				if(request.getParameter("title")!=null){
					WEEK week = new WEEK()
						.setTitle(request.getParameter("title"))
						.setDay(Integer.parseInt(request.getParameter("day")))
						.setStart_time(Integer.parseInt(request.getParameter("start_time")))
						.setEnd_time(Integer.parseInt(request.getParameter("end_time")))
						.setClass_room(request.getParameter("class_room"))
						.setContents(request.getParameter("contents"))
					.setColor(request.getParameter("color"));
					model.put("week", week);
				}
			}else if("/USER/logout.do".equals(servletPath)){
				pageController = new LogoutController();
			}else if("/USER/UserInfo.do".equals(servletPath)){
				pageController = new UserListController();
			}else if("/USER/UserAdd.do".equals(servletPath)){
				pageController = new UserAddController();
				if(request.getParameter("id")!= null){
					model.put("user", new USER()
						.setID(request.getParameter("id"))
						.setNAME(request.getParameter("name"))
						.setPASSWORD(request.getParameter("password"))
						.setEMAIL(request.getParameter("email"))
					);
				}
			}else if("/USER/Check_id.do".equals(servletPath)){
				pageController = new Check_idController();
				model.put("id", request.getParameter("id"));
			}else if("/USER/UserLevelgrade.do".equals(servletPath)){
				pageController = new UserLevelgradeController();
				model.put("id",request.getParameter("id"));
			}else if("/SCHEDULE/scheduleInfo.do".equals(servletPath)){
				pageController = new getScheduleController();
			}
			
			String viewUrl = pageController.execute(model);
			
			for(String key : model.keySet()){
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
				return;
			}else{
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error/Error.jsp");
			rd.forward(request, response);
		}
	}//service
}//class
