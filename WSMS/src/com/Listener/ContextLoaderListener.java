package com.Listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.dao.Userdao.UserDaoImpl;
import com.dao.WeekDao.WeekDaoImpl;
import com.service.UserService.UserServiceImpl;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			ServletContext sc = event.getServletContext();

			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/spms");
			
			UserDaoImpl userdao = new UserDaoImpl();
			userdao.setDataSource(ds);
			WeekDaoImpl weekdao = new WeekDaoImpl();
			weekdao.setDataSource(ds);
			UserServiceImpl service = new UserServiceImpl();
			service.setUserdao(userdao);
			
			sc.setAttribute("userdao", userdao);
			sc.setAttribute("weekdao", weekdao);
			sc.setAttribute("servicedao", service);
			
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
