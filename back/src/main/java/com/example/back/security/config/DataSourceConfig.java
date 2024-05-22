package com.example.back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.util.Base64;

/**
 * Represents a class configuration for setting up the data source.
 */

@Configuration
public class DataSourceConfig {

	/**
	 * Autowired field for accessing environment properties.
	 */
    @Autowired
    private Environment env;

    /**
     * Creates and configures the data source bean.
     *
     * @return the configured DataSource bean.
     */
    @Bean
    public DataSource dataSource() {
    	
    	/**
    	 * The username from the encoded properties.
    	 */
        String encodedUsername = env.getProperty("spring.datasource.username");
        
    	/**
    	 * The password from the encoded properties.
    	 */
        String encodedPassword = env.getProperty("spring.datasource.password");

    	/**
    	 * The username using Base64 decoding.
    	 */
        String username = new String(Base64.getDecoder().decode(encodedUsername));
        
    	/**
    	 * The email using Base64 decoding.
    	 */
        String password = new String(Base64.getDecoder().decode(encodedPassword));

        /**
         * Builds and returns a configured DataSource object.
         */
        return DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.url"))
                .username(username)
                .password(password)
                .driverClassName(env.getProperty("spring.datasource.driver-class-name", "org.postgresql.Driver"))
                .build();
    }
}
