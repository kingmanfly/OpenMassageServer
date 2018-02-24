package com.massage.infosys.po;

import java.sql.Timestamp;

public class DataStatistics {

	private Long id;

	private String content;

	private Long counting;

	private Timestamp create_time;

	private Timestamp rec_time;

	private byte category;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCounting() {
		return counting;
	}

	public void setCounting(Long counting) {
		this.counting = counting;
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

	public byte getCategory() {
		return category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

}
