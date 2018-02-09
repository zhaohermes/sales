package org.yun.sales.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTools {

	/**
	 * 字符串转化成日期
	 * @param strDate 时间字符串
	 * @param pattern 时间字符串格式
	 * @return LocalDate
	 */
	public static LocalDateTime formatDateTime(String strDate, String pattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime date = LocalDateTime.parse(strDate,dateTimeFormatter);
		return date;
	}
	
}
