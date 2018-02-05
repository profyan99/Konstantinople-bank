package main;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    @Value(value = "#{spring.datasource.driver-class-name}")
    private String driver;

    @Value(value = "#{spring.datasource.url}")
    private String url;

    @Value(value = "#{spring.datasource.username}")
    private String user;

    @Value(value = "#{spring.datasource.password}")
    private String pass;

    public static void main(String[] argv) throws Exception {
        SpringApplication.run(Application.class, argv);
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean
    public JdbcTemplate mysqlJdbcTemplate() {
        return new JdbcTemplate(mysqlDataSource());
    }

    @Bean
    @Scope(scopeName = "prototype")
    public SimpleJdbcInsert mysqlSimpleJdbcInsert() {
        return new SimpleJdbcInsert(mysqlDataSource());
    }

}
