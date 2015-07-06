package com.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OracleDBConn {
	public Connection getDBConn() throws ClassNotFoundException,SQLException{
		Connection conn = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@dalma.dongguk.ac.kr:1521:stud2","pjh0130","pjh0130");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection getDSConn() throws ClassNotFoundException,SQLException,NamingException{
		Connection conn = null;
		
		try{
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/oracle");  
			
			conn = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
