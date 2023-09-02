package com.polizasconexion.polizasconexion.controller;

import com.polizasconexion.polizasconexion.dto.PolizasEmpleadosDetalleDTO;
import com.polizasconexion.polizasconexion.dto.ResponseDTO;
import com.polizasconexion.polizasconexion.entidades.Polizas;
import com.polizasconexion.polizasconexion.service.impl.PolizaServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PolizaController {

    private final PolizaServiceImpl polizaService;

    public PolizaController(PolizaServiceImpl polizaService) {
        this.polizaService = polizaService;
    }


    @GetMapping("/obtener/poliza/{idpoliza}")
    public ResponseDTO obtenerPoliza(@PathVariable("idpoliza") Integer idPoliza)
    {
        return polizaService.obtenerPoliza(idPoliza);
    }

    @PostMapping("/crearPoliza")
    public ResponseDTO crearPoliza(@RequestBody Polizas poliza)
    {
        return polizaService.crearPoliza(poliza.getCantidad(),poliza.getNombre(),poliza.getApellido(),poliza.getEmpleadoGenera(),poliza.getSkuArticulo());
    }

    @DeleteMapping("/elimiar/poliza/{idpoliza}")
    public ResponseDTO eliminarPoliza(@PathVariable("idpoliza") Integer idPoliza)
    {
        return polizaService.eliminarPoliza(idPoliza);
    }

    @PutMapping("/actualizar")
    public ResponseDTO actualizarPoliza(@RequestBody Polizas poliza)
    {
        return polizaService.actualizarPoliza(poliza.getIdPoliza(), poliza.getNombre(), poliza.getApellido());
    }
}
