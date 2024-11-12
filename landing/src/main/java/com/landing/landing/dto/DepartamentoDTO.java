package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartamentoDTO implements Serializable {
    private Long id;
    private String nombre;
    private boolean eliminado;
    private Long provinciaId;
    private ProvinciaDTO provincia;

}
