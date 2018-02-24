package com.massage.infosys.dto;

public class NearlySkillerPojo {

	private Long id;
	
	private String nickname;

	private String pic_head_path;

	private Double distance;

	private int age;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPic_head_path() {
		return pic_head_path;
	}

	public void setPic_head_path(String pic_head_path) {
		this.pic_head_path = pic_head_path;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
}
