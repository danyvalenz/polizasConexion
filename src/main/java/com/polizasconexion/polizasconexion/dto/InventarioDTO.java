package com.polizasconexion.polizasconexion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    @JsonProperty("inventario")
    private Inventario inventario;
}
