package web.tracker.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("web.tracker")
@PropertySource({"classpath:persistence-mysql.properties","classpath:security-persistence-mysql.properties"})
public class ConfigurationApp implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	
	private Logger logger=Logger.getLogger(getClass().getName());
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	@Bean
	public DataSource myDataSource() {
		
		ComboPooledDataSource securityDataSource=new ComboPooledDataSource();
		
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		logger.info("jdbc URL: "+env.getProperty("jdbc.url"));
		logger.info("jdbc user: "+env.getProperty("jdbc.user"));
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		
		
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	
	private int getIntProperty(String propertyName) {
		String property=env.getProperty(propertyName);
		
		int intProp=Integer.parseInt(property);
		
		return intProp;
	}
	
	
	
	private Properties getHibernateProperties() {  
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
		}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(myDataSource());
	sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
	sessionFactory.setHibernateProperties(getHibernateProperties());
	return sessionFactory;
	}
	
	@Bean
	public DataSource securityDataSource() {
		
		// create connection pool
		ComboPooledDataSource securityDataSource
									= new ComboPooledDataSource();
				
		// set the jdbc driver class
		
		try {
			securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		
		logger.info(">>> security.jdbc.url=" + env.getProperty("security.jdbc.url"));
		logger.info(">>> security.jdbc.user=" + env.getProperty("security.jdbc.user"));
		
		
		// set database connection props
		
		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		// set connection pool props
		
		securityDataSource.setInitialPoolSize(
				getIntProperty("security.connection.pool.initialPoolSize"));

		securityDataSource.setMinPoolSize(
				getIntProperty("security.connection.pool.minPoolSize"));

		securityDataSource.setMaxPoolSize(
				getIntProperty("security.connection.pool.maxPoolSize"));

		securityDataSource.setMaxIdleTime(
				getIntProperty("security.connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry
//	          .addResourceHandler("/resources/**")
//	          .addResourceLocations("/resources/"); 
		 registry
         .addResourceHandler("/resources/**")
		 .addResourceLocations("classpath:/");
//		 	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	        registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
//	        registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
//	        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
//	        registry.addResourceHandler("/resources/**")
//	                .addResourceLocations("classpath:/resources/"); 
	    }	
	
	
}
