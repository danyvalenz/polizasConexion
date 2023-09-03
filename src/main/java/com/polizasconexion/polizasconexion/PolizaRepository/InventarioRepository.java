package com.polizasconexion.polizasconexion.PolizaRepository;

import com.polizasconexion.polizasconexion.entidades.Inventario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class InventarioRepository {

    @Autowired
    @Qualifier("jdbcPoliza")
    private NamedParameterJdbcTemplate jdbcPoliza;

    private String mensajeError  = "";
    private static final Logger LOG = LogManager.getLogger(InventarioRepository.class.getName());


    public Inventario obtenerInventarioPorSKU(Long sku) throws SQLException
    {
        String sql = "select sku,cantidad,nombre from fun_obtenerinventario(:skuinventario);";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("skuinventario",sku);
        Inventario inventario = new Inventario();
        try{
            inventario =jdbcPoliza.queryForObject(sql,params,new BeanPropertyRowMapper<>(Inventario.class));
        }catch (Exception ex){
             mensajeError = String.format("Ocurrio un error [%s]",ex);
            LOG.error(mensajeError);
        }

        return inventario;

    }






}
