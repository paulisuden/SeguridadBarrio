package com.landing.landing.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NegocioDTO implements Serializable {
  private String id;
  private String nombre;
  private List<String> serviciosId;
  private List<ServicioDTO> servicios;
  private String direccionId;
  private DireccionDTO direccion;
  private String imagenId;
  private ImagenDTO imagen;
}
