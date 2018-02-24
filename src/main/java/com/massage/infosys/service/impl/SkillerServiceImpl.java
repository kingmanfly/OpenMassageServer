package com.massage.infosys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.massage.infosys.constant.Constant;
import com.massage.infosys.dao.SkillerMapper;
import com.massage.infosys.dao.UserMapper;
import com.massage.infosys.dto.HotSkillerPojo;
import com.massage.infosys.dto.NearlySkillerPojo;
import com.massage.infosys.dto.SkillerDetailPojo;
import com.massage.infosys.dto.SkillerPojo;
import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.po.Skiller;
import com.massage.infosys.service.SkillerService;
import com.massage.infosys.util.Distance;
import com.massage.infosys.util.FileUploadUtil;
import com.massage.infosys.util.Page;
import com.massage.infosys.util.SkillerPictureUtil;
import com.massage.infosys.util.StringUtil;

@Service
public class SkillerServiceImpl implements SkillerService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SkillerMapper skillerMapper;
	@Autowired
	private Constant constant;
	@Autowired
	private SkillerPictureUtil skillerPictureUtil;

	@Override
	public List<SkillerPojo> findAll(Integer pageNo, Integer pageSize) {
		List<Skiller> skList = skillerMapper.findAll((pageNo - 1) * pageSize, pageSize);
		List<SkillerPojo> skillerPojos = null;
		if (null != skList & skList.size() > 0) {
			skillerPojos = new ArrayList<SkillerPojo>();
			SkillerPojo item = null;
			for (Skiller skiller : skList) {
				item = new SkillerPojo();
				item.setNickname(skiller.getUser().getNickname());
				item.setAge(skiller.getAge());
				item.setDescription(skiller.getDescription());
				item.setId(skiller.getSharp_id());
				item.setLocation(skiller.getLocation());
				item.setStatus(skiller.getStatus());
				skillerPojos.add(item);
			}
		}
		return skillerPojos;
	}
	
	@Override
	public List<HotSkillerPojo> findhotSkillers() {
		List<Skiller> skList = skillerMapper.findhotSkillers();
		List<HotSkillerPojo> hotSkillerPojos = null;
		if (null != skList & skList.size() > 0) {
			hotSkillerPojos = new ArrayList<HotSkillerPojo>();
			HotSkillerPojo hotSkillerPojo = null;
			for (Skiller skiller : skList) {
				hotSkillerPojo = new HotSkillerPojo();
				hotSkillerPojo.setId(skiller.getSharp_id());
				hotSkillerPojo.setNickname(skiller.getUser().getNickname());
				hotSkillerPojo.setPic_head_path(skiller.getPic_head_path());
				hotSkillerPojo.setAge(skiller.getAge());
				hotSkillerPojos.add(hotSkillerPojo);
			}
			return hotSkillerPojos;
		}
		return null;
	}

	@Override
	public List<NearlySkillerPojo> findNearlySkillers(Double longitude,
			Double latitude, int pageNo, int pageSize) {
		pageNo = new Page(pageNo, pageSize).getPageNo();
		List<Skiller> skList = skillerMapper.findNearlySkillers(longitude,
				latitude, pageNo, pageSize);
		List<NearlySkillerPojo> nearlySkillerPojos = null;
		// 距离
		Double distance = 0.0;
		if (null != skList & skList.size() > 0) {
			nearlySkillerPojos = new ArrayList<NearlySkillerPojo>();
			NearlySkillerPojo nearlySkillerPojo = null;
			for (Skiller skiller : skList) {
				distance = Distance.getDistance(longitude, latitude, skiller
						.getUser().getLongitude(), skiller.getUser()
						.getLatitude());
				nearlySkillerPojo = new NearlySkillerPojo();
				nearlySkillerPojo.setId(skiller.getSharp_id());
				nearlySkillerPojo.setNickname(skiller.getUser().getNickname());
				nearlySkillerPojo.setPic_head_path(skiller.getPic_head_path());
				nearlySkillerPojo.setAge(skiller.getAge());
				nearlySkillerPojo.setDistance(distance);
				
				nearlySkillerPojos.add(nearlySkillerPojo);
			}
			return nearlySkillerPojos;
		}
		return null;
	}

	@Override
	public SkillerDetailPojo findSkillerDetailById(Long id) {
		Skiller skiller = skillerMapper.findById(id);
		SkillerDetailPojo skillerDetailPojo = null;
		if (null != skiller) {
			skillerDetailPojo = new SkillerDetailPojo();
			skillerDetailPojo.setId(skiller.getSharp_id());
			skillerDetailPojo.setAge(skiller.getAge());
			skillerDetailPojo.setDescription(skiller.getDescription());
			skillerDetailPojo.setHeight(skiller.getHeight());
			skillerDetailPojo.setLevel(skiller.getLevel());
			skillerDetailPojo.setLocation(skiller.getLocation());
			skillerDetailPojo.setNickname(skiller.getUser().getNickname());
			skillerDetailPojo.setPhone(skiller.getPhone());
			skillerDetailPojo.setPic_head_path(skiller.getPic_head_path());
			skillerDetailPojo.setPic_show_path(skiller.getPic_show_path());
			skillerDetailPojo.setSex(skiller.getSex());
			if(skiller.getPic_show_path() != null && skiller.getPic_show_path().length()>0){
				String[] picShowsList = skiller.getPic_show_path().split(",");
				skillerDetailPojo.setPic_show(picShowsList);
			}
		}

		return skillerDetailPojo;
	}

	// 技师注册升级对user和skiller表进行更新或添加
	@Transactional
	public int insertOrUpdateUserAndSkiller(SkillerRegisterPojo sPojo,
			HttpServletRequest request)  {
		
		int n = 0;
		int a = 0;
		int b = 0;
		String token = sPojo.getToken();
		// 技师注册说明已经是普通用户，user表中已经存在记录
		// 更新user表
		n = userMapper.updateUserBySpojo(sPojo);
		
		// 查询user表根据外键sharp_id查询skiller表判断skiller表是插入还是更新
		Long sharpId = userMapper.selectUserSharpIdByToken(token);
		sPojo.setSharp_id(sharpId);
		// 根据sharpId查询skiller表判断表中是否存在记录
		Skiller skiller = skillerMapper.selectSkillerPhoneBySharpId(sharpId);
		// 上传图片
		
		sPojo = skillerPictureUtil.uploadPicture(sPojo, request);

		// 如果skiller表中有数据
		if (skiller != null) {
			// 更新skiller
			a = skillerMapper.updateSkillerBySpojo(sPojo);
		} else {
			// 插入数据
			b = skillerMapper.insertSkillBySpojo(sPojo);
		}
		int i = n + a + b;
		return i;
	}

	/**
	 * 删除生活照 根据token获取旧的生活照路径
	 */
	@Override
	public String selectPicShowPathByToken(String token) {
		String pic_show = null;
		pic_show = skillerMapper.selectPicShowPathByToken(token);
		return pic_show;
	}

	/**
	 * 删除生活照
	 */
	@Transactional
	public void deletePicShow(String[] picSplit, String[] picShow,
			String token, HttpServletRequest request) {
		String pic_showPath = "";
		// 删除数据库中的路径
		// 获取删除之后的路径
		pic_showPath = StringUtil.removeduplicate(picSplit, picShow);
		// 更新数据库
		skillerMapper.updateskillerBypicShowPath(pic_showPath, token);
		// 删除本地的生活照图片
		String[] show = StringUtil.getfileName(picShow);
		String realPath = request.getSession().getServletContext()
				.getRealPath(constant.getPic_show());
		for (int i = 0; i < show.length; i++) {
			String path = realPath + show[i];
			FileUploadUtil.deleteFile(path);
		}

	}

	/**
	 * 根据token查询个人电话
	 */
	@Override
	public String selectPhoneByToken(String token) {
		String phone = skillerMapper.selectPhoneByToken(token);
		return phone;
	}

	/**
	 * 添加个人生活照！
	 */
	@Transactional
	public void uploadPicShow(String phone,
			HttpServletRequest request, String token) throws Exception {
		String pic_showPath = "";
		String oldPicShow = "";
		// 将图片存到本地并且返回需要存到数据库的图片url
		pic_showPath = skillerPictureUtil.uploadPicShow(phone,
				request);
		// 通过token获取当前数据库中存在的生活照路径
		Skiller skiller = skillerMapper.selectSkillerByToken(token);
		if (skiller != null) {
			oldPicShow = skiller.getPic_show_path();
			Long sharp_id = skiller.getSharp_id();
			if (oldPicShow == null || oldPicShow.isEmpty()) {
				// 数据库中没有生活照url，添加路径
				skillerMapper.updatePicShowPath(pic_showPath, sharp_id);
			}else {
				String newPicShowPath = oldPicShow + "," + pic_showPath;
				skillerMapper.updatePicShowPath(newPicShowPath, sharp_id);
			}
		}
	}

	@Override
	public int findAllTotalPagesCount(Integer pageSize) {
		int count = skillerMapper.findAllCounts();
		return (count - 1) / pageSize + 1;
	}
}
