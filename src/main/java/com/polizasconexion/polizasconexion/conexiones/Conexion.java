package com.polizasconexion.polizasconexion.conexiones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.logging.Logger;
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conexion {

    private static final Logger LOG = Logger.getLogger(String.valueOf(Conexion.class));
    private Connection conexion;
    private String cadenacon;
    private String username;
    private String password;
    public Connection abrirConectionPostgresql()
    {

        try {
            Class.forName("org.postgresql.Driver");
            this.conexion = DriverManager.getConnection(cadenacon,username,password);
        }catch (Exception e)
        {
            String logError = "Error al intentar conectarse al servidor" + e;
            LOG.info(logError);
            this.conexion = null;

        }

        return conexion;
    }
}
