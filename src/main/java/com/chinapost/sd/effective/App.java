package com.chinapost.sd.effective;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 *
 * @author tangyang
 * @since 2023-07-05
 */
@SpringBootApplication()
@MapperScan(basePackageClasses = App.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}