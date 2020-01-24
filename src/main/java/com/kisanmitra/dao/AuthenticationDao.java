package com.kisanmitra.dao;

import com.kisanmitra.dto.User;

public interface AuthenticationDao {

	boolean createUser(User user);
	User loginUser(User user);
	User selectUser(User user);
}
