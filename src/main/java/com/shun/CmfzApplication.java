package com.shun;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.shun.dao")
@EnableScheduling
public class CmfzApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmfzApplication.class, args);
	}

}