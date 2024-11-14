package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalidadDTO implements Serializable {
    private String id;
    private String nombre;
    private String codigoPostal;
    private String departamentoId;
    private DepartamentoDTO departamento;

}