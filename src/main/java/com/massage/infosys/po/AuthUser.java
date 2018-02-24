package com.massage.infosys.po;

import java.sql.Timestamp;

public class AuthUser {
	private long id;
	private String username;
	private String nickname;
	private String password;
	private int role;
	private Timestamp create_time;
	private Timestamp rec_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

}
