package com.lfh.classManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lfh.classManage.dao")
@ComponentScan(basePackages = {"com.lfh.*"})
public class ClassManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassManageApplication.class, args);
	}

}
