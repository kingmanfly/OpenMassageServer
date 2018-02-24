package com.massage.infosys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.massage.infosys.dao.AuthUserMapper;
import com.massage.infosys.po.AuthUser;
import com.massage.infosys.service.AuthUserService;
import com.massage.infosys.util.MD5Util;

@Service
public class AuthUserServiceImpl implements AuthUserService{

	@Autowired
	AuthUserMapper authUserMapper;
	
	@Override
	public List<AuthUser> findAll() {
		return authUserMapper.findAll();
	}

	@Override
	public AuthUser findByUserName(String username) {
		return authUserMapper.findByUserName(username);
	}

	
	@Override
	public int insertAuthUser(AuthUser authUser) {
		return authUserMapper.save(authUser.getUsername(),
				MD5Util.encode(authUser.getPassword(), ""),
				authUser.getNickname(),
				authUser.getRole());
	}
}
