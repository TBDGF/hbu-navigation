package cn.allenji.hbunavigation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.allenji.hbunavigation.repository")
public class HbuNavigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbuNavigationApplication.class, args);
    }

}
