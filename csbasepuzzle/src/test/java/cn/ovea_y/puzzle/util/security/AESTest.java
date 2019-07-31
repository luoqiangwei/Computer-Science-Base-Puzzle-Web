package cn.ovea_y.puzzle.util.security;

import org.junit.Test;

public class AESTest {
    @Test
    public void fun(){
        String s = AES.ASEEncoding("13ee3yfe2dgu6hfr".getBytes(), "46t58ui9jhgt654e".getBytes(), "325552224tyggru675rffhh");
        System.out.println(s);
        s = AES.ASEDncoding("13ee3yfe2dgu6hfr".getBytes(), "46t58ui9jhgt654e".getBytes(), s);
        System.out.println(s);
    }
}
