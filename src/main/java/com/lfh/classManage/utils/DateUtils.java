package com.lfh.classManage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 *
 * @author lfh
 * @version 2019年3月12日
 */
public final class DateUtils {
	public static Date parse(String date,String format) {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
