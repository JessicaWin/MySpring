package com.jessica.service.impl;

import org.springframework.stereotype.Service;

import com.jessica.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void insertUser(String userName) {
		System.out.println("insertUser:" + userName);
	}

	@Override
	public void updateUser(String userId, String userName) {
		System.out.println("updateUser:" + userName);
	}

}
