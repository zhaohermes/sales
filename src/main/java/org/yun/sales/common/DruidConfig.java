package org.yun.sales.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据库连接池配置类
 */
@Configuration
public class DruidConfig {
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.initial-size}")
	private int initialSize;
	@Value("${spring.datasource.min-idle}")
	private int minIdle;
	@Value("${spring.datasource.max-active}")
	private int maxActive;
	@Value("${spring.datasource.max-wait}")
	private int maxWait;
	@Value("${spring.datasource.time-between-eviction-runs-millis}")
	private int timeBetweenEvictionRunsMillis;
	@Value("${spring.datasource.min-evictable-idle-time-millis}")
	private int minEvictableIdleTimeMillis;
	@Value("${spring.datasource.validation-query}")
	private String validationQuery;
	@Value("${spring.datasource.test-while-idle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource.test-on-borrow}")
	private boolean testOnBorrow;
	@Value("${spring.datasource.test-on-return}")
	private boolean testOnReturn;

	@Bean
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		return datasource;
	}
	
}
