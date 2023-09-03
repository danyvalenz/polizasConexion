package com.polizasconexion.polizasconexion.controller;

import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import com.polizasconexion.polizasconexion.service.impl.InventarioServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
