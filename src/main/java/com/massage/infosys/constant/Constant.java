package com.massage.infosys.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "constant",locations = "classpath:constant.properties")
public class Constant {

	private  String hotskillers;
	
	private  String nearlyskillers;
	
	private  String salt;
	
	private String checkcode;
	
	private String pic_head;
	private String pic_show;
	private String profile;
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPic_head() {
		return pic_head;
	}

	public void setPic_head(String pic_head) {
		this.pic_head = pic_head;
	}

	public String getPic_show() {
		return pic_show;
	}

	public void setPic_show(String pic_show) {
		this.pic_show = pic_show;
	}

	public String getHotskillers() {
		return hotskillers;
	}

	public void setHotskillers(String hotskillers) {
		this.hotskillers = hotskillers;
	}

	public String getNearlyskillers() {
		return nearlyskillers;
	}

	public void setNearlyskillers(String nearlyskillers) {
		this.nearlyskillers = nearlyskillers;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
}
