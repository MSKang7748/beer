package com.springbeer.beerproject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

	@Bean // <bean id="" 과 같은 기능
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig(); // 데이터베이스 연결 정보 설정		/ 히카리CP의 설정파일을 만듦.
		return config;
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig()); //데이터 소스가 정상적으로 생성 되었는지 확인 용도
		return dataSource;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.jpa")
	public Properties hibernateConfig() {
		return new Properties();
	}
	
}
