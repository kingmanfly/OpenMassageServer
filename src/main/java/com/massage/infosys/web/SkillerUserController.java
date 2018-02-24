package com.massage.infosys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.massage.infosys.constant.Constant;
import com.massage.infosys.dto.HotSkillerPojo;
import com.massage.infosys.dto.NearlySkillerPojo;
import com.massage.infosys.dto.SkillerDetailPojo;
import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.enums.MassageEnum;
import com.massage.infosys.service.SkillerService;
import com.massage.infosys.webvo.ResponseResult;

@RestController
@RequestMapping("/skillerUser")
public class SkillerUserController {

	@Autowired
	private SkillerService skillerService;

	@Autowired
	private Constant constant;

	/**
	 * 技师升级注册
	 * 
	 * @param sPojo
	 * @param model
	 * @param response
	 * @param request
	 * @return
	 */
	
	@PostMapping("/skillerRegister")
	public Map<String, Object> skillerRegister(SkillerRegisterPojo sPojo,
			HttpServletResponse response,
			HttpServletRequest request) {
		int n = 0;

		// 更新或添加user与skiller表（内含删除旧图片，添加新图片）
		try {
			n = skillerService.insertOrUpdateUserAndSkiller(sPojo, request);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<String>("sdf", MassageEnum.UPDATEERROR, null)
					.getResult();
		}
		if (n > 0) {
			return new ResponseResult<Object>(sPojo, MassageEnum.SUCCESS, null)
					.getResult();
		}
		return null;
	}

	/**
	 * 删除个人生活照
	 * 
	 * @param picShowUrl
	 * @param token
	 * @param request
	 * @return
	 */
	@PostMapping("/deletePicShow")
	public Map<String, Object> deletePicShow(String picShowUrl, String token,
			HttpServletRequest request) {
		String[] picSplit = null;
		String[] picShow = null;
		// 获取原来数据库中的路径
		String pic_show = skillerService.selectPicShowPathByToken(token);
		if (!pic_show.isEmpty()) {
			picSplit = pic_show.split(",");
		}
		// 前端传过来需要删除的图片路径
		picShow = picShowUrl.split(",");
		// 删除数据库路径与本地图片
		try {
			skillerService.deletePicShow(picSplit, picShow, token, request);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<String>("", MassageEnum.DELETEERROR, null)
					.getResult();
		}
		return new ResponseResult<String>("", MassageEnum.SUCCESS, null)
				.getResult();
	}

	/**
	 * 添加生活照
	 * 
	 * @param pic_show
	 * @param token
	 * @param request
	 * @return
	 */
	@PostMapping("/insertPicShow")
	public Map<String, Object> insertPicShow(@RequestParam("token") String token, 
			HttpServletRequest request) {
		// 1.生成路径（路径由当前时间与个人电话号码组成）
		// 2.根据token获取个人电话

		String phone = skillerService.selectPhoneByToken(token);

		// 将图片存到本地并且返回需要存到数据库的图片url
		try {
			skillerService.uploadPicShow(phone, request, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseResult<String>("", MassageEnum.FAILURE, null)
					.getResult();
		}
		return new ResponseResult<String>("", MassageEnum.SUCCESS, null)
				.getResult();
	}

	@GetMapping("/hotskillers")
	public Map<String, Object> findhotSkillers() {
		List<HotSkillerPojo> skillers = null;
		try {

			skillers = skillerService.findhotSkillers();
			if (null != skillers) {
				return new ResponseResult<List<HotSkillerPojo>>(skillers,
						MassageEnum.SUCCESS, constant.getHotskillers())
						.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseResult<List<HotSkillerPojo>>(skillers,
				MassageEnum.FAILURE, null).getResult();
	}

	@PostMapping(value = "/nearlyskillers")
	public Map<String, Object> findNearlySkillers(
			@RequestParam("longitude") Double longitude,
			@RequestParam("latitude") Double latitude,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		List<NearlySkillerPojo> skillers = null;
		try {

			skillers = skillerService.findNearlySkillers(longitude, latitude,
					pageNo, pageSize);
			if (null != skillers && skillers.size() > 0) {
				return new ResponseResult<List<NearlySkillerPojo>>(skillers,
						MassageEnum.SUCCESS, constant.getNearlyskillers())
						.getResult();
			} else {
				return new ResponseResult<List<NearlySkillerPojo>>(skillers,
						MassageEnum.NOTHING, null).getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<List<NearlySkillerPojo>>(skillers,
					MassageEnum.FAILURE, null).getResult();
		}

	}

	@GetMapping(value = "/skillerdetail")
	public Map<String, Object> findSkillerdetailById(Long id) {
		try {
			SkillerDetailPojo skillerdetail = null;
			skillerdetail = this.skillerService.findSkillerDetailById(id);
			if (null != skillerdetail) {
				return new ResponseResult<SkillerDetailPojo>(skillerdetail,
						MassageEnum.SUCCESS, null).getResult();
			} else {
				return new ResponseResult<SkillerDetailPojo>(skillerdetail,
						MassageEnum.NOTHING, null).getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult<SkillerDetailPojo>(null,
					MassageEnum.FAILURE, null).getResult();
		}
	}
}
