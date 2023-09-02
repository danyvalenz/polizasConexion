package com.polizasconexion.polizasconexion.service.impl;

import com.polizasconexion.polizasconexion.PolizaRepository.PolizaRepository;
import com.polizasconexion.polizasconexion.dto.MensajeFailureDTO;
import com.polizasconexion.polizasconexion.dto.Meta;
import com.polizasconexion.polizasconexion.dto.PolizasEmpleadosDetalleDTO;
import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.service.PolizaService;
import org.springframework.stereotype.Service;

@Service
public class PolizaServiceImpl implements PolizaService {

    private final PolizaRepository polizaRepository;
    MensajeFailureDTO mensajeFailureDTO = new MensajeFailureDTO();

    public PolizaServiceImpl(PolizaRepository polizaRepository) {
        this.polizaRepository = polizaRepository;
    }

    @Override
    public ResponseDTO obtenerPoliza(Integer idPoliza) {
        ResponseDTO responseDTO = new ResponseDTO();
        Meta meta = new Meta();
        PolizasEmpleadosDetalleDTO polizasEmpleadosDetalle = polizaRepository.obtenerPoliza(idPoliza);
        if (polizasEmpleadosDetalle.getPoliza() != null)
        {
            meta.setStatus("OK");
            responseDTO.setMeta(meta);
            responseDTO.setData(polizasEmpleadosDetalle);
        }else {
            meta.setStatus("MENSAJE");
            responseDTO.setMeta(meta);
            responseDTO.setData("Poliza no encontrada, favor de validar .");
        }
        return responseDTO;
    }



    @Override
    public ResponseDTO crearPoliza(Integer cantidad,String nombre, String apellido, Long empleadoGenera, Long skuArticulo) {
        PolizasEmpleadosDetalleDTO polizasEmpleadosDetalleDTO = new PolizasEmpleadosDetalleDTO();
        ResponseDTO responseDTO = new ResponseDTO();
        Meta meta = new Meta();
        Integer idPoliza = polizaRepository.crearPoliza(cantidad,nombre,apellido, empleadoGenera, skuArticulo);
        if (idPoliza != 0)
        {
            polizasEmpleadosDetalleDTO =  polizaRepository.obtenerPoliza(idPoliza);
            if (polizaRepository != null)
            {
                meta.setStatus("OK");
                responseDTO.setMeta(meta);
                responseDTO.setData(polizasEmpleadosDetalleDTO);
            }
        }else
        {
            meta.setStatus("FAILURE");
            mensajeFailureDTO.setMensajeFailure("Ha ocurrido un error en los grabados de póliza.");
            responseDTO.setMeta(meta);
            responseDTO.setData(mensajeFailureDTO);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO eliminarPoliza(Integer idPoliza) {
        ResponseDTO responseDTO = new ResponseDTO();
        Meta meta = new Meta();
        Integer polizaEliminada = polizaRepository.borrarPoliza(idPoliza);
        if (polizaEliminada == 1)
        {
            meta.setStatus("OK");
            responseDTO.setMeta(meta);
            responseDTO.setData("Se eliminó correctamente la poliza: " + idPoliza);
        }else
        {
            meta.setStatus("MENSAJE");
            responseDTO.setMeta(meta);
            mensajeFailureDTO.setMensajeFailure("Poliza No encontrada, favor de validar ");
            responseDTO.setData(mensajeFailureDTO);

        }
        return responseDTO;
    }

    @Override
    public ResponseDTO actualizarPoliza(Integer idPoliza, String nombreEmpleado, String apellidoEmpleado) {
        ResponseDTO responseDTO = new ResponseDTO();
        Meta meta = new Meta();
        Integer polizaActualizada = polizaRepository.actualizarPoliza(idPoliza,nombreEmpleado,apellidoEmpleado);
        if (polizaActualizada == 1)
        {
            meta.setStatus("OK");
            responseDTO.setMeta(meta);
            responseDTO.setData("Se Actualizó  correctamente la poliza: " + idPoliza);
        }else
        {
            meta.setStatus("MENSAJE");
            responseDTO.setMeta(meta);
            mensajeFailureDTO.setMensajeFailure("Poliza No encontrada, favor de validar");
            responseDTO.setData(mensajeFailureDTO);

        }
        return responseDTO;
    }




}
