package com.example.back.security.config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import static org.mockito.Mockito.*;

class DataSourceConfigTest {

    @Mock
    private Environment env;

    @InjectMocks
    private DataSourceConfig dataSourceConfig;

    @Test
    void testDataSourceCreation() {
    	// to be done
    }




    @Test
    void testMissingDataSourceProperties() {
        // to be done
    }
}
