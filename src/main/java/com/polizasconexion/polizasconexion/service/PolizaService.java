package com.polizasconexion.polizasconexion.service;


import com.polizasconexion.polizasconexion.dto.ResponseDTO;

public interface PolizaService {

    public ResponseDTO obtenerPoliza(Integer idPoliza);

    public ResponseDTO crearPoliza(Integer cantidad,String nombre,String apellido, Long empleadoGenera, Long skuArticulo);
    public ResponseDTO eliminarPoliza(Integer idPoliza);
    public ResponseDTO actualizarPoliza(Integer idPoliza,String nombreEmpleado,String apellidoEmpleado);
}
