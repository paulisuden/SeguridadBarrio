package com.is2.seguridad_barrio_cliente.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ServicioDTO implements Serializable {
  private String id;
  private String nombre;

  private String imagenId;
  private ImagenDTO imagen;
}
