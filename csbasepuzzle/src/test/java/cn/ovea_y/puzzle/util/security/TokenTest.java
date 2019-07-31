package cn.ovea_y.puzzle.util.security;

import org.junit.Test;

public class TokenTest {
    @Test
    public void addToken(){
        Token.addToken("a", "123321");
    }

    @Test
    public void checkToken() {
        new Thread(()->{
            Token.addToken("a", "123321");
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Token.checkToken("a", "123311"));
            System.out.println(Token.checkToken("a", "123321"));
            System.out.println(Token.checkToken("a", null));
            System.out.println(Token.checkToken("b", "123321"));
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Token.checkToken("a", "123311"));
        System.out.println(Token.checkToken("a", "123321"));
        System.out.println(Token.checkToken("a", null));
        System.out.println(Token.checkToken("b", "123321"));
    }
}
