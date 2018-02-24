package com.massage.infosys.service;

import java.util.List;

import com.massage.infosys.po.AuthUser;

public interface AuthUserService {
	AuthUser findByUserName(String username);
	List<AuthUser> findAll();
	int insertAuthUser(AuthUser authUser);
}
