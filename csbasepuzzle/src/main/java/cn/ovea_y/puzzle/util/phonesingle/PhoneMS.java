package cn.ovea_y.puzzle.util.phonesingle;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PhoneMS {
    // 短信应用 SDK AppID
    private static int appid; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    private static String appkey;
    // 需要发送短信的手机号码
//    private static String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
    // 短信模板 ID，需要在短信应用中申请
    private static int templateId;
    // 签名
    private static String smsSign = "个人学习记录";

    static {
        InputStream inStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("config/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        appid = Integer.parseInt(properties.get("appkey").toString());
        appkey = properties.get("appid").toString();
        templateId = Integer.parseInt(properties.get("templateId").toString());
    }

}
