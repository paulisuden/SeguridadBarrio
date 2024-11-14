package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data

public class DireccionDTO implements Serializable {
    private String id;
    private String calle;
    private String numeracion;
    private String barrio;
    private String observacion;
    private String localidadId;
    private LocalidadDTO localidad;
}
