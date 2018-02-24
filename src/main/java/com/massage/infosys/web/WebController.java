package com.massage.infosys.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.massage.infosys.enums.MassageEnum;
import com.massage.infosys.service.impl.LeaveMessageServiceImpl;
import com.massage.infosys.webvo.ResponseResult;

@RestController
@RequestMapping("/web")
public class WebController {
	
	@Autowired
	LeaveMessageServiceImpl leaveMessageServiceImpl;
	
	@PostMapping(value = "/leaveMessage")
	public Map<String, Object> leaveAMessage(
			@RequestParam(value = "from", required = true)String from,
			@RequestParam(value = "title", defaultValue = "unknown")String title,
			@RequestParam(value = "name", defaultValue = "unknown")String name,
			@RequestParam(value = "contact", required = true)String contact,
			@RequestParam(value = "content", defaultValue = "unknown")String content
			){
		if("9tiger".equals(from) || "beautifulstar".equals(from)){
			//写入数据库
			leaveMessageServiceImpl.addAMessage(from, name, contact, title, content);
		}else{
			System.out.println("【我没授权】");
		}
		System.out.println("【插入成功】" + name);
		return new ResponseResult<String>("", MassageEnum.SUCCESS, "").getResult();
		
	}
}
