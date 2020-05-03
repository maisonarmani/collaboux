package com.randomsturvs.collaboux.config;


import com.randomsturvs.collaboux.GoalService;
import com.randomsturvs.collaboux.sqlinspector.SqlCommandStatementInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Autowired
    JpaVendorAdapter vendorAdapter;

    @Autowired
    GoalService service;

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        Properties properties = new Properties();
        properties.put("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class);
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class);
        properties.put("hibernate.session_factory.statement_inspector", SqlCommandStatementInspector.class);


        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5435/collaboux");
        dataSourceBuilder.username("collaboux");
        dataSourceBuilder.password("collaboux");

        emf.setDataSource(dataSourceBuilder.build());
        emf.setPackagesToScan("com.randomsturvs.collaboux");

        emf.setJpaVendorAdapter(vendorAdapter);

        emf.setJpaProperties(properties);
        emf.setPersistenceUnitName("default");

        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public void repoTest(){
        service.saveGoal("Miracle");
    }
}
