package com.polizasconexion.polizasconexion.controller;

import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import com.polizasconexion.polizasconexion.service.impl.InventarioServiceImpl;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioServiceImpl inventarioService;


    public InventarioController(InventarioServiceImpl inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/consultar/inventario/{sku}")
    public ResponseDTO consultarInventario(@PathVariable("sku") Long sku)
    {
        return inventarioService.consultarInventarioporSKU(sku);
    }

    @GetMapping("/obtener/inventario/total")
    public ResponseDTO obtenerInventarioTotal()
    {
        return inventarioService.obtenerInventarioTotal();
    }

    @DeleteMapping("/borrar/sku/inventario/{sku}")
    public ResponseDTO borrarSKudeInventario(@PathVariable("sku") Long skuArticulo)
    {
        return inventarioService.borrarInventario(skuArticulo);
    }
    @PutMapping("/actualizar/sku/inventario")
    public ResponseDTO actualizarSkuInventario(@RequestBody Inventario inventario)
    {
        return inventarioService.actualizarInventario(inventario.getSku(), inventario.getCantidad(), inventario.getNombrearticulo());
    }

    @PostMapping("/alta/sku/inventario")
    public ResponseDTO altaSkuInventario(@RequestBody Inventario inventario)
    {
        return inventarioService.insertarSkuaInventario(inventario);
    }
}
