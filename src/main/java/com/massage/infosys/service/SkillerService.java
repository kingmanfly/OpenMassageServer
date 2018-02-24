package com.massage.infosys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.massage.infosys.dto.HotSkillerPojo;
import com.massage.infosys.dto.NearlySkillerPojo;
import com.massage.infosys.dto.SkillerDetailPojo;
import com.massage.infosys.dto.SkillerPojo;
import com.massage.infosys.dto.SkillerRegisterPojo;

public interface SkillerService {
	List<SkillerPojo> findAll(Integer pageNo, Integer pageSize);
	
	int findAllTotalPagesCount(Integer pageSize);
	
	List<HotSkillerPojo> findhotSkillers();
	
	List<NearlySkillerPojo> findNearlySkillers(Double longitude,Double latitude,int pageNo,int pageSize);
	
	SkillerDetailPojo findSkillerDetailById(Long id);
	
	int insertOrUpdateUserAndSkiller(SkillerRegisterPojo sPojo, HttpServletRequest request) throws IOException;

	String selectPicShowPathByToken(String token);

	void deletePicShow(String[] picSplit, String[] picShow, String token, HttpServletRequest request);

	String selectPhoneByToken(String token);

	void uploadPicShow(String phone, HttpServletRequest request, String token) throws Exception;
}
