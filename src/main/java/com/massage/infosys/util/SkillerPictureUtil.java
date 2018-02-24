package com.massage.infosys.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.massage.infosys.constant.Constant;
import com.massage.infosys.dto.SkillerRegisterPojo;

@Service
public class SkillerPictureUtil {

	@Autowired
	private Constant constant;

	FileUploadUtil fileUploadUtil = new FileUploadUtil();

	public SkillerRegisterPojo uploadPicture(SkillerRegisterPojo sPojo,
			HttpServletRequest request) {
		// 绝对路径
		// 个人头像保存的文件夹路径

		String realPath1 = request.getSession().getServletContext()
				.getRealPath(constant.getPic_head());
		// 个人头像文件创建与上传
		Part part = null;
		try {
			part = request.getPart("file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String phone = sPojo.getPhone();
		// 生成个人头像全路径
		String newpath = "";
		String pic_headUrl = "";
		String headpath = "";
		newpath = System.currentTimeMillis() + phone + ".jpg";
		headpath = realPath1 + newpath;
		try {
			part.write(headpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 生成图片获取url
		pic_headUrl = fileUploadUtil.getPictureUrl(request, newpath,
				constant.getPic_head());
		sPojo.setPic_head_path(pic_headUrl);

		return sPojo;

	}

	// 个人生活照上传
	public String uploadPicShow(String phone, HttpServletRequest request)
			throws IOException {

		String realPath1 = request.getSession().getServletContext()
				.getRealPath(constant.getPic_show());
		// 生成个人生活照全路径
		String newpath = "";
		String pic_ShowUrl = "";
		String showPath = "";
		// 存储文件
		Part part = null;
		try {
			part = request.getPart("file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (part != null) {
			newpath = System.currentTimeMillis() + phone + ".jpg";
			showPath = realPath1 + newpath;
			try {
				part.write(showPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 生成图片获取url
			pic_ShowUrl = fileUploadUtil.getPictureUrl(request, newpath,
					constant.getPic_show());
		}
		// 生成图片获取url
		pic_ShowUrl = fileUploadUtil.getPictureUrl(request, newpath,
				constant.getPic_show());
		return pic_ShowUrl;
	}

	public String uploadPicHead(MultipartFile picHead, String phone,
			HttpServletRequest request, Constant constant) throws IOException {
		String realPath = request.getSession().getServletContext()
				.getRealPath(constant.getPic_head());
		String newpath = "";
		String pic_headUrl = "";
		String headPath = "";
		if (picHead != null) {

			newpath = fileUploadUtil.getpath(picHead, phone);
			headPath = realPath + newpath;
			File headFile = new File(headPath);

			if (!headFile.exists()) {
				headFile.createNewFile();
			}
			fileUploadUtil.copyFile(headFile, picHead);
		}
		// 生成图片获取url
		pic_headUrl = fileUploadUtil.getPictureUrl(request, newpath,
				constant.getPic_head());
		return pic_headUrl;
	}

}
