package cn.oveay.cspuzzle.util.commons;


import cn.ovea_y.puzzle.util.commons.exception.DateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
    private static String format = "yyyy-MM-dd HH:mm:ss";

    public static String formatToDate(Long date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String formatToDate(Long date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Long formatToTimestamp(String date) throws DateFormatException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            throw new DateFormatException("时间格式错误，当前默认格式为：" + format);
        }
    }

    public static Long formatToTimestamp(String date, String format) throws DateFormatException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            throw new DateFormatException("时间转换错误 ：" + e.getMessage());
        }
    }
}
