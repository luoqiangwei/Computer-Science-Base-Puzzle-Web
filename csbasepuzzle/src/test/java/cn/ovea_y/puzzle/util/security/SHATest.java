package cn.ovea_y.puzzle.util.security;

import cn.ovea_y.puzzle.util.security.SHA;
import org.junit.Test;

public class SHATest {
    @Test
    public void fun(){
        System.out.println(SHA.SHA512Encoding("111244554trt3tyyu6uuuh5"));
    }
}
