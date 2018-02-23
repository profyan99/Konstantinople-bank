package com.konstantinoplebank;


import com.googlecode.flyway.core.Flyway;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.InputStream;

@SpringBootApplication
@Import(DataBaseConfig.class)
public class Application {

    private final DataBaseConfig dataBaseConfig;

    @Autowired
    public Application(DataBaseConfig dataBaseConfig) {
        this.dataBaseConfig = dataBaseConfig;
    }

    public static void main(String[] argv) throws Exception {
        SpringApplication.run(Application.class, argv);

    }

    @Bean(name = "SimpleDataSource")
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseConfig.getDriver());
        dataSource.setUrl(dataBaseConfig.getUrl());
        dataSource.setUsername(dataBaseConfig.getUsername());
        dataSource.setPassword(dataBaseConfig.getPassword());
        System.out.println("driverClassNameL "+dataBaseConfig.getDriver());
        return dataSource;
    }

    @Bean(name = "SimpleSqlFactory")
    public SqlSessionFactory mysqlSessionFactory() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Bean(name = "SimpleSqlSession")
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(mysqlSessionFactory());
    }

    @Bean(initMethod = "migrate", name = "flyway")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(mysqlDataSource());
        return flyway;
    }


}
