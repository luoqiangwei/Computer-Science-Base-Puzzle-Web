package cn.ovea_y.puzzle.util;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;
import cn.ovea_y.puzzle.util.security.Token;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class CacheTest {
    @Test
    public void fun(){
        int i = 0;
        long start = System.currentTimeMillis();
        while (true){
            long end = System.currentTimeMillis();
            if(end - start >= 1000){
                break;
            }
            i++;
            Token.addToken("test " + i, i + "");
        }
        System.out.println("redis Qps: " + i);
    }
}
