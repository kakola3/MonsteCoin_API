package monstercoin.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import monstercoin.coinbot.Coinbot;
import monstercoin.dao.UserDAOImpl;
import monstercoin.entity.CryptoCurrency;
import monstercoin.entity.Quote;
import monstercoin.entity.QuoteDetail;
import monstercoin.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@ComponentScan("monstercoin")
@PropertySource({ "classpath:persistence-mysql.properties" })
public class DemoAppConfig implements WebMvcConfigurer
{
	StringBuffer[] content;
	QuoteDetail quoteDetail = new QuoteDetail();
	Quote quote = new Quote();
	CryptoCurrency cryptoCurrency = new CryptoCurrency();
	//Coinbot coinbot = new Coinbot();

	@Bean
	public Coinbot coinbot(){
		Coinbot coinbot = new Coinbot();

		return  coinbot;
	}

	@Scheduled(fixedRate = 300000)
	public long scheduleFixedDelayTask() throws IOException {
		content = Coinbot.request();
		System.out.println("CONTENT IN CONFIG: " + content.toString());
		for (int i=0; i<content.length; i++) {
			quoteDetail = Coinbot.quoteDetail(content[i]);
			System.out.println("QUOTE_DETAIL IN CONFIG: " + quoteDetail.toString());
			Coinbot coinbot = coinbot();
			coinbot.contentOperations(cryptoCurrency, quote, quoteDetail, i+1); // i+1 because id's in database are numbered from 1 not 0
			coinbot.runningCoinBot();
		}
//		quote = Coinbot.quote(content, quoteDetail);
//		System.out.println("QUOTE IN CONFIG: " + quote.toString());
//		cryptoCurrency = Coinbot.cryptoCurrency(content, quote);
//		System.out.println("CRYPTOCURRENCY IN CONFIG: " + cryptoCurrency.toString());

		long value = System.currentTimeMillis() / 1000;

		return value;
	}


	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public DataSource myDataSource() {
		
		// create connection pool
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			myDataSource.setDriverClass("com.mysql.jdbc.Driver");		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection props
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return myDataSource;
	}
	
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;				
	}

	
	// need a helper method 
	// read environment property and convert to int
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

}









