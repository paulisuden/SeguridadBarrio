package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalidadDTO implements Serializable {
    private Long id;
    private String nombre;
    private String codigoPostal;
    private Long departamentoId;
    private DepartamentoDTO departamento;

}