package cn.oveay.cspuzzle.util.security;

import cn.oveay.cspuzzle.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Service
public class Verify {
    @Autowired
    private RedisService redisService;
    private Long timeout;
    {
        InputStream inStream = Token.class.getClassLoader().getResourceAsStream("config/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeout = Long.parseLong(properties.get("token-timeout").toString());
    }

    /**
     * 检查电话验证码
     * @param userId
     * @param key
     * @return
     */
    public synchronized boolean checkVerify(String userId, String key){
        return String.valueOf(redisService.get(userId + "P")).equals(key);
    }
    public synchronized void addVerify(String userId, String key){
        redisService.set(userId + "P", key, timeout);
    }
}
