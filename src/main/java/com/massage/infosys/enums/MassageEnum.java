package com.massage.infosys.enums;

public enum MassageEnum {
	SUCCESS(1, "成功"), 
	FAILURE(0, "失败"), 
	NOTHING(2, "没有数据"),
	CODEWRONG(3, "验证码错误"),
	FILENOTFOUNDEXCEPTION(4, "文件未找到"),
	IOEXCEPTION(5,"文件上传异常"), 
	UPDATEERROR(6, "技师更新失败"), 
	DELETEERROR(7, "删除生活照异常"),
	MODIFYFAILURE(8, "修改失败,验证码错误或密码过长！"),
	SQLEXCEPTION(9, "用户表更新异常"),
	HAVE_NO_PHONE(10, "本手机号未注册")
	;

	private MassageEnum(int code, String failure) {
		this.code = code;
		this.failure = failure;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	private int code;

	private String failure;

	public static MassageEnum stateOf(int code) {
		for (MassageEnum seckillStateEnum : values()) {
			if (seckillStateEnum.getCode() == code) {
				return seckillStateEnum;
			}
		}
		return null;
	}

}
