package com.polizasconexion.polizasconexion.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private Long sku;
    private Integer cantidad;
    private String nombre;
}
