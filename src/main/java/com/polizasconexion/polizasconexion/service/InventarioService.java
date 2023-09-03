package com.polizasconexion.polizasconexion.service;

import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Inventario;

public interface InventarioService {

    public ResponseDTO consultarInventarioporSKU(Long sku);



}
