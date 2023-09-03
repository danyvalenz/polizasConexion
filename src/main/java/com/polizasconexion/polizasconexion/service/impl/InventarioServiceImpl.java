package com.polizasconexion.polizasconexion.service.impl;

import com.polizasconexion.polizasconexion.PolizaRepository.InventarioRepository;
import com.polizasconexion.polizasconexion.dto.InventarioDTO;
import com.polizasconexion.polizasconexion.dto.Meta;
import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import com.polizasconexion.polizasconexion.service.InventarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class InventarioServiceImpl implements InventarioService {

    private String mensajeError  = "";
    private static final Logger LOG = LogManager.getLogger(InventarioServiceImpl.class.getName());
    Meta meta = new Meta();

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
}
