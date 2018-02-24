package com.massage.infosys.dto;

import org.springframework.web.multipart.MultipartFile;

public class SkillerDetailPojo {
	private Long id;
	private String phone;
	private String nickname;
	
	private int age;
	
	private int sex;
	
	private int height;
	
	private int level;
	
	private String description;
	
	private String location;
	
	private String pic_head_path;
	
	private String pic_show_path;
	
	private String token;
	
	private Integer category;
	private MultipartFile pic_head;
	private String[] pic_show;
	
	public MultipartFile getPic_head() {
		return pic_head;
	}

	public void setPic_head(MultipartFile pic_head) {
		this.pic_head = pic_head;
	}

	public String[] getPic_show() {
		return pic_show;
	}

	public void setPic_show(String[] pic_show) {
		this.pic_show = pic_show;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPic_head_path() {
		return pic_head_path;
	}

	public void setPic_head_path(String pic_head_path) {
		this.pic_head_path = pic_head_path;
	}

	public String getPic_show_path() {
		return pic_show_path;
	}

	public void setPic_show_path(String pic_show_path) {
		this.pic_show_path = pic_show_path;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
