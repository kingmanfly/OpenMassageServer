package com.massage.infosys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.massage.infosys.dao.LeaveMessageMapper;
import com.massage.infosys.service.LeaveMessageService;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService{

	@Autowired
	private LeaveMessageMapper leaveMessageMapper;
	
	@Override
	public int addAMessage(String from, String name, String contact,
			String title, String content) {
		int result = leaveMessageMapper.save(from, name, contact, title, content);
		
		return result;
	}

}
