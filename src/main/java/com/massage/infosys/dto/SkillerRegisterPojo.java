package com.massage.infosys.dto;

import org.springframework.web.multipart.MultipartFile;

//技师升级注册/个人信息
public class SkillerRegisterPojo {

	private Long sharp_id;
	private String phone;
	private String token;
	private int age;
	private int height;
	private String description;
	private int level;
	private String location;	
	private MultipartFile pic_head;
	private MultipartFile[] pic_show;
	private Double latitude;
	private Double longitude;
	private String pic_head_path;
	private String pic_show_path;
	private int sex;
	private String nickname;
	private Integer category;
	private MultipartFile filePath;

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Long getSharp_id() {
		return sharp_id;
	}

	public void setSharp_id(Long sharp_id) {
		this.sharp_id = sharp_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public MultipartFile getPic_head() {
		return pic_head;
	}

	public void setPic_head(MultipartFile pic_head) {
		this.pic_head = pic_head;
	}

	public MultipartFile[] getPic_show() {
		return pic_show;
	}

	public void setPic_show(MultipartFile[] pic_show) {
		this.pic_show = pic_show;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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

}
