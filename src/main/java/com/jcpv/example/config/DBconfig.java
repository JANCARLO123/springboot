package com.jcpv.example.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


/**
 * Created by JanCarlo on 21/08/2017.
 */
@Configuration
@EntityScan(basePackages = {"com.jcpv.example.entity"})
@EnableJpaRepositories(basePackages = "com.jcpv.example.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@PropertySource("classpath:database-prod.properties")
public class DBconfig {

    private static final Logger logger = LoggerFactory.getLogger(DBconfig.class);


    @Autowired
    private Environment environment;

    /*
    * Populate SpringBoot DataSourceProperties object directly from application.yml
    */

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.sampleapp")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setDataSource(getDataSource());
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("myJpaPersistence");
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.jcvp.example.entity");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties());
        return localContainerEntityManagerFactoryBean;
    }
/*
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "com.jcpv.example.entity" });
        factoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }
*/
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter(){
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("database.driverClassName"));
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        return dataSource;
    }

    public DataSource getDataSourceC3P0(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty("database.driverClassName"));
        } catch (PropertyVetoException pve){
            logger.error("Cannot load datasource driver (" + environment.getProperty("database.driverClassName") +") : " + pve.getMessage());
            return null;
        }
        dataSource.setJdbcUrl(environment.getProperty("database.url"));
        dataSource.setUser(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("hibernate.c3p0.min_size")));
        dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("hibernate.c3p0.max_size")));
        dataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("hibernate.c3p0.idle_test_period")));
        dataSource.setInitialPoolSize(10);
        return dataSource;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        return properties;
    }

}

