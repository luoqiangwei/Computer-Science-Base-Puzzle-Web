package cn.oveay.cspuzzle.util.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool;
    private static String password;
    static {
        InputStream inStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        password = properties.get("redis.password").toString();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.get("redis.maxIdle").toString()));
        jedisPoolConfig.setMinIdle(Integer.parseInt(properties.get("redis.minIdle").toString()));//最小闲置个数
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.get("redis.maxTotal").toString()));//最大连接数
        jedisPool = new JedisPool(jedisPoolConfig, properties.get("redis.url").toString(), Integer.parseInt(properties.get("redis.port").toString()));
    }

    public static Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(password);
        return jedis;
    }
}
