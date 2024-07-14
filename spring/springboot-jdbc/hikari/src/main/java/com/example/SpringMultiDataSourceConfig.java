package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringMultiDataSourceConfig {
	@Bean(name = "primaryDataSource")
	@Primary
	public DriverManagerDataSource primaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test1?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	@Bean(name = "primaryJdbcTemplate")
	@Primary
	public JdbcTemplate masterJdbcTemplate(@Qualifier("primaryDataSource") DataSource dsMySQL) {
		return new JdbcTemplate(dsMySQL);
	}

	@Bean(name = "secondaryDataSource")
	public DriverManagerDataSource secondaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test2?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dsMySQL) {
		return new JdbcTemplate(dsMySQL);
	}
	
}
