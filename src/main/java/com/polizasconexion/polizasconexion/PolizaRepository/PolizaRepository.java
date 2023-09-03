package com.polizasconexion.polizasconexion.PolizaRepository;

import com.polizasconexion.polizasconexion.conexiones.Conexion;
import com.polizasconexion.polizasconexion.dto.DetalleArticuloDTO;
import com.polizasconexion.polizasconexion.dto.EmpleadoDTO;
import com.polizasconexion.polizasconexion.dto.PolizaDTO;
import com.polizasconexion.polizasconexion.dto.PolizasEmpleadosDetalleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PolizaRepository {

    private final Conexion conexion;
    private Connection con;
    private String mensajeError  = "";
    private static final Logger LOG = LogManager.getLogger(PolizaRepository.class.getName());

    public PolizaRepository(Conexion conexion) {
        this.conexion = conexion;
    }

    public PolizasEmpleadosDetalleDTO obtenerPoliza(Integer idPoliza) {
        String sql = "";
        ResultSet resultSet;

        PolizasEmpleadosDetalleDTO polizasEmpleadosDetalleDTO = new PolizasEmpleadosDetalleDTO();;
        try {
            this.con = conexion.abrirConectionPostgresql();
           // if (conexion.conexionPostgresql()) {
                sql = String.format("select poliza,cantidad,nombreempleado,apellidoempleado,skuinventario,nombrearticulo from fun_obtenerpoliza($$%d$$);",idPoliza );
                //ResultSet entry = conexion.exec(sql); //
                PreparedStatement statement = con.prepareCall(sql);
                resultSet = statement.executeQuery();
                PolizaDTO polizaDTO = new PolizaDTO();
                EmpleadoDTO empleadoDTO = new EmpleadoDTO();
                DetalleArticuloDTO detalleArticuloDTO = new DetalleArticuloDTO();
                if (resultSet != null) {
                    if (resultSet.next()) {
                        polizaDTO.setIdPoliza(resultSet.getInt("poliza"));
                        polizaDTO.setCantidad(resultSet.getInt("cantidad"));
                        empleadoDTO.setNombre(resultSet.getString("nombreempleado"));
                        empleadoDTO.setApellido(resultSet.getString("apellidoempleado"));
                        detalleArticuloDTO.setSku(resultSet.getLong("skuinventario"));
                        detalleArticuloDTO.setNombreSku(resultSet.getString("nombrearticulo"));
                        polizasEmpleadosDetalleDTO.setPoliza(polizaDTO);
                        polizasEmpleadosDetalleDTO.setEmpleado(empleadoDTO);
                        polizasEmpleadosDetalleDTO.setDetalleArticulo(detalleArticuloDTO);

                    }
                }
           // }
            this.con.close();

        } catch (Exception ex) {
            mensajeError = "Ocurrio un error al intentar ejecutar la consulta:[%s]  " + ex;
            LOG.error(mensajeError);

        }
        return polizasEmpleadosDetalleDTO;
    }


    public Integer crearPoliza(Integer cantidad,String nombre, String apellido, Long empleadoGenera, Long skuArticulo)
    {
        Integer idPoliza = 0;
        String sql = "";
        ResultSet resultSet;
        try {
            this.con = conexion.abrirConectionPostgresql();
            sql = String.format("select idpoliza from fun_crearpoliza($$%d$$,$$%s$$,$$%s$$,$$%d$$,$$%d$$)",cantidad,nombre,apellido,empleadoGenera,skuArticulo);
            PreparedStatement statement = con.prepareCall(sql);
            resultSet = statement.executeQuery();
            if (resultSet != null)
            {
                if (resultSet.next())
                {
                    idPoliza = resultSet.getInt("idpoliza");
                }

            }
            this.con.close();
        }catch (Exception ex)
        {
            mensajeError = String.format("Ocurrio un error al grabar poliza [%s]",ex);
            LOG.error(mensajeError);
        }
        return idPoliza;
    }

    public Integer borrarPoliza(Integer idPoliza)
    {
        String sql = "";
        ResultSet resultSet;
        Integer status = 0;

        try {
            this.con = conexion.abrirConectionPostgresql();
            sql = String.format("select boradoexitoso from fun_borrarpoliza($$%d$$)",idPoliza);
            PreparedStatement statement = con.prepareCall(sql);
            resultSet = statement.executeQuery();
            if (resultSet != null)
            {
                while (resultSet.next())
                {
                    status = resultSet.getInt("boradoexitoso");
                }
            }
            con.close();

        }catch (Exception ex)
        {
            mensajeError = String.format("Ocurrio un error al borrarPoliza poliza [%s]",ex);
            LOG.error(mensajeError);
        }
        return status;
    }

    public Integer actualizarPoliza(Integer idPoliza,String nombreEmpleado, String apellidoEmpleado)
    {
        String sql = "";
        ResultSet resultSet;
        Integer status = 0;

        try {
            this.con = conexion.abrirConectionPostgresql();
            sql = String.format("select actualizacionexitosa from fun_actualizarpoliza($$%d$$,$$%s$$,$$%s$$)",idPoliza,nombreEmpleado,apellidoEmpleado);
            PreparedStatement statement = con.prepareCall(sql);
            resultSet = statement.executeQuery();
            if (resultSet != null)
            {
                while (resultSet.next())
                {
                    status = resultSet.getInt("actualizacionexitosa");
                }
            }
            con.close();

        }catch (Exception ex)
        {
            mensajeError = String.format("Ocurrio un error al borrarPoliza poliza [%s]",ex);
            LOG.error(mensajeError);
        }
        return status;
    }



}
