package cn.ovea_y.puzzle.util.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// 将长整形转换成日期格式的字符串
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");

		return df.format(new Date(time));
	}

	//将字符串格式的日期转换成长整形
	public static Long toLong(String time) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(time).getTime();
	}
	
}
