package com.massage.infosys.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.massage.infosys.constant.Constant;

public class FileUploadUtil {
	@Autowired
	private Constant constant;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 上传图片到本地服务器
	public void copyFile(File file, MultipartFile pic_head)
			throws FileNotFoundException, IOException {

		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(pic_head.getBytes());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	// 生成图片保存路径
	public String getpath(MultipartFile pic_head, String phone) {
		String filename = pic_head.getOriginalFilename();
		String extensionName = filename
				.substring(filename.lastIndexOf(".") + 1);
		String newName = String.valueOf(System.currentTimeMillis() + phone
				+ "." + extensionName);
		return newName;
	}

	// 删除旧照片
	public static void deleteFile(String oldHeadPath) {
		// TODO Auto-generated method stub
		// 旧头像
		if (!oldHeadPath.isEmpty()) {
			File oldHeadPathfile = new File(oldHeadPath);
			oldHeadPathfile.delete();
		}
		// 旧生活照删除
		// if (oldShowPath!=null||oldShowPath.length>0) {
		// for (int i = 0; i < oldShowPath.length; i++) {
		// File oldShowPathFile = new File(oldShowPath[i]);
		// oldShowPathFile.delete();
		// }
		// }
	}

	public String getPictureUrl(HttpServletRequest request, String newpath,
			String picPath) {
		// 域名或ip
		String serverName = request.getServerName();
		// 端口
		int serverPort = request.getServerPort();
		// http
		String scheme = request.getScheme();
		System.out.println(serverName + "-------------" + serverPort
				+ "-------" + scheme);
		// 项目的访问路径中部包含项目名的情况：（如有项目名需后续添加到路径中去）
		String picUrl = scheme + "://" + serverName + ":" + serverPort
				+ picPath + newpath;
		return picUrl;

	}

}
