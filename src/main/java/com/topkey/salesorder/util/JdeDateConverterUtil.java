package com.topkey.salesorder.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JdeDateConverterUtil {

	public static String convertFromJdeDate(String jdeDate) {
		try {
			// JDE 日期解析
			int jdeYear = Integer.parseInt(jdeDate.substring(0, 3));
			int dayOfYear = Integer.parseInt(jdeDate.substring(3));

			// 计算年份
			int jdeStartYear = 1900;
			int century = (jdeYear + jdeStartYear) / 100 * 100;
			int year = jdeYear + century - (century - jdeStartYear);

			LocalDate localDate = LocalDate.ofYearDay(year, dayOfYear);
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return localDate.format(outputFormatter);
		} catch (Exception e) {
			throw new RuntimeException("Invalid JDE date format", e);
		}
	}
}
