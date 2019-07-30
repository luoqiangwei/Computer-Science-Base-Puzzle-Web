package cn.ovea_y.puzzle.util.phonesingle;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

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
    private static String smsSign;

    private static Random random = new Random();

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
        smsSign = properties.get("smsSign").toString();
    }

    public static String sendMsg(String phone){
        SmsSingleSender smsSingleSender = new SmsSingleSender(appid, appkey);
        // 分别是模板中 {1} 和 {2}
        String[] params = new String[]{String.valueOf(random.nextInt(9999)), "10"};
        try {
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return params[0];
    }

}
