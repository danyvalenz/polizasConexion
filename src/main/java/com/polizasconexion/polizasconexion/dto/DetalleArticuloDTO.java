package com.polizasconexion.polizasconexion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleArticuloDTO {

    private Long sku;

    private String nombreSku;
}
