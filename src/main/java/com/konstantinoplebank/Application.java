package com.konstantinoplebank;


import com.googlecode.flyway.core.Flyway;
import com.konstantinoplebank.dao.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.InputStream;

@SpringBootApplication
public class Application {

    private String driver;

    private String url;

    private String username;

    private String password;

    public static void main(String[] argv) throws Exception {
        SpringApplication.run(Application.class, argv);
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory mysqlSessionFactory() throws Exception {
        String resource = "main/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Bean(initMethod = "migrate", name = "flyway")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(mysqlDataSource());
        return flyway;
    }
    @Bean @DependsOn("flyway")
    EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(mysqlDataSource());
        return bean.getObject();
    }


}
