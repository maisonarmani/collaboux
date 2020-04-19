package com.randomsturvs.collaboux.utils;

import com.randomsturvs.collaboux.inspector.SqlCommandStatementInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateUtil {

    @Autowired
    private JpaVendorAdapter vendorAdapter;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.randomsturvs.collaboux");

        emf.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.put("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class);
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class);
        properties.put("hibernate.session_factory.statement_inspector", SqlCommandStatementInspector.class);
        emf.setJpaProperties(properties);
        emf.setPersistenceUnitName("default");

        emf.afterPropertiesSet();
        return emf.getObject();
    }
}