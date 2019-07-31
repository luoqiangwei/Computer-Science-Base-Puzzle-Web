package cn.ovea_y.puzzle.util.security;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Verify {
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
     * 检查电话验证码
     * @param userId
     * @param key
     * @return
     */
    public static boolean checkVerify(String userId, String key){
        return String.valueOf(jedis.get(userId) + "P").equals(key);
    }
    public static void addVerify(String userId, String key){
        jedis.set(userId + "P", key);
        jedis.expire(userId, timeout);
    }
}
