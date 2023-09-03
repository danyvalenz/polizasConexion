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
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventarioRepository {

    @Autowired
    @Qualifier("jdbcPoliza")
    private NamedParameterJdbcTemplate jdbcPoliza;

    private String mensajeError  = "";
    private static final Logger LOG = LogManager.getLogger(InventarioRepository.class.getName());


    public Inventario obtenerInventarioPorSKU(Long sku) throws SQLException
    {
        String sql = "select sku,cantidad,nombrearticulo from fun_obtenerinventarioporsku(:skuinventario);";
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

    public Integer borrarInventario(Long skuArticulo) throws SQLException
    {
        String sql = "select boradoexitoso from fun_borrarinventario(:skuArticulo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("skuArticulo",skuArticulo);
        return jdbcPoliza.queryForObject(sql, params, Integer.class);
    }
    public List<Inventario> obtenerInventarioTotal() throws SQLException
    {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "select sku,cantidad,nombrearticulo from fun_obtenerinventariototal();";
        inventarios = jdbcPoliza.query(sql, new BeanPropertyRowMapper<>(Inventario.class));

        return inventarios;
    }
    public Integer actualizarInventario(Long skuArticulo,Integer cantidadArticulo,String nombreArticulo) throws SQLException
    {
        String sql = "select actualizacionexitoso from fun_actualizarinventario(:skuArticulo,:cantidad,:nombreArticulo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("skuArticulo",skuArticulo);
        params.addValue("cantidad",cantidadArticulo);
        params.addValue("nombreArticulo",nombreArticulo);
        Integer actualizacionExitosa = jdbcPoliza.queryForObject(sql,params, Integer.class);
        return actualizacionExitosa;
    }

    public Inventario insertarskuaInventario(Inventario inventario) throws SQLException
    {
        String sql  = "select * from fun_altaskuinventario(:sku, :cantidad,:nombrearticulo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("sku",inventario.getSku());
        params.addValue("cantidad",inventario.getCantidad());
        params.addValue("nombrearticulo",inventario.getNombrearticulo());
        return jdbcPoliza.queryForObject(sql,params, new BeanPropertyRowMapper<>(Inventario.class));
    }
}
