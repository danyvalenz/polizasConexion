package com.polizasconexion.polizasconexion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeFailureDTO {

    @JsonProperty("Mensaje")
    private String mensajeFailure;
}
