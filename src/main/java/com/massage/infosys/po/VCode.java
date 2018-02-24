package com.massage.infosys.po;

import java.sql.Timestamp;

public class VCode {
	private long id;
	private String phone;
	private String vcode;
	private int expired;
	private Timestamp create_time;
	private Timestamp rec_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public int getExpired() {
		return expired;
	}

	public void setExpired(int expired) {
		this.expired = expired;
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

	@Override
	public String toString() {
		return "VCode [phone=" + phone + ", vcode=" + vcode + ", expired="
				+ expired + ", create_time=" + create_time + ", rec_time="
				+ rec_time + "]";
	}
}
