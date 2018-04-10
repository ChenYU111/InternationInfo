package com.internation.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.SessionCookieConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;

import com.github.pagehelper.PageHelper;

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
	
	//配置mybatis的分页插件pageHelper
	    /* @Bean
	      public PageHelper pageHelper(){
	          PageHelper pageHelper = new PageHelper();
	          Properties properties = new Properties();
	          properties.setProperty("offsetAsPageNum","true");
	          properties.setProperty("rowBoundsWithCount","true");
	          properties.setProperty("reasonable","true");
	          properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	         pageHelper.setProperties(properties);
	         return pageHelper;
	     }*/
	
	     
	     
	   
	     
}        
