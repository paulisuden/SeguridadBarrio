package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartamentoDTO implements Serializable {
    private String id;
    private String nombre;
    private boolean eliminado;
    private String provinciaId;
    private ProvinciaDTO provincia;
}
