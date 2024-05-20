package com.example.back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.util.Base64;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        String encodedUsername = env.getProperty("spring.datasource.username");
        String encodedPassword = env.getProperty("spring.datasource.password");

        String username = new String(Base64.getDecoder().decode(encodedUsername));
        String password = new String(Base64.getDecoder().decode(encodedPassword));

        return DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.url"))
                .username(username)
                .password(password)
                .driverClassName(env.getProperty("spring.datasource.driver-class-name", "org.postgresql.Driver"))
                .build();
    }
}
