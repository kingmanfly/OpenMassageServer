package com.massage.infosys.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

	public static String removeduplicate(String[] picSplit, String[] picShow) {
		List<String> lista = new ArrayList<String>(Arrays.asList(picSplit));
		List<String> listb = new ArrayList<String>(Arrays.asList(picShow));
		lista.removeAll(listb);
		String s = "";
		for (int i = 0; i < lista.size(); i++) {
			s = s + lista.get(i) + ",";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

	public static String[] getfileName(String[] picShow) {
		String s = "";
		String[] split = null;
		for (int i = 0; i < picShow.length; i++) {
			int lastIndexOf = picShow[i].lastIndexOf('/');
			String substring = picShow[i].substring(lastIndexOf + 1,
					picShow[i].length());
			s += substring + ",";
		}
		s.substring(0, s.length() - 1);
		split = s.split(",");
		return split;
	}

	public static String getheadFileName(String pic_head) {
		int lastIndexOf = pic_head.lastIndexOf('/');
		String substring = pic_head.substring(lastIndexOf + 1,
				pic_head.length());
		return substring;
	}

}
