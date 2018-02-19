package com.konstantinoplebank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "db")
@PropertySource("db.properties")
@Data
public class DataBaseConfig
{
    private String driver;

    private String url;

    private String username;

    private String password;
}
