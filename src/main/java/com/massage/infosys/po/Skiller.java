package com.massage.infosys.po;

import java.sql.Timestamp;

public class Skiller {

	private Long skiller_id;
	
	private Long sharp_id;

	private String phone;
	
	private String pic_head_path;
	
	private String pic_show_path;
	
	private String description;
	
	private int age;
	
	private int height;
	
	private int level;
	
	private int sex;
	
	private String location;
	
	private Timestamp create_time;
	
	private Timestamp rec_time;
	
	private User user;
	
	private int status;
	
	private DataStatistics dataStatistics;
	
	public Long getSkiller_id() {
		return skiller_id;
	}

	public void setSkiller_id(Long skiller_id) {
		this.skiller_id = skiller_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getRec_time() {
		return rec_time;
	}

	public void setRec_time(Timestamp rec_time) {
		this.rec_time = rec_time;
	}

	public Long getSharp_id() {
		return sharp_id;
	}

	public void setSharp_id(Long sharp_id) {
		this.sharp_id = sharp_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DataStatistics getDataStatistics() {
		return dataStatistics;
	}

	public void setDataStatistics(DataStatistics dataStatistics) {
		this.dataStatistics = dataStatistics;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
