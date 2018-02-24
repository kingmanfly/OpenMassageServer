package com.massage.infosys.util;

import java.util.regex.Pattern;

public class Util {
	public static String createRandomVcode(int digit) {
		// 验证码
		String vcode = "";
		for (int i = 0; i < digit; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}
	
	/**
	 * 验证手机号码
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145
	 * 电信号码段:133、153、180、189、173、177
	 * 
	 * @param cellphone
	 * @return
	 */
	public static boolean isCellphonePattern(String cellphone) {
		if(cellphone == null || cellphone.length() != 11) {
			return false;
		}
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3|7])|(18[0,5-9]))\\d{8}$"; 
		return Pattern.matches(regex, cellphone);
	}
}
