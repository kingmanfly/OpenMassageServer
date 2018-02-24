package com.massage.infosys.webvo;

import java.util.HashMap;
import java.util.Map;

import com.massage.infosys.enums.MassageEnum;

public class ResponseResult<T> {
	private T data;
	private Map<String, Object> map, datamap;

	public T getData() {
		return data;
	}

	public void setData(T data, String constant) {
		if (map == null)
			map = new HashMap<String, Object>();
		if (null != constant && datamap == null) {
			datamap = new HashMap<String, Object>();
			datamap.put(constant, data);
			map.put("data", datamap);
		} else if (null != data) {
			map.put("data", data);
		} else {
			map.put("data", "");
		}
		this.data = data;
	}

	public MassageEnum getMassageEnum() {
		return massageEnum;
	}

	public void setMassageEnum(MassageEnum massageEnum) {
		if (map == null)
			map = new HashMap<String, Object>();
		map.put("code", massageEnum.getCode());
		map.put("failureDetail", massageEnum.getFailure());
		this.massageEnum = massageEnum;
	}

	private MassageEnum massageEnum;

	public ResponseResult(T data, MassageEnum massageEnum, String constant) {
		setMassageEnum(massageEnum);
		setData(data, constant);
	}

	public Map<String, Object> getResult() {
		return map;
	}

}
