package com.massage.infosys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.massage.infosys.constant.Constant;
import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.enums.MassageEnum;
import com.massage.infosys.po.User;
import com.massage.infosys.service.UserService;
import com.massage.infosys.webvo.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final String USER_SESSION_VERIFY_CODE_KEY = "session_vcode_key";

	@Autowired
	private UserService userService;
	@Autowired
	private Constant constant;

	@PostMapping(value = "/getVerifyCode")
	public Map<String, Object> getVerifyCode(
			@RequestParam("phone") String phone, HttpServletResponse response,
			HttpServletRequest request) {

		String result = this.userService.getVerifyCode(phone, request);
		if (result != null && result.length() == 4 ) {
			return new ResponseResult<String>(result, MassageEnum.SUCCESS, "vcode")
					.getResult();
		} else {
			return new ResponseResult<String>("", MassageEnum.FAILURE, "")
					.getResult();
		}

	}

	/**
	 * 注册
	 * 
	 * @param phone
	 * @param password
	 * @param checkcode
	 * @param nickname
	 * @return
	 */
	@PostMapping(value = "/register")
	public Map<String, Object> register(@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("checkcode") String checkcode,
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "latitude", required = true) Double latitude,
			@RequestParam(value = "longitude", required = true) Double longitude) {

		int result = 0;
		result = this.userService
				.register(phone, checkcode, nickname, password, latitude, longitude);
		switch (result) {
		case 1:
			return new ResponseResult<String>("", MassageEnum.SUCCESS, "")
					.getResult();
		case 2:
			return new ResponseResult<String>("", MassageEnum.CODEWRONG, "")
					.getResult();
		case 3:
			return new ResponseResult<String>("", MassageEnum.SQLEXCEPTION, "")
					.getResult();
		default:
			return new ResponseResult<String>("", MassageEnum.FAILURE, "")
					.getResult();
		}
	}

	/**
	 * 忘记密码
	 * 
	 * @param phone
	 * @param verificationCode
	 * @param newpassword
	 * @param model
	 * @param response
	 * @return
	 * @throws InterruptedException
	 */
	@PostMapping("/passwordBack")
	public Map<String, Object> passwordBack(String phone,
			@RequestParam("checkcode") String verificationCode, @RequestParam("password") String newpassword){
		int result = 0;
		result = userService.passwordBack(newpassword, phone, verificationCode);
		switch (result) {
		case 1:
			return new ResponseResult<String>("", MassageEnum.SUCCESS, "")
					.getResult();
		case 2:
			return new ResponseResult<String>("", MassageEnum.CODEWRONG, "")
					.getResult();
		case 3:
			return new ResponseResult<String>("", MassageEnum.HAVE_NO_PHONE, "")
					.getResult();
		case 4:
			return new ResponseResult<String>("", MassageEnum.SQLEXCEPTION, "")
					.getResult();
		default:
			return new ResponseResult<String>("", MassageEnum.MODIFYFAILURE, "")
					.getResult();
		}
	}

	/**
	 * 登陆
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/login")
	public Map<String, Object> findNearlySkillers(
			@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		User user = null;
		user = userService.login(phone, password);
		if (null != user) {
			return new ResponseResult<String>(user.getToken(),
					MassageEnum.SUCCESS, "token").getResult();
		} else {

		}
		return new ResponseResult<String>("", MassageEnum.NOTHING, "")
				.getResult();
	}

	/**
	 * 编辑
	 */
	@PostMapping("/edit")
	public Map<String, Object> edit(SkillerRegisterPojo skillerRegisterPojo,
			Model model, HttpServletRequest request) {
		int n = 0;
		try {
			Integer category = skillerRegisterPojo.getCategory();
			if (category == 1) {
				// 普通用户编辑
				String token = skillerRegisterPojo.getToken();
				String nickName = skillerRegisterPojo.getNickname();
				n = userService.updateUserInfoByToken(token, nickName);
			}
			if (category == 2) {
				// 技师编辑
				n = userService.updateSkillerInfoByToken(skillerRegisterPojo,
						request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<String>("", MassageEnum.FAILURE, "")
					.getResult();
		}
		if (n > 0) {
			return new ResponseResult<String>("", MassageEnum.SUCCESS, "")
					.getResult();
		}
		return null;
	}

	/**
	 * 技师的头像编辑
	 * 
	 * @param picHead
	 * @param request
	 * @return
	 */
	@PostMapping("/headEdit")
	public Map<String, Object> headEdit(MultipartFile picHead, String token,
			HttpServletRequest request) {
		// 获取数据库中的原头像url
		String pic_head = userService.selectPicHeadPathByToken(token);
		// 删除旧头像添加新头像
		try {
			userService.updatePicHead(picHead, request, pic_head, token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<String>("", MassageEnum.FAILURE, "")
					.getResult();
		}
		return new ResponseResult<String>("", MassageEnum.SUCCESS, "")
				.getResult();

	}

	/**
	 * 获取个人信息
	 */
	@GetMapping("/getprofile")
	public Map<String, Object> getprofile(
			@RequestParam(value = "token") String token) {
		List<SkillerRegisterPojo> list = new ArrayList<SkillerRegisterPojo>();
		list = userService.selectPeopleDeilByToken(token);
		if (list != null && list.size() > 0) {
			return new ResponseResult<List<SkillerRegisterPojo>>(list,
					MassageEnum.SUCCESS, constant.getProfile()).getResult();
		}
		return new ResponseResult<List<SkillerRegisterPojo>>(list,
				MassageEnum.FAILURE, null).getResult();

	}

	/**
	 * 进入首页时刷新当前位置
	 */
	@PostMapping("/flashposition")
	public Map<String, Object> flashposition(
			@RequestParam("token") String token,
			@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude) {
		System.out.println("flashposition");
		int n = 0;
		// 更新user的位置信息
		try {
			n = userService.updateUserLocation(token, latitude, longitude);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<String>("", MassageEnum.FAILURE, null)
					.getResult();
		}
		if (n > 0) {
			return new ResponseResult<String>("", MassageEnum.SUCCESS, null)
					.getResult();
		}
		return null;
	}
}
