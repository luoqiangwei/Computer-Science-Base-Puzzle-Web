package cn.ovea_y.puzzle.util;

import cn.ovea_y.puzzle.util.cache.JedisPoolUtils;
import cn.ovea_y.puzzle.util.security.Token;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class CacheTest {
    @Test
    public void fun() throws InterruptedException {
        ArrayList a = new ArrayList(100);
        int i = 0;
        long start = System.currentTimeMillis();
        while (true){
            long end = System.currentTimeMillis();
            if(end - start >= 1000){
                break;
            }
            i++;
            Token.addToken("test" + i, i + "");
//            System.out.println(i);
//            a.add(Token.checkToken("test" + i, i + ""));
        }
//        Thread.sleep(10000);
        System.out.println(a);
        System.out.println("redis Qps: " + i);
    }

    @Test
    public void fun2() throws InterruptedException {
        Token.addToken("test1", "13");
        Thread.sleep(1000);
    }
}
