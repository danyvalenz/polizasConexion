package com.polizasconexion.polizasconexion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolizasEmpleadosDetalleDTO {

    @JsonProperty("Poliza")
    private PolizaDTO poliza;

    @JsonProperty("Empleado")
    private EmpleadoDTO empleado;

    @JsonProperty("DetalleArticulo")
    private DetalleArticuloDTO detalleArticulo;

}
