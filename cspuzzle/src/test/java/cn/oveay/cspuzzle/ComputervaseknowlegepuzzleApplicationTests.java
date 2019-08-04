package cn.oveay.cspuzzle;

import cn.oveay.cspuzzle.util.security.Token;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ComputervaseknowlegepuzzleApplicationTests {
    @Autowired
    private Token token;

    @Test
    public void contextLoads() {
        token.addToken("aov", "123");
        log.warn(token.checkToken("aov", "123"));
    }

}
