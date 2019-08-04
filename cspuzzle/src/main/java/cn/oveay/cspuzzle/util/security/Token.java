package cn.oveay.cspuzzle.util.security;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Token {
    private static Jedis jedis = JedisPoolUtils.getJedis();
    private static int timeout;
    static {
        InputStream inStream = Token.class.getClassLoader().getResourceAsStream("config/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeout = Integer.parseInt(properties.get("token-timeout").toString());
    }

    /**
     * 检查form的token是否正确，用 + "⚪"来标识时fOrm表单的验证信息
     * @param userId
     * @param key
     * @return
     */
    public static boolean checkToken(String userId, String key){
        return String.valueOf(jedis.get(userId + "O")).equals(key);
    }
    public static void addToken(String userId, String key){
        jedis.set(userId + "O", key);
        jedis.expire(userId, timeout);
    }
}
