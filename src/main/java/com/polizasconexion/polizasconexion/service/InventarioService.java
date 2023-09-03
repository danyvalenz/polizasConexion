package com.polizasconexion.polizasconexion.service;

import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Inventario;

import java.util.List;

public interface InventarioService {

    public ResponseDTO consultarInventarioporSKU(Long sku);
    public ResponseDTO obtenerInventarioTotal();
    public ResponseDTO borrarInventario(Long sku);
    public ResponseDTO actualizarInventario(Long sku,Integer cantidad,String nombreArticulo);
    public ResponseDTO insertarSkuaInventario(Inventario inventario);

}
