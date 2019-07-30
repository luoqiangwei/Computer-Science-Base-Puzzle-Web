package cn.ovea_y.puzzle.util.security;

import org.junit.Test;

public class TokenTest {
    @Test
    public void addToken(){
        Token.addToken("a", "123321");
    }

    @Test
    public void checkToken(){
        System.out.println(Token.checkToken("a", "123311"));
        System.out.println(Token.checkToken("a", "123321"));
        System.out.println(Token.checkToken("a", null));
        System.out.println(Token.checkToken("b", "123321"));
    }
}
