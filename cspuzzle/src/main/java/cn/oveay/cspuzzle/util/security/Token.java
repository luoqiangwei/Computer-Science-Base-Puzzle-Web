package cn.oveay.cspuzzle.util.security;

import cn.oveay.cspuzzle.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class Token {
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
     * 检查form的token是否正确，用 + "⚪"来标识时fOrm表单的验证信息
     * @param userId
     * @param key
     * @return
     */
    public boolean checkToken(String userId, String key){
        return String.valueOf(redisService.get(userId + "O")).equals(key);
    }
    public void addToken(String userId, String key){
        redisService.set(userId + "O", key, timeout);
    }
}
