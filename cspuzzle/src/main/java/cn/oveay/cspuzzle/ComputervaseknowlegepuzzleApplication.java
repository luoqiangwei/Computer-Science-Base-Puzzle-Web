package cn.oveay.cspuzzle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.oveay.cspuzzle.dao")
public class ComputervaseknowlegepuzzleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputervaseknowlegepuzzleApplication.class, args);
    }

}
