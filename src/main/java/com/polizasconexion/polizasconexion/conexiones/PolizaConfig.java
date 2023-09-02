package com.polizasconexion.polizasconexion.conexiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class PolizaConfig {

    @Bean(name = "dsPoliza")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource conexionPolizaDAtaSource()
    {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "jdbcPoliza")
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dsPoliza") DataSource dsPoliza) {

        return new NamedParameterJdbcTemplate(dsPoliza);
    }
}
