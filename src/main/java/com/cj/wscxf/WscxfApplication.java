package com.cj.wscxf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class WscxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(WscxfApplication.class, args);
    }

}
