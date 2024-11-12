package com.landing.landing.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ServicioDTO implements Serializable {
  private Long id;
  private String nombre;

  private Long imagenId;
  private ImagenDTO imagen;
}
