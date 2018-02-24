package com.massage.infosys.po;

import java.sql.Timestamp;


public class User {

	private long sharp_id;
	
    private String phone;
	
	private String nickname;
	
	private String password;
	
	private Timestamp create_time;
	
	private Timestamp rec_time;
	
	private Double latitude;
	
	private Double longitude;
	
	private int category;
	
	private String token;
	
	@Override
	public String toString() {
		return "User [sharp_id=" + sharp_id + ", phone=" + phone
				+ ", nickname=" + nickname + ", password=" + password
				+ ", create_time=" + create_time + ", rec_time=" + rec_time
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", category=" + category + ", token=" + token +  "]";
	}
	
	public long getSharp_id() {
		return sharp_id;
	}

	public void setSharp_id(long sharp_id) {
		this.sharp_id = sharp_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longtitude) {
		this.longitude = longtitude;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
