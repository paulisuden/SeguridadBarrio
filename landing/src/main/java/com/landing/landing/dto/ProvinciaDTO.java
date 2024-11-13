package com.landing.landing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinciaDTO implements Serializable {
    private String id;
    private String nombre;
    private String paisId;
    private PaisDTO pais;

}
