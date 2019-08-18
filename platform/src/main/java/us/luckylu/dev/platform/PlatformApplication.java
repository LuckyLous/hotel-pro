package us.luckylu.dev.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lu
 * @create 2019-03-21 15:15
 */

@SpringBootApplication
@MapperScan({"us.luckylu.dev.dao", "us.luckylu.dev.platform.dao"})
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
