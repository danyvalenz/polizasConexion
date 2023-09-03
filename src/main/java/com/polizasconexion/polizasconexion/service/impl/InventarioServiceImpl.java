package com.polizasconexion.polizasconexion.service.impl;

import com.polizasconexion.polizasconexion.PolizaRepository.InventarioRepository;
import com.polizasconexion.polizasconexion.dto.*;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import com.polizasconexion.polizasconexion.service.InventarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class InventarioServiceImpl implements InventarioService {

    private String mensajeError  = "";
    private static final Logger LOG = LogManager.getLogger(InventarioServiceImpl.class.getName());
    Meta meta = new Meta();
    MensajeFailureDTO mensajeFailureDTO = new MensajeFailureDTO();

    private final InventarioRepository inventarioRepository;
    @Autowired
    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public ResponseDTO consultarInventarioporSKU(Long sku) {
            Inventario inventario = new Inventario();
            ResponseDTO responseDTO = new ResponseDTO();
            InventarioDTO inventarioDTO = new InventarioDTO();
        try{
             inventario= inventarioRepository.obtenerInventarioPorSKU(sku);
             meta.setStatus("OK");
             responseDTO.setMeta(meta);
             inventarioDTO.setInventario(inventario);
             responseDTO.setData(inventarioDTO);
        }catch ( Exception ex){
            mensajeError = String.format("ocurrio un error al obtener inventario [%s]",ex);
            LOG.error(mensajeError);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO obtenerInventarioTotal() {
        List<Inventario> inventarios = new ArrayList<>();
        InventarioListDTO inventarioListDTO = new InventarioListDTO();
        ResponseDTO responseDTO = new ResponseDTO();
        try{
             inventarios = inventarioRepository.obtenerInventarioTotal();
             inventarioListDTO.setInventarios(inventarios);
             meta.setStatus("OK");
             responseDTO.setMeta(meta);
             responseDTO.setData(inventarioListDTO);
        }catch (Exception ex){
            meta.setStatus("FAILURE");
            responseDTO.setMeta(meta);
            mensajeError = String.format("Ha ocurrido un error al consultar el inventario [%s]",ex);
            LOG.error(mensajeError);
            mensajeFailureDTO.setMensajeFailure("Ha ocurrido un error al consultar el inventario");
            responseDTO.setData(mensajeFailureDTO);

        }
        return responseDTO;
    }

    @Override
    public ResponseDTO borrarInventario(Long sku) {
        ResponseDTO responseDTO = new ResponseDTO();
        InventarioIDMensajeExito inventarioIDMensajeExito = new InventarioIDMensajeExito();
        try {
            Integer borradoExitoso = inventarioRepository.borrarInventario(sku);
            if (borradoExitoso == 1)
            {
                String mensajeExito = String.format("se eliminó correctamente el sku: [%d]",sku);
                inventarioIDMensajeExito.setIdMensaje(mensajeExito);
                meta.setStatus("OK");
                responseDTO.setMeta(meta);
                responseDTO.setData(inventarioIDMensajeExito);
            }else {
                String mensajeExito = String.format("No se encontró el sku: [%d] en en inventario ",sku);
                inventarioIDMensajeExito.setIdMensaje(mensajeExito);
                meta.setStatus("AVISO");
                responseDTO.setMeta(meta);
                responseDTO.setData(inventarioIDMensajeExito);
            }

        }catch (Exception ex) {
            mensajeError = String.format("ocurrio un error al eliminar sku de inventario [%s]",ex);
            LOG.error(mensajeError);
            meta.setStatus("FAILURE");
            mensajeFailureDTO.setMensajeFailure(mensajeError);
            responseDTO.setMeta(meta);
            responseDTO.setData(mensajeFailureDTO);

        }



        return responseDTO;
    }

    @Override
    public ResponseDTO actualizarInventario(Long sku, Integer cantidad, String nombreArticulo) {
        ResponseDTO responseDTO = new ResponseDTO();
        InventarioIDMensajeExito inventarioIDMensajeExito = new InventarioIDMensajeExito();

        try {
            Integer actualizacionExitosa = inventarioRepository.actualizarInventario(sku, cantidad, nombreArticulo);
            if (actualizacionExitosa == 1){
                inventarioIDMensajeExito.setIdMensaje("se actualizó correctamente el inventario");
                meta.setStatus("OK");
                responseDTO.setMeta(meta);
                responseDTO.setData(inventarioIDMensajeExito);
            }else {
                meta.setStatus("AVISO");
                responseDTO.setMeta(meta);
                String mensajeSkuNoEncontrado = String.format("el sku no se encuentra en el inventario: [%s]",sku);
                responseDTO.setData(mensajeSkuNoEncontrado);
            }
        }catch (Exception ex){
            mensajeError = String.format("Ha ocurrido un error al intentar actualizar el inventario [%s]",ex);
            LOG.error(mensajeError);
            meta.setStatus("FAILURE");
            mensajeFailureDTO.setMensajeFailure("Ha ocurrido un error al intentar actualizar el inventario");
            responseDTO.setMeta(meta);
            responseDTO.setData(mensajeFailureDTO);

        }

        return responseDTO;
    }

    @Override
    public ResponseDTO insertarSkuaInventario(Inventario inventario) {
        ResponseDTO responseDTO = new ResponseDTO();
        Inventario inventario1 = new Inventario();
        try {
            inventario1 = inventarioRepository.insertarskuaInventario(inventario);
            if (inventario1.getSku() == 0){
                meta.setStatus("AVISO");
                String mensaje = "el Sku ya existe en el inventario";
                responseDTO.setMeta(meta);
                responseDTO.setData(mensaje);
            }else {
                meta.setStatus("OK");
                responseDTO.setMeta(meta);
                responseDTO.setData(inventario1);
            }
        }catch (Exception ex){
            mensajeError = String.format("Ocurrio un error al grabar [%s] ",ex);
            mensajeFailureDTO.setMensajeFailure("Ha ocurrido un error en los grabados de de sku en inventario");
            LOG.error(mensajeError);
            meta.setStatus("FAILURE");
            responseDTO.setMeta(meta);
            responseDTO.setData(mensajeFailureDTO);
        }
        return responseDTO;
    }
}
