package com.kisanmitra.dao;



import org.springframework.stereotype.Component;

import com.kisanmitra.connection.MyConnection;
import com.kisanmitra.dto.User;
import com.kisanmitra.mapper.AuthenticationRowMapper;

@Component
public class AuthenticationDaoImplementation implements AuthenticationDao{

	MyConnection obj = new MyConnection();
	
	@Override
	public boolean createUser(User user) {
		try {
			String sql = "insert into user(user_id,first_name,last_name,password,phone,address,city,email_id,isActive,role_id) values (?,?,?,?,?,?,?,?,'yes',?)";
			
			obj.getJdbcTemplate().update(sql, new Object[] {user.getUserId(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getPhone(),user.getAddress(),user.getCity(),user.getEmailId(),user.getRoleId()} );
			}catch(Exception e) {
				System.out.println(e.getMessage()+"" +"error");
			}
			
			return true;
	}

	@Override
	public User loginUser(User user) {
		String sql = "select * from user where user_id=? AND password=?";
		
		
		User dbuser = obj.getJdbcTemplate().queryForObject(sql, new Object[] {user.getUserId(), user.getPassword()}, new AuthenticationRowMapper());
		
		if(dbuser == null) {
			return null;
		}
		return dbuser;
	}

	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
