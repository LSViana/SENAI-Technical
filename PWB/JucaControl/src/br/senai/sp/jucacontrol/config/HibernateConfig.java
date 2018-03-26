package br.senai.sp.jucacontrol.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * It creates the object with the informations about connection to database created by Hibernate
 * @author LSViana
 */
@Configuration
public class HibernateConfig {
	/**
	 * It determinates that every time Spring needs a DataSource, this method is going to be called 
	 * @return
	 */
	@Bean
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jucacontrol_production?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("root132");
		return ds;
	}
	public Properties getHibernateProperties() {
		Properties props = new Properties();
		// Shows the SQL executed by Hibernate
		props.setProperty("hibernate.show_sql", "true");
		// HBM to DDL
		/*
		 create: Drop and recreate the tables at every new start
		 update: Perform an alter table only when changes are detected between database and model
		 validate: just validate database and doesn't change anything, used in production
		 */
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		// Determines the dialect that is going to be used by Hibernate to communicate with database
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return props;
	}
	@Bean
	public LocalSessionFactoryBean getSessionFactoryBean() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setHibernateProperties(getHibernateProperties());
		lsfb.setPackagesToScan("br.senai.sp.jucacontrol.model");
		return lsfb;
	}
}
