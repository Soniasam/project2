package com.codekages.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ORMConfiguration {
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));

		dataSource.setUrl(env.getProperty("db_url"));
		dataSource.setUsername(env.getProperty("db_username"));
		dataSource.setPassword(env.getProperty("db_password"));

//		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");

//		dataSource.setUrl(System.getenv("db_url"));
//		dataSource.setUsername(System.getenv("db_username"));
//		dataSource.setPassword(System.getenv("db_password"));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(dataSource()); // setter injection
		sessionFactory.setPackagesToScan("com.codekages.model");

		// Create the properties object
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl")); // create and validate
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("dialect"));
		
//		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create"); // create and validate
//		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");

		// Configure hibernate properties
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();

		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}

}
