package com.is2.seguridad_barrio_cliente.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmpresaDTO implements Serializable {

    private String id;

    private String nombre;
    private String descripcion;

    private String imagenId;
    private ImagenDTO imagen;

    private String direccionId;
    private DireccionDTO direccion;
}