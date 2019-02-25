package com.hoperun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String nowDateStr(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
}
