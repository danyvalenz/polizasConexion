package com.polizasconexion.polizasconexion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polizasconexion.polizasconexion.entidades.Inventario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioIDMensajeExito {

    @JsonProperty("IDMensaje")
    private String idMensaje;
}
