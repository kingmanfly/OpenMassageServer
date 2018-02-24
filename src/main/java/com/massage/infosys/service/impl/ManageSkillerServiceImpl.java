package com.massage.infosys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.massage.infosys.dao.ManagerMapper;
import com.massage.infosys.service.ManageSkillerService;

@Service
public class ManageSkillerServiceImpl implements ManageSkillerService{

	@Autowired
	ManagerMapper managerMapper;
	
	@Override
	public int auditingPass(String id) {
		return managerMapper.updateStatus(id, 1);
	}

	@Override
	public int auditingReject(String id) {
		return managerMapper.updateStatus(id, 2);
	}

}
