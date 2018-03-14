package com.internation.info;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;


import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.internation.info.dao")
public class InternationInfoApplication {

	public static void main(String[] args) {
		TemplateEngine templateEngine = new TemplateEngine(); 
		templateEngine.addDialect(new LayoutDialect());
		SpringApplication.run(InternationInfoApplication.class, args);
	}
	

}        
