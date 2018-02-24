package com.massage.infosys.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.massage.infosys.constant.Constant;
import com.massage.infosys.dao.SkillerMapper;
import com.massage.infosys.dao.UserMapper;
import com.massage.infosys.dao.VCodeMapper;
import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.po.User;
import com.massage.infosys.po.VCode;
import com.massage.infosys.service.UserService;
import com.massage.infosys.util.FileUploadUtil;
import com.massage.infosys.util.MD5Util;
import com.massage.infosys.util.SkillerPictureUtil;
import com.massage.infosys.util.StringUtil;
import com.massage.infosys.util.Util;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private VCodeMapper vCodeMapper;
	
	@Autowired
	private Constant constant;
	
	@Autowired
	private SkillerMapper skillerMapper;

	@Override
	public int register(String phone, String vCode, String nickName, String password, Double latitude, Double longitude) {
		int result = -1;
		if(!checkVerifyCode(phone, vCode)){
			return 2;
		}
		if(!Util.isCellphonePattern(phone)) {
			System.out.println("不是合法手机号");
			result = 4;
		} else if(checkPhoneExist(phone)){
			result = this.userMapper.updateUser(nickName, password, phone);
		}else{
			// 根据手机号码+盐+md5生产token
			String token = MD5Util.encode(phone, this.constant.getSalt());
			
			try {
				result = this.userMapper.save(phone, nickName, password, token, latitude, longitude);
			} catch (Exception e) {
				result = 3;
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 修改密码
	 */
	public int passwordBack(String newpassword, String phone, String vcode) {
		int result = 0;
		if(!checkVerifyCode(phone, vcode)){
			return 2;
		}
		if(!checkPhoneExist(phone)){
			return 3;
		}else {
			try {
				result = userMapper.updatePassword(newpassword, phone);
			} catch (Exception e) {
				result = 4;
				logger.info("数据库写入失败！");
			}
		}
		return result;
	}

	@Override
	public User login(String phone, String passwrod) {
		User user = userMapper.findByPhoneAndpassWord(phone, passwrod);
		return user;
	}

	@Override
	/**
	 * 获取个人信息
	 */
	public List<SkillerRegisterPojo> selectPeopleDeilByToken(String token) {
		// TODO Auto-generated method stub
		List<SkillerRegisterPojo> list = new ArrayList<SkillerRegisterPojo>();
		list = userMapper.selectPeopleDeilByToken(token);
		if (list != null || list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 刷新用户位置信息
	 */
	@Override
	public int updateUserLocation(String token, Double latitude,
			Double longitude) {
		int n = 0;
		n = userMapper.updateUserLocation(token, latitude, longitude);
		return n;
	}

	/***
	 * 普通用户编辑
	 */
	@Override
	public int updateUserInfoByToken(String token, String nickName) {
		int n = 0;
		n = userMapper.updateUserInfoByToken(token, nickName);
		return n;
	}

	/**
	 * 技师编辑
	 * 
	 * @throws IOException
	 */
	@Override
	@Transactional
	public int updateSkillerInfoByToken(
			SkillerRegisterPojo skillerRegisterPojo, HttpServletRequest request) {

		int n = 0;
		// 更新数据库
		n = userMapper.updateSkillerInfoByToken(skillerRegisterPojo);
		return n;
	}

	/**
	 * 通过token获取技师原头像url
	 */
	@Override
	public String selectPicHeadPathByToken(String token) {
		String picheadpath = userMapper.selectPicHeadPathByToken(token);
		return picheadpath;
	}

	@Override
	public void updatePicHead(MultipartFile picHead,
			HttpServletRequest request, String pic_head, String token)
			throws IOException {
		// 1.删除旧头像
		// 获取url中的文件名
		String oldFileName = StringUtil.getheadFileName(pic_head);
		String realPath = request.getSession().getServletContext()
				.getRealPath(constant.getPic_head());
		// 文件在服务器中的绝对路径
		String path = realPath + oldFileName;
		// 删除旧文件
		FileUploadUtil.deleteFile(path);

		// 2.添加新的头像到服务器
		// 将图片存到本地并且返回需要存到数据库的图片url
		String phone = skillerMapper.selectPhoneByToken(token);
		SkillerPictureUtil skillerPictureUtil = new SkillerPictureUtil();
		String headUrl = skillerPictureUtil.uploadPicHead(picHead, phone,
				request, constant);
		// 将新的url更新到数据库
		skillerMapper.updatePicHeadPath(headUrl, token);
	}

	@Override
	public String getVerifyCode(String phone,HttpServletRequest request) {
		logger.info("getVerifyCode phone = " + phone);
		
		//服务器端的逻辑过程：
		//1、接受用户的手机号，进行合法性判断。

		//2、随机产生 4 位数字验证码。
		String captcha = Util.createRandomVcode(4);
		logger.info("getVerifyCode captcha = " + captcha);
		
		//TODO 3、调用短信接口平台的 API 接口，将随机产生的验证码和用户的手机号作为输入参数，接收此接口的输出并判断短信验证码是否成功发送。
		/*YunpianClient client = MassageServerApplication.getClnt();
		//发送短信API
		Map<String, String> param = client.newParam(2);
		param.put(YunpianClient.MOBILE, phone);
		param.put(YunpianClient.TEXT, "【星帅科技】您的验证码是" + captcha);
		Result<SmsSingleSend> r = client.sms().single_send(param);
		if(r.isSucc()){
			logger.info("【发送验证码】 success " + r.getMsg());
			return 0;
		}
		logger.info("【发送验证码】 fail " + r.getMsg());*/
		
		//TODO 4、将验证码和手机号存入数据库中，并设置 TTL 即验证码的有效时间。
		if(vCodeMapper.findByPhone(phone) == null){
			vCodeMapper.save(phone, captcha);
		}else{
			vCodeMapper.updateByPhone(phone, captcha);
		}
		
		//TODO 5、校验过程，输入手机号和验证码查询数据库中是否有对应存在的数据。  --将会在注册时候验证，请参考register接口

		return captcha;
	}

	@Override
	public boolean checkVerifyCode(String phone, String vcode) {
		VCode v = vCodeMapper.findByPhone(phone);
		
		if(v == null || vcode == null || !vcode.equals(v.getVcode())){
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean checkPhoneExist(String phone) {
		User user = userMapper.findByPhone(phone);
		if(user == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<User> findAll(Integer pageNo, Integer pageSize) {
		return (List<User>) userMapper.findAll((pageNo - 1) * pageSize, pageSize);
	}

	@Override
	public int findAllTotalPagesCount(Integer pageSize) {
		return (userMapper.findAllCounts() - 1) / pageSize + 1;
	}
}
