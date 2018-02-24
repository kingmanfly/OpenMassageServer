package com.massage.infosys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.po.User;

/**
 * 用户service接口
 * 
 */
public interface UserService {
	// 获取验证码
	String getVerifyCode(String phone, HttpServletRequest request);
	// 验证验证码
	boolean checkVerifyCode(String phone, String vcode);
	// 验证手机号是否已注册
	boolean checkPhoneExist(String phone);
	
	// 注册
	int register(String phone, String vCode, String nickName, String password, Double latitude, Double longitude);

	// 登陆
	User login(String phone, String passwrod);

	// 修改密码
	int passwordBack(String newpassword, String phone, String vcode);

	// 获取个人信息
	List<SkillerRegisterPojo> selectPeopleDeilByToken(String token);

	// 获取用户列表
	List<User> findAll(Integer pageNo, Integer pageSize);
	
	//获取用户总数
	int findAllTotalPagesCount(Integer pageSize);
	
	// 刷新用户位置信息
	int updateUserLocation(String token, Double latitude, Double longitude);

	// 普通用户编辑
	int updateUserInfoByToken(String token, String nickName);

	// 技师编辑个人信息
	int updateSkillerInfoByToken(SkillerRegisterPojo SkillerRegisterPojo,
			HttpServletRequest request);

	// 技师编辑头像获取数据库原头像url
	String selectPicHeadPathByToken(String token);

	void updatePicHead(MultipartFile picHead, HttpServletRequest request,
			String pic_head, String token) throws IOException;

}
