package com.polizasconexion.polizasconexion.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Polizas {
    private Integer idPoliza;
    private Integer cantidad;
    private String nombre;
    private String apellido;
    private Long empleadoGenera;
    private Long skuArticulo;
}
