package com.kwaksamo.kwaksinsa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	@ConfigurationProperties(prefix="kwaksinsa.datasource")
	public DataSource dataSource(){
    	return DataSourceBuilder.create().build();
	}
}
