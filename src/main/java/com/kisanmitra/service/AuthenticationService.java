package com.kisanmitra.service;

import com.kisanmitra.dto.User;

public interface AuthenticationService {

	User loginUser(User user);
    boolean createUser(User user);
}
