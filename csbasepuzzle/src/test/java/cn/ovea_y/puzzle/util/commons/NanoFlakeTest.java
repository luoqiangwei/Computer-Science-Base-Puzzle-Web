package cn.ovea_y.puzzle.util.commons;

import org.junit.Test;

import java.util.Date;

public class NanoFlakeTest {
    @Test
    public void fun(){
        for(int i = 0; i < 1000; i++){
            System.out.println(Nanoflake.getNanoflakeNum());
        }
        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Date().getTime());
    }
}
