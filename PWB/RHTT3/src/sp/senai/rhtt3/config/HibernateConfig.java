package sp.senai.rhtt3.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class HibernateConfig {
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		//
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/rhtt3?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("root132");
		//
		return ds;
	}
	
	public Properties getHibernateProperties() {
		Properties props = new Properties();
		//
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		//
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		//
		bean.setDataSource(getDataSource());
		bean.setHibernateProperties(getHibernateProperties());
		bean.setPackagesToScan("sp.senai.rhtt3.model");
		//
		return bean;
	}
	
}