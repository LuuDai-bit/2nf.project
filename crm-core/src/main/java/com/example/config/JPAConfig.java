package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.repository"}) //Enable JPA
@EnableTransactionManagement //Enable
@PropertySource("classpath:application.properties")
public class JPAConfig {
    @Autowired
    private Environment enviroment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(enviroment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(enviroment.getProperty("jdbc.url"));
        dataSource.setUsername(enviroment.getProperty("jdbc.username"));
        dataSource.setPassword(enviroment.getProperty("jdbc.password"));
        return dataSource;
    }

    Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", enviroment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", enviroment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", enviroment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.default_schema", enviroment.getProperty("hibernate.default_schema"));
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        return properties;
    }
}
