package org.yun.sales.common;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

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
	@Value("${spring.datasource.filters}")
	private String filters;
	@Value("${spring.datasource.connection_properties}")
	private String connectionProperties;
	@Value("${spring.datasource.use_global_data_source_stat}")
	private boolean useGlobalDataSourceStat;

	@Bean
	public DataSource druidDataSource() throws SQLException {
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
		datasource.setFilters(filters);
		datasource.setConnectionProperties(connectionProperties);
		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		return datasource;
	}

	@Bean
	public ServletRegistrationBean druidStatViewServle() {
		// org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 添加初始化参数：initParams
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.11");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");
		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}
