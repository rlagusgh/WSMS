/*

*/
package com.dao.Userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.vo.domain.LEVEL;
import com.vo.domain.USER;

public class UserDaoImpl implements UserDao{
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public USER getUser(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		USER user = null;
		
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement("select * from USERS where USER_ID = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				user = new USER();
				user.setID(rs.getString("user_id"));
				user.setNAME(rs.getString("user_name"));
				user.setPASSWORD(rs.getString("user_password"));
				user.setEMAIL(rs.getString("user_email"));
				user.setLevel(LEVEL.ValueOf(rs.getString("user_auth")));
				user.setJOIN_DATE(rs.getString("join_date"));
				user.setMOD_DATE(rs.getString("mod_date"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return user;
	}

	@Override
	public void addUser(USER user) {
		Connection conn = null;
		PreparedStatement ps = null;

		try{
			conn = ds.getConnection();
			
			ps = conn.prepareStatement("insert into USERS(USER_ID,USER_NAME,USER_PASSWORD,USER_EMAIL,USER_AUTH,JOIN_DATE,MOD_DATE)"
					+ " values(?,?,?,?,'ROLE_USER',NOW(),NOW())");
			ps.setString(1, user.getID());
			ps.setString(2, user.getNAME());
			ps.setString(3, user.getPASSWORD());
			ps.setString(4, user.getEMAIL());
			
			ps.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//addUser

	@Override
	public List<USER> getAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<USER> users = new ArrayList<USER>();
		USER user;
		
		try{
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users");
			
			while(rs.next()){
				user = new USER();
				user.setID(rs.getString("user_id"));
				user.setNAME(rs.getString("user_name"));
				user.setPASSWORD(rs.getString("user_password"));
				user.setEMAIL(rs.getString("user_email"));
				user.setLevel(LEVEL.ValueOf(rs.getString("user_auth")));
				user.setJOIN_DATE(rs.getString("join_date"));
				user.setMOD_DATE(rs.getString("mod_date"));
				
				users.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(stmt!=null){try{stmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return users;
	}//getUsers

	@Override
	public void deleteAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		
		try{
			conn = ds.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from USERS");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null){try{stmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
	}//deleteAllUsers

	@Override
	public int getCountAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from users");
			
			rs.next();
			count = rs.getInt(1);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(stmt!=null){try{stmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		return count;
	}//getCountAllUsers

	@Override
	public void UserUpdate(USER user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement("update USERS set USER_NAME=?,USER_PASSWORD=?,USER_EMAIL=?,USER_AUTH=? where USER_ID=?");
			ps.setString(1, user.getNAME());
			ps.setString(2, user.getPASSWORD());
			ps.setString(3, user.getEMAIL());
			ps.setInt(4, user.getLevel().intValue());
			ps.setString(5, user.getID());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
	}//UserUpdate

	@Override
	public int bCheck_id(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getString = null;
		
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement("select * from USERS where USER_ID=?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			getString = rs.getString("user_id");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(ps!=null){try{ps.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		
		if(getString == null){
			return 0; 	//���̵� ����
		}else{
			return 1;	//���̵� ����
		}
	}//bCheck_id
}//UserDaoImpl
